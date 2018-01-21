/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import org.dao.PuestosDAO;
import org.entities.TPuesto;

/**
 *
 * @author Yo
 */
@ManagedBean
@RequestScoped
public class PuestosController {

   @EJB
    private PuestosDAO puestosDAO;
    static TPuesto itemNuevo;
    private DataModel<TPuesto> listaPuestos;

    public PuestosController() {
    }
    public String agregar() {
        mensajeClass mensaje = new mensajeClass();
        try {
            itemNuevo.setEcodArrenda(ArrendatarioController.itemSeleccionado);
            itemNuevo.setCodImpuesto(ImpuestosController.itemSeleccionado);
            puestosDAO.persist(itemNuevo);
            System.out.println("Insert exitoso");
            itemNuevo = new TPuesto();
            mensaje.DatosGuardados();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }
    public String buscar() {

        try {
            System.out.println("Buscar Arrendatarios");
            List<TPuesto> lista = puestosDAO.getFindByArrendatario(ArrendatarioController.itemSeleccionado);
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TPuesto> modeloListaPuestos = new ListDataModel<>(lista);
            setListaPuestos(modeloListaPuestos);
            System.out.println("Exitoooooooooooo!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";

    }
    public TPuesto getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TPuesto();
        }
        return itemNuevo;
    }
    
    public void setItemNuevo(TPuesto itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public DataModel<TPuesto> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(DataModel<TPuesto> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }
    
}
