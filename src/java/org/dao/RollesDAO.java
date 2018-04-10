/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.entities.TRolmercado;

/**
 *
 * @author migue
 */
@Stateless
public class RollesDAO {

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
     public List<TRolmercado> getFindAll() {
        List<TRolmercado> lista = null;
        Query q = em.createNamedQuery("TRolmercado.findAll");
        /*List<TRolmercado> lista = new ArrayList<TRolmercado>();
        for(int i = 0 ; i < 2 ; i++) {
            lista.add(new TRolmercado(i, "Juan", "Perez","",null,null,null,null));
        }*/
        lista = q.getResultList();
        return lista;
}
     public boolean modificaroles(TRolmercado roles) {
        try {
            em.merge(roles);
            return true;
        } catch (Exception e) {
           return false; 
        }
}
     }