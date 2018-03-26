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
import org.entities.TMejoras;
import org.entities.TMejorasdetalle;

/**
 *
 * @author Yo
 */
@Stateless
public class MejorasDetalleDAO {
    
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
    
        public List<TMejorasdetalle> getFindAll() {
        List<TMejorasdetalle> lista = null;
        Query q = em.createNamedQuery("TMejorasdetalle.findAll");        
        lista = q.getResultList();
        /*for (int i=0; i< lista.size(); i++) {
            System.out.println(lista.get(i).getUbicacion() + "\t" +
                    lista.get(i).getEcodCobrador().getCnomCobra());
        }*/
        return lista;

    }
        public List<TMejorasdetalle> getFindPuesto(TMejoras mejora) {
        List<TMejorasdetalle> lista = null;
        Query q = em.createNamedQuery("TMejorasdetalle.findPuestos");
        q.setParameter("mejora", mejora);                
        lista = q.getResultList();
        /*for (int i=0; i< lista.size(); i++) {
            System.out.println(lista.get(i).getUbicacion() + "\t" +
                    lista.get(i).getEcodCobrador().getCnomCobra());
        }*/
        return lista;

    }
}
