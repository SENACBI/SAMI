package co.edu.sena.sami.jsf.controllers;

import co.edu.sena.sami.jpa.entities.Cargo;
import co.edu.sena.sami.jpa.entities.Contratos;
import co.edu.sena.sami.jpa.entities.Polizas;
import co.edu.sena.sami.jpa.entities.Rol;
import co.edu.sena.sami.jpa.entities.Usuarios;
import co.edu.sena.sami.jpa.entities.UsuariosContratos;
import co.edu.sena.sami.jpa.entities.UsuariosContratosPK;
import co.edu.sena.sami.jpa.sessions.CargoFacade;
import co.edu.sena.sami.jpa.sessions.ContratosFacade;
import co.edu.sena.sami.jpa.sessions.PolizasFacade;
import co.edu.sena.sami.jpa.sessions.UsuariosContratosFacade;
import co.edu.sena.sami.jpa.sessions.UsuariosFacade;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil;
import co.edu.sena.sami.jsf.controllers.util.JsfUtil.PersistAction;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "contratosModulo1Controller")
@SessionScoped
public class ContratosModulo1Controller implements Serializable {

    @EJB
    private PolizasFacade polizasFacade;
    @EJB
    private UsuariosFacade usuariosFacade;
    @EJB
    private UsuariosContratosFacade usuariosContratosFacade;
    @EJB
    private co.edu.sena.sami.jpa.sessions.ContratosFacade ejbFacade;
    @EJB
    private CargoFacade cargoFacade;
    
    private List<Contratos> items = null;
    private Contratos selected;
    private Polizas selectedPolizas;
    private UsuariosContratos selectedUsuariosContratos;
    private Usuarios selectedUsuarios;
    private Rol selectedRol;
    private Usuarios selectedSupervisor;
    private UsuariosContratos selectedSupervisorContratos;
    private List<UsuariosContratos> listaUsuariosContratos;

    public ContratosModulo1Controller() {
    }

    public List<UsuariosContratos> getListaUsuariosContratos() {
         if (listaUsuariosContratos == null) {
            listaUsuariosContratos = getUsuariosContratosFacade().findAll();
         }
        return listaUsuariosContratos;
    }
    
    public Polizas getSelectedPolizas() {
        return selectedPolizas;
    }

    public void setSelectedPolizas(Polizas selectedPolizas) {
        this.selectedPolizas = selectedPolizas;
    }

    public Usuarios getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(Usuarios selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }
    
    public CargoFacade getCargoFacade() {
        return cargoFacade;
    }
    
    public void setCargoFacade(CargoFacade cargoFacade) {
        this.cargoFacade = cargoFacade;
    }

    public Rol getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(Rol selectedRol) {
        this.selectedRol = selectedRol;
    }

    public UsuariosContratos getSelectedUsuariosContratos() {
        return selectedUsuariosContratos;
    }
    
    public Usuarios getSelectedSupervisor() {
        return selectedSupervisor;
    }

    public void setSelectedSupervisor(Usuarios selectedSupervisor) {
        this.selectedSupervisor = selectedSupervisor;
    }
    
     public UsuariosContratos getSelectedSupervisorContratos() {
        return selectedSupervisorContratos;
    }

    public void setSelectedSupervisorContratos(UsuariosContratos selectedSupervisorContratos) {
        this.selectedSupervisorContratos = selectedSupervisorContratos;
    }

    public Usuarios getUsuariosByContratos(Contratos contrato) {
        if (getUsuariosContratosFacade().findByIdContrato(contrato) == null) {
            return new UsuariosContratos().getUsuarios();
        } else {
            return getUsuariosContratosFacade().findByIdContrato(contrato).getUsuarios();
        }
    }

  public List<UsuariosContratos> getUsuariosSupervisores(Contratos contrato) {
        if (contrato != null) {
            return getUsuariosContratosFacade().findByIdContratoSupervisor(contrato);
        } else {
            return null;
        }
    }

