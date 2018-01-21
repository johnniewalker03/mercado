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
import org.entities.Usuarios;

/**
 *
 * @author Yo
 */
@Stateless
public class UsuariosDAO {
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
// busqueda a traves del metodo find de la clase EntityManager

    public boolean buscaUsuario(String userName) {
        //Tusuario usuario;
        //usuario = em.find(Tusuario.class, userName);
        List<Usuarios> lista = null;     //new ArrayList<Address>();
        System.out.println("DAO 1");
        Query q = em.createNamedQuery("Usuarios.findByUser");
        q.setParameter("user", userName);        
        lista = q.getResultList();
        System.out.println("DAO 2");
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

    public List<Usuarios> buscaLogin(String userName, String pass) {
        //Tusuario usuario;
        //usuario = em.find(Tusuario.class, userName);
        Integer codigo = 0;
        List<Usuarios> lista = null;     //new ArrayList<Address>();
        //pass = StringMD.getStringMessageDigest(pass, StringMD.MD5);
        Query q = em.createNamedQuery("Usuarios.findByRol");
        //q.setParameter("cnomUsuario", userName);
        //q.setParameter("ccontrasena", pass);
        lista = q.getResultList();
        boolean estado;
        if (lista.isEmpty()) {
            System.out.println(userName + " not found! ");
            estado = false;
        } else {
            //System.out.println("Nombre de usuario " + lista.get(0).getCcodUsuario());
            codigo = lista.get(0).getId();
            estado = true;
        }
        //return estado;
        return lista;
    }

    /*public boolean cambiarContra(String userName, String pass) {
        //Tusuario usuario;
        //usuario = em.find(Tusuario.class, userName);
        Integer codigo = 0;
        List<Usuarios> lista = null;     //new ArrayList<Address>();
        Query q = em.createNamedQuery("Tusuario.findByLogin");
        q.setParameter("cnomUsuario", userName);
        q.setParameter("ccontrasena", pass);
        lista = q.getResultList();
        boolean estado;
        if (lista.isEmpty()) {
            System.out.println(userName + " not found! ");
            estado = false;
        } else {
            //System.out.println("Nombre de usuario " + lista.get(0).getCcodUsuario());
            codigo = lista.get(0).getCcodUsuario();
            estado = true;
        }
        //return estado;
        return estado;
    }*/

    public List<Usuarios> getUsuariosFindAll() {
        List<Usuarios> lista = null;     //new ArrayList<Address>();
        Query q = em.createNamedQuery("Usuarios.findAll");
        //q.setParameter("consignatario", "%".concat(consi.getConsignatario()).concat("%"));
        lista = q.getResultList();
        return lista;
    }

    public List<Usuarios> getBuscarUsuario(String usuario) {
        List<Usuarios> lista = null;     //new ArrayList<Address>();
        //try {
        Query q = em.createNamedQuery("Usuarios.findAll");
        //q.setParameter("ccodProyecto", "%".concat(empresa.getCnomEmpresa()).concat("%"));            
        //q.setParameter("cnombre", "%".concat(socio).concat("%"));
        //q.setParameter("capellido", "%".concat(socio).concat("%"));
        //q.setParameter("cnomComunidad", "Cutumayo");
        lista = q.getResultList();
        ///System.out.println(rubro);
        //} catch (Exception e) {
        //  System.out.println(e.getMessage());
        //}
        return lista;
    }

    /*public Integer modificarUsuario(String usuario, String actual, String nueva) {
        //boolean esta
        Integer records = 0;
        try {
            //Query q2 = em.createNativeQuery("update tproducto set stock=? where codigo=?", Tproducto.class);
            //Query query = em.createQuery("DELETE FROM Employee emp WHERE emp.empSalary =  ? 1");
            //query.setParameter(1, 6000);
            //int records = query.executeUpdate();
            Query query = em.createQuery("UPDATE Tusuario u SET u.ccontrasena=?1 WHERE u.cnomUsuario=?2 AND u.ccontrasena=?3");
            query.setParameter(1, StringMD.getStringMessageDigest(nueva, StringMD.MD5));
            query.setParameter(2, usuario);
            query.setParameter(3, StringMD.getStringMessageDigest(actual, StringMD.MD5));
            records = query.executeUpdate();
            System.out.println("Update OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }*/
}
