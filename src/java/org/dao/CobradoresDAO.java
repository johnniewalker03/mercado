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
import org.entities.TCobrador;

/**
 *
 * @author Yo
 */
@Stateless
public class CobradoresDAO {
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
    
        public List<TCobrador> getFindAll() {
        List<TCobrador> lista = null;
        Query q = em.createNamedQuery("TCobrador.findAll");
        lista = q.getResultList();
        /*for (int i=0; i<lista.size(); i++){
            System.out.println("item " + i +" "+lista.get(i).getCnomCobra());
        }*/
        return lista;

    }
        public boolean modificaCobrador(TCobrador cobrador) {
        try {
            em.merge(cobrador);
            return true;
        } catch (Exception e) {
            return false;
        }
        }
}
