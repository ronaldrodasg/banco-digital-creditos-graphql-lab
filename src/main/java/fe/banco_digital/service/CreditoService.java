package fe.banco_digital.service;

import java.util.List;

import fe.banco_digital.dto.CategoriaCredito;
import fe.banco_digital.dto.OtorgarCreditoInput;
import fe.banco_digital.entity.Credito;
import fe.banco_digital.entity.EstadoCredito;
import fe.banco_digital.entity.TipoCredito;

public interface CreditoService {
    Credito otorgarCredito(OtorgarCreditoInput input);
    Credito cambiarEstadoCredito(Long id, EstadoCredito estado, String observacion);
    Credito consultarCredito(Long id);
    List<Credito> creditosPorDocumentoCliente(String documento);
    List<Credito> creditosPorEstado(EstadoCredito estado);
    List<CategoriaCredito> creditosPorCategoria(TipoCredito tipo);
}
