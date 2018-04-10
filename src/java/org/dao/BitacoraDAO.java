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
import org.entities.TBitacora;

/**
 *
 * @author Yo
 */
@Stateless
public class BitacoraDAO {

    @PersistenceContext(unitName = "mercadoPU")
    private EntityManager em;

    public void persist(Object object) {
        try {
            em.persist(object);
            //System.out.println("datos guardados exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TBitacora> getFindAll() {
        List<TBitacora> lista = null;
        Query q = em.createNamedQuery("TBitacora.findAll");
        lista = q.getResultList();
        /*for (int i=0; i<lista.size(); i++){
         System.out.println("item " + i +" "+lista.get(i).getCnomCobra());
         }*/
        return lista;

    }

    public List<TBitacora> getFindByUsuario(String busqueda) {
        List<TBitacora> lista = null;
        Query q = em.createNamedQuery("TBitacora.findByUsuario");
        q.setParameter("usuario", "%" + busqueda + "%");
        lista = q.getResultList();
        /*for (int i=0; i<lista.size(); i++){
         System.out.println("item " + i +" "+lista.get(i).getCnomCobra());
         }*/
        return lista;

    }
}
