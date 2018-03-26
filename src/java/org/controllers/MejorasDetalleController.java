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
import org.dao.MejorasDAO;
import org.dao.MejorasDetalleDAO;
import org.entities.TMejoras;
import org.entities.TMejorasdetalle;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class MejorasDetalleController {

    @EJB
    private MejorasDetalleDAO MejorasDetalleDAO;
    private TMejorasdetalle itemNuevo;
    private DataModel<TMejorasdetalle> listaMejorasDetalle;
    private String busqueda;
    static TMejorasdetalle itemSeleccionado;
    public MejorasDetalleController() {
        itemNuevo = new TMejorasdetalle();
        itemSeleccionado = new TMejorasdetalle();        
    }
      @PostConstruct
    public void init() { // metodo init que se ejecuta
        //buscar(); // carga los datos de fondo una vez iniciado el facelet     
    }           

    public String agregar() {
        try {            
            MejorasDetalleDAO.persist(itemNuevo);
            itemNuevo = new TMejorasdetalle();
            System.out.println("Insert exitoso");
            mensajeClass msj = new mensajeClass();
            msj.DatosGuardados();            
            //buscar(); // Llama al método buscar, para que cargue la lista de cobradores
            //save();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }
    public String buscar() {

        try {
            //System.out.println("Buscar impuestos");
            List<TMejorasdetalle> lista = MejorasDetalleDAO.getFindPuesto(MejorasController.itemSeleccionado);
            System.out.println("Mejoras: " + MejorasController.itemSeleccionado.getFfechaMejora());
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TMejorasdetalle> modeloListaMejoras = new ListDataModel<>(lista);
            setListaMejorasDetalle(modeloListaMejoras);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";

    }
  public TMejorasdetalle getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TMejorasdetalle();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TMejorasdetalle itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }     
    
    public TMejorasdetalle getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TMejorasdetalle itemNuevo) {
        this.itemNuevo = itemNuevo;
    }   

    public DataModel<TMejorasdetalle> getListaMejorasDetalle() {
        return listaMejorasDetalle;
    }

    public void setListaMejorasDetalle(DataModel<TMejorasdetalle> listaMejorasDetalle) {
        this.listaMejorasDetalle = listaMejorasDetalle;
    }
    
}
