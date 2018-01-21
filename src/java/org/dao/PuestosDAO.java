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
import org.entities.TPuesto;

/**
 *
 * @author Yo
 */
@Stateless
public class PuestosDAO {

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
    public List<TPuesto> getFindByArrendatario(TArrendatario Arrenda) {
        List<TPuesto> lista = null;     //new ArrayList<Address>();
        System.out.println("DAO 1: ");
        Query q = em.createQuery("select t from TPuesto t where t.ecodArrenda = :Arrenda"); //+ " t. = :an");
        //Query q = em.createNamedQuery("TPuesto.findByECodArrenda");
        //System.out.println("DAO 2: " + codArrenda);
        q.setParameter("Arrenda", Arrenda);
        lista = q.getResultList();
        return lista;
    }
}
