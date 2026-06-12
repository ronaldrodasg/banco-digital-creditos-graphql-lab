package fe.banco_digital.graphql;

import java.util.Arrays;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import fe.banco_digital.dto.CategoriaCredito;
import fe.banco_digital.dto.OtorgarCreditoInput;
import fe.banco_digital.entity.Credito;
import fe.banco_digital.entity.EstadoCredito;
import fe.banco_digital.entity.TipoCredito;
import fe.banco_digital.service.CreditoService;

@Controller
public class CreditoGraphQLController {

    private final CreditoService creditoService;

    public CreditoGraphQLController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @QueryMapping
    public Credito creditoPorId(@Argument Long id) {
        return creditoService.consultarCredito(id);
    }

    @QueryMapping
    public List<Credito> creditosPorCliente(@Argument String documentoCliente) {
        return creditoService.creditosPorDocumentoCliente(documentoCliente);
    }

    @QueryMapping
    public List<Credito> creditosPorEstado(@Argument EstadoCredito estado) {
        return creditoService.creditosPorEstado(estado);
    }

    @QueryMapping
    public List<CategoriaCredito> creditosPorCategoria(@Argument TipoCredito tipo) {
        return creditoService.creditosPorCategoria(tipo);
    }

    @QueryMapping
    public List<TipoCredito> tiposCredito() {
        return Arrays.asList(TipoCredito.values());
    }

    @QueryMapping
    public List<EstadoCredito> estadosCredito() {
        return Arrays.asList(EstadoCredito.values());
    }

    @MutationMapping
    public Credito otorgarCredito(@Argument OtorgarCreditoInput input) {
        return creditoService.otorgarCredito(input);
    }

    @MutationMapping
    public Credito cambiarEstadoCredito(@Argument Long id, @Argument EstadoCredito estado, @Argument String observacion) {
        return creditoService.cambiarEstadoCredito(id, estado, observacion);
    }
}
