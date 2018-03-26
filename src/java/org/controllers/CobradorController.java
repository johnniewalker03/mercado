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
import org.clases.mensajeClass;
import org.dao.CobradoresDAO;
import org.entities.TCobrador;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class CobradorController {

    @EJB
    private CobradoresDAO cobradoresDAO;
    private TCobrador itemNuevo;
    private DataModel<TCobrador> listaCobradores;
    private String busqueda;
    static TCobrador itemSeleccionado;
    public CobradorController() {
        itemNuevo = new TCobrador();
        itemSeleccionado = new TCobrador();        
    }
      @PostConstruct
    public void init() { // metodo init que se ejecuta
        buscar(); // carga los datos de fondo una vez iniciado el facelet     
    }           

    public String agregar() {
        try {            
            cobradoresDAO.persist(itemNuevo);
            itemNuevo = new TCobrador();
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
            List<TCobrador> lista = cobradoresDAO.getFindAll();
            ListDataModel<TCobrador> modeloListaCobradores = new ListDataModel<>(lista);
            setListaCobradores(modeloListaCobradores);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }
    
     public void onEdit(RowEditEvent event) {
        TCobrador cobrador = (TCobrador) event.getObject();
        //System.out.println("Editando: " + institucion);        
        boolean resultado = cobradoresDAO.modificaCobrador(cobrador); 
        FacesMessage msg;
        if (resultado) {
            msg = new FacesMessage("Cobrador editado", "Cobrador editado: "+((TCobrador) event.getObject()).getCnomCobra() + " " +((TCobrador) event.getObject()).getCapeCobra());
        } else {
            msg = new FacesMessage("No se puede actualizar", "No se puede actualizar: "+((TCobrador) event.getObject()).getCnomCobra()+ " " +((TCobrador) event.getObject()).getCapeCobra());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        /*userBean.update(user.getUserId(), user.getUsername(), user.getPassword(), 
         User.AccountType.valueOf(user.getAccountType()), user.getFirstname(), user.getLastname(),
         user.getTeam() == null ? null : user.getTeam().getTeamId());*/
    }

    public void onCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Actualización cancelada", ((Tsocio) event.getObject()).getCnombre() + " " + ((Tsocio) event.getObject()).getCapellido());
        FacesMessage msg = new FacesMessage("Actualización cancelada", "Actualización cancelada: " +((TCobrador) event.getObject()).getCnomCobra()+ " " +((TCobrador) event.getObject()).getCapeCobra());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public TCobrador getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TCobrador();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TCobrador itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }     
    
    public TCobrador getItemNuevo() {
        return itemNuevo;
    }

    public void setItemNuevo(TCobrador itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public DataModel<TCobrador> getListaCobradores() {
        return listaCobradores;
    }

    public void setListaCobradores(DataModel<TCobrador> listaCobradores) {
        this.listaCobradores = listaCobradores;
    }      
}