    public String obtenerCargo(Rol r) {
        Cargo c = new Cargo();
        switch (r.getIdRol()) {
            case 1:
                c = getCargoFacade().find(15);
                break;
            case 2:
                c = getCargoFacade().find(13);
                break;
            default:
                return null;
        }
        return c.getNombreCargo();
    }

    public UsuariosContratos getSupervisoresByRol(Contratos c) {
        if (getUsuariosContratosFacade().findByIdRol(c) == null) {
            return new UsuariosContratos();
        } else {
            return getUsuariosContratosFacade().findByIdRol(c);
        }
    }

     public UsuariosContratos getContratistasByRol(Contratos c) {
        if (getUsuariosContratosFacade().findByIdRol(c) == null) {
            return new UsuariosContratos();
        } else {
            return getUsuariosContratosFacade().findByIdRol(c);
        }
    }
    
    public void setSelectedUsuariosContratos(UsuariosContratos selectedUsuariosContratos) {
        this.selectedUsuariosContratos = selectedUsuariosContratos;
    }

    public PolizasFacade getPolizasFacade() {
        return polizasFacade;
    }

    public UsuariosFacade getUsuariosFacade() {
        return usuariosFacade;
    }
    

    public UsuariosContratosFacade getUsuariosContratosFacade() {
        return usuariosContratosFacade;
    }

    public Contratos getSelected() {
        return selected;
    }

    public void setSelected(Contratos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContratosFacade getFacade() {
        return ejbFacade;
    }

    public String prepareCreate() {
        selected = new Contratos();
        selectedPolizas = new Polizas();
        selectedUsuariosContratos = new UsuariosContratos();
        selectedUsuariosContratos.setUsuariosContratosPK(new UsuariosContratosPK());

        selectedUsuarios = new Usuarios();
        selectedRol = new Rol();
        selectedSupervisor = new Usuarios();
        selectedSupervisorContratos = new UsuariosContratos();
        selectedSupervisorContratos.setUsuariosContratosPK(new UsuariosContratosPK());
        //initializeEmbeddableKey();
        return "/modulo1/ContratacionPrestacionDeServicios/Contratos/CrearContrato";
    }

    public String prepareEdit() {
        return "/modulo1/ContratacionPrestacionDeServicios/Contratos/EditContrato";
    }
    public String prepareView() {
        return "/modulo1/ContratacionPrestacionDeServicios/Contratos/ViewContrato";
    }

    public String create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/resources/Bundle").getString("ContratosCreated"));
        createPolizas();
        selectedUsuariosContratos.setContratos(selected);
        selectedUsuariosContratos.setPolizas(selectedPolizas);
        selectedUsuariosContratos.setRol(new Rol(1));
        selectedUsuariosContratos.setUsuarios(selectedUsuarios);
        
        selectedSupervisorContratos.setContratos(selected);
        selectedSupervisorContratos.setPolizas(selectedPolizas);
        selectedSupervisorContratos.setRol(new Rol(2));
        selectedSupervisorContratos.setUsuarios(selectedSupervisor);
       
        selectedSupervisorContratos.getUsuariosContratosPK().setIdContrato(selectedSupervisorContratos.getContratos().getIdContrato());
        selectedSupervisorContratos.getUsuariosContratosPK().setIdRol(selectedSupervisorContratos.getRol().getIdRol());
        selectedSupervisorContratos.getUsuariosContratosPK().setIdUsuario(selectedSupervisorContratos.getUsuarios().getIdUsuario());
        selectedSupervisorContratos.getUsuariosContratosPK().setNumeroDePoliza(selectedSupervisorContratos.getPolizas().getNumeroDePoliza());
        
        selectedUsuariosContratos.getUsuariosContratosPK().setIdContrato(selectedUsuariosContratos.getContratos().getIdContrato());
        selectedUsuariosContratos.getUsuariosContratosPK().setIdRol(selectedUsuariosContratos.getRol().getIdRol());
        selectedUsuariosContratos.getUsuariosContratosPK().setIdUsuario(selectedUsuariosContratos.getUsuarios().getIdUsuario());
        selectedUsuariosContratos.getUsuariosContratosPK().setNumeroDePoliza(selectedUsuariosContratos.getPolizas().getNumeroDePoliza());
        try {
            getUsuariosContratosFacade().create(selectedUsuariosContratos);
            getUsuariosContratosFacade().create(selectedSupervisorContratos);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        return "/modulo1/ContratacionPrestacionDeServicios/Contratos/ListContrato";
    }

    public void createPolizas() {
        try {
            getPolizasFacade().create(selectedPolizas);
        } catch (Exception e) {

        }
    }
    public void createUsers() {
        try {
            getUsuariosFacade().create(selectedUsuarios);
        } catch (Exception e) {

        }
    }

    public String prepareListContratosModulo1() {
        return "/modulo1/ContratacionPrestacionDeServicios/Contratos/ListContrato";
    }

//  
     public String update() {
        try{
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/resources/Bundle").getString("ContratosUpdated"));
        return "ListContrato";
        }catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/resources/Bundle").getString("ContratosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Contratos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                switch (persistAction) { //aqui se acomodo el codigo para que guardara en la tabla intermedia usuario roles
                    case CREATE:
                        getFacade().create(selected);
                        break;
                    case UPDATE:
                        getFacade().edit(selected);
                        break;
                    case DELETE:
                        getFacade().remove(selected);
                        break;
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

    public Contratos getContratos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Contratos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contratos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Contratos.class)
    public static class ContratosModulo1ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratosModulo1Controller controller = (ContratosModulo1Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratosModulo1Controller");
            return controller.getContratos(getKey(value));
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
            if (object instanceof Contratos) {
                Contratos o = (Contratos) object;
                return getStringKey(o.getIdContrato());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contratos.class.getName()});
                return null;
            }
        }

    }
     private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void validarContrato(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getFacade().findByNumeroDeContrato((String) value) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contrato ya existente", "El contrato ya existe en la base de datos !!!!"));
        } else {
            selected.setNumeroDeContrato((String) value);
        }

    }
    //boton importar los datos
    //boton para importar los datos//
    private UploadedFile file;
    private String destination = "C:\\temp\\Archivos";

