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
    
    //select p.*, t2.NombreCompleto, FechaTraslado, t1.NombreCompleto  from t_puesto p, t_traslados tr, t_arrendatario t1, t_arrendatario t2 where Correlativo = EcodPuesto and EcodArrendaAnte = t1.EcodArrenda and EcodArrendaNuevo = t2.EcodArrenda
            
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
    public List<TPuesto> getFindByTodo() {
        List<TPuesto> lista = null;     //new ArrayList<Address>();                
        Query q = em.createNamedQuery("TPuesto.findAll");
        //System.out.println("DAO 2: " + codArrenda);
        lista = q.getResultList();
        return lista;
    }
    // Metodo para modificar puesto
    public boolean modificaPuesto(TPuesto puesto) {
        try {
            System.out.println("Puestosss" + puesto.getUbicacion());
            em.merge(puesto);
            return true;
        } catch (Exception e) {
            System.out.println("false");
            return false;
        }
    }   
}
