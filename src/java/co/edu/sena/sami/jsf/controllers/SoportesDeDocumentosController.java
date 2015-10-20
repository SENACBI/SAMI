package co.edu.sena.sami.jsf.controllers;

import co.edu.sena.sami.jpa.entities.Contratos;
import co.edu.sena.sami.jpa.entities.SoportesDeDocumentos;
import co.edu.sena.sami.jpa.sessions.ContratosFacade;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil.PersistAction;
import co.edu.sena.sami.jpa.sessions.SoportesDeDocumentosFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;
import org.primefaces.model.UploadedFile;

@Named("soportesDeDocumentosController")
@SessionScoped
public class SoportesDeDocumentosController implements Serializable {

    @EJB
    private ContratosFacade contratosFacade;
    @EJB
    private co.edu.sena.sami.jpa.sessions.SoportesDeDocumentosFacade ejbFacade;
    private List<SoportesDeDocumentos> items = null;
    private SoportesDeDocumentos selected;
    private UploadedFile file;
    private String destination = "C:\\users\\Aprendiz\\docs\\";
//    private String destination = "var\\sami\\sup_cont\\doc\\";

    public SoportesDeDocumentosController() {
    }

    public SoportesDeDocumentos getSelected() {
        return selected;
    }

    public void setSelected(SoportesDeDocumentos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SoportesDeDocumentosFacade getFacade() {
        return ejbFacade;
    }
    public ContratosFacade getContratosFacade() {
        return contratosFacade;
    }

    public String prepareCreate() {
        selected = new SoportesDeDocumentos();
        return "/modulo3/GestionContractual/CreateSoporte";
    }

    public String prepareEdit() {
        return "/modulo3/GestionContractual/EditarSoporte";
    }

    public String prepareView() {
        return "/modulo3/GestionContractual/VerSoporte";
    }

    public String prepareList() {
        recargarLista();
        return "/modulo3/GestionContractual/ListInformes";
    }

    public void recargarLista() {
        items = null;
    }

    public String create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("SoportesDeDocumentosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "ListaSoportes";
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("SoportesDeDocumentosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("SoportesDeDocumentosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SoportesDeDocumentos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    public List<Contratos> getListaContratosSelectOne(){
        return getContratosFacade().findAll();
       
    }
    
     public void validarSoporte(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getFacade().findByIdSoporte((int) value) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Soporte ya existente", "El Soporte ya existe en la base de datos"));
        } else {
            selected.setIdSoporte((int) value);
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

    public SoportesDeDocumentos getSoportesDeDocumentos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SoportesDeDocumentos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SoportesDeDocumentos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SoportesDeDocumentos.class)
    public static class SoportesDeDocumentosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SoportesDeDocumentosController controller = (SoportesDeDocumentosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "soportesDeDocumentosController");
            return controller.getSoportesDeDocumentos(getKey(value));
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
            if (object instanceof SoportesDeDocumentos) {
                SoportesDeDocumentos o = (SoportesDeDocumentos) object;
                return getStringKey(o.getIdSoporte());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SoportesDeDocumentos.class.getName()});
                return null;
            }
        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
     public void upload() {
//        if (file != null) {
//            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
        FacesMessage msg = new FacesMessage("Success! ", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(file.getFileName(), file.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    

//    public void upload(FileUploadEvent event) {
//       
//
//    }
    public void copyFile(String fileName, InputStream in) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + selected.getIdContrato().getNumeroDeContrato()+fileName));
            selected.setUrlDocumento(destination + selected.getIdContrato().getNumeroDeContrato()+fileName);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void openFile(){
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + selected.getUrlDocumento());
        } catch (IOException e) {
                e.printStackTrace();
}
    }

}
