/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author migue
 */
@Entity
@Table(name = "t_puesto", catalog = "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPuesto.findAll", query = "SELECT t FROM TPuesto t"),
    @NamedQuery(name = "TPuesto.findByCorrelativo", query = "SELECT t FROM TPuesto t WHERE t.correlativo = :correlativo"),
    @NamedQuery(name = "TPuesto.findByUbicacion", query = "SELECT t FROM TPuesto t WHERE t.ubicacion = :ubicacion"),
    @NamedQuery(name = "TPuesto.findByTipo", query = "SELECT t FROM TPuesto t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TPuesto.findByTipoPuesto", query = "SELECT t FROM TPuesto t WHERE t.tipoPuesto = :tipoPuesto"),
    @NamedQuery(name = "TPuesto.findByMedidaH", query = "SELECT t FROM TPuesto t WHERE t.medidaH = :medidaH"),
    @NamedQuery(name = "TPuesto.findByNumPuesto", query = "SELECT t FROM TPuesto t WHERE t.numPuesto = :numPuesto"),
    @NamedQuery(name = "TPuesto.findByMedidaV", query = "SELECT t FROM TPuesto t WHERE t.medidaV = :medidaV"),
    @NamedQuery(name = "TPuesto.findByGalera", query = "SELECT t FROM TPuesto t WHERE t.galera = :galera"),
    @NamedQuery(name = "TPuesto.findByCajon", query = "SELECT t FROM TPuesto t WHERE t.cajon = :cajon"),
    @NamedQuery(name = "TPuesto.findByMadera", query = "SELECT t FROM TPuesto t WHERE t.madera = :madera"),
    @NamedQuery(name = "TPuesto.findByMetal", query = "SELECT t FROM TPuesto t WHERE t.metal = :metal"),
    @NamedQuery(name = "TPuesto.findByMatriculaPagada", query = "SELECT t FROM TPuesto t WHERE t.matriculaPagada = :matriculaPagada"),
    @NamedQuery(name = "TPuesto.findByNumRecibo", query = "SELECT t FROM TPuesto t WHERE t.numRecibo = :numRecibo"),
    @NamedQuery(name = "TPuesto.findByEnergia", query = "SELECT t FROM TPuesto t WHERE t.energia = :energia"),
    @NamedQuery(name = "TPuesto.findByFechaPago", query = "SELECT t FROM TPuesto t WHERE t.fechaPago = :fechaPago"),
 @NamedQuery(name = "TPuesto.findByDatos", query = "SELECT t FROM TPuesto t WHERE t.numPuesto LIKE :puesto OR t.ecodArrenda.dui LIKE :dui OR t.ecodArrenda.nombreCompleto LIKE :nombre OR SUBSTRING(t.fechaPago, 1, 4)= :fecha")})
public class TPuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Correlativo")
    private Integer correlativo;
    @Size(max = 200)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @Size(max = 50)
    @Column(name = "Tipo")
    private String tipo;
    @Size(max = 12)
    @Column(name = "TipoPuesto")
    private String tipoPuesto;
    @Size(max = 45)
    @Column(name = "MedidaH")
    private String medidaH;
    @Size(max = 45)
    @Column(name = "NumPuesto")
    private String numPuesto;
    @Size(max = 45)
    @Column(name = "MedidaV")
    private String medidaV;
    @Size(max = 6)
    @Column(name = "Galera")
    private String galera;
    @Size(max = 6)
    @Column(name = "Cajon")
    private String cajon;
    @Size(max = 6)
    @Column(name = "Madera")
    private String madera;
    @Size(max = 6)
    @Column(name = "Metal")
    private String metal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MatriculaPagada")
    private Double matriculaPagada;
    @Size(max = 12)
    @Column(name = "NumRecibo")
    private String numRecibo;
    @Column(name = "FechaPago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "energia")
    private String energia;
    @JoinColumn(name = "EcodArrenda", referencedColumnName = "EcodArrenda")
    @ManyToOne
    private TArrendatario ecodArrenda;
    @JoinColumn(name = "CodImpuesto", referencedColumnName = "CodImpuestos")
    @ManyToOne
    private TImpuestos codImpuesto;

    public TPuesto() {
    }

    public TPuesto(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public TPuesto(Integer correlativo, String energia) {
        this.correlativo = correlativo;
        this.energia = energia;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoPuesto() {
        return tipoPuesto;
    }

    public void setTipoPuesto(String tipoPuesto) {
        this.tipoPuesto = tipoPuesto;
    }

    public String getMedidaH() {
        return medidaH;
    }

    public void setMedidaH(String medidaH) {
        this.medidaH = medidaH;
    }

    public String getNumPuesto() {
        return numPuesto;
    }

    public void setNumPuesto(String numPuesto) {
        this.numPuesto = numPuesto;
    }

    public String getMedidaV() {
        return medidaV;
    }

    public void setMedidaV(String medidaV) {
        this.medidaV = medidaV;
    }

    public String getGalera() {
        return galera;
    }

    public void setGalera(String galera) {
        this.galera = galera;
    }

    public String getCajon() {
        return cajon;
    }

    public void setCajon(String cajon) {
        this.cajon = cajon;
    }

    public String getMadera() {
        return madera;
    }

    public void setMadera(String madera) {
        this.madera = madera;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public Double getMatriculaPagada() {
        return matriculaPagada;
    }

    public void setMatriculaPagada(Double matriculaPagada) {
        this.matriculaPagada = matriculaPagada;
    }

    public String getNumRecibo() {
        return numRecibo;
    }

    public void setNumRecibo(String numRecibo) {
        this.numRecibo = numRecibo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEnergia() {
        return energia;
    }

    public void setEnergia(String energia) {
        this.energia = energia;
    }

    public TArrendatario getEcodArrenda() {
        return ecodArrenda;
    }

    public void setEcodArrenda(TArrendatario ecodArrenda) {
        this.ecodArrenda = ecodArrenda;
    }

    public TImpuestos getCodImpuesto() {
        return codImpuesto;
    }

    public void setCodImpuesto(TImpuestos codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correlativo != null ? correlativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TPuesto)) {
            return false;
        }
        TPuesto other = (TPuesto) object;
        if ((this.correlativo == null && other.correlativo != null) || (this.correlativo != null && !this.correlativo.equals(other.correlativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TPuesto[ correlativo=" + correlativo + " ]";
    }
    
}
