/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.sena.sami.jpa.entities;

import co.edu.sena.sami.jsf.controllers.util.DigestUtil;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author Adsim
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNumeroDoc", query = "SELECT u FROM Usuarios u WHERE u.numeroDoc LIKE :numeroDoc"),
    @NamedQuery(name = "Usuarios.findByFechaExpedicionDoc", query = "SELECT u FROM Usuarios u WHERE u.fechaExpedicionDoc = :fechaExpedicionDoc"),
    @NamedQuery(name = "Usuarios.findByRazonSocial", query = "SELECT u FROM Usuarios u WHERE u.razonSocial LIKE :razonSocial"),
    @NamedQuery(name = "Usuarios.findByPrimerNombre", query = "SELECT u FROM Usuarios u WHERE u.primerNombre LIKE :primerNombre"),
    @NamedQuery(name = "Usuarios.findBySegundoNombre", query = "SELECT u FROM Usuarios u WHERE u.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "Usuarios.findByPrimerApellido", query = "SELECT u FROM Usuarios u WHERE u.primerApellido = :primerApellido"),
    @NamedQuery(name = "Usuarios.findBySegundoApellido", query = "SELECT u FROM Usuarios u WHERE u.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "Usuarios.findByNombreRepreLegal", query = "SELECT u FROM Usuarios u WHERE u.nombreRepreLegal = :nombreRepreLegal"),
    @NamedQuery(name = "Usuarios.findByFechaNac", query = "SELECT u FROM Usuarios u WHERE u.fechaNac = :fechaNac"),
    @NamedQuery(name = "Usuarios.findByEmail", query = "SELECT u FROM Usuarios u WHERE u.email = :email"),
    @NamedQuery(name = "Usuarios.findByTelefonoPrincipalUsu", query = "SELECT u FROM Usuarios u WHERE u.telefonoPrincipalUsu = :telefonoPrincipalUsu"),
    @NamedQuery(name = "Usuarios.findByTelefonoAlternoUsu", query = "SELECT u FROM Usuarios u WHERE u.telefonoAlternoUsu = :telefonoAlternoUsu"),
    @NamedQuery(name = "Usuarios.findByDireccionUsu", query = "SELECT u FROM Usuarios u WHERE u.direccionUsu = :direccionUsu"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByGpRh", query = "SELECT u FROM Usuarios u WHERE u.gpRh = :gpRh"),
    @NamedQuery(name = "Usuarios.findByCalificacion", query = "SELECT u FROM Usuarios u WHERE u.calificacion = :calificacion"),
    @NamedQuery(name = "Usuarios.findByFechaDeCreacion", query = "SELECT u FROM Usuarios u WHERE u.fechaDeCreacion = :fechaDeCreacion"),
    @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuarios.findByNumCuentaBanco", query = "SELECT u FROM Usuarios u WHERE u.numCuentaBanco = :numCuentaBanco"),
    @NamedQuery(name = "Usuarios.findByNombreTipoCuenta", query = "SELECT u FROM Usuarios u WHERE u.nombreTipoCuenta = :nombreTipoCuenta"),
    @NamedQuery(name = "Usuarios.findByGrado", query = "SELECT u FROM Usuarios u WHERE u.grado = :grado"),
    @NamedQuery(name = "Usuarios.findBySueldo", query = "SELECT u FROM Usuarios u WHERE u.sueldo = :sueldo"),
    @NamedQuery(name = "Usuarios.findByFechaIngreso", query = "SELECT u FROM Usuarios u WHERE u.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Usuarios.findByPersonalidad", query = "SELECT u FROM Usuarios u WHERE u.personalidad = :personalidad"),
    @NamedQuery(name = "Usuarios.findByDv", query = "SELECT u FROM Usuarios u WHERE u.dv = :dv")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_doc")
    private String numeroDoc;
    @Column(name = "fecha_expedicion_doc")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionDoc;
    @Size(min = 1, max = 20)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Size(min = 1, max = 20)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 20)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Size(max = 45)
    @Column(name = "nombre_repre_legal")
    private String nombreRepreLegal;
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefono_principal_usu")
    private String telefonoPrincipalUsu;
    @Size(max = 15)
    @Column(name = "telefono_alterno_usu")
    private String telefonoAlternoUsu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "direccion_usu")
    private String direccionUsu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Size(max = 3)
    @Column(name = "gp_rh")
    private String gpRh;
    @Size(max = 10)
    @Column(name = "calificacion")
    private String calificacion;
    @Column(name = "fecha_de_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Size(max = 30)
    @Column(name = "num_cuenta_banco")
    private String numCuentaBanco;
    @Size(max = 45)
    @Column(name = "nombre_tipo_cuenta")
    private String nombreTipoCuenta;
    @Column(name = "grado")
    private Integer grado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldo")
    private Double sueldo;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Size(max = 45)
    @Column(name = "personalidad")
    private String personalidad;
    @Column(name = "dv")
    private String dv;
    @Size(max = 45)
    @JoinTable(name = "usuario_formacion_academica", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_formacion", referencedColumnName = "id_formacion")})
    @ManyToMany
    private List<FormacionAcademica> formacionAcademicaList;
    @ManyToMany(mappedBy = "usuariosList")
    private List<Cargo> cargoList;
    @JoinTable(name = "usuarios_ficha_caracterizacion", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_ficha_caracterizacion", referencedColumnName = "id_ficha_caracterizacion")})
    @ManyToMany
    private List<FichaCaracterizacion> fichaCaracterizacionList;
    @JoinTable(name = "rol_usuarios", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")})
    @ManyToMany
    private List<Rol> rolList;
    @JoinTable(name = "usuarios_riesgos_laborales", joinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_riesgos_laborales", referencedColumnName = "id_riesgos_laborales")})
    @ManyToMany
    private List<RiesgosLaborales> riesgosLaboralesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Documentos> documentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Novedades> novedadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Pagos> pagosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Notificaciones> notificacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<AvanceUsuarios> avanceUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<SolicitudServicios> solicitudServiciosList;
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne
    private TipoContrato idTipoContrato;
    @JoinColumn(name = "id_centro_formacion", referencedColumnName = "id_centro_formacion")
    @ManyToOne
    private CentroFormacion idCentroFormacion;
    @JoinColumns({
        @JoinColumn(name = "id_ciudad_residencia", referencedColumnName = "id_ciudad"),
        @JoinColumn(name = "id_dpto_residencia", referencedColumnName = "id_dpto")})
    @ManyToOne(optional = false)
    private Ciudad ciudad;
    @JoinColumns({
        @JoinColumn(name = "id_ciudad_exp_doc", referencedColumnName = "id_ciudad"),
        @JoinColumn(name = "id_dpto_exp_doc", referencedColumnName = "id_dpto")})
    @ManyToOne
    private Ciudad ciudad1;
    @JoinColumn(name = "id_arl", referencedColumnName = "id_arl")
    @ManyToOne
    private Arl idArl;
    @JoinColumn(name = "id_eps", referencedColumnName = "id_eps")
    @ManyToOne
    private Eps idEps;
    @JoinColumn(name = "id_fondo_pensiones", referencedColumnName = "id_fondo_pensiones")
    @ManyToOne
    private FondoDePensiones idFondoPensiones;
    @JoinColumn(name = "id_ccf", referencedColumnName = "id_ccf")
    @ManyToOne
    private Ccf idCcf;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id_sexo")
    @ManyToOne
    private Sexo idSexo;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id_estado_civil")
    @ManyToOne
    private EstadoCivil idEstadoCivil;
    @JoinColumn(name = "id_estrato_social", referencedColumnName = "id_estrato_social")
    @ManyToOne
    private EstratoSocial idEstratoSocial;
    @JoinColumn(name = "id_tipo_doc", referencedColumnName = "id_tipo_doc")
    @ManyToOne(optional = false)
    private TipoDocumentos idTipoDoc;
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    @ManyToOne
    private Bancos idBanco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<UsuariosSolicitudMaterialesAlmacen> usuariosSolicitudMaterialesAlmacenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<ActaVerificacion> actaVerificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Comisiones> comisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private List<UsuariosContratos> usuariosContratosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Facturas> facturasList;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String numeroDoc, String razonSocial, String primerNombre,String primerApellido, String email, String telefonoPrincipalUsu, String direccionUsu, String password, boolean estado) {
        this.idUsuario = idUsuario;
        this.numeroDoc = numeroDoc;
        this.razonSocial = razonSocial;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.email = email;
        this.telefonoPrincipalUsu = telefonoPrincipalUsu;
        this.direccionUsu = direccionUsu;
        this.password = password;
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Date getFechaExpedicionDoc() {
        return fechaExpedicionDoc;
    }

    public void setFechaExpedicionDoc(Date fechaExpedicionDoc) {
        this.fechaExpedicionDoc = fechaExpedicionDoc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
    
    public String getRazonSocial2() {
        return razonSocial + " " + primerApellido + " " + segundoApellido;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombreRepreLegal() {
        return nombreRepreLegal;
    }

    public void setNombreRepreLegal(String nombreRepreLegal) {
        this.nombreRepreLegal = nombreRepreLegal;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonoPrincipalUsu() {
        return telefonoPrincipalUsu;
    }

    public void setTelefonoPrincipalUsu(String telefonoPrincipalUsu) {
        this.telefonoPrincipalUsu = telefonoPrincipalUsu;
    }

    public String getTelefonoAlternoUsu() {
        return telefonoAlternoUsu;
    }

    public void setTelefonoAlternoUsu(String telefonoAlternoUsu) {
        this.telefonoAlternoUsu = telefonoAlternoUsu;
    }

    public String getDireccionUsu() {
        return direccionUsu;
    }

    public void setDireccionUsu(String direccionUsu) {
        this.direccionUsu = direccionUsu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
         try {
            this.password = DigestUtil.generateDigest(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getGpRh() {
        return gpRh;
    }

    public void setGpRh(String gpRh) {
        this.gpRh = gpRh;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNumCuentaBanco() {
        return numCuentaBanco;
    }

    public void setNumCuentaBanco(String numCuentaBanco) {
        this.numCuentaBanco = numCuentaBanco;
    }

    public String getNombreTipoCuenta() {
        return nombreTipoCuenta;
    }

    public void setNombreTipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
    }
    
     public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }
    
    @XmlTransient
    public List<FormacionAcademica> getFormacionAcademicaList() {
        return formacionAcademicaList;
    }

    public void setFormacionAcademicaList(List<FormacionAcademica> formacionAcademicaList) {
        this.formacionAcademicaList = formacionAcademicaList;
    }

    @XmlTransient
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @XmlTransient
    public List<FichaCaracterizacion> getFichaCaracterizacionList() {
        return fichaCaracterizacionList;
    }

    public void setFichaCaracterizacionList(List<FichaCaracterizacion> fichaCaracterizacionList) {
        this.fichaCaracterizacionList = fichaCaracterizacionList;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @XmlTransient
    public List<RiesgosLaborales> getRiesgosLaboralesList() {
        return riesgosLaboralesList;
    }

    public void setRiesgosLaboralesList(List<RiesgosLaborales> riesgosLaboralesList) {
        this.riesgosLaboralesList = riesgosLaboralesList;
    }

    @XmlTransient
    public List<Documentos> getDocumentosList() {
        return documentosList;
    }

    public void setDocumentosList(List<Documentos> documentosList) {
        this.documentosList = documentosList;
    }

    @XmlTransient
    public List<Novedades> getNovedadesList() {
        return novedadesList;
    }

    public void setNovedadesList(List<Novedades> novedadesList) {
        this.novedadesList = novedadesList;
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
    public List<AvanceUsuarios> getAvanceUsuariosList() {
        return avanceUsuariosList;
    }

    public void setAvanceUsuariosList(List<AvanceUsuarios> avanceUsuariosList) {
        this.avanceUsuariosList = avanceUsuariosList;
    }

    @XmlTransient
    public List<SolicitudServicios> getSolicitudServiciosList() {
        return solicitudServiciosList;
    }

    public void setSolicitudServiciosList(List<SolicitudServicios> solicitudServiciosList) {
        this.solicitudServiciosList = solicitudServiciosList;
    }

    public TipoContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public CentroFormacion getIdCentroFormacion() {
        return idCentroFormacion;
    }

    public void setIdCentroFormacion(CentroFormacion idCentroFormacion) {
        this.idCentroFormacion = idCentroFormacion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Ciudad getCiudad1() {
        return ciudad1;
    }

    public void setCiudad1(Ciudad ciudad1) {
        this.ciudad1 = ciudad1;
    }

    public Arl getIdArl() {
        return idArl;
    }

    public void setIdArl(Arl idArl) {
        this.idArl = idArl;
    }

    public Eps getIdEps() {
        return idEps;
    }

    public void setIdEps(Eps idEps) {
        this.idEps = idEps;
    }

    public FondoDePensiones getIdFondoPensiones() {
        return idFondoPensiones;
    }

    public void setIdFondoPensiones(FondoDePensiones idFondoPensiones) {
        this.idFondoPensiones = idFondoPensiones;
    }

    public Ccf getIdCcf() {
        return idCcf;
    }

    public void setIdCcf(Ccf idCcf) {
        this.idCcf = idCcf;
    }

    public Sexo getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Sexo idSexo) {
        this.idSexo = idSexo;
    }

    public EstadoCivil getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(EstadoCivil idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public EstratoSocial getIdEstratoSocial() {
        return idEstratoSocial;
    }

    public void setIdEstratoSocial(EstratoSocial idEstratoSocial) {
        this.idEstratoSocial = idEstratoSocial;
    }

    public TipoDocumentos getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(TipoDocumentos idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public Bancos getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Bancos idBanco) {
        this.idBanco = idBanco;
    }

    @XmlTransient
    public List<UsuariosSolicitudMaterialesAlmacen> getUsuariosSolicitudMaterialesAlmacenList() {
        return usuariosSolicitudMaterialesAlmacenList;
    }

    public void setUsuariosSolicitudMaterialesAlmacenList(List<UsuariosSolicitudMaterialesAlmacen> usuariosSolicitudMaterialesAlmacenList) {
        this.usuariosSolicitudMaterialesAlmacenList = usuariosSolicitudMaterialesAlmacenList;
    }

    @XmlTransient
    public List<ActaVerificacion> getActaVerificacionList() {
        return actaVerificacionList;
    }

    public void setActaVerificacionList(List<ActaVerificacion> actaVerificacionList) {
        this.actaVerificacionList = actaVerificacionList;
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
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return primerNombre;
    }
    
    
    public String informacionUsuario(){
        return (numeroDoc==null?"":numeroDoc + " - ") 
                + (primerNombre==null?"": primerNombre)
                + (segundoNombre == null? "": " " + segundoNombre) 
                + (primerApellido==null?"": " " + primerApellido);
    }
    
}
