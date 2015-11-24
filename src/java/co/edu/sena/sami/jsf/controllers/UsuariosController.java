package co.edu.sena.sami.jsf.controllers;

import co.edu.sena.sami.jpa.entities.Cargo;
import co.edu.sena.sami.jpa.entities.CentroFormacion;
import co.edu.sena.sami.jpa.entities.Ciudad;
import co.edu.sena.sami.jpa.entities.Rol;
import co.edu.sena.sami.jpa.entities.TipoContrato;
import co.edu.sena.sami.jpa.entities.Usuarios;
import co.edu.sena.sami.jpa.sessions.CargoFacade;
import co.edu.sena.sami.jpa.sessions.CentroFormacionFacade;
import co.edu.sena.sami.jpa.sessions.CiudadFacade;
import co.edu.sena.sami.jpa.sessions.RolFacade;
import co.edu.sena.sami.jpa.sessions.TipoContratoFacade;
import co.edu.sena.sami.jpa.sessions.UsuariosFacade;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private co.edu.sena.sami.jpa.sessions.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private List<Rol> listaRoles = null;
    private List<Cargo> listaCargo = null;
    private Usuarios selected;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @EJB
    private CentroFormacionFacade centroFormacionFacade;
    @EJB
    private CargoFacade cargoFacade;
    @EJB
    private TipoContratoFacade tipoContratoFacade;


    public UsuariosController() {
    }

    public Usuarios getSelected() {
        return selected;
    }

    public CiudadFacade getCiudadFacade() {
        return ciudadFacade;
    }

    public CargoFacade getCargoFacade() {
        return cargoFacade;
    }

    public TipoContratoFacade getTipoContratoFacade() {
        return tipoContratoFacade;
    }

    public CentroFormacionFacade getCentroFormacionFacade() {
        return centroFormacionFacade;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public List<Cargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<Cargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuariosFacade getFacade() {
        return ejbFacade;
    }
    
    public boolean isContratista(){
        return selected.getIdTipoContrato() == null? false : selected.getIdTipoContrato().getIdTipoContrato() == (short) 2;
    }
    
    public boolean isCarreraAdministrativa(){
        return selected.getIdTipoContrato() == null? false : selected.getIdTipoContrato().getIdTipoContrato() == (short) 2;
    }
    public boolean isPersonaJuridica() {
        return selected.getNaturaleza() == null? false : selected.getNaturaleza().equals("personaJuridica");
    }
    public boolean isPersonaNatural() {
        return selected.getNaturaleza() == null? false : selected.getNaturaleza().equals("personaNatural");
    }
    
    public String prepareCreate() {
        selected = new Usuarios();
        listaCargo = new ArrayList<>();
        listaRoles = new ArrayList<>();
        initializeEmbeddableKey();
        return "/Configuracion/Usuarios/Agregar";
    }
    public String prepareCreateModulo1() {
        selected = new Usuarios();
        listaCargo = new ArrayList<>();
        listaRoles = new ArrayList<>();
        initializeEmbeddableKey();
        return "/modulo1/ContratacionPrestacionDeServicios/Contratistas/AgregarN";
    }
     public String prepareModificarUsuario() {
        return "ModificarTercero";
    }

    public String prepareConsultarUsuario() {
        return "Consultar";
    }

    public String prepareViewUsuario() {
        return "Ver";
    }

    public String prepareListUsuario() {
        return "/Configuracion/Usuarios/Listar";
    }
    public String prepareListUsuario1() {
        return "/modulo1/ContratacionPrestacionDeServicios/ListContratistas";
    }
    public byte generarDv(String nit) {
       int[] nums = {3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71};
       int sum = 0;
       String str = nit;
       for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
           sum += Character.digit(str.charAt(i), 10) * nums[j];
       }
       byte dv = (byte) ((sum % 11) > 1 ? (11 - (sum % 11)) : (sum % 11));
       return dv;
   }
    public String create() {
        try {
            selected.setFechaDeCreacion(new Date());
            selected.setEstado(true);
            selected.setRolList(listaRoles);
            selected.setCargoList(listaCargo);
            selected.setPassword(selected.getNumeroDoc());
            getFacade().create(selected);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("UsuariosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/Configuracion/Usuarios/Listar";

    }

    public String createModuloUno() {
        try {
            selected.setFechaDeCreacion(new Date());
            selected.setEstado(true);
            selected.setRolList(listaRoles);
            selected.setCargoList(listaCargo);
            selected.setPassword(selected.getNumeroDoc());
            getFacade().create(selected);
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

        }

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("UsuariosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/modulo1/ContratacionPrestacionDeServicios/Contratistas/ListContratistas";

    }

    public String update() {
        try{
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("UsuariosUpdated"));
        return "ListContratistas";
        }catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }

    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("UsuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuarios> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Ciudad> getListCiudadesAutoComplete(String query) {
        try {
            return getCiudadFacade().findByNombre(query);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<CentroFormacion> getListCentroFormacionAutoComplete(String query) {
        try {
            return getCentroFormacionFacade().findByNombre(query);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Usuarios getUsuarios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Rol> getListRolSelectOne() {
        return getRolFacade().findAll();
    }

    public List<Cargo> getListCargoSelectOne() {
        return getCargoFacade().findAll();
    }

    public List<Usuarios> getListRazonSocialAutoComplete(String query) {//se agrego metodo de autocompletar
        try {
            return getFacade().findByRazonSocialCompletar(query);
        } catch (Exception ex) {
            Logger.getLogger(ContratosController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public List<Usuarios> getListPrimerNombreAutoComplete(String query) {//se agrego metodo de autocompletar pendiente por revision
        try {
            return getFacade().findByPrimerNombreCompletar(query);
        } catch (Exception ex) {
            Logger.getLogger(ContratosController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public List<Usuarios> getListNumeroDocumentoAutoComplete(String query) {//se agrego metodo de autocompletar
        try {
            return getFacade().findByNumeroDoc(query);
        } catch (Exception ex) {
            Logger.getLogger(ContratosModulo1Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @FacesConverter(forClass = Usuarios.class, value = "usuariosConverter")
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
                return null;
            }
        }

    }

    public void validarDocumento(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        Usuarios UsuarioConsulta = getFacade().findByNumeroDocumento((String) value);

        if (UsuarioConsulta != null) {
            if (selected.getNumeroDoc() != null) {
                if (!Objects.equals(selected.getNumeroDoc(), UsuarioConsulta.getNumeroDoc())) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumentoTitle"),
                            ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumento")));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumentoTitle"),
                        ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumento")));
            }
        } else {
            String documento = (String) value;
            selected.setNumeroDoc(documento);
            selected.setDigito_verificacion(String.valueOf(generarDv(documento)));
        }
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

}
