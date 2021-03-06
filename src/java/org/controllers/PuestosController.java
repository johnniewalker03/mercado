/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.clases.mensajeClass;
import static org.controllers.UsuarioController.rolAdmin;
import org.dao.EstructurasDAO;
import org.dao.PuestosDAO;
import org.dao.UsuariosDAO;
import org.entities.TArrendatario;
import org.entities.TEstructura;
import org.entities.TPuesto;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Yo
 */
@ManagedBean
@SessionScoped
public class PuestosController {

    @EJB
    private PuestosDAO puestosDAO;
    @EJB
    private EstructurasDAO estructurasDAO;
    @EJB
    private UsuariosDAO usuariosDAO;
    static TPuesto itemNuevo;
    private Integer desde;
    private Integer hasta;
    private Integer periodopago;
    private TEstructura itemEstructura;
    private DataModel<TPuesto> listaPuestos;
    static TPuesto itemSeleccionado;
    private Date fecha;
    private String dia;
    private String mes;
    private String anio;
    private String sino;
    private String busqueda;
    private boolean cajon, madera, galera, metal, energia;

    public PuestosController() {
        itemSeleccionado = new TPuesto();
        itemNuevo = new TPuesto();
        itemEstructura = new TEstructura();
        desde = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        desde = cal.get(Calendar.YEAR);
        itemSeleccionado.setMatriculaPagada(Double.valueOf(desde));
        //itemSeleccionado.setMatriculaPagada(Double.valueOf(desde-1));
        periodopago = 1;
        hasta = cal.get(Calendar.YEAR);
        fecha = new Date();
        anio = String.valueOf(cal.get(Calendar.YEAR));
        itemNuevo.setMatriculaPagada(Double.parseDouble(anio));
        mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
        mes = new SimpleDateFormat("MMMM").format(cal.getTime());
        dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        energia = false;
        sino = "No";
    }

    @PostConstruct
    public void init() {
        periodoPago();
        buscarTodo();
    }

