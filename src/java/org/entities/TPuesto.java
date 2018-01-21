/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_puesto", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TPuesto.findAll", query = "SELECT t FROM TPuesto t"),
    @NamedQuery(name = "TPuesto.findByCorrelativo", query = "SELECT t FROM TPuesto t WHERE t.correlativo = :correlativo"),
    @NamedQuery(name = "TPuesto.findByUbicacion", query = "SELECT t FROM TPuesto t WHERE t.ubicacion = :ubicacion"),
    @NamedQuery(name = "TPuesto.findByTipo", query = "SELECT t FROM TPuesto t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TPuesto.findByMixto", query = "SELECT t FROM TPuesto t WHERE t.mixto = :mixto"),
    @NamedQuery(name = "TPuesto.findByMedidaH", query = "SELECT t FROM TPuesto t WHERE t.medidaH = :medidaH"),
    @NamedQuery(name = "TPuesto.findByNumPuesto", query = "SELECT t FROM TPuesto t WHERE t.numPuesto = :numPuesto"),
    @NamedQuery(name = "TPuesto.findByMedidaV", query = "SELECT t FROM TPuesto t WHERE t.medidaV = :medidaV"),
    @NamedQuery(name = "TPuesto.findByEstructura", query = "SELECT t FROM TPuesto t WHERE t.estructura = :estructura")})
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
    @Size(max = 10)
    @Column(name = "Mixto")
    private String mixto;
    @Size(max = 45)
    @Column(name = "MedidaH")
    private String medidaH;
    @Size(max = 45)
    @Column(name = "NumPuesto")
    private String numPuesto;
    @Size(max = 45)
    @Column(name = "MedidaV")
    private String medidaV;
    @Size(max = 45)
    @Column(name = "Estructura")
    private String estructura;
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

    public String getMixto() {
        return mixto;
    }

    public void setMixto(String mixto) {
        this.mixto = mixto;
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

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
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
