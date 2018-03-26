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
import org.entities.TDepartamentos;

/**
 *
 * @author migue
 */
@Stateless
public class DepartamentosDAO {
    @PersistenceContext(unitName = "mercadoPU")
    private EntityManager em;
    public List<TDepartamentos> getFindByTodo() {
        List<TDepartamentos> lista = null;     //new ArrayList<Address>();
        //Query q = em.createQuery("select t from TPuesto t where t.ecodArrenda = :Arrenda"); //+ " t. = :an");
        Query q = em.createNamedQuery("TDepartamentos.findAll");
        //System.out.println("DAO 2: " + codArrenda);
        //q.setParameter("idDepto", Departamento);
        lista = q.getResultList();
        return lista;
    }
    public String nombreDepartamento(Integer id) {
        String result;
        try {
            Query q = em.createQuery("SELECT s.departamento FROM TDepartamentos s WHERE s.id='" + id + "'");
            result = q.getSingleResult().toString();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
    public Integer departamentoID(String depto) {
        Integer result;
        try {
            Query q = em.createQuery("SELECT s.id FROM TDepartamentos s WHERE s.departamento ='" + depto + "'");
            //Query q = em.createNamedQuery("TDepartamentos.findByDepartamento");
            //Query query = em.createQuery("DELETE FROM Employee emp WHERE emp.empSalary =  ? 1");
            //query.setParameter(1, 6000);
            //q.setParameter("departamento", depto);
            result = Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception e) {
            result = null;
            System.out.println("exception: " + e.getMessage());
        }
        return result;
    }
}