    public String agregar() {
        mensajeClass mensaje = new mensajeClass();
        try {
            itemNuevo.setEcodArrenda(ArrendatarioController.itemSeleccionado);
            itemNuevo.setCodImpuesto(ImpuestosController.itemSeleccionado);
            itemNuevo.setUbicacion(ZonaController.itemSeleccionado.getUbicacion());
            itemNuevo.setCajon(String.valueOf(cajon));
            itemNuevo.setGalera(String.valueOf(galera));
            itemNuevo.setMadera(String.valueOf(madera));
            itemNuevo.setMetal(String.valueOf(metal));
            itemNuevo.setEnergia(sino);
            System.out.println("Datos:" + itemNuevo.getNumPuesto() + "" + itemNuevo.getUbicacion());
            if (puestosDAO.getPuestoUbicacion(itemNuevo.getNumPuesto(), itemNuevo.getUbicacion()) == true) {
                puestosDAO.persist(itemNuevo);
                itemNuevo = new TPuesto();
                cajon = false;
                galera = false;
                madera = false;
                metal = false;
                energia = false; //Para que el checkbox de energía quede unchecked cuando se guarde
                mensaje.DatosGuardados();
            } else {
                mensaje.DatosError("Mensaje", "El número de puesto en " + itemNuevo.getUbicacion() + " ya existe");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return "";
    }

    public String buscarPuesto() {
        List<TPuesto> lista1 = puestosDAO.getBuscarPuesto(busqueda);
        ListDataModel<TPuesto> modeloLista = new ListDataModel<>(lista1);
        setListaPuestos(modeloLista);
        //System.out.println("Datos guardados con éxito");
        //} catch (Exception e) {
        //System.out.println("ERROR: " + e.getMessage());
        ///}
        return "";
    }

    public String buscar() {
        try {
            System.out.println("Buscar Arrendatarios");
            List<TPuesto> lista = puestosDAO.getFindByArrendatario(ArrendatarioController.itemSeleccionado);
            //ListDataModel<TImpuestos> modeloListaImpuestos = new ListDataModel<TImpuestos>(lista);
            ListDataModel<TPuesto> modeloListaPuestos = new ListDataModel<>(lista);
            setListaPuestos(modeloListaPuestos);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public String buscaEstructura() {
        this.metal = false;
        this.galera = false;
        this.madera = false;
        this.cajon = false;
        this.energia = false;
        this.sino = "No";
        System.out.println("Entra a la conversion de estructuras");
        if (Boolean.valueOf(itemSeleccionado.getMetal()) == true) {
            this.metal = true;
        }
        if (Boolean.valueOf(itemSeleccionado.getGalera()) == true) {
            this.galera = true;
        }
        if (Boolean.valueOf(itemSeleccionado.getMadera()) == true) {
            this.madera = true;
        }
        if (Boolean.valueOf(itemSeleccionado.getCajon()) == true) {
            this.cajon = true;
        }
        //System.out.println("energia " +itemSeleccionado.getEnergia());
        if ("Si".equals(itemSeleccionado.getEnergia())) {
            this.energia = true;
            sino = "Si";
        }

        System.out.println("Pasa la conversion de estructuras");
        return "";
    }

    public String pasarValor() {
        itemSeleccionado.setCodImpuesto(ImpuestosController.itemSeleccionado);
        return "";
    }

    public String buscarTodo() {

        try {
            List<TPuesto> lista = puestosDAO.getFindByTodo();
            //lista.clear();
            ListDataModel<TPuesto> modeloListaPuestos = new ListDataModel<>(lista);
            setListaPuestos(modeloListaPuestos);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public String limpiarLista() {
        //List<TPuesto> lista = new List<TPuesto>();
        ListDataModel<TPuesto> modeloListaPuestos = new ListDataModel<>();
        setListaPuestos(modeloListaPuestos);
        return "";
    }

    public String control() {
        try {
            System.out.println("Buscar Arrendatarios");
            System.out.println(itemSeleccionado.getNumPuesto());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return "";
    }

    public String fechaConvierte() {
        System.out.println("Entra a fecha Convierte");
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        anio = String.valueOf(cal.get(Calendar.YEAR));
        mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
        //Calendar cal = Calendar.getInstance();
        mes = new SimpleDateFormat("MMMM").format(cal.getTime());
        dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        return "";
    }

    public String periodoPago() {
        mensajeClass mensaje = new mensajeClass();
        if (itemSeleccionado.getMatriculaPagada() > hasta) {
            mensaje.DatosError("Atención!", "El periodo de pago no es valido!, Verifique la información");
            periodopago = null;
        } else {
            int pagada = itemSeleccionado.getMatriculaPagada().intValue();
            periodopago = (hasta - pagada) + 1;
        }
        return "";
    }

    public String actualizarPeriodo() {
        if (itemSeleccionado != null) {
            itemSeleccionado.setMatriculaPagada(itemSeleccionado.getMatriculaPagada() + 1);
            hasta = desde;
            periodoPago();
            //periodopago = (hasta - pagada) + 1;
        }
        return "";
    }

    public String verificaActualizar() {
        RequestContext context = RequestContext.getCurrentInstance();
        //context.execute("conf.show()");
        if (rolAdmin) { // Si es administrador, no deberia pedir las contraseñas, por lo tanto es de llamar actualizar
            actualizar(); //
        } else {
            context.execute("PF('conf').show();");
            System.out.println("Entra a validar admin");
        }
        return "";
    }

    public String actualizar() {
        //System.out.println("Es administrador: "+UsuarioController.validarAdministrador());
        mensajeClass mensaje = new mensajeClass();
        //if (user.validarAdmin()) {
        RequestContext context = RequestContext.getCurrentInstance();
        System.out.println("Valores " + UsuarioController.rolAdmin);
        if (rolAdmin || usuariosDAO.validarAministrador(UsuarioController.userUpdate, UsuarioController.passUpdate)) {
            System.out.println("ubicacion " + itemSeleccionado.getUbicacion());
            System.out.println("Entra a actualizar " + itemSeleccionado.getNumPuesto() + itemSeleccionado.getTipoPuesto());
            itemSeleccionado.setCajon(String.valueOf(cajon));
            itemSeleccionado.setGalera(String.valueOf(galera));
            itemSeleccionado.setMadera(String.valueOf(madera));
            itemSeleccionado.setMetal(String.valueOf(metal));
            itemSeleccionado.setEnergia(sino);
            System.out.println("itemseleccionado " + itemSeleccionado);
            boolean resultado = puestosDAO.modificaPuesto(itemSeleccionado);
            System.out.println("Pasa actualizacion");
            if (resultado) {
                itemSeleccionado = new TPuesto();
                this.metal = false;
                this.galera = false;
                this.madera = false;
                this.cajon = false;
                this.energia = false;
                this.sino = "No";
                UsuarioController.userUpdate = null;
                UsuarioController.passUpdate = null;
                context.execute("PF('conf').hide();");
                mensaje.DatosActualizados();
            } else {
                mensaje.DatosError("Mensaje de error", "No se pueden actualizar los datos");
            }
        } else {
            context.execute("PF('conf').hide();");
            mensaje.DatosError("Mensaje de error", "No se pueden actualizar los datos");
        }
        return "";
    }

    public String chequeado() {
        System.out.println("Chequeado: " + energia);
        if (energia == true) {
            sino = "Si";
        } else {
            sino = "No";
        }
        return "";
    }

    public TPuesto getItemNuevo() {
        if (itemNuevo == null) {
            itemNuevo = new TPuesto();
        }
        return itemNuevo;
    }

    public void setItemNuevo(TPuesto itemNuevo) {
        this.itemNuevo = itemNuevo;
    }

    public DataModel<TPuesto> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(DataModel<TPuesto> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public TPuesto getItemSeleccionado() {
        if (itemSeleccionado == null) {
            itemSeleccionado = new TPuesto();
        } else {
        }
        return itemSeleccionado;
    }

    public void setItemSeleccionado(TPuesto itemSeleccionado) {
        this.itemSeleccionado = itemSeleccionado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public boolean isCajon() {
        return cajon;
    }

    public void setCajon(boolean cajon) {
        this.cajon = cajon;
    }

    public boolean isMadera() {
        return madera;
    }

    public void setMadera(boolean madera) {
        this.madera = madera;
    }

    public boolean isGalera() {
        return galera;
    }

    public void setGalera(boolean galera) {
        this.galera = galera;
    }

    public boolean isMetal() {
        return metal;
    }

    public void setMetal(boolean metal) {
        this.metal = metal;
    }

    public TEstructura getItemEstructura() {
        if (itemEstructura == null) {
            itemEstructura = new TEstructura();
        }
        return itemEstructura;
    }

    public void setItemEstructura(TEstructura itemEstructura) {
        this.itemEstructura = itemEstructura;
    }

    public Integer getDesde() {
        return desde;
    }

    public void setDesde(Integer desde) {
        this.desde = desde;
    }

    public Integer getHasta() {
        return hasta;
    }

    public void setHasta(Integer hasta) {
        this.hasta = hasta;
    }

    public Integer getPeriodopago() {
        return periodopago;
    }

    public void setPeriodopago(Integer periodopago) {
        this.periodopago = periodopago;
    }

    public boolean isEnergia() {
        return energia;
    }

    public void setEnergia(boolean energia) {
        this.energia = energia;
    }

    public String getSino() {
        return sino;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

}
