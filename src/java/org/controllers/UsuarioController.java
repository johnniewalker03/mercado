/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.clases.mensajeClass;
import org.dao.BitacoraDAO;
import org.dao.UsuariosDAO;
import org.entities.TBitacora;
import org.entities.Usuarios;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class UsuarioController {

    @EJB
    private UsuariosDAO usuarioDAO;
    @EJB
    private BitacoraDAO bitacoraDAO;
    static Usuarios itemNuevo;
    public String contraAgain, contraActual;
    private boolean administrador = true;
    private boolean usuario = false;
    static boolean bandera = false; // Para saber si esta logueado, sino redirecciona
    static Integer codUser;
    private boolean verLink = false;
    private String url;
    public String user, pass;
    static String userUpdate, passUpdate; // Variables para validar que el usuario sea administrador
    static boolean rolAdmin = false;
    /*private empresaDAO empresaDAO;
     private Tempresa itemNuevo;
     //private socioDAO empresadao;
     public EmpresaController() {
     itemNuevo = new Tempresa();
     //bandera = false;
     }*/

    public UsuarioController() {
        bandera = false;
    }

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
                        contraAgain = null;
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

    public String cambiarContra() {
        mensajeClass mensaje = new mensajeClass();
        try {
            if (itemNuevo.getPassword().equals(contraAgain)) {

                if (contraActual.equals(pass)) {
                    if (itemNuevo.getPassword().length() < 6) {
                        mensaje.DatosError("Mensaje de error", "La contraseña debe contener como mínimo 6 caracteres");
                    } else {
                        usuarioDAO.modificarUsuario(itemNuevo.getPassword(), codUser);
                        contraAgain = null;
                        itemNuevo = new Usuarios();
                        mensaje.DatosActualizados();
                    }
                } else {
                    mensaje.DatosError("Mensaje", "La contraseña actual no coincide");
                }
            } else {
                mensaje.DatosError("Mensaje", "Las nuevas contraseñas no coinciden");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }

    /*public boolean validarAdministrador() {
     List<Usuarios> lis1 = usuarioDAO.buscaLogin(userUpdate, passUpdate);
     //System.out.println("Lenght> " + retorno.toString().length());        
     if (lis1.size() > 0) {
     System.out.println("Entra");
     String nivel = lis1.get(0).getRol();
     if (nivel.equals("Administrador")) {
     //PuestosController pc = new PuestosController();
     //System.out.println("Es administrador " + PuestosController.itemSeleccionado.getNumPuesto());
     //pc.actualizar();
     //pc.actualizar(); // Para que actualice inmediatamente
     return true;
     } else {
     return false;
     }
     } else {
     System.out.println("Cierra el dialog");
     RequestContext context = RequestContext.getCurrentInstance();
     context.execute("PF('conf').hide();");
     return false;
     }
     //return "";
     }*/
    /*public String validarAdmin() {
     //if (usuario) {
     RequestContext context = RequestContext.getCurrentInstance();
     //context.execute("conf.show()");
     if (rolAdmin) { // Si es administrador, no deberia pedir las contraseñas, por lo tanto es de llamar actualizar
     PuestosController controller = new PuestosController();
     controller.actualizar(); //
     } else {
     context.execute("PF('conf').show();");
     System.out.println("Entra a validar admin");
     }
     /*} else {
     System.out.println("Ejecuta actualizar desde validarAdmin porque era administrador");
     PuestosController pc = new PuestosController();
     pc.actualizar(); // Para que actualice inmediatamente
     }*/
    //  return "";
    //}
    public String login() {

        /*administrador = false;         
         proyectos = false;*/
        mensajeClass mensaje = new mensajeClass();
        String url = "";
        //try {        
        List<Usuarios> lis1 = usuarioDAO.buscaLogin(user, pass);
        //System.out.println("Lenght> " + retorno.toString().length());
        if (lis1.size() > 0) {
            /*itemSeleccionado.setCcodEmpleado(lis1.get(0).getCcodEmpleado());
             EmpleadoController.itemSeleccionado = new Templeado();
             EmpleadoController.itemSeleccionado.setCcodEmpleado(lis1.get(0).getCcodEmpleado().getCcodEmpleado());
             EmpleadoController.itemSeleccionado.setCnomEmpleado(lis1.get(0).getCcodEmpleado().getCnomEmpleado());
             nombreempleado = lis1.get(0).getCcodEmpleado().getCnomEmpleado();
             codigoEmpleado = lis1.get(0).getCcodEmpleado().getCcodEmpleado();
             EmpleadoController.itemSeleccionado = new Templeado(codigoEmpleado, nombreempleado); // Para que de entrada aparezca seleccionado el usuario logueado como empleado
             */

            String nivel = lis1.get(0).getRol();
            if (nivel.equals("Administrador")) {
                administrador = true;
                usuario = false;
                rolAdmin = true;
                //ayuda = "../ayuda/indice.html";                
            }
            if (nivel.equals("Usuario")) {
                usuario = true;
                administrador = false;
                rolAdmin = false;
                //ayuda = "../ayuda/indicecontabilidad.html";
            }
            //fondoDAO.persist(itemNuevo);
            //itemNuevo.setCcodUsuario(retorno);
            codUser = lis1.get(0).getId();
            //Date date = new Date();
            bandera = true;
            verLink = false;
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("window.location.href='administracion/inicio.xhtml'; return false;");
            Usuarios us = new Usuarios();
            us.setId(lis1.get(0).getId());
            TBitacora item = new TBitacora();
            item.setCodUsuario(us);
            item.setFechaIngreso(new Date()); //Guarda la fecha
            bitacoraDAO.persist(item);
            
            System.out.println("Insert de bitácora exitoso!");
//VentasController vc = new VentasController();
            //vc.irURLLogin("administracion/inicio.xhtml");
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("window.location.href='ventasCFBuscar.xhtml'; return false;");
            System.out.println("Entra a ir");
            //String dir = "index.xhtml";
            //context.execute("window.location.href='" + dir + "'; return false;");
            //mensaje.DatosGuardados();
            return "index.xhtml";
        } else {
            bandera = false;
            verLink = true;
            mensaje.DatosError("Mensaje de error", "El usuario o contraseña ingresado no es válido");
            return "";
        }
        //} catch (Exception e) {
        //    System.out.println("Error " + e.getMessage());
        //}
        //System.out.println(url);
        //return "";
    }

    public String redirec() {
        if (UsuarioController.bandera == false) {
            RequestContext context = RequestContext.getCurrentInstance();
            //String dir = "administracion/inicio.xhtml";
            context.execute("window.location.href='../pages/login.xhtml'; return false;");
        }
        return "";
    }

    public String irLogin() {
        if (UsuarioController.bandera == false) {
            RequestContext context = RequestContext.getCurrentInstance();
            //String dir = "administracion/inicio.xhtml";
            context.execute("window.location.href='faces/pages/login.xhtml'; return false;");
        }
        return "";
    }

    public String irURL() {
        RequestContext context = RequestContext.getCurrentInstance();
        //context.execute("window.location.href='ventasCFBuscar.xhtml'; return false;");            
        context.execute("window.location.href='" + url + "'; return false;");
        //context.execute("window.open('" + url + "', '_blank')");        
        return "";
    }

    public String cerrarSesion() {
        bandera = false;
        RequestContext context = RequestContext.getCurrentInstance();
        //String dir = "administracion/inicio.xhtml";
        context.execute("window.location.href='../pages/login.xhtml'; return false;");
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

    public static boolean isBandera() {
        return bandera;
    }

    public static void setBandera(boolean bandera) {
        UsuarioController.bandera = bandera;
    }

    public static Integer getCodUser() {
        return codUser;
    }

    public static void setCodUser(Integer codUser) {
        UsuarioController.codUser = codUser;
    }

    public boolean isVerLink() {
        return verLink;
    }

    public void setVerLink(boolean verLink) {
        this.verLink = verLink;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        UsuarioController.userUpdate = userUpdate;
    }

    public String getPassUpdate() {
        return passUpdate;
    }

    public void setPassUpdate(String passUpdate) {
        UsuarioController.passUpdate = passUpdate;
    }

    public boolean isRolAdmin() {
        return rolAdmin;
    }

    public void setRolAdmin(boolean rolAdmin) {
        UsuarioController.rolAdmin = rolAdmin;
    }

    public String getContraActual() {
        return contraActual;
    }

    public void setContraActual(String contraActual) {
        this.contraActual = contraActual;
    }

}
