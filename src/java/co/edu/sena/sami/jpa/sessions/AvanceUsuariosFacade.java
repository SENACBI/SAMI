/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.sami.jpa.sessions;

import co.edu.sena.sami.jpa.entities.AvanceUsuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adsim
 */
@Stateless
public class AvanceUsuariosFacade extends AbstractFacade<AvanceUsuarios> {
    @PersistenceContext(unitName = "SAMIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvanceUsuariosFacade() {
        super(AvanceUsuarios.class);
    }
    
}