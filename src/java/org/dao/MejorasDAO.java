
package org.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.entities.TMejoras;

/**
 *
 * @author Yo
 */
@Stateless
public class MejorasDAO {
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
        public List<TMejoras> getFindAll() {
        List<TMejoras> lista = null;
        Query q = em.createNamedQuery("TMejoras.findAll");        
        lista = q.getResultList();
        /*for (int i=0; i< lista.size(); i++) {
            System.out.println(lista.get(i).getUbicacion() + "\t" +
         lista.get(i).getEcodCobrador().getCnomCobra());
         }*/
        return lista;
    }

    public List<TMejoras> getBuscarMejoras(String busqueda) {
        List<TMejoras> lista = null;     //new ArrayList<Address>();
        //try {
        Query q = em.createNamedQuery("TMejoras.findByDatos");
        //q.setParameter("ccodProyecto", "%".concat(empresa.getCnomEmpresa()).concat("%"));
        //q.setParameter("fecha", "%".concat(busqueda).concat("%"));
        q.setParameter("nombre", "%".concat(busqueda).concat("%"));
        q.setParameter("puesto", "%".concat(busqueda).concat("%"));
        q.setParameter("dui", "%".concat(busqueda).concat("%"));
        lista = q.getResultList();
        //System.out.println();
        //} catch (Exception e) {
        //  System.out.println(e.getMessage());
        //}
        return lista;
    }
}
