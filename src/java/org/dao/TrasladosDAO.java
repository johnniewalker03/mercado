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
import org.entities.TPuesto;
import org.entities.TTraslados;

/**
 *
 * @author Yo
 */
@Stateless
public class TrasladosDAO {
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
    
        public List<TTraslados> getFindAll() {
        List<TTraslados> lista = null;
        Query q = em.createNamedQuery("TTraslados.findAll");
        lista = q.getResultList();
        return lista;
    }
        public List<TTraslados> getBuscarTraslados(TPuesto puesto) {
        List<TTraslados> lista = null;     //new ArrayList<Address>();
        //try {
        //SELECT * FROM `t_traslados` where EcodPuesto = 2 order by FechaTraslado desc limit 1;        
        //Query q = em.createQuery("select t from TPuesto t where t.ecodArrenda = :Arrenda"); //+ " t. = :an");
        Query q = em.createQuery("SELECT t FROM TTraslados t where t.ecodPuesto = :busqueda order by t.fechaTraslado desc"); //+ " t. = :an"); 
        //Query q = em.createNativeQuery("SELECT * FROM t_traslados where EcodPuesto = ? order by FechaTraslado desc, limit 1"); //+ " t. = :an"); 
        /*Query q = em.createNativeQuery("SELECT iva FROM tdescuento_empleado t WHERE "
                    + "MONTH(t.fecha_vigencia) <= ? and YEAR(t.fecha_vigencia) <= ? order by t.fecha_vigencia desc limit 1");
            q.setParameter(1, mesDescuento(fecha, "mes"));
            q.setParameter(2, mesDescuento(fecha, "anio"));*/
        //q.setParameter(1, busqueda);
        System.out.println("DAO 2: ");
        q.setParameter("busqueda", puesto);
        lista = q.setMaxResults(1).getResultList();
        return lista;
    }
      public List<TTraslados> getBuscarPorCampos(String busqueda) {
        List<TTraslados> lista = null;     //new ArrayList<Address>();
        //try {
          System.out.println("Entra a busqueda por campos");
        Query q = em.createNamedQuery("TTraslados.findByTodo");        
        q.setParameter("puesto", "%".concat(busqueda).concat("%"));        
        lista = q.getResultList();
        //} catch (Exception e) {
        //  System.out.println(e.getMessage());
        //}
        return lista;
    }
}
