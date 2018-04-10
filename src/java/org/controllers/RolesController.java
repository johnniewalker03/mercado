/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dao.RollesDAO;
import org.entities.TRolmercado;
import org.primefaces.event.RowEditEvent;


    
/**
 *
 * @author migue
 */
@ManagedBean
@SessionScoped
public class RolesController {
    @EJB
    private RollesDAO rollesDAO;
    private TRolmercado itemNuevo;
    private DataModel<TRolmercado> ListaRoles;
    private String busqueda;
    static TRolmercado itemSeleccionado;
    /**
     * Creates a new instance of RolesController
     */
    public RolesController() {
        itemNuevo = new TRolmercado();
        itemSeleccionado = new TRolmercado();
    }
    public TRolmercado getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TRolmercado();
        } else {
        }
        return itemSeleccionado;
    }
@PostConstruct
    public void init() { // metodo init que se ejecuta
        buscar(); // carga los datos de fondo una vez iniciado el facelet
        System.out.println("entra a buscar");
    }
    
    public void setItemSeleccionado(TRolmercado itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public void setItemNuevo(TRolmercado itemNuevo) {
        this.itemNuevo = itemNuevo;
    }
    public String buscar() {

        //try {
            System.out.println("Buscar Roles");
            List<TRolmercado> lista = rollesDAO.getFindAll();
            //System.out.println("Impuestos: " + itemSeleccionado.getMonto());
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TRolmercado> modeloListaRoles = new ListDataModel<>(lista);
            setListaRoles(modeloListaRoles);
            System.out.println("Exito!");
        //} catch (Exception e) {
        //    System.out.println("ERROR: " + e.getMessage());
       // }
        return "";

    }
public void onEdit(RowEditEvent event) {
        TRolmercado roles = (TRolmercado) event.getObject();
        //System.out.println("Editando: " + institucion);
        boolean resultado = rollesDAO.modificaroles(roles); 
        FacesMessage msg;
        if (resultado) {
            msg = new FacesMessage("Actualización Exitosa");
        } else {
            msg = new FacesMessage("No se puede actualizar", "No se puede actualizar: "+((TRolmercado) event.getObject()).getCnombre()+ " " +((TRolmercado) event.getObject()).getCapellido());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        /*userBean.update(user.getUserId(), user.getUsername(), user.getPassword(), 
         User.AccountType.valueOf(user.getAccountType()), user.getFirstname(), user.getLastname(),
         user.getTeam() == null ? null : user.getTeam().getTeamId());*/
    }

    public void onCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Actualización cancelada", ((Tsocio) event.getObject()).getCnombre() + " " + ((Tsocio) event.getObject()).getCapellido());
        FacesMessage msg = new FacesMessage("Actualización cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    //seter & getter
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public TRolmercado getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TRolmercado();

        }
        return itemNuevo;
    }

    public DataModel<TRolmercado> getListaRoles() {
        return ListaRoles;
    }

    public void setListaRoles(DataModel<TRolmercado> ListaRoles) {
        this.ListaRoles = ListaRoles;
    }
   
    
}