//
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
// if (file != null) {
// FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
// FacesContext.getCurrentInstance().addMessage(null, message);
        FacesMessage msg = new FacesMessage("Success! ",
                file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
// Do what you want with the file 
        try {
            copyFile(file.getFileName(), file.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
// }
    }

// public void upload(FileUploadEvent event) {
// 
//
// }
    public void copyFile(String fileName, InputStream in) {
        try {

// write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

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
    
    //Metodo para la validacion de un numero de contrato repetido
     public void validarContratoModuloUno(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        if (getFacade().findByNumeroDeContrato((String) value) != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contrato ya existente", "El contrato ya existe en la base de datos"));
        } else {
            selected.setNumeroDeContrato((String) value);
        }

    }
    
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        wb.removeSheetAt(0);
       
        HSSFSheet sheet = wb.createSheet("datos");
        List<UsuariosContratos> selectUsuariosContratos = getUsuariosContratosFacade().findAll();
        HSSFRow header = sheet.createRow(0);
        HSSFCell cellHeader = header.createCell(0);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_idContrato"));
        cellHeader = header.createCell(1);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaSuscripcion"));
        cellHeader = header.createCell(2);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaInicioContrato"));
        cellHeader = header.createCell(3);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_razonSocial"));
        cellHeader = header.createCell(4);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_numeroDoc"));
        cellHeader = header.createCell(5);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_objetoContractual"));
        cellHeader = header.createCell(6);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_valorContrato"));
        cellHeader = header.createCell(7);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_direccionUsu"));
        cellHeader = header.createCell(8);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListSexoTitle_nombreSexo"));
        cellHeader = header.createCell(9);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_telefonoPrincipalUsu"));
        cellHeader = header.createCell(10);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_telefonoAlternoUsu"));
        cellHeader = header.createCell(11);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_email"));
        cellHeader = header.createCell(12);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_fechaNac"));
        cellHeader = header.createCell(13);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListEpsTitle_nombreEps"));
        cellHeader = header.createCell(14);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListFondoDePensionesTitle_nombreFondoPensiones"));
        cellHeader = header.createCell(15);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_crp"));
        cellHeader = header.createCell(16);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_plazoMeses"));
        cellHeader = header.createCell(17);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaInicioContrato"));
        cellHeader = header.createCell(18);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaFinContrato"));
        cellHeader = header.createCell(19);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_razonSocial"));
        cellHeader = header.createCell(20);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_razonSocial"));
        cellHeader = header.createCell(21);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_idCuentasBancarias"));
        cellHeader = header.createCell(22);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListBancosTitle_nombreDeBanco"));
        cellHeader = header.createCell(23);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListTipoDeCuentaTitle_nombreTipoDeCuenta"));

        /*Estilos para que quede con un color de fondo*/
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
        /*Formatos para las fechas */
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyleDate = wb.createCellStyle();
        CellStyle cellStyleDateMes = wb.createCellStyle();
        CellStyle cellStyleTime = wb.createCellStyle();
        CellStyle cellStyleDateTime = wb.createCellStyle();
        cellStyleDate.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
        cellStyleDateMes.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/MMMM/yyyy"));
        cellStyleTime.setDataFormat(
                createHelper.createDataFormat().getFormat("h:mm:SS"));
        cellStyleDateTime.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yyyy h:mm:SS"));

        /* Llenar el reporte */
        for (int rowNum = 1; rowNum <= selectUsuariosContratos.size(); rowNum++) {
            HSSFRow row = sheet.createRow(rowNum);

            UsuariosContratos item = selectUsuariosContratos.get(rowNum-1);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(item.getContratos().getNumeroDeContrato());
            cell = row.createCell(1);
            cell.setCellValue(item.getContratos().getFechasuscripcion());
            cell.setCellStyle(cellStyleDate);
            cell = row.createCell(2);
            cell.setCellValue(item.getContratos().getFechaInicioContrato());
            cell.setCellStyle(cellStyleDateMes);
            cell = row.createCell(3);
            cell.setCellValue(item.getUsuarios().getRazonSocial()+ item.getUsuarios().getSegundoNombre() + item.getUsuarios().getPrimerApellido() + item.getUsuarios().getSegundoApellido()
                    == null? "":item.getUsuarios().getRazonSocial() + " " + item.getUsuarios().getSegundoNombre() + " " + item.getUsuarios().getPrimerApellido() + " " + item.getUsuarios().getSegundoApellido());
            cell = row.createCell(4);
            cell.setCellValue(item.getUsuarios().getNumeroDoc());
            cell = row.createCell(5);
            cell.setCellValue(item.getContratos().getObjetoContractual()== null? "":item.getContratos().getObjetoContractual());
            cell = row.createCell(6);
            cell.setCellValue(item.getContratos().getValorInicialContrato());
            cell = row.createCell(7);
            cell.setCellValue(item.getUsuarios().getDireccionUsu());
            cell = row.createCell(8);
            cell.setCellValue(item.getUsuarios().getIdSexo().getNombreSexo());
            cell = row.createCell(9);
            cell.setCellValue(item.getUsuarios().getTelefonoPrincipalUsu() == null? "":item.getUsuarios().getTelefonoPrincipalUsu());
            cell = row.createCell(10);
            cell.setCellValue(item.getUsuarios().getTelefonoAlternoUsu() == null? "":item.getUsuarios().getTelefonoAlternoUsu());
            cell = row.createCell(11);
            cell.setCellValue(item.getUsuarios().getEmail());
            cell = row.createCell(12);
            cell.setCellValue(item.getUsuarios().getFechaNac());
            cell.setCellStyle(cellStyleDate);
            cell = row.createCell(13);
            cell.setCellValue(item.getUsuarios().getIdEps().getNombreEps() == null? "":item.getUsuarios().getIdEps().getNombreEps());
            cell = row.createCell(14);
            cell.setCellValue(item.getUsuarios().getIdFondoPensiones().getNombreFondoPensiones());
            cell = row.createCell(15);
            cell.setCellValue(item.getUsuarios().getIdCcf().getNombreCcf() == null? "":item.getUsuarios().getIdCcf().getNombreCcf());
            cell = row.createCell(16);
            cell.setCellValue(item.getContratos().getFechaInicioContrato());
            cell.setCellStyle(cellStyleDate);
            cell = row.createCell(17);
            cell.setCellValue(item.getContratos().getFechaFinContrato());
            cell.setCellStyle(cellStyleDate);
            cell = row.createCell(18);
            cell.setCellValue(item.getUsuarios().getRazonSocial() == null? "":item.getUsuarios().getRazonSocial());
            cell = row.createCell(19);
            cell.setCellValue(item.getUsuarios().getRazonSocial() == null? "":item.getUsuarios().getRazonSocial());
            cell = row.createCell(20);
            cell.setCellValue(item.getUsuarios().getNumCuentaBanco() == null? "":item.getUsuarios().getNumCuentaBanco());
            cell = row.createCell(21);           
            cell.setCellValue(item.getUsuarios().getIdBanco() == null? "":item.getUsuarios().getIdBanco().getNombreDeBanco());
            cell = row.createCell(22);
            cell.setCellValue(item.getUsuarios().getNombreTipoCuenta() == null? "":item.getUsuarios().getNombreTipoCuenta());

        }
    }
    
     //--------------------------------------------------------------------------------------------------------------------
    /*segundo reporte*/
    public void postProcessXLS1(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        wb.removeSheetAt(0);

        HSSFSheet sheet = wb.createSheet("datos");
        List<UsuariosContratos> selectUsuariosContratos = getUsuariosContratosFacade().findAll();
        HSSFRow header = sheet.createRow(0);
        HSSFCell cellHeader = header.createCell(0);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_numeroDeContrato"));
        cellHeader = header.createCell(1);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaSuscripcion"));
        cellHeader = header.createCell(2);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaInicioContrato"));
        cellHeader = header.createCell(3);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListPolizasTitle_valorDePoliza"));
//         cellHeader = header.createCell(3);
//        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString(""));
//         cellHeader = header.createCell(3);
//        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString(""));
        cellHeader = header.createCell(4);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_razonSocial"));
        cellHeader = header.createCell(5);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_segundoNombre"));
        cellHeader = header.createCell(6);//=============
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_razonSocial"));
        cellHeader = header.createCell(7);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_numeroDoc"));
        cellHeader = header.createCell(8);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListUsuariosTitle_fechaExpedicionDoc"));
        cellHeader = header.createCell(9);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaDeEstudioPrevio"));
        cellHeader = header.createCell(10);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaAnexo"));
        cellHeader = header.createCell(11);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaDeAutorizacion"));
        cellHeader = header.createCell(12);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_fechaDeIdoneidad"));
        cellHeader = header.createCell(13);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_idCuentasBancarias"));
        cellHeader = header.createCell(14);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListBancosTitle_nombreDeBanco"));
        cellHeader = header.createCell(15);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_objetoContractual"));
        cellHeader = header.createCell(16);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_obligaciones"));
        cellHeader = header.createCell(17);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_primerPago"));
        cellHeader = header.createCell(18);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_ultimoPago"));
        cellHeader = header.createCell(19);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_numeroDeMensualidades"));
        cellHeader = header.createCell(20);
        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString("ListContratosTitle_valorMensual"));
//        cellHeader = header.createCell(18);
//        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString(""));
        //         cellHeader = header.createCell(8);
//        cellHeader.setCellValue(ResourceBundle.getBundle("/resources/Bundle").getString(""));
        
        /*Estilos para que quede con un color de fondo*/
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }

        /*Formatos para las fechas */
        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyleDate = wb.createCellStyle();
        CellStyle cellStyleTime = wb.createCellStyle();
        CellStyle cellStyleDateTime = wb.createCellStyle();
        cellStyleDate.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy"));
        cellStyleTime.setDataFormat(
                createHelper.createDataFormat().getFormat("h:mm:SS"));
        cellStyleDateTime.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yyyy h:mm:SS"));

        /* Llenar el reporte */
        for (int rowNum = 1; rowNum <= selectUsuariosContratos.size(); rowNum++) {
            HSSFRow row = sheet.createRow(rowNum);


            UsuariosContratos item = selectUsuariosContratos.get(rowNum - 1);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(item.getContratos().getNumeroDeContrato());
            cell = row.createCell(1);
            cell.setCellValue(item.getContratos().getFechasuscripcion());
            cell = row.createCell(2);
            cell.setCellValue(item.getContratos().getFechaInicioContrato());
            cell = row.createCell(3);
            cell.setCellValue(item.getPolizas().getValorDePoliza());
//            cell = row.createCell(3);//=============
//            cell.setCellValue(item.getContratos().get());
//            cell = row.createCell(3);//================
//            cell.setCellValue(item.getContratos().getValorDePoliza());
            cell = row.createCell(4);
            cell.setCellValue(item.getUsuarios().getRazonSocial()+ " " +item.getUsuarios().getSegundoNombre());
            cell = row.createCell(5);
            cell.setCellValue(item.getUsuarios().getPrimerApellido()+ " " +item.getUsuarios().getSegundoApellido());
            cell = row.createCell(6);
            cell.setCellValue(item.getUsuarios().getRazonSocial()+ " " +item.getUsuarios().getSegundoNombre()+ " " +item.getUsuarios().getPrimerApellido()+ " " +item.getUsuarios().getSegundoApellido());
            cell = row.createCell(7);
            cell.setCellValue(item.getUsuarios().getNumeroDoc());
            cell = row.createCell(8);
            cell.setCellValue(item.getUsuarios().getFechaExpedicionDoc());
            cell = row.createCell(9);
            cell.setCellValue(item.getContratos().getFechaDeEstudioPrevio());
            cell = row.createCell(10);
            cell.setCellValue(item.getContratos().getFechaEstudioMercado());
            cell = row.createCell(11);
            cell.setCellValue(item.getContratos().getFechaDeAutorizacion());
            cell = row.createCell(12);
            cell.setCellValue(item.getContratos().getFechaDeIdoneidad());
            cell = row.createCell(13);
            cell.setCellValue(item.getUsuarios().getNumCuentaBanco()== null? "":item.getUsuarios().getNumCuentaBanco());
            cell = row.createCell(14);
            cell.setCellValue(item.getUsuarios().getIdBanco() == null? "":item.getUsuarios().getIdBanco().getNombreDeBanco());
            cell = row.createCell(15);
            cell.setCellValue(item.getContratos().getObjetoContractual()== null? "":item.getContratos().getObjetoContractual());
            cell = row.createCell(16);
            cell.setCellValue(item.getContratos().getObligaciones());
            cell = row.createCell(17);
            cell.setCellValue(item.getContratos().getPrimerPago());
            cell = row.createCell(18);
            cell.setCellValue(item.getContratos().getUltimoPago());
            cell = row.createCell(19);
            cell.setCellValue(item.getContratos().getNumeroDeMensualidades());
            cell = row.createCell(20);
            cell.setCellValue(item.getContratos().getValorMensual());
            cell = row.createCell(21);
            cell.setCellValue(item.getPolizas().getIdAseguradora().toString());
                           //            cell = row.createCell(10);
//          cell.setCellValue(item.get().get());
 
            
            
        }
    }

}
