/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.dao.DepartamentosDAO;
import org.dao.MunicipiosDAO;
import org.entities.TDepartamentos;
import org.entities.TMunicipios;


@ManagedBean
@SessionScoped
public class DepartamentosController {

    @EJB
    private DepartamentosDAO departamentosDAO;
    @EJB
    private MunicipiosDAO municipiosDAO;
    private List<TDepartamentos> ListaDepartamentos;
    private List<TMunicipios> ListaMunicipios;
    static TDepartamentos itemDepartamentos;
    static TMunicipios itemMunicipios;
    public DepartamentosController() {
        itemDepartamentos = new TDepartamentos();
        itemMunicipios =new TMunicipios();
    }
    
    @PostConstruct
    public void init()
    {
        ListaDepartamentos = departamentosDAO.getFindByTodo();
        ListaMunicipios = municipiosDAO.getFindByMunicipios();        
    }
    public List<TDepartamentos> obtenerListados(){    
        return departamentosDAO.getFindByTodo();
    }
    
    public void cargarMunicipios() {
        
        //System.out.println("Departamento: " + id);
        //itemSeleccionado.setDepartamento(departamentosDAO.nombreDepartamento(itemSeleccionado.getId()));
        itemDepartamentos.setId(departamentosDAO.departamentoID(itemDepartamentos.getDepartamento()));
        System.out.println("vamos a ver "+ departamentosDAO.departamentoID(itemDepartamentos.getDepartamento()));
        //itemDepartamentos.setDepartamento(departamentosDAO.nombreDepartamento(itemDepartamentos.getId()));        
        System.out.println("idDepto: " + itemDepartamentos.getId());
        System.out.println("Nombre depto: " + itemDepartamentos.getDepartamento());
        ListaMunicipios = municipiosDAO.getFindByMunicipios(itemDepartamentos);
        //return "";
    }
    public List<TDepartamentos> getListaDepartamentos() {
        return ListaDepartamentos;
    }

    public void setListaDepartamentos(List<TDepartamentos> ListaDepartamentos) {
        this.ListaDepartamentos = ListaDepartamentos;
    }

    public List<TMunicipios> getListaMunicipios() {
        return ListaMunicipios;
    }

    public void setListaMunicipios(List<TMunicipios> ListaMunicipios) {
        this.ListaMunicipios = ListaMunicipios;
    }

   
    
  public TDepartamentos getItemDepartamentos() {
        if (itemDepartamentos == null) {
            itemDepartamentos = new TDepartamentos();
        } else {
        }
        return itemDepartamentos;
    }

    public void setItemDepartamentos(TDepartamentos itemSeleccionado) {
        this.itemDepartamentos = itemSeleccionado;
    }   
     public TMunicipios getItemMunicipios() {
        if (itemMunicipios == null) {
            itemMunicipios = new TMunicipios();
        } else {
        }
        return itemMunicipios;
    }

    public void setItemMunicipios(TMunicipios itemSeleccionado) {
        this.itemMunicipios = itemSeleccionado;
    }    
}
