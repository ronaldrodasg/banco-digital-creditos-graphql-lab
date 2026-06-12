package fe.banco_digital.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credito")
    private Long idCredito;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 40)
    private TipoCredito tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 40)
    private EstadoCredito estado = EstadoCredito.EN_ESTUDIO;

    @Column(name = "monto", nullable = false, precision = 18, scale = 2)
    private BigDecimal monto;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "tasa_interes_anual", nullable = false, precision = 8, scale = 4)
    private BigDecimal tasaInteresAnual;

    @Column(name = "destino_credito", length = 250)
    private String destinoCredito;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    public Long getId() { return idCredito; }

    public Long getIdCredito() { return idCredito; }

    public void setIdCredito(Long idCredito) { this.idCredito = idCredito; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public TipoCredito getTipo() { return tipo; }

    public void setTipo(TipoCredito tipo) { this.tipo = tipo; }

    public EstadoCredito getEstado() { return estado; }

    public void setEstado(EstadoCredito estado) { this.estado = estado; }

    public BigDecimal getMonto() { return monto; }

    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public Integer getPlazoMeses() { return plazoMeses; }

    public void setPlazoMeses(Integer plazoMeses) { this.plazoMeses = plazoMeses; }

    public BigDecimal getTasaInteresAnual() { return tasaInteresAnual; }

    public void setTasaInteresAnual(BigDecimal tasaInteresAnual) { this.tasaInteresAnual = tasaInteresAnual; }

    public String getDestinoCredito() { return destinoCredito; }

    public void setDestinoCredito(String destinoCredito) { this.destinoCredito = destinoCredito; }

    public String getObservacion() { return observacion; }

    public void setObservacion(String observacion) { this.observacion = observacion; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}
