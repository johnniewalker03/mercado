/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import org.clases.mensajeClass;
import org.dao.EstructurasDAO;
import org.entities.TEstructura;
import org.entities.TPuesto;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class EstructurasController {

    @EJB
    private EstructurasDAO estructurasDAO;
    static TEstructura itemNuevo;
    private DataModel<TEstructura> listaPuestos;
    public EstructurasController() {
        
    }
    public String agregar(TPuesto itemPuestos, String madera, String cajon, String galera, String metal) {
        mensajeClass mensaje = new mensajeClass();
        System.out.println("madera" + madera);
        System.out.println("Cajon" + cajon);
        System.out.println("galera " + galera);
        System.out.println("metal" + metal);
        //try {
            //int otherThing = something ? 1 : 0;  
        /*itemNuevo.setGalera(galera);
        itemNuevo.setCajon(cajon);
        itemNuevo.setMadera(madera);
        itemNuevo.setMetal(metal);*/
            /*System.out.println("Estructura Galera: "+ itemNuevo.getGalera());
            System.out.println("Estructura Madera: "+ itemNuevo.getMadera());
            System.out.println("Estructura Metal: "+ itemNuevo.getMetal());
            System.out.println("Estructura Cajón: "+ itemNuevo.getCajon());*/
            //itemNuevo.setEcodPuesto(itemPuestos);
            //estructurasDAO.persist(itemNuevo);
            System.out.println("Insert de Estructuras");
            itemNuevo = new TEstructura();
            //mensaje.DatosGuardados();
        //} catch (Exception e) {
        //    System.out.println("Error " + e.getMessage());
        //}
        return "";
    }

    public TEstructura getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TEstructura();
        }
        return itemNuevo;
    }
    
    public void setItemNuevo(TEstructura itemNuevo) {
        this.itemNuevo = itemNuevo;
    }


    public DataModel<TEstructura> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(DataModel<TEstructura> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

  
}
