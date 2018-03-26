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
import org.entities.TZona;

/**
 *
 * @author Yo
 */
@Stateless
public class ZonaDAO {
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
    
        public List<TZona> getFindAll() {
        List<TZona> lista = null;
        Query q = em.createNamedQuery("TZona.findAll");
        //Query q = em.createNativeQuery("SELECT * FROM {h-schema}TZona");
        //final Query nativeQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM {h-schema}TODOENTRY");
        //final Object res = nativeQuery.getSingleResult();
        //logger.info("Total count is: " + res);
        //Query q = em.createQuery("SELECT t FROM TZona t");
        //Query q = em.createQuery("SELECT t FROM TTraslados t where t.ecodPuesto = :busqueda order by t.fechaTraslado desc");
        //System.out.println("Entra a FindAll");
        lista = q.getResultList();
        /*for (int i=0; i< lista.size(); i++) {
            System.out.println(lista.get(i).getUbicacion() + "\t" +
                    lista.get(i).getEcodCobrador().getCnomCobra());
        }*/
        return lista;

    }
        public boolean modificaZona(TZona zona) {
            try {
            em.merge(zona);
            //System.out.println("Modifica zona: "+ zona.getEcodCobrador().getEcodCobrador());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
