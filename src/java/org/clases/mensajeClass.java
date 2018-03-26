/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clases;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yo
 */
public class mensajeClass {
    public void DatosGuardados() {
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage("Successful", "Hello " + text));
        //context.addMessage(null, new FacesMessage("Mensaje", "Datos guardados con exito"));
            //public void Guardados(){        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operación completada!", "Datos guardados con éxito!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    //}
    }
    public void DatosError(String encabezado, String mensaje) {
        //FacesContext context = FacesContext.getCurrentInstance();         
        //context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        //context.addMessage(null, new FacesMessage(encabezado, mensaje));
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, encabezado, mensaje);
        FacesContext.getCurrentInstance().addMessage(null, message);
    //}
    }
    public void DatosActualizados() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operación completada!", "Información actualizada exitosamente!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    //}
    }
    public void actualizacionError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Operación incorrecta!", "No es posible actualizar la información!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    //}
    }
}
