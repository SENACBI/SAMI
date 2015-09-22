package co.edu.sena.sami.jsf.controllers;

import co.edu.sena.sami.jpa.entities.Avance;
import co.edu.sena.sami.jpa.entities.Estados;
import co.edu.sena.sami.jpa.entities.Prioridades;
import co.edu.sena.sami.jpa.entities.SolicitudServicios;
import co.edu.sena.sami.jpa.entities.TipoDeServicios;
import co.edu.sena.sami.jpa.entities.TipoUsuario;
import co.edu.sena.sami.jpa.entities.Usuarios;
import co.edu.sena.sami.jpa.sessions.AvanceFacade;
import co.edu.sena.sami.jpa.sessions.EstadosFacade;
import co.edu.sena.sami.jpa.sessions.PrioridadesFacade;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil.PersistAction;
import co.edu.sena.sami.jpa.sessions.SolicitudServiciosFacade;
import co.edu.sena.sami.jpa.sessions.TipoDeServiciosFacade;
import co.edu.sena.sami.jpa.sessions.TipoUsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;

@Named("solicitudServiciosController")
@SessionScoped
public class SolicitudServiciosController implements Serializable {

    private Avance avanceActual;

    private Usuarios usuarioActual;

    private List<Prioridades> listPrioridades = null;

    private List<Avance> listAvance = null;

    private List<Estados> listEstado = null;

    private List<SolicitudServicios> listSolicituServiciosByUsuario = null;

    @EJB
    private co.edu.sena.sami.jpa.sessions.SolicitudServiciosFacade ejbFacade;
    private List<SolicitudServicios> items = null;
    private SolicitudServicios selected;

    @EJB
    private AvanceFacade avanceFacade;

    @EJB
    private TipoUsuarioFacade tipoUsarioFacade;

    @EJB
    private PrioridadesFacade prioridadesFacade;

    @EJB
    private EstadosFacade estadosFacade;

    private Date fechaInicio;

    private Date fechaFin;

