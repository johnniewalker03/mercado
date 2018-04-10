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
import org.dao.CobradoresDAO;
import org.dao.MejorasDAO;
import org.dao.MejorasDetalleDAO;
import org.entities.TCobrador;
import org.entities.TMejoras;
import org.entities.TMejorasdetalle;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class MejorasController {

    @EJB
    private MejorasDAO MejorasDAO;
    @EJB
    private MejorasDetalleDAO mejorasDetalleDAO;
    private TMejoras itemNuevo;
    private TMejorasdetalle itemDetalle;
    private DataModel<TMejoras> listaMejoras;
    private String busqueda;
    static TMejoras itemSeleccionado;
    private List<String> items;

    public MejorasController() {
        itemNuevo = new TMejoras();
        itemDetalle = new TMejorasdetalle();
        itemSeleccionado = new TMejoras();
    }

    @PostConstruct
    public void init() { // metodo init que se ejecuta
        if (UsuarioController.bandera == false) {
            RequestContext context = RequestContext.getCurrentInstance();
            //String dir = "administracion/inicio.xhtml";
            context.execute("window.location.href='../login.xhtml'; return false;");
        }
        //buscar(); // carga los datos de fondo una vez iniciado el facelet     
    }

    public String redirec() {
        if (UsuarioController.bandera == false) {
            RequestContext context = RequestContext.getCurrentInstance();
            //String dir = "administracion/inicio.xhtml";
            context.execute("window.location.href='../login.xhtml'; return false;");
        }
        return "";
    }

    public String agregar() {
        mensajeClass msj = new mensajeClass();
        try {
            if (PuestosController.itemSeleccionado.getCorrelativo() == null) {
                msj.DatosError("Mensaje", "Debe seleccionar un puesto!");
            } else {
                itemNuevo.setEcodPuesto(PuestosController.itemSeleccionado);
                MejorasDAO.persist(itemNuevo);
                //itemNuevo = new TMejoras();
                System.out.println("Insert exitoso " + PuestosController.itemSeleccionado);
                for (String item : items) { //Foreach para ingreso de detalles de mejoras
                    System.out.println("Mejoras: " + item);
                    itemDetalle.setEcodMejora(itemNuevo);
                    itemDetalle.setCmejora(item);
                    mejorasDetalleDAO.persist(itemDetalle);
                }
                msj.DatosGuardados();
                itemNuevo = new TMejoras();
                itemDetalle = new TMejorasdetalle();
                items = null;
                //buscar(); // Llama al método buscar, para que cargue la lista de cobradores
                //save();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }

    public String buscar() {
        try {
            List<TMejoras> lista = MejorasDAO.getFindAll();
            ListDataModel<TMejoras> modeloListaMejoras = new ListDataModel<>(lista);
            this.setListaMejoras(modeloListaMejoras);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public String buscarMejora() {
        try {
            List<TMejoras> lista = MejorasDAO.getBuscarMejoras(busqueda);
            ListDataModel<TMejoras> modeloListaMejoras = new ListDataModel<>(lista);
            this.setListaMejoras(modeloListaMejoras);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public TMejorasdetalle getItemDetalle() {
        return itemDetalle;
    }

    public void setItemDetalle(TMejorasdetalle itemDetalle) {
        this.itemDetalle = itemDetalle;
    }

    public TMejoras getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TMejoras();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TMejoras itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public TMejoras getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TMejoras itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        //System.out.println(items);
        this.items = items;
    }

    public DataModel<TMejoras> getListaMejoras() {
        return listaMejoras;
    }

    public void setListaMejoras(DataModel<TMejoras> listaMejoras) {
        this.listaMejoras = listaMejoras;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

}
