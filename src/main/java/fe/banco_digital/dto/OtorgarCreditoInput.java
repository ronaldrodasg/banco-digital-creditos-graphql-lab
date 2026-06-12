package fe.banco_digital.dto;

import fe.banco_digital.entity.EstadoCredito;
import fe.banco_digital.entity.TipoCredito;

public record OtorgarCreditoInput(
        String documentoCliente,
        TipoCredito tipo,
        Double monto,
        Integer plazoMeses,
        Double tasaInteresAnual,
        EstadoCredito estado,
        String destinoCredito,
        String observacion
) {
}
