package fe.banco_digital.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fe.banco_digital.dto.CategoriaCredito;
import fe.banco_digital.dto.OtorgarCreditoInput;
import fe.banco_digital.entity.Cliente;
import fe.banco_digital.entity.Credito;
import fe.banco_digital.entity.EstadoCredito;
import fe.banco_digital.entity.TipoCredito;
import fe.banco_digital.repository.ClienteRepository;
import fe.banco_digital.repository.CreditoRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
@Transactional
public class CreditoServiceImpl implements CreditoService {

    private static final Logger log = LoggerFactory.getLogger(CreditoServiceImpl.class);

    private final CreditoRepository creditoRepository;
    private final ClienteRepository clienteRepository;
    private final MeterRegistry meterRegistry;

    public CreditoServiceImpl(CreditoRepository creditoRepository, ClienteRepository clienteRepository, MeterRegistry meterRegistry) {
        this.creditoRepository = creditoRepository;
        this.clienteRepository = clienteRepository;
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Credito otorgarCredito(OtorgarCreditoInput input) {
        validarInput(input);

        Cliente cliente = clienteRepository.findByDocumento(input.documentoCliente())
                .orElseThrow(() -> new IllegalArgumentException("No existe un cliente con documento " + input.documentoCliente()));

        Credito credito = new Credito();
        credito.setCliente(cliente);
        credito.setTipo(input.tipo());
        credito.setMonto(BigDecimal.valueOf(input.monto()));
        credito.setPlazoMeses(input.plazoMeses());
        credito.setTasaInteresAnual(BigDecimal.valueOf(input.tasaInteresAnual()));
        credito.setEstado(input.estado() == null ? EstadoCredito.EN_ESTUDIO : input.estado());
        credito.setDestinoCredito(input.destinoCredito());
        credito.setObservacion(input.observacion());
        credito.setFechaSolicitud(LocalDateTime.now());
        credito.setFechaActualizacion(LocalDateTime.now());

        Credito guardado = creditoRepository.save(credito);
        meterRegistry.counter("banco_creditos_otorgados_total", "tipo", guardado.getTipo().name(), "estado", guardado.getEstado().name()).increment();
        log.info("Credito otorgado id={} tipo={} estado={} clienteDocumento={}", guardado.getIdCredito(), guardado.getTipo(), guardado.getEstado(), cliente.getDocumento());
        return guardado;
    }

    @Override
    public Credito cambiarEstadoCredito(Long id, EstadoCredito estado, String observacion) {
        Credito credito = consultarCredito(id);
        credito.setEstado(estado);
        credito.setObservacion(observacion);
        credito.setFechaActualizacion(LocalDateTime.now());
        Credito actualizado = creditoRepository.save(credito);
        meterRegistry.counter("banco_creditos_cambio_estado_total", "estado", estado.name()).increment();
        log.info("Estado de credito actualizado id={} nuevoEstado={}", id, estado);
        return actualizado;
    }

    @Override
    @Transactional(readOnly = true)
    public Credito consultarCredito(Long id) {
        meterRegistry.counter("banco_creditos_consultas_id_total").increment();
        return creditoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el credito con id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Credito> creditosPorDocumentoCliente(String documento) {
        meterRegistry.counter("banco_creditos_consultas_cliente_total").increment();
        log.info("Consulta de creditos por documento de cliente={}", documento);
        return creditoRepository.findByClienteDocumento(documento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Credito> creditosPorEstado(EstadoCredito estado) {
        meterRegistry.counter("banco_creditos_consultas_estado_total", "estado", estado.name()).increment();
        return creditoRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaCredito> creditosPorCategoria(TipoCredito tipo) {
        meterRegistry.counter("banco_creditos_consultas_categoria_total", "tipo", tipo == null ? "TODAS" : tipo.name()).increment();
        if (tipo != null) {
            return List.of(new CategoriaCredito(tipo, creditoRepository.findByTipoOrderByFechaSolicitudDesc(tipo)));
        }
        return Arrays.stream(TipoCredito.values())
                .map(t -> new CategoriaCredito(t, creditoRepository.findByTipoOrderByFechaSolicitudDesc(t)))
                .toList();
    }

    private void validarInput(OtorgarCreditoInput input) {
        if (input == null) {
            throw new IllegalArgumentException("La solicitud del credito es obligatoria");
        }
        if (input.documentoCliente() == null || input.documentoCliente().isBlank()) {
            throw new IllegalArgumentException("El documento del cliente es obligatorio");
        }
        if (input.tipo() == null) {
            throw new IllegalArgumentException("El tipo de credito es obligatorio");
        }
        if (input.monto() == null || input.monto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        if (input.plazoMeses() == null || input.plazoMeses() <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor que cero");
        }
        if (input.tasaInteresAnual() == null || input.tasaInteresAnual() < 0) {
            throw new IllegalArgumentException("La tasa de interes anual no puede ser negativa");
        }
    }
}
