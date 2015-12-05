/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.sena.sami.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Snyck
 */
@Entity
@Table(name = "contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratos.findAll", query = "SELECT c FROM Contratos c"),
    @NamedQuery(name = "Contratos.findByIdContrato", query = "SELECT c FROM Contratos c WHERE c.idContrato = :idContrato"),
    @NamedQuery(name = "Contratos.findByNumeroDeContrato", query = "SELECT c FROM Contratos c WHERE c.numeroDeContrato LIKE :numeroDeContrato"),
    @NamedQuery(name = "Contratos.findByFechasuscripcion", query = "SELECT c FROM Contratos c WHERE c.fechasuscripcion BETWEEN :fechaInicio AND :fechaFin"),
    @NamedQuery(name = "Contratos.findByFechaInicioContrato", query = "SELECT c FROM Contratos c WHERE c.fechaInicioContrato = :fechaInicioContrato"),
    @NamedQuery(name = "Contratos.findByFechaFinContrato", query = "SELECT c FROM Contratos c WHERE c.fechaFinContrato = :fechaFinContrato"),
    @NamedQuery(name = "Contratos.findByPlazoNumeroDias", query = "SELECT c FROM Contratos c WHERE c.plazoNumeroDias = :plazoNumeroDias"),
    @NamedQuery(name = "Contratos.findByObjetoContractual", query = "SELECT c FROM Contratos c WHERE c.objetoContractual = :objetoContractual"),
    @NamedQuery(name = "Contratos.findByObligaciones", query = "SELECT c FROM Contratos c WHERE c.obligaciones = :obligaciones"),
    @NamedQuery(name = "Contratos.findByValorInicialContrato", query = "SELECT c FROM Contratos c WHERE c.valorInicialContrato = :valorInicialContrato"),
    @NamedQuery(name = "Contratos.findByPrimerPago", query = "SELECT c FROM Contratos c WHERE c.primerPago = :primerPago"),
    @NamedQuery(name = "Contratos.findByUltimoPago", query = "SELECT c FROM Contratos c WHERE c.ultimoPago = :ultimoPago"),
    @NamedQuery(name = "Contratos.findByNumeroDeMensualidades", query = "SELECT c FROM Contratos c WHERE c.numeroDeMensualidades = :numeroDeMensualidades"),
    @NamedQuery(name = "Contratos.findByValorMensual", query = "SELECT c FROM Contratos c WHERE c.valorMensual = :valorMensual"),
    @NamedQuery(name = "Contratos.findByFechaEstudioMercado", query = "SELECT c FROM Contratos c WHERE c.fechaEstudioMercado = :fechaEstudioMercado"),
    @NamedQuery(name = "Contratos.findByFechaDeAutorizacion", query = "SELECT c FROM Contratos c WHERE c.fechaDeAutorizacion = :fechaDeAutorizacion"),
    @NamedQuery(name = "Contratos.findByFechaDeEstudioPrevio", query = "SELECT c FROM Contratos c WHERE c.fechaDeEstudioPrevio = :fechaDeEstudioPrevio"),
    @NamedQuery(name = "Contratos.findByFechaDeIdoneidad", query = "SELECT c FROM Contratos c WHERE c.fechaDeIdoneidad = :fechaDeIdoneidad"),
    @NamedQuery(name = "Contratos.findByValorAdicionPresupuestal", query = "SELECT c FROM Contratos c WHERE c.valorAdicionPresupuestal = :valorAdicionPresupuestal"),
    @NamedQuery(name = "Contratos.findByAdicionNumeroDias", query = "SELECT c FROM Contratos c WHERE c.adicionNumeroDias = :adicionNumeroDias"),
    @NamedQuery(name = "Contratos.findByIdPoliza", query = "SELECT c FROM Contratos c WHERE c.idPoliza = :idPoliza"),
    @NamedQuery(name = "Contratos.findByIdCuentasBancarias", query = "SELECT c FROM Contratos c WHERE c.idCuentasBancarias = :idCuentasBancarias"),
    @NamedQuery(name = "Contratos.findByObservaciones", query = "SELECT c FROM Contratos c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "Contratos.findByJustificacion", query = "SELECT c FROM Contratos c WHERE c.justificacion = :justificacion"),
    @NamedQuery(name = "Contratos.findByRegistroPresupuestal", query = "SELECT c FROM Contratos c WHERE c.registroPresupuestal = :registroPresupuestal"),
    @NamedQuery(name = "Contratos.findByFechaSolicitudContratacion", query = "SELECT c FROM Contratos c WHERE c.fechaSolicitudContratacion = :fechaSolicitudContratacion"),
    @NamedQuery(name = "Contratos.findByFechaVerificacionPlanCompras", query = "SELECT c FROM Contratos c WHERE c.fechaVerificacionPlanCompras = :fechaVerificacionPlanCompras"),
    @NamedQuery(name = "Contratos.findByNumeroCdp", query = "SELECT c FROM Contratos c WHERE c.numeroCdp = :numeroCdp"),
    @NamedQuery(name = "Contratos.findByFechaCdp", query = "SELECT c FROM Contratos c WHERE c.fechaCdp = :fechaCdp"),
    @NamedQuery(name = "Contratos.findByNumeroActaSeleccion", query = "SELECT c FROM Contratos c WHERE c.numeroActaSeleccion = :numeroActaSeleccion"),
    @NamedQuery(name = "Contratos.findByFechaActaSeleccion", query = "SELECT c FROM Contratos c WHERE c.fechaActaSeleccion = :fechaActaSeleccion"),
    @NamedQuery(name = "Contratos.findByNumeroContratoRadicado", query = "SELECT c FROM Contratos c WHERE c.numeroContratoRadicado = :numeroContratoRadicado"),
    @NamedQuery(name = "Contratos.findByFechaContratoRadicado", query = "SELECT c FROM Contratos c WHERE c.fechaContratoRadicado = :fechaContratoRadicado"),
    @NamedQuery(name = "Contratos.findByTipoAdicionPresupuestal", query = "SELECT c FROM Contratos c WHERE c.tipoAdicionPresupuestal = :tipoAdicionPresupuestal"),
    @NamedQuery(name = "Contratos.findByTipoNumeroAutorizacionServicios", query = "SELECT c FROM Contratos c WHERE c.numeroAutorizacionServicios = :numeroAutorizacionServicios"),
    @NamedQuery(name = "Contratos.findByFechaLiquidacionContrato", query = "SELECT c FROM Contratos c WHERE c.fechaLiquidacionContrato = :fechaLiquidacionContrato"),
    @NamedQuery(name = "Contratos.findByPeriodoEjecucionDias", query = "SELECT c FROM Contratos c WHERE c.periodoEjecucionDias = :periodoEjecucionDias"),
    @NamedQuery(name = "Contratos.findByPeriodoEjecucionMeses", query = "SELECT c FROM Contratos c WHERE c.periodoEjecucionMeses = :periodoEjecucionMeses"),
    @NamedQuery(name = "Contratos.findByRubroPresupuestal", query = "SELECT c FROM Contratos c WHERE c.rubroPresupuestal = :rubroPresupuestal"),
    @NamedQuery(name = "Contratos.findByValorHora", query = "SELECT c FROM Contratos c WHERE c.valorHora = :valorHora"),
    @NamedQuery(name = "Contratos.findByEjecucionMultidestino", query = "SELECT c FROM Contratos c WHERE c.ejecucionMultidestino = :ejecucionMultidestino")})
