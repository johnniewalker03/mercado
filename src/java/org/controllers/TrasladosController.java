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
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import org.dao.PuestosDAO;
import org.dao.TrasladosDAO;
import org.entities.TArrendatario;
import org.entities.TPuesto;
import org.entities.TTraslados;


@ManagedBean
@SessionScoped
public class TrasladosController {

    @EJB
    private TrasladosDAO trasladosDAO;
    @EJB
    private PuestosDAO puestosDAO;
    private TTraslados itemNuevo;
    static TTraslados itemSeleccionado;
    private DataModel<TTraslados> listaTraslados;
    private String busqueda;    
    
    public TrasladosController(){
    itemNuevo = new TTraslados();
    itemSeleccionado = new TTraslados();
    }
       public TTraslados getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TTraslados();
        }
        return itemNuevo;
    }
    public void setItemNuevo(TTraslados itemNuevo) {
        this.itemNuevo = itemNuevo;
    }
    public String buscarTraslados(){
        System.out.println("Esta entrando a buscar traslado: "+PuestosController.itemSeleccionado.getCorrelativo());
        List<TTraslados> lista1 = trasladosDAO.getBuscarTraslados(PuestosController.itemSeleccionado);
        ListDataModel<TTraslados> modeloLista = new ListDataModel<>(lista1);
        setListaTraslados(modeloLista);
        System.out.println("Esta saliendo a buscar traslado");
        return "";
    }
    
    public String buscarPorCampos(){
        List<TTraslados> lista1 = trasladosDAO.getBuscarPorCampos(busqueda);
        ListDataModel<TTraslados> modeloLista = new ListDataModel<>(lista1);
        setListaTraslados(modeloLista);
        return "";
    }
    
    public String buscar(){        
        List<TTraslados> lista1 = trasladosDAO.getFindAll();
        ListDataModel<TTraslados> modeloLista = new ListDataModel<>(lista1);
        setListaTraslados(modeloLista);
        return "";
    }
    mensajeClass msj = new mensajeClass();
    public String agregar() {
        try {            
            itemNuevo.setEcodArrendaAnte(ArrendatarioController.itemSeleccionado);
            itemNuevo.setEcodArrendaNuevo(ArrendatarioController.itemSeleccionadoSegundo);
            itemNuevo.setEcodPuesto(PuestosController.itemSeleccionado);            
            System.out.println("Codigo Arrendatario: " + ArrendatarioController.itemSeleccionadoSegundo.getEcodArrenda());
            PuestosController.itemSeleccionado.setEcodArrenda(ArrendatarioController.itemSeleccionadoSegundo);
            System.out.println("Puesto: " + PuestosController.itemSeleccionado.getNumPuesto());
            boolean resultado = puestosDAO.modificaPuesto(PuestosController.itemSeleccionado);           
            if (resultado == true) {
                trasladosDAO.persist(itemNuevo);
                itemNuevo = new TTraslados();
                ArrendatarioController.itemSeleccionado = new TArrendatario();
                ArrendatarioController.itemSeleccionadoSegundo = new TArrendatario();
                PuestosController.itemSeleccionado = new TPuesto();
                PuestosController ps = new PuestosController();
                ps.limpiarLista();
                System.out.println("Insert exitoso");
                msj.DatosGuardados();
                System.out.println("Datos actualizados");
            } else {
                System.out.println("No se pudieron actualizar los datos");
            }
            //save();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            msj.DatosError("Mensaje de error", e.getMessage());
        }
        return "";
    }

    public DataModel<TTraslados> getListaTraslados() {
        return listaTraslados;
    }

    public void setListaTraslados(DataModel<TTraslados> listaTraslados) {
        this.listaTraslados = listaTraslados;
    }
   public TTraslados getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TTraslados();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TTraslados itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

}
