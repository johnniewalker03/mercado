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

/**
 *
 * @author Yo
 */
@Stateless
public class ArrendatarioDAO {
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
    
   public List<TArrendatario> getFindAll() {
        List<TArrendatario> lista = null;
        System.out.println("Entra a getFindAll");
        Query q = em.createNamedQuery("TArrendatario.findAll");
        lista = q.getResultList();
        return lista;
    }
   public List<TArrendatario> getBuscarArrendatario(String busqueda) {
        List<TArrendatario> lista = null;     //new ArrayList<Address>();
        //try {
        Query q = em.createNamedQuery("TArrendatario.findByTodo");
        //q.setParameter("ccodProyecto", "%".concat(empresa.getCnomEmpresa()).concat("%"));
        q.setParameter("dui", "%".concat(busqueda).concat("%"));
        q.setParameter("nombre", "%".concat(busqueda).concat("%"));
        q.setParameter("expediente", "%".concat(busqueda).concat("%"));
        lista = q.getResultList();
        //System.out.println();
        //} catch (Exception e) {
        //  System.out.println(e.getMessage());
        //}
        return lista;
    }
   public boolean getBuscarDUICompleto(String DUI) {
        List<TArrendatario> lista = null;     //new ArrayList<Address>();
        //try {
        Query q = em.createNamedQuery("TArrendatario.findByDuiCompleto");
        //q.setParameter("ccodProyecto", "%".concat(empresa.getCnomEmpresa()).concat("%"));
        q.setParameter("dui", DUI);
        lista = q.getResultList();
        //System.out.println("duis encontrados:" + lista.size());
        //System.out.println("DUI"+DUI);        
        return lista.size()<1;
   }
   public boolean modificaArrendatario(TArrendatario arrendatario) {
        try {
            em.merge(arrendatario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
