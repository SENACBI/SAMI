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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Snyck
 */
@Entity
@Table(name = "polizas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polizas.findAll", query = "SELECT p FROM Polizas p"),
    @NamedQuery(name = "Polizas.findByNumeroDePoliza", query = "SELECT p FROM Polizas p WHERE p.numeroDePoliza = :numeroDePoliza"),
    @NamedQuery(name = "Polizas.findByValorDePoliza", query = "SELECT p FROM Polizas p WHERE p.valorDePoliza = :valorDePoliza"),
    @NamedQuery(name = "Polizas.findByVigenciaDesde", query = "SELECT p FROM Polizas p WHERE p.vigenciaDesde = :vigenciaDesde"),
    @NamedQuery(name = "Polizas.findByVigenciaHasta", query = "SELECT p FROM Polizas p WHERE p.vigenciaHasta = :vigenciaHasta"),
    @NamedQuery(name = "Polizas.findByFechaAprobacion", query = "SELECT p FROM Polizas p WHERE p.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "Polizas.findByPorcentajeAsegurado", query = "SELECT p FROM Polizas p WHERE p.porcentajeAsegurado = :porcentajeAsegurado")})
public class Polizas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_de_poliza")
    private Integer numeroDePoliza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_de_poliza")
    private Integer valorDePoliza;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigencia_desde")
    @Temporal(TemporalType.DATE)
    private Date vigenciaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigencia_hasta")
    @Temporal(TemporalType.DATE)
    private Date vigenciaHasta;
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;
    @Column(name = "fecha_expedicion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
    @Column(name = "porcentaje_asegurado")
    private Integer porcentajeAsegurado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "polizas")
    private List<UsuariosContratos> usuariosContratosList;
    @JoinColumn(name = "id_aseguradora", referencedColumnName = "id_aseguradora")
    @ManyToOne(optional = false)
    private Aseguradora idAseguradora;

    public Polizas() {
    }

    public Polizas(Integer numeroDePoliza) {
        this.numeroDePoliza = numeroDePoliza;
    }

    public Polizas(Integer numeroDePoliza, Integer valorDePoliza, Date vigenciaDesde, Date vigenciaHasta) {
        this.numeroDePoliza = numeroDePoliza;
        this.valorDePoliza = valorDePoliza;
        this.vigenciaDesde = vigenciaDesde;
        this.vigenciaHasta = vigenciaHasta;
    }

    public Integer getNumeroDePoliza() {
        return numeroDePoliza;
    }

    public void setNumeroDePoliza(Integer numeroDePoliza) {
        this.numeroDePoliza = numeroDePoliza;
    }

    public Integer getValorDePoliza() {
        return valorDePoliza;
    }

    public void setValorDePoliza(Integer valorDePoliza) {
        this.valorDePoliza = valorDePoliza;
    }

    public Date getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(Date vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    public Date getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(Date vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Integer getPorcentajeAsegurado() {
        return porcentajeAsegurado;
    }

    public void setPorcentajeAsegurado(Integer porcentajeAsegurado) {
        this.porcentajeAsegurado = porcentajeAsegurado;
    }

    @XmlTransient
    public List<UsuariosContratos> getUsuariosContratosList() {
        return usuariosContratosList;
    }

    public void setUsuariosContratosList(List<UsuariosContratos> usuariosContratosList) {
        this.usuariosContratosList = usuariosContratosList;
    }

    public Aseguradora getIdAseguradora() {
        return idAseguradora;
    }

    public void setIdAseguradora(Aseguradora idAseguradora) {
        this.idAseguradora = idAseguradora;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDePoliza != null ? numeroDePoliza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Polizas)) {
            return false;
        }
        Polizas other = (Polizas) object;
        if ((this.numeroDePoliza == null && other.numeroDePoliza != null) || (this.numeroDePoliza != null && !this.numeroDePoliza.equals(other.numeroDePoliza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(numeroDePoliza);
    }
    
}
