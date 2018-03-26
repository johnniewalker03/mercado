package org.controllers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.clases.funciones;
import org.clases.mensajeClass;
import org.dao.ArrendatarioDAO;
import org.entities.TArrendatario;
import org.entities.Usuarios;
import org.primefaces.event.FileUploadEvent;
@ManagedBean
@SessionScoped
public class ArrendatarioController {

    @EJB
    private ArrendatarioDAO ArrendatarioDAO;
    static TArrendatario itemNuevo;
    public String ruta;
    private FileUploadEvent event1;
    private File result;
    private String busqueda;
    static TArrendatario itemSeleccionado;
    static TArrendatario itemSeleccionadoSegundo;
    private DataModel<TArrendatario> listaArrendatario;
    private Date fec = null;

    public ArrendatarioController() {
        itemNuevo = new TArrendatario();
        itemSeleccionado = new TArrendatario();
        itemSeleccionadoSegundo =new TArrendatario();        
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
            mensajeClass mensaje = new mensajeClass();
            if (ArrendatarioDAO.getBuscarDUICompleto(itemNuevo.getDui())==true) {
                if (ruta.equals("/resources/images/images.jpg") || ruta.length() < 1) {
                    itemNuevo.setFoto("/resources/arrendatarios/imagenvacia.png");
                    //System.out.println("Foto default");
                } else {
                    itemNuevo.setFoto(ruta);
                }
                //itemNuevo.setStock(0);
                itemNuevo.setDepartamento(DepartamentosController.itemDepartamentos.getDepartamento());
                itemNuevo.setMunicipio(DepartamentosController.itemMunicipios.getMunicipio());
                ArrendatarioDAO.persist(itemNuevo);
                itemNuevo = new TArrendatario();                
                mensaje.DatosGuardados();
                ruta = "/resources/images/images.jpg";
                //System.out.println("Insert exitoso");
            } else {
                mensaje.DatosError("Atención!", "El número de DUI, ya ha sido ingresado!, Verifique la información");
            }
        } catch (Exception e) {
            //System.out.println("Error producto :" + e.getLocalizedMessage() + "dd" + e.getMessage() + " mensaje:\n" + e.getCause() + " localizado:\n" + e.getStackTrace());
            e.printStackTrace();
        }
        return "";
    }
    public String actualizar(){
        boolean resultado = ArrendatarioDAO.modificaArrendatario(itemSeleccionado);        
        mensajeClass mensaje = new mensajeClass();
        if (resultado) {            
            mensaje.DatosActualizados();
        } else {            
            mensaje.DatosError("Mensaje de error", "No se pueden actualizar los datos");
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
            funciones fn = new funciones();
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");         
            fec = formatoDelTexto.parse("25/09/1989");
            System.out.println("Dif de fechas " +fn.restarFechas(new Date(), fec)/365);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }
     public String buscarDUI(){
        boolean resultado = ArrendatarioDAO.getBuscarDUICompleto(itemNuevo.getDui());        
        mensajeClass mensaje = new mensajeClass();         
        if (resultado) {            
            //System.out.println("Buscando dui...");
        } else {            
            //mensaje.DatosError("Mensaje de error", "No se pueden actualizar los datos");            
            mensaje.DatosError("El DUI ya existe! - Verifique la información", "");
            //System.out.println("EL DUI YA EXISTE!");
        }              
        return "";
    }
      public String buscarArrendatario() {
        //List<consignatario> lista = consignaDAO.getConsignatarioListByConsignatario(Consignatario);
        //try {
        List<TArrendatario> lista1 = ArrendatarioDAO.getBuscarArrendatario(busqueda);
        ListDataModel<TArrendatario> modeloLista = new ListDataModel<>(lista1);
        setListaArrendatario(modeloLista);
        //System.out.println("Datos guardados con éxito");
        //} catch (Exception e) {
        //System.out.println("ERROR: " + e.getMessage());
        ///}
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
        itemSeleccionado.setFoto("/resources/images/imagenvacia.png");
        System.out.println(itemSeleccionado.getFoto());        
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
        itemSeleccionado.setFoto(ruta);
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
     public TArrendatario getItemSeleccionadoSegundo() {
        if (itemSeleccionadoSegundo == null) {
            itemSeleccionadoSegundo = new TArrendatario();
        } else {
        }
        return itemSeleccionadoSegundo;
    }

    public void setItemSeleccionadoSegundo(TArrendatario itemSeleccionadoSegundo) {
        this.itemSeleccionadoSegundo = itemSeleccionadoSegundo;
    }

    public DataModel<TArrendatario> getListaArrendatario() {
        return listaArrendatario;
    }

    public void setListaArrendatario(DataModel<TArrendatario> listaArrendatario) {
        this.listaArrendatario = listaArrendatario;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
}
