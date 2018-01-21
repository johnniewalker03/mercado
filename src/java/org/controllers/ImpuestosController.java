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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import org.dao.ImpuestosDAO;
import org.entities.TImpuestos;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class ImpuestosController {

    @EJB
    private ImpuestosDAO impuestosDAO;
    private TImpuestos itemNuevo;
    private DataModel<TImpuestos> listaImpuestos;
    private String busqueda;
    static TImpuestos itemSeleccionado;

    public ImpuestosController() {
        itemNuevo = new TImpuestos();
        itemSeleccionado = new TImpuestos();
    }

    public TImpuestos getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TImpuestos();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TImpuestos itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public void setItemNuevo(TImpuestos itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    @PostConstruct
    public void init() { // metodo init que se ejecuta
        buscar(); // carga los datos de fondo una vez iniciado el facelet
    }

    //metodos

    public String agregar() {
        try {            
            impuestosDAO.persist(itemNuevo);
            itemNuevo = new TImpuestos();
            System.out.println("Insert exitoso");
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
            System.out.println("Buscar impuestos");
            List<TImpuestos> lista = impuestosDAO.getFindAll();
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<>(lista);
            setListaImpuestos(modeloListaImpuestos);
            System.out.println("Exito!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";

    }

    //seter & getter
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public TImpuestos getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TImpuestos();

        }
        return itemNuevo;
    }
    public ImpuestosDAO getImpuestosDAO() {
        return impuestosDAO;
    }

    public void setImpuestosDAO(ImpuestosDAO impuestosDAO) {
        this.impuestosDAO = impuestosDAO;
    }

    public DataModel<TImpuestos> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(DataModel<TImpuestos> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

}
