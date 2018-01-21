/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletResponse;
import org.clases.mensajeClass;
import org.dao.ArrendatarioDAO;
import org.entities.TArrendatario;
import org.entities.Usuarios;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class ArrendatarioController {

    @EJB
    private ArrendatarioDAO ArrendatarioDAO;
    static TArrendatario itemNuevo;
    public String ruta;
    private FileUploadEvent event1;
    private File result;
    static TArrendatario itemSeleccionado;
    private DataModel<TArrendatario> listaArrendatario;

    public ArrendatarioController() {
        itemNuevo = new TArrendatario();
        itemSeleccionado = new TArrendatario();
    }
     public String agregar() {
        try {                   
                //itemNuevo.setCodigoProducto("0");                
                //System.out.println("Codigo de categoria " + CategoriaController.itemSeleccionado.getIcodCategoria());
                //System.out.println("Categoria seleccionada: " + CategoriaController.itemSeleccionado.getCnomCategoria());
                //itemNuevo.setIcodCategoria(CategoriaController.itemSeleccionado);
                //System.out.println("Codigo de categoria " + itemNuevo.getIcodCategoria().getIcodCategoria());
                //System.out.println("Categoria seleccionada: " + itemNuevo.getIcodCategoria().getCnomCategoria());
                //ProveedorController.itemSeleccionado = 
                //itemNuevo.setIcodProvedor(ProveedorController.itemSeleccionado);                
                itemNuevo.setFoto(ruta);
                //itemNuevo.setStock(0);
                ArrendatarioDAO.persist(itemNuevo);
                itemNuevo = new TArrendatario();
                mensajeClass mensaje = new mensajeClass();
                mensaje.DatosGuardados();
                ruta = "/resources/images/images.jpg";
                //System.out.println("Insert exitoso");           
        } catch (Exception e) {
            //System.out.println("Error producto :" + e.getLocalizedMessage() + "dd" + e.getMessage() + " mensaje:\n" + e.getCause() + " localizado:\n" + e.getStackTrace());
            e.printStackTrace();
        }
        return "";
    }
    
     public String buscar() {

        try {
            System.out.println("Buscar Arrendatarios");
            List<TArrendatario> lista = ArrendatarioDAO.getFindAll();
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TArrendatario> modeloListaArrendatarios = new ListDataModel<>(lista);
            setListaArrendatario(modeloListaArrendatarios);
            System.out.println("Exito!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";

    }

    public TArrendatario getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TArrendatario();
        }
        return itemNuevo;
    }

    public void setItemNuevo(TArrendatario itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @PostConstruct
    public void init() {
        ruta = "/resources/images/images.jpg";
        System.out.println("Entra al init");
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public FileUploadEvent getEvent1() {
        return event1;
    }

    public void setEvent1(FileUploadEvent event1) {
        this.event1 = event1;
    }

    public File getResult() {
        return result;
    }

    public void setResult(File result) {
        this.result = result;
    }

    /*public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }*/

    public void handleFileUpload(FileUploadEvent event) {

        //System.out.println("ENTRA AL HANDELFILE UPLOAD");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        //System.out.println("path:" + externalContext.getRealPath("C:/images/"));
        event1 = event;
        ruta = "/resources/arrendatarios/" + event.getFile().getFileName();

        //System.out.println("Ruta:" + ruta);

        //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources/arrendatarios/"));

        //System.out.println("file solo:" + event1.getFile().getFileName());
        result = new File(externalContext.getRealPath("resources/arrendatarios/") + File.separator + event.getFile().getFileName());
        //result = new File("C:/images/" + File.separator + event1.getFile().getFileName());

        //System.out.println("final file:" + result.getName());
        //current.setEmpLog(event.getFile().getFileName());
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(result);

            //byte[] buffer = new byte[BUFFER_SIZE];
            byte[] buffer = new byte[1024];

            int bulk;

            // Here you get uploaded picture bytes, while debugging you can see that 34818
            InputStream inputStream = event1.getFile().getInputstream();

            System.out.println("final file:" + result.getName());
            while (true) {

                bulk = inputStream.read(buffer);

                if (bulk < 0) {

                    break;

                } //end of if

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();

            } //end fo while(true)

            fileOutputStream.close();
            inputStream.close();

            //FacesMessage msg = new FacesMessage("Carga finalizada", event1.getFile().getFileName() + " ha sido almacenada.");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {

            e.printStackTrace();

            FacesMessage error = new FacesMessage("No se puede subir el archivo!");
            FacesContext.getCurrentInstance().addMessage(null, error);

        }

    }
    public TArrendatario getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TArrendatario();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TArrendatario itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public DataModel<TArrendatario> getListaArrendatario() {
        return listaArrendatario;
    }

    public void setListaArrendatario(DataModel<TArrendatario> listaArrendatario) {
        this.listaArrendatario = listaArrendatario;
    }
    
}
