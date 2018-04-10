/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import org.dao.BitacoraDAO;
import org.entities.TBitacora;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class BitacoraController {

    @EJB
    private BitacoraDAO bitacoraDAO;
    private TBitacora itemNuevo;
    private DataModel<TBitacora> listaBitacora;
    private String busqueda;
    public BitacoraController() {
        itemNuevo = new TBitacora();
    }
    public String agregar() {
        try {            
            bitacoraDAO.persist(itemNuevo);
            itemNuevo = new TBitacora();
            System.out.println("Insert exitoso");
            mensajeClass msj = new mensajeClass();
            msj.DatosGuardados();            
            buscar(); // Llama al método buscar, para que cargue la lista de cobradores
            //save();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }
    public String buscar() {
        try {            
            List<TBitacora> lista = bitacoraDAO.getFindAll();
            ListDataModel<TBitacora> modeloListaBitacora = new ListDataModel<>(lista);
            setListaBitacora(modeloListaBitacora);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }
    public String buscarIngreso() {
        try {            
            List<TBitacora> lista = bitacoraDAO.getFindByUsuario(busqueda);
            ListDataModel<TBitacora> modeloListaBitacora = new ListDataModel<>(lista);
            setListaBitacora(modeloListaBitacora);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public TBitacora getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TBitacora itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public DataModel<TBitacora> getListaBitacora() {
        return listaBitacora;
    }

    public void setListaBitacora(DataModel<TBitacora> listaBitacora) {
        this.listaBitacora = listaBitacora;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
}