    public List<SolicitudServicios> getItems() {
        if (items == null) {
            try {
                items = getFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return items;
    }
    public List<SolicitudServicios> solicitudSinAvance() {
        List<SolicitudServicios> csi = new ArrayList<>();
        items = null;
        for (SolicitudServicios c : getItems()) {
            if (c.getAvanceList().isEmpty()) {
                csi.add(c);
            }
        }
        return csi;
    }

    public String obtenerAvanceFA(SolicitudServicios s) {
        List<Avance> a = s.getAvanceList();
        boolean b = false;
        if (a != null && a.size() > 0) {
            b = a.get(a.size() - 1).getFueAtendido();
            if (b) {
                return "Si";
            } else {
                return "No";
            }
        } else {
            return "No";
        }
    }
    
      public String obtenerAvanceFS(SolicitudServicios s) {
        List<Avance> a = s.getAvanceList();
        boolean b = false;
        if (a != null && a.size() > 0) {
            b = a.get(a.size() - 1).getFueSolucionado();
            if (b) {
                return "Si";
            } else {
                return "No";
            }
        } else {
            return "No";
        }
    }

    public List<SolicitudServicios> getListSolicituServiciosByUsuario() {
        if (listSolicituServiciosByUsuario == null) {
            try {
                listSolicituServiciosByUsuario = getFacade().consultaUsuario(usuarioActual);
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listSolicituServiciosByUsuario;
    }

    public List<Estados> getListEstado() {
        if (listEstado == null) {
            try {
                listEstado = getEstadosFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listEstado;
    }

    public List<Prioridades> getListPrioridades() {
        if (listPrioridades == null) {
            try {
                listPrioridades = getPrioridadesFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listPrioridades;
    }

    public List<Prioridades> getListPrioridadesSelectOne() {
        return getPrioridadesFacade().findAll();
    }

    public List<Avance> getListAvance() {
        if (listAvance == null) {
            listAvance = getAvanceFacade().findBySolicitud(selected);
        }
        return listAvance;
    }

    public List<Avance> getListAvance(SolicitudServicios solicitud) {
        return getAvanceFacade().findBySolicitud(solicitud);
    }

    public List<TipoUsuario> getListTipoUsusarioSelectOne() {
        return getTipoUsarioFacade().findAll();
    }

    public List<SolicitudServicios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SolicitudServicios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<SolicitudServicios> getItemsRango() {
        return getFacade().rangoFecha(fechaInicio, fechaFin);
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public AvanceFacade getAvanceFacade() {
        return avanceFacade;
    }

    public TipoUsuarioFacade getTipoUsarioFacade() {
        return tipoUsarioFacade;
    }

    public PrioridadesFacade getPrioridadesFacade() {
        return prioridadesFacade;
    }

    public EstadosFacade getEstadosFacade() {
        return estadosFacade;
    }

    public Avance getAvanceActual() {
        if (avanceActual == null) {
            avanceActual = new Avance();
        }
        return avanceActual;
    }

    public void setAvanceActual(Avance avanceActual) {
        this.avanceActual = avanceActual;
    }

    public SolicitudServiciosController() {
    }

    public SolicitudServicios getSelected() {
        return selected;
    }

    public void setSelected(SolicitudServicios selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SolicitudServiciosFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new SolicitudServicios();
        initializeEmbeddableKey();
        return "/modulo2/mantenimiento/solicitudDeMantenimiento/CreateSolicitud.xhtml";
    }

    public String prepareCreateAvance() {
        avanceActual = new Avance();
        recargarListaAvance();
        return "/modulo2/mantenimiento/solicitudDeMantenimiento/CreateAvance.xhtml";
    }

    public String prepareEdit() {
        return "Edit";

    }

    public String prepareView() {
        return "/modulo2/mantenimiento/solicitudDeMantenimiento/View.xhtml";

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("SolicitudServiciosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("SolicitudServiciosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("SolicitudServiciosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
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

    public SolicitudServicios getSolicitudServicios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public String cargarLista() {
        return "ListSolicitudAvance";
    }

    private void recargarListaAvance() {
        listAvance = null;
    }

    public void cargarUsuario(ActionEvent event) {
        usuarioActual = (Usuarios) event.getComponent().getAttributes().get("usuario");
        listSolicituServiciosByUsuario = null;
    }

    private void recargarListaSolicitud() {
        items = null;
    }

    public String addSolicitudServicios(ActionEvent event) {
        try {
            selected.setIdUsuario((Usuarios) event.getComponent().getAttributes().get("usuario"));
            selected.setFechaSolicitudServicio(new Date());
            getFacade().create(selected);
            recargarListaSolicitud();
            getListSolicituServiciosByUsuario();
            addSuccesMessage("Crear Solocitud", "Solicitud Creada Exitosamente");
            return "View";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return "View";
        }
    }

    public void addAvance() {
        try {
            avanceActual.setFechaAvance(new Date());
            avanceActual.setIdSolicitudServicio(selected);
            getAvanceFacade().create(avanceActual);
            avanceActual = new Avance();
            update();
            recargarListaAvance();
            addSuccesMessage("Crear Avance", "Avance Creado Exitosamente");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
    }

    public String updateAvance() {
        try {
            getAvanceFacade().edit(avanceActual);
            recargarListaAvance();
            addSuccesMessage("Actualizar Solicitud", "Avance Actualizado Exitosamente");
            return "View";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteAvance() {
        try {
            getAvanceFacade().remove(avanceActual);
            recargarListaAvance();
            addSuccesMessage("Borrar Avance", "Avance Borrado Exitosamente");
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";

    }

    @FacesConverter(forClass = SolicitudServicios.class, value = "solicitudConverter")
    public static class SolicitudServiciosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SolicitudServiciosController controller = (SolicitudServiciosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "solicitudServiciosController");
            return controller.getSolicitudServicios(getKey(value));
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
            if (object instanceof SolicitudServicios) {
                SolicitudServicios o = (SolicitudServicios) object;
                return getStringKey(o.getIdSolicitudServicio());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SolicitudServicios.class.getName()});
                return null;
            }
        }

    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage faceMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, faceMsg);
    }

    private void addSuccesMessage(String title, String msg) {
        FacesMessage facesMessage
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
    }

}
