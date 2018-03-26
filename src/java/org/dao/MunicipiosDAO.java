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
import org.entities.TMunicipios;

/**
 *
 * @author migue
 */
@Stateless
public class MunicipiosDAO {
    @PersistenceContext(unitName = "mercadoPU")
    private EntityManager em;
    public List<TMunicipios> getFindByMunicipios(TDepartamentos cod_depto) {
        List<TMunicipios> lista = null;     //new ArrayList<Address>();
        System.out.println("DAO 1: " + cod_depto.getDepartamento());
        //Query q = em.createQuery("select t from TPuesto t where t.ecodArrenda = :Arrenda"); //+ " t. = :an");
        Query q = em.createNamedQuery("TMunicipios.findByIdDepto");
        //System.out.println("DAO 2: " + codArrenda);
        q.setParameter("idDepto", cod_depto);
        lista = q.getResultList();
        return lista;
    }    
    public List<TMunicipios> getFindByMunicipios() {
        List<TMunicipios> lista = null;     //new ArrayList<Address>();
        System.out.println("DAO 1: ");
        //Query q = em.createQuery("select t from TPuesto t where t.ecodArrenda = :Arrenda"); //+ " t. = :an");
        Query q = em.createNamedQuery("TMunicipios.findAll");
        lista = q.getResultList();
        return lista;
    }
}
