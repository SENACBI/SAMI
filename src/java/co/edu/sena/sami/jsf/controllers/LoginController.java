/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.sami.jsf.controllers;

import co.edu.sena.sami.jpa.entities.Usuarios;
import co.edu.sena.sami.jpa.sessions.UsuariosFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private String username;
    private String password;
    private Usuarios usuarios;
    @EJB
    private UsuariosFacade usuariosFacade;

    public LoginController() {
      
    }

    public String getUsername() {
        return username;
    }

    private UsuariosFacade getUsuariosFacade() {
        return usuariosFacade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return getRequest().getUserPrincipal() != null;
    }

    public Principal getPrincipal() {
        return getRequest().getUserPrincipal();
    }

    private HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object request = externalContext.getRequest();
        return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
    }

    private String getLogueado() {
        return getPrincipal().getName();
    }

    public Usuarios getUserLogueado() {
        return getUsuariosFacade().findByNumeroDocumento(getLogueado());
    }

    public boolean isContratacion() {
        return getRequest().isUserInRole("webModulo1");
    }

    public boolean isMantenimiento() {
        return getRequest().isUserInRole("webModulo2") ;
    }

    public boolean isOnlyMantenimiento() {
        return getRequest().isUserInRole("webModulo2") && !getRequest().isUserInRole("Invitado_Mantenimiento")&& !getRequest().isUserInRole("superMantenimiento");
    }
    public boolean isSuperMantenimiento() {
        return getRequest().isUserInRole("superMantenimiento")||getRequest().isUserInRole("webModulo2")&& !getRequest().isUserInRole("Invitado_Mantenimiento");
    }
    public boolean isSupervisionSuministros() {
        return getRequest().isUserInRole("webModulo3");
    }

    public boolean isGestionTalento() {
        return getRequest().isUserInRole("webModulo4");
    }

    public boolean isGestionDocumental() {
        return getRequest().isUserInRole("webModulo5");
    }

    public boolean isGestionMateriales() {
        return getRequest().isUserInRole("webModulo6");
    }
    
    public boolean isConfiguracion() {
        return getRequest().isUserInRole("webconfiguracion");
    }
    
    public boolean isUsuarioGeneral() {
        return getRequest().isUserInRole("webusuariogeneral");
    }
    
    public boolean isUsuarioInvitadoMantenimiento() {
        return getRequest().isUserInRole("Invitado_Mantenimiento")||getRequest().isUserInRole("webModulo2");
    }

    public String login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);

            usuarios = getUserLogueado();
            limpiar();

//            //Cancela login para usuarios inactivos
            if (!usuarios.getEstado()) {
                logout();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Inactivo", null));
                return "/index";
            }
            //Redirigir a la página de portada
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ " + usuarios.toString(), null));
            return "/sami_inicio";

        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña Invalida", null));
            return "/index";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
            return "/inicio_sami/mensaje_sami.xhtml";
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            return "/index";
        }
    }

    private void limpiar() {
        username = "";
        password = "";
    }
}
