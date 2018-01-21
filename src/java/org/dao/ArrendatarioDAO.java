/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.entities.TArrendatario;

/**
 *
 * @author Yo
 */
@Stateless
public class ArrendatarioDAO {
    @PersistenceContext(unitName = "mercadoPU")
    private EntityManager em;

    public void persist(Object object) {
        try {
            em.persist(object);
            System.out.println("datos guardados exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public List<TArrendatario> getFindAll() {
        List<TArrendatario> lista = null;
        System.out.println("Entra a getFindAll");
        Query q = em.createNamedQuery("TArrendatario.findAll");
        lista = q.getResultList();
        return lista;

    }
}