public class Contratos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Column(name = "numero_de_contrato")
    private String numeroDeContrato;
    @Column(name = "Fecha_suscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechasuscripcion;
    @Column(name = "fecha_inicio_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioContrato;
    @Column(name = "fecha_fin_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaFinContrato;
    @Column(name = "plazo_numero_dias")
    private Integer plazoNumeroDias;
    @Column(name = "objeto_contractual")
    private String objetoContractual;
    @Size(max = 4000)
    @Column(name = "obligaciones")
    private String obligaciones;
    @Column(name = "valor_inicial_contrato")
    private Long valorInicialContrato;
    @Column(name = "primer_pago")
    private Integer primerPago;
    @Column(name = "ultimo_pago")
    private Long ultimoPago;
    @Column(name = "numero_de_mensualidades")
    private Short numeroDeMensualidades;
    @Column(name = "valor_mensual")
    private Integer valorMensual;
    @Column(name = "fecha_estudio_mercado")
    @Temporal(TemporalType.DATE)
    private Date fechaEstudioMercado;
    @Column(name = "fecha_de_autorizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaDeAutorizacion;
    @Column(name = "fecha_de_estudio_previo")
    @Temporal(TemporalType.DATE)
    private Date fechaDeEstudioPrevio;
    @Column(name = "fecha_de_idoneidad")
    @Temporal(TemporalType.DATE)
    private Date fechaDeIdoneidad;
    @Column(name = "valor_adicion_presupuestal")
    private Integer valorAdicionPresupuestal;
    @Column(name = "adicion_numero_dias")
    private Integer adicionNumeroDias;
    @Column(name = "id_poliza")
    private Integer idPoliza;
    @Column(name = "id_cuentas_bancarias")
    private Integer idCuentasBancarias;
    @Size(max = 5000)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 500)
    @Column(name = "justificacion")
    private String justificacion;
    @Size(max = 25)
    @Column(name = "registro_presupuestal")
    private String registroPresupuestal;
    @Column(name = "fecha_reg_presupuestal")
    @Temporal(TemporalType.DATE)
    private Date fechaRegPresupuestal;
    @Column(name = "fecha_solicitud_contratacion")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitudContratacion;
    @Column(name = "fecha_verificacion_plan_compras")
    @Temporal(TemporalType.DATE)
    private Date fechaVerificacionPlanCompras;
    @Size(max = 45)
    @Column(name = "numero_cdp")
    private String numeroCdp;
    @Column(name = "fecha_cdp")
    @Temporal(TemporalType.DATE)
    private Date fechaCdp;
    @Size(max = 45)
    @Column(name = "numero_acta_seleccion")
    private String numeroActaSeleccion;
    @Column(name = "fecha_acta_seleccion")
    @Temporal(TemporalType.DATE)
    private Date fechaActaSeleccion;
    @Size(max = 45)
    @Column(name = "numero_contrato_radicado")
    private String numeroContratoRadicado;
    @Column(name = "fecha_contrato_radicado")
    @Temporal(TemporalType.DATE)
    private Date fechaContratoRadicado;
    @Size(max = 45)
    @Column(name = "tipo_adicion_presupuestal")
    private String tipoAdicionPresupuestal;
    @Size(max = 45)
    @Column(name = "numero_autorizacion_servicios")
    private String numeroAutorizacionServicios;
    @Column(name = "fecha_liquidacion_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaLiquidacionContrato;
    @Column(name = "periodo_ejecucion_dias")
    private Integer periodoEjecucionDias;
    @Column(name = "periodo_ejecucion_meses")
    private Integer periodoEjecucionMeses;
    @Size(max = 50)
    @Column(name = "rubro_presupuestal")
    private String rubroPresupuestal;
    @Column(name = "valor_hora")
    private Integer valorHora;
    @Column(name = "ejecucion_multidestino")
    private boolean ejecucionMultidestino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<SoportesDeDocumentos> soportesDeDocumentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<Pagos> pagosList;
    @OneToMany(mappedBy = "idContrato")
    private List<Notificaciones> notificacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<PagosContratos> pagosContratosList;
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne(optional = false)
    private TipoDeContrato idTipoContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<Comisiones> comisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratos")
    private List<UsuariosContratos> usuariosContratosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<Informes> informesList;

    public Contratos() {
    }

    public Contratos(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contratos(Integer idContrato, String numeroDeContrato, Date fechasuscripcion, Date fechaInicioContrato, Date fechaFinContrato, String objetoContractual) {
        this.idContrato = idContrato;
        this.numeroDeContrato = numeroDeContrato;
        this.fechasuscripcion = fechasuscripcion;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinContrato = fechaFinContrato;
        this.objetoContractual = objetoContractual;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getNumeroDeContrato() {
        return numeroDeContrato;
    }

    public void setNumeroDeContrato(String numeroDeContrato) {
        this.numeroDeContrato = numeroDeContrato;
    }

    public Date getFechasuscripcion() {
        return fechasuscripcion;
    }

    public void setFechasuscripcion(Date fechasuscripcion) {
        this.fechasuscripcion = fechasuscripcion;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public Integer getPlazoNumeroDias() {
        return plazoNumeroDias;
    }

    public void setPlazoNumeroDias(Integer plazoNumeroDias) {
        this.plazoNumeroDias = plazoNumeroDias;
    }

    public String getObjetoContractual() {
        return objetoContractual;
    }

    public void setObjetoContractual(String objetoContractual) {
        this.objetoContractual = objetoContractual;
    }

    public String getObligaciones() {
        return obligaciones;
    }

    public void setObligaciones(String obligaciones) {
        this.obligaciones = obligaciones;
    }

    public Long getValorInicialContrato() {
        return valorInicialContrato;
    }

    public void setValorInicialContrato(Long valorInicialContrato) {
        this.valorInicialContrato = valorInicialContrato;
    }

    
    public Integer getPrimerPago() {
        return primerPago;
    }

    public void setPrimerPago(Integer primerPago) {
        this.primerPago = primerPago;
    }

    public Long getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(Long ultimoPago) {
        this.ultimoPago = ultimoPago;
    }

    public Short getNumeroDeMensualidades() {
        return numeroDeMensualidades;
    }

    public void setNumeroDeMensualidades(Short numeroDeMensualidades) {
        this.numeroDeMensualidades = numeroDeMensualidades;
    }

    public Integer getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(Integer valorMensual) {
        this.valorMensual = valorMensual;
    }

    public Date getFechaEstudioMercado() {
        return fechaEstudioMercado;
    }

    public void setFechaEstudioMercado(Date fechaEstudioMercado) {
        this.fechaEstudioMercado = fechaEstudioMercado;
    }

    
    public Date getFechaDeAutorizacion() {
        return fechaDeAutorizacion;
    }

    public void setFechaDeAutorizacion(Date fechaDeAutorizacion) {
        this.fechaDeAutorizacion = fechaDeAutorizacion;
    }

    public Date getFechaDeEstudioPrevio() {
        return fechaDeEstudioPrevio;
    }

    public void setFechaDeEstudioPrevio(Date fechaDeEstudioPrevio) {
        this.fechaDeEstudioPrevio = fechaDeEstudioPrevio;
    }

    public Date getFechaDeIdoneidad() {
        return fechaDeIdoneidad;
    }

    public void setFechaDeIdoneidad(Date fechaDeIdoneidad) {
        this.fechaDeIdoneidad = fechaDeIdoneidad;
    }

    public Integer getValorAdicionPresupuestal() {
        return valorAdicionPresupuestal;
    }

    public void setValorAdicionPresupuestal(Integer valorAdicionPresupuestal) {
        this.valorAdicionPresupuestal = valorAdicionPresupuestal;
    }

    public Integer getAdicionNumeroDias() {
        return adicionNumeroDias;
    }

    public void setAdicionNumeroDias(Integer adicionNumeroDias) {
        this.adicionNumeroDias = adicionNumeroDias;
    }
    
    public Integer getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(Integer idPoliza) {
        this.idPoliza = idPoliza;
    }

    public Integer getIdCuentasBancarias() {
        return idCuentasBancarias;
    }

    public void setIdCuentasBancarias(Integer idCuentasBancarias) {
        this.idCuentasBancarias = idCuentasBancarias;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getRegistroPresupuestal() {
        return registroPresupuestal;
    }

    public void setRegistroPresupuestal(String registroPresupuestal) {
        this.registroPresupuestal = registroPresupuestal;
    }

    public Date getFechaRegPresupuestal() {
        return fechaRegPresupuestal;
    }

    public void setFechaRegPresupuestal(Date fechaRegPresupuestal) {
        this.fechaRegPresupuestal = fechaRegPresupuestal;
    }

    public Date getFechaSolicitudContratacion() {
        return fechaSolicitudContratacion;
    }

    public void setFechaSolicitudContratacion(Date fechaSolicitudContratacion) {
        this.fechaSolicitudContratacion = fechaSolicitudContratacion;
    }

    public Date getFechaVerificacionPlanCompras() {
        return fechaVerificacionPlanCompras;
    }

    public void setFechaVerificacionPlanCompras(Date fechaVerificacionPlanCompras) {
        this.fechaVerificacionPlanCompras = fechaVerificacionPlanCompras;
    }

    public String getNumeroCdp() {
        return numeroCdp;
    }

    public void setNumeroCdp(String numeroCdp) {
        this.numeroCdp = numeroCdp;
    }

    public Date getFechaCdp() {
        return fechaCdp;
    }

    public void setFechaCdp(Date fechaCdp) {
        this.fechaCdp = fechaCdp;
    }

    public String getNumeroActaSeleccion() {
        return numeroActaSeleccion;
    }

    public void setNumeroActaSeleccion(String numeroActaSeleccion) {
        this.numeroActaSeleccion = numeroActaSeleccion;
    }

    public Date getFechaActaSeleccion() {
        return fechaActaSeleccion;
    }

    public void setFechaActaSeleccion(Date fechaActaSeleccion) {
        this.fechaActaSeleccion = fechaActaSeleccion;
    }

    public String getNumeroContratoRadicado() {
        return numeroContratoRadicado;
    }

    public void setNumeroContratoRadicado(String numeroContratoRadicado) {
        this.numeroContratoRadicado = numeroContratoRadicado;
    }

    public Date getFechaContratoRadicado() {
        return fechaContratoRadicado;
    }

    public void setFechaContratoRadicado(Date fechaContratoRadicado) {
        this.fechaContratoRadicado = fechaContratoRadicado;
    }

    public String getTipoAdicionPresupuestal() {
        return tipoAdicionPresupuestal;
    }

    public void setTipoAdicionPresupuestal(String tipoAdicionPresupuestal) {
        this.tipoAdicionPresupuestal = tipoAdicionPresupuestal;
    }

    public String getNumeroAutorizacionServicios() {
        return numeroAutorizacionServicios;
    }

    public void setNumeroAutorizacionServicios(String numeroAutorizacionServicios) {
        this.numeroAutorizacionServicios = numeroAutorizacionServicios;
    }

    public Date getFechaLiquidacionContrato() {
        return fechaLiquidacionContrato;
    }

    public void setFechaLiquidacionContrato(Date fechaLiquidacionContrato) {
        this.fechaLiquidacionContrato = fechaLiquidacionContrato;
    }

    public Integer getPeriodoEjecucionDias() {
        return periodoEjecucionDias;
    }

    public void setPeriodoEjecucionDias(Integer periodoEjecucionDias) {
        this.periodoEjecucionDias = periodoEjecucionDias;
    }

    public Integer getPeriodoEjecucionMeses() {
        return periodoEjecucionMeses;
    }

    public void setPeriodoEjecucionMeses(Integer periodoEjecucionMeses) {
        this.periodoEjecucionMeses = periodoEjecucionMeses;
    }

    public String getRubroPresupuestal() {
        return rubroPresupuestal;
    }

    public void setRubroPresupuestal(String rubroPresupuestal) {
        this.rubroPresupuestal = rubroPresupuestal;
    }

    public Integer getValorHora() {
        return valorHora;
    }

    public void setValorHora(Integer valorHora) {
        this.valorHora = valorHora;
    }

    public boolean isEjecucionMultidestino() {
        return ejecucionMultidestino;
    }

    public void setEjecucionMultidestino(boolean ejecucionMultidestino) {
        this.ejecucionMultidestino = ejecucionMultidestino;
    }
    
    @XmlTransient
    public List<SoportesDeDocumentos> getSoportesDeDocumentosList() {
        return soportesDeDocumentosList;
    }

    public void setSoportesDeDocumentosList(List<SoportesDeDocumentos> soportesDeDocumentosList) {
        this.soportesDeDocumentosList = soportesDeDocumentosList;
    }

    @XmlTransient
    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @XmlTransient
    public List<PagosContratos> getPagosContratosList() {
        return pagosContratosList;
    }

    public void setPagosContratosList(List<PagosContratos> pagosContratosList) {
        this.pagosContratosList = pagosContratosList;
    }

    public TipoDeContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoDeContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    @XmlTransient
    public List<Comisiones> getComisionesList() {
        return comisionesList;
    }

    public void setComisionesList(List<Comisiones> comisionesList) {
        this.comisionesList = comisionesList;
    }

    @XmlTransient
    public List<UsuariosContratos> getUsuariosContratosList() {
        return usuariosContratosList;
    }

    public void setUsuariosContratosList(List<UsuariosContratos> usuariosContratosList) {
        this.usuariosContratosList = usuariosContratosList;
    }

    @XmlTransient
    public List<Informes> getInformesList() {
        return informesList;
    }

    public void setInformesList(List<Informes> informesList) {
        this.informesList = informesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(numeroDeContrato);
    }
    
}
