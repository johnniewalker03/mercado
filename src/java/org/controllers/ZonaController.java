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
import org.dao.ZonaDAO;
import org.entities.TCobrador;
import org.entities.TZona;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class ZonaController {

    @EJB
    private ZonaDAO zonaDAO;
    private TZona itemNuevo;
    private DataModel<TZona> listaZonas;
    private String busqueda;
    static TZona itemSeleccionado; 
    
    public ZonaController() {
        itemNuevo = new TZona();
        itemSeleccionado = new TZona();
    }

    @PostConstruct
    public void init() { // metodo init que se ejecuta
        busqueda(); // carga los datos de fondo una vez iniciado el facelet     
    }

    public String agregar() {
        try {            
            itemNuevo.setEcodCobrador(CobradorController.itemSeleccionado);
            //System.out.println("Cobrador seleccionado" + CobradorController.itemSeleccionado.getEcodCobrador());
            zonaDAO.persist(itemNuevo);                        
            CobradorController.itemSeleccionado=new TCobrador();
            itemNuevo = new TZona();
            busqueda();                        
            mensajeClass msj = new mensajeClass();            
            msj.DatosGuardados();
            //save();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }
    
    public String busqueda() {
        try {            
            //System.out.println("Entra a buscar");
            List<TZona> lista = zonaDAO.getFindAll();                        
            ListDataModel<TZona> modeloListaZona = new ListDataModel<>(lista);
            setListaZonas(modeloListaZona);            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }    
    
    public void updateZona() {  
        //System.out.println("Entra a zona update " + itemSeleccionado.getUbicacion());
        itemSeleccionado.setEcodCobrador(CobradorController.itemSeleccionado);        
        
        if (itemSeleccionado.getUbicacion()!=null){
        zonaDAO.modificaZona(itemSeleccionado);
        CobradorController.itemSeleccionado=new TCobrador();
        busqueda();
        }
        else{
            mensajeClass msj = new mensajeClass();
            msj.actualizacionError();
        }
        /*userBean.update(user.getUserId(), user.getUsername(), user.getPassword(), 
         User.AccountType.valueOf(user.getAccountType()), user.getFirstname(), user.getLastname(),
         user.getTeam() == null ? null : user.getTeam().getTeamId());*/
    }
 public void onRowSelect(SelectEvent event) {
     try {
            System.out.println("Entra a onrowselect");
            listaZonas.getRowData();
            if (itemSeleccionado == null) {
                //itemSeleccionado = ventaDetalleConsigna.getRowData();
            }
            //mensajeClass mensaje = new mensajeClass();
            RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("confirmations.show()");
            context.execute("PF('dl_datos').show()");
            
            System.out.println("Sale de onrowselect");
            //mensaje.DatosError("Mensajee", itemSeleccionado.getIcodDetalle().toString());

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(), e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
        //FacesMessage msg = new FacesMessage("Ubicación seleccionado", ((TZona) event.getObject()).getUbicacion());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onEdit(RowEditEvent event) {
        TZona zona = (TZona) event.getObject();
        //System.out.println("Editando: " + institucion);
        boolean resultado = zonaDAO.modificaZona(zona); 
        FacesMessage msg;
        if (resultado) {
            msg = new FacesMessage("Zona editado", ((TZona) event.getObject()).getUbicacion());
        } else {
            msg = new FacesMessage("No se puede actualizar", ((TZona) event.getObject()).getUbicacion());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        /*userBean.update(user.getUserId(), user.getUsername(), user.getPassword(), 
         User.AccountType.valueOf(user.getAccountType()), user.getFirstname(), user.getLastname(),
         user.getTeam() == null ? null : user.getTeam().getTeamId());*/
    }

    public void onCancel(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Actualización cancelada", ((Tsocio) event.getObject()).getCnombre() + " " + ((Tsocio) event.getObject()).getCapellido());
        FacesMessage msg = new FacesMessage("Actualización cancelada", ((TZona) event.getObject()).getUbicacion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void setItemNuevo(TZona itemNuevo) {
        this.itemNuevo = itemNuevo;
    }
    public TZona getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TZona();
        }
        return itemNuevo;
    }

    public DataModel<TZona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(DataModel<TZona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public TZona getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TZona();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TZona itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }
    
}
