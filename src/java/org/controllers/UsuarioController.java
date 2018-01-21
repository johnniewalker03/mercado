/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.clases.mensajeClass;
import org.dao.UsuariosDAO;
import org.entities.Usuarios;

@ManagedBean
@SessionScoped
public class UsuarioController {
    
    @EJB
    private UsuariosDAO usuarioDAO;
    static Usuarios itemNuevo;
    public String contraAgain;
    private boolean administrador = true;
    private boolean usuario = false;
    /*private empresaDAO empresaDAO;
    private Tempresa itemNuevo;
    //private socioDAO empresadao;
    public EmpresaController() {
        itemNuevo = new Tempresa();
        //bandera = false;
    }*/
    
    public String agregar() {
        mensajeClass mensaje = new mensajeClass();
        try {
            if (itemNuevo.getUser().length() < 6 || itemNuevo.getPassword().length() < 6) {
                mensaje.DatosError("Mensaje de error", "El nombre de usuario y contraseña debe contener como mínimo 6 caracteres");
            } else {
                //if (EmpleadoController.itemSeleccionado.getCcodEmpleado() == null) {
                //    mensaje.DatosError("Mensaje de error", "Debe completar los datos");
                //} else {
                if (usuarioDAO.buscaUsuario(itemNuevo.getUser()) == false) { // usuario no existe
                    if (itemNuevo.getPassword().equals(contraAgain)) {
                    //itemNuevo.setCcodEmpleado(EmpleadoController.itemSeleccionado);
                            /*String mensaje = "Mensaje secreto";
                     StringMD StringMD = new StringMD();
                     System.out.println("Mensaje = " + mensaje);
                     System.out.println("MD2 = " + StringMD.getStringMessageDigest(mensaje, StringMD.MD2));
                     System.out.println("MD5 = " + StringMD.getStringMessageDigest(mensaje, StringMD.MD5));
                     System.out.println("SHA-1 = " + StringMD.getStringMessageDigest(mensaje, StringMD.SHA1));
                     System.out.println("SHA-256 = " + StringMD.getStringMessageDigest(mensaje, StringMD.SHA256));
                     System.out.println("SHA-384 = " + StringMD.getStringMessageDigest(mensaje, StringMD.SHA384));
                     System.out.println("SHA-512 = " + StringMD.getStringMessageDigest(mensaje, StringMD.SHA512));*/
                    //itemNuevo.setCcontrasena(StringMD.getStringMessageDigest(contraAgain, StringMD.MD5));
                    usuarioDAO.persist(itemNuevo);
                    System.out.println("Insert exitoso");
                    itemNuevo = new Usuarios();
                    mensaje.DatosGuardados();
                        } else {
                        mensaje.DatosError("Error", "No coinciden las contraseñas");
                    }
                } else {
                        mensaje.DatosError("Error", "El nombre de usuario ya existe!");
                    }
                //}
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }
    
     public Usuarios getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new Usuarios();
        }
        return itemNuevo;
    }
    
    public void setItemNuevo(Usuarios itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }    
    
    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getContraAgain() {
        return contraAgain;
    }

    public void setContraAgain(String contraAgain) {
        this.contraAgain = contraAgain;
    }


}
