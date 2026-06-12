package fe.banco_digital.dto;

import java.util.List;

import fe.banco_digital.entity.Credito;
import fe.banco_digital.entity.TipoCredito;

public class CategoriaCredito {
    private final TipoCredito tipo;
    private final int total;
    private final List<Credito> creditos;

    public CategoriaCredito(TipoCredito tipo, List<Credito> creditos) {
        this.tipo = tipo;
        this.creditos = creditos;
        this.total = creditos.size();
    }

    public TipoCredito getTipo() { return tipo; }

    public int getTotal() { return total; }

    public List<Credito> getCreditos() { return creditos; }
}
