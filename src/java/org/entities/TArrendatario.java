/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_arrendatario", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TArrendatario.findAll", query = "SELECT t FROM TArrendatario t"),
    @NamedQuery(name = "TArrendatario.findByEcodArrenda", query = "SELECT t FROM TArrendatario t WHERE t.ecodArrenda = :ecodArrenda"),
    @NamedQuery(name = "TArrendatario.findByNoExpediente", query = "SELECT t FROM TArrendatario t WHERE t.noExpediente = :noExpediente"),
    @NamedQuery(name = "TArrendatario.findByNombreCompleto", query = "SELECT t FROM TArrendatario t WHERE t.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "TArrendatario.findByDireccion", query = "SELECT t FROM TArrendatario t WHERE t.direccion = :direccion"),    
    @NamedQuery(name = "TArrendatario.findByDui", query = "SELECT t FROM TArrendatario t WHERE t.dui LIKE :dui"),    
    @NamedQuery(name = "TArrendatario.findByTodo", query = "SELECT t FROM TArrendatario t WHERE t.dui LIKE :dui OR t.nombreCompleto LIKE :nombre OR t.noExpediente LIKE :expediente"),
    @NamedQuery(name = "TArrendatario.findByDuiCompleto", query = "SELECT t FROM TArrendatario t WHERE t.dui = :dui"),
    @NamedQuery(name = "TArrendatario.findByNit", query = "SELECT t FROM TArrendatario t WHERE t.nit = :nit"),
    @NamedQuery(name = "TArrendatario.findByDepartamento", query = "SELECT t FROM TArrendatario t WHERE t.departamento = :departamento"),
    @NamedQuery(name = "TArrendatario.findByMunicipio", query = "SELECT t FROM TArrendatario t WHERE t.municipio = :municipio"),
    @NamedQuery(name = "TArrendatario.findByTelefono", query = "SELECT t FROM TArrendatario t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "TArrendatario.findByFoto", query = "SELECT t FROM TArrendatario t WHERE t.foto = :foto"),
    @NamedQuery(name = "TArrendatario.findByFechaIngreso", query = "SELECT t FROM TArrendatario t WHERE t.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "TArrendatario.findByDUIExtendidoEn", query = "SELECT t FROM TArrendatario t WHERE t.dUIExtendidoEn = :dUIExtendidoEn"),
    @NamedQuery(name = "TArrendatario.findByFechaExpedicion", query = "SELECT t FROM TArrendatario t WHERE t.fechaExpedicion = :fechaExpedicion")})
public class TArrendatario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EcodArrenda")
    private Integer ecodArrenda;
    @Size(max = 60)
    @Column(name = "NoExpediente")
    private String noExpediente;
    @Size(max = 50)
    @Column(name = "NombreCompleto")
    private String nombreCompleto;
    @Size(max = 100)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 12)
    @Column(name = "DUI")
    private String dui;
    @Size(max = 20)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 30)
    @Column(name = "Departamento")
    private String departamento;
    @Size(max = 30)
    @Column(name = "Municipio")
    private String municipio;
    @Size(max = 10)
    @Column(name = "Telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "Foto")
    private String foto;
    @Column(name = "FechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Size(max = 45)
    @Column(name = "DUIExtendidoEn")
    private String dUIExtendidoEn;
    @Basic(optional = false)
    @Column(name = "FechaExpedicion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
    @Column(name = "FechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 45)
    @Column(name = "CorreoElectronico")
    private String correoElectronico;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ecodArrendaAnte")
    private List<TTraslados> tTrasladosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ecodArrendaNuevo")
    private List<TTraslados> tTrasladosList1;
    @OneToMany(mappedBy = "ecodArrenda")
    private List<TPuesto> tPuestoList;

    public TArrendatario() {
    }

    public String getdUIExtendidoEn() {
        return dUIExtendidoEn;
    }

    public void setdUIExtendidoEn(String dUIExtendidoEn) {
        this.dUIExtendidoEn = dUIExtendidoEn;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public TArrendatario(Integer ecodArrenda) {
        this.ecodArrenda = ecodArrenda;
    }

    public TArrendatario(Integer ecodArrenda, Date fechaExpedicion) {
        this.ecodArrenda = ecodArrenda;
        this.fechaExpedicion = fechaExpedicion;
    }

    public Integer getEcodArrenda() {
        return ecodArrenda;
    }

    public void setEcodArrenda(Integer ecodArrenda) {
        this.ecodArrenda = ecodArrenda;
    }

    public String getNoExpediente() {
        return noExpediente;
    }

    public void setNoExpediente(String noExpediente) {
        this.noExpediente = noExpediente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDUIExtendidoEn() {
        return dUIExtendidoEn;
    }

    public void setDUIExtendidoEn(String dUIExtendidoEn) {
        this.dUIExtendidoEn = dUIExtendidoEn;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    @XmlTransient
    public List<TTraslados> getTTrasladosList() {
        return tTrasladosList;
    }

    public void setTTrasladosList(List<TTraslados> tTrasladosList) {
        this.tTrasladosList = tTrasladosList;
    }

    @XmlTransient
    public List<TTraslados> getTTrasladosList1() {
        return tTrasladosList1;
    }

    public void setTTrasladosList1(List<TTraslados> tTrasladosList1) {
        this.tTrasladosList1 = tTrasladosList1;
    }

    @XmlTransient
    public List<TPuesto> getTPuestoList() {
        return tPuestoList;
    }

    public void setTPuestoList(List<TPuesto> tPuestoList) {
        this.tPuestoList = tPuestoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecodArrenda != null ? ecodArrenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TArrendatario)) {
            return false;
        }
        TArrendatario other = (TArrendatario) object;
        if ((this.ecodArrenda == null && other.ecodArrenda != null) || (this.ecodArrenda != null && !this.ecodArrenda.equals(other.ecodArrenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TArrendatario[ ecodArrenda=" + ecodArrenda + " ]";
    }
    
}
