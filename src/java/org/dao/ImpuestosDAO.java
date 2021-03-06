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
import org.entities.TImpuestos;

/**
 *
 * @author Yo
 */
@Stateless
public class ImpuestosDAO {

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

    public List<TImpuestos> getFindAll() {
        List<TImpuestos> lista = null;
        Query q = em.createNamedQuery("TImpuestos.findAll");
        lista = q.getResultList();
        return lista;

}
     public boolean buscarImpuesto(double monto) {        
        List<TImpuestos> lista = null;     //new ArrayList<Address>();        
        Query q = em.createNamedQuery("TImpuestos.findByMonto");
        q.setParameter("monto", monto);        
        lista = q.getResultList();        
        boolean estado;
        if (lista.isEmpty()) {
            //System.out.println(userName + " not found! ");
            estado = false;
        } else {
            //System.out.println("Found " + emp);
            estado = true;
        }
        return estado;
    }
    public boolean modificaImpuestos(TImpuestos impuestos) {
        try {
            em.merge(impuestos);
            return true;
        } catch (Exception e) {
           return false; 
        }
        
    }
}
