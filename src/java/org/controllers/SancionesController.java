/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import org.dao.SancionesDAO;
import org.entities.TSanciones;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class SancionesController {

    @EJB
    private SancionesDAO sancionesDAO;
    private TSanciones itemNuevo;
    private DataModel<TSanciones> listaSanciones;

    public SancionesController() {
        itemNuevo = new TSanciones();
    }

    @PostConstruct
    public void init() { // metodo init que se ejecuta
        buscar(); // carga los datos de fondo una vez iniciado el facelet     
    }

    public String agregar() {
        try {
            itemNuevo.setEcodPuesto(PuestosController.itemSeleccionado);
            //System.out.println("Cobrador seleccionado" + CobradorController.itemSeleccionado.getEcodCobrador());
            sancionesDAO.persist(itemNuevo);
            //CobradorController.itemSeleccionado=new TSanciones();
            itemNuevo = new TSanciones();
            //busqueda();                        
            mensajeClass msj = new mensajeClass();
            msj.DatosGuardados();
            //save();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }

    public String buscar() {
        try {
            List<TSanciones> lista = sancionesDAO.getFindAll();
            ListDataModel<TSanciones> modeloListaSanciones = new ListDataModel<>(lista);
            setListaSanciones(modeloListaSanciones);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public TSanciones getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TSanciones itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public DataModel<TSanciones> getListaSanciones() {
        return listaSanciones;
    }

    public void setListaSanciones(DataModel<TSanciones> listaSanciones) {
        this.listaSanciones = listaSanciones;
    }

}
