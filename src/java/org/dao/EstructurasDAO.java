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
import org.entities.TEstructura;

/**
 *
 * @author Yo
 */
@Stateless
public class EstructurasDAO {
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
    
        public List<TEstructura> getFindByEstructura(TEstructura Puesto) {
        List<TEstructura> lista = null;
        Query q = em.createQuery("select t from TEstructura t where t.ecodPuesto = :puesto"); //+ " t. = :an");
        q.setParameter("puesto", Puesto);
        lista = q.getResultList();
        return lista;

    }
}
