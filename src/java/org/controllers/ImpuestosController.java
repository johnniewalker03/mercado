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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import org.dao.ImpuestosDAO;
import org.entities.TCobrador;
import org.entities.TImpuestos;
import org.primefaces.event.RowEditEvent;

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
   private TImpuestos itemNuevo1;
    private DataModel<TImpuestos> listaImpuestos;
    private String busqueda;
    static TImpuestos itemSeleccionado;

    public ImpuestosController() {
        itemNuevo1 = new TImpuestos();
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
        System.out.println("entra a buscar");
    }

    //metodos

    public String agregar() {
        mensajeClass msj = new mensajeClass();
        try {   
            if (itemNuevo1.getMonto()>=0.0){
                System.out.println(impuestosDAO.buscarImpuesto(itemNuevo1.getMonto()));
                System.out.println("monto " + itemNuevo1.getMonto());
                if (!impuestosDAO.buscarImpuesto(itemNuevo1.getMonto())) {
                    impuestosDAO.persist(itemNuevo1);
                    itemNuevo1 = new TImpuestos();
                    System.out.println("Insert exitoso");
                    msj.DatosGuardados();
                    buscar();
                    //save();
                }else{
                    msj.DatosError("Error", "El valor del impuesto ya existe");
                }
            }else{
                msj.DatosError("Error", "El valor del impuesto no es válido");
            }                
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }

    public String buscar() {

        try {
            System.out.println("Buscar impuestos");
            List<TImpuestos> lista = impuestosDAO.getFindAll();
            System.out.println("Impuestos: " + itemSeleccionado.getMonto());
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<>(lista);
            setListaImpuestos(modeloListaImpuestos);
            System.out.println("Exito!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";

    }
public void onEdit(RowEditEvent event) {
        TImpuestos impuestos = (TImpuestos) event.getObject();
        //System.out.println("Editando: " + institucion);
        boolean resultado = impuestosDAO.modificaImpuestos(impuestos); 
        FacesMessage msg;
        if (resultado) {
            msg = new FacesMessage("Impuesto editado", "Impuesto editado: "+((TImpuestos) event.getObject()).getMonto() + " " +((TImpuestos) event.getObject()).getDescripcion());
        } else {
            msg = new FacesMessage("No se puede actualizar", "No se puede actualizar: "+((TImpuestos) event.getObject()).getMonto()+ " " +((TImpuestos) event.getObject()).getDescripcion());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        /*userBean.update(user.getUserId(), user.getUsername(), user.getPassword(), 
         User.AccountType.valueOf(user.getAccountType()), user.getFirstname(), user.getLastname(),
         user.getTeam() == null ? null : user.getTeam().getTeamId());*/
    }

    public void onCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Actualización cancelada", ((Tsocio) event.getObject()).getCnombre() + " " + ((Tsocio) event.getObject()).getCapellido());
        FacesMessage msg = new FacesMessage("Actualización cancelada", "Actualización cancelada: " +((TImpuestos) event.getObject()).getMonto()+ " " +((TImpuestos) event.getObject()).getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public TImpuestos getItemNuevo1() {
        if (itemNuevo == null) {
            itemNuevo = new TImpuestos();
        
    }
      return itemNuevo1;

    
        
    }
public void setItemNuevo1(TImpuestos itemNuevo1) {
           this.itemNuevo1 = itemNuevo1;
    }
}
