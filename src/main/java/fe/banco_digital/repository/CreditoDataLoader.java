package fe.banco_digital.repository;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fe.banco_digital.entity.Cliente;
import fe.banco_digital.entity.Credito;
import fe.banco_digital.entity.EstadoCredito;
import fe.banco_digital.entity.TipoCredito;

@Configuration
@Profile("seed")
public class CreditoDataLoader {

    @Bean
    CommandLineRunner initCreditos(ClienteRepository clienteRepository, CreditoRepository creditoRepository) {
        return args -> {
            if (creditoRepository.count() > 0) {
                return;
            }

            Cliente bryan = clienteRepository.findByDocumento("123456789").orElse(null);
            Cliente ana = clienteRepository.findByDocumento("987654321").orElse(null);
            Cliente carlos = clienteRepository.findByDocumento("111111111").orElse(null);

            if (bryan != null) {
                creditoRepository.save(crearCredito(bryan, TipoCredito.PERSONAL, EstadoCredito.APROBADO, "5000000", 24, "21.50", "Libre inversion para gastos personales"));
                creditoRepository.save(crearCredito(bryan, TipoCredito.VEHICULAR, EstadoCredito.EN_ESTUDIO, "42000000", 60, "18.20", "Compra de vehiculo familiar"));
            }
            if (ana != null) {
                creditoRepository.save(crearCredito(ana, TipoCredito.HIPOTECARIO, EstadoCredito.PENDIENTE, "180000000", 180, "13.80", "Compra de vivienda"));
            }
            if (carlos != null) {
                creditoRepository.save(crearCredito(carlos, TipoCredito.EDUCATIVO, EstadoCredito.RECHAZADO, "12000000", 36, "12.10", "Financiacion de estudios"));
            }
        };
    }

    private Credito crearCredito(Cliente cliente, TipoCredito tipo, EstadoCredito estado, String monto, Integer plazo, String tasa, String destino) {
        Credito credito = new Credito();
        credito.setCliente(cliente);
        credito.setTipo(tipo);
        credito.setEstado(estado);
        credito.setMonto(new BigDecimal(monto));
        credito.setPlazoMeses(plazo);
        credito.setTasaInteresAnual(new BigDecimal(tasa));
        credito.setDestinoCredito(destino);
        credito.setObservacion("Registro inicial para pruebas del laboratorio GraphQL de creditos");
        return credito;
    }
}
