package fe.banco_digital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fe.banco_digital.entity.Cliente;
import fe.banco_digital.entity.Credito;
import fe.banco_digital.entity.EstadoCredito;
import fe.banco_digital.entity.TipoCredito;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByCliente(Cliente cliente);
    List<Credito> findByClienteDocumento(String documento);
    List<Credito> findByTipo(TipoCredito tipo);
    List<Credito> findByEstado(EstadoCredito estado);
    List<Credito> findByTipoOrderByFechaSolicitudDesc(TipoCredito tipo);
}
