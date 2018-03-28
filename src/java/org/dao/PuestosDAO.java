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
            System.out.println("mensaje " + e.getMessage() );
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

    public List<TPuesto> getBuscarPuesto(String busqueda) {
        List<TPuesto> lista = null;     //new ArrayList<Address>();
        //try {
        Query q = em.createNamedQuery("TPuesto.findByDatos");
        //System.out.println("Fecha " + busqueda);
        //Query q = em.createQuery("SELECT t FROM TPuesto t WHERE SUBSTRING(t.matriculaPagada, 1, 4) LIKE :fecha");
        //Query q = em.createNativeQuery("SELECT * FROM t_puesto p WHERE YEAR(p.fechaPago)=?");        
        q.setParameter("nombre", "%".concat(busqueda).concat("%"));
        q.setParameter("puesto", "%".concat(busqueda).concat("%"));
        q.setParameter("dui", "%".concat(busqueda).concat("%"));
        q.setParameter("fecha", "%".concat(busqueda).concat("%"));
        //q.setParameter(1, busqueda);
        lista = q.getResultList();
        //System.out.println();
        //} catch (Exception e) {
        //  System.out.println(e.getMessage());
        //}
        return lista;
    }

    public List<TPuesto> getFindByTodo() {
        List<TPuesto> lista = null;     //new ArrayList<Address>();                
        Query q = em.createNamedQuery("TPuesto.findAll");
        //System.out.println("DAO 2: " + codArrenda);
        lista = q.getResultList();
        return lista;
    }

    public boolean getPuestoUbicacion(String numPuesto, String ubicacion) {
        List<TPuesto> lista = null;     //new ArrayList<Address>();        
        Query q = em.createNamedQuery("TPuesto.findByPuestoUbicacion");
        q.setParameter("puesto", numPuesto);
        q.setParameter("ubicacion", ubicacion);
        lista = q.getResultList();
        System.out.println("Cantidad de registros " + lista.size());
        System.out.println("Is empty " + lista.isEmpty());
        /*for (int i=0; i<=lista.size();i++){
            System.out.println("Num de puesto " + lista.get(i).getNumPuesto());
            System.out.println("ubicacion " + lista.get(i).getUbicacion());
        }*/
        return lista.isEmpty();
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
