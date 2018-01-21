/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_impuestos", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TImpuestos.findAll", query = "SELECT t FROM TImpuestos t"),
    @NamedQuery(name = "TImpuestos.findByCodImpuestos", query = "SELECT t FROM TImpuestos t WHERE t.codImpuestos = :codImpuestos"),
    @NamedQuery(name = "TImpuestos.findByMonto", query = "SELECT t FROM TImpuestos t WHERE t.monto = :monto"),
    @NamedQuery(name = "TImpuestos.findByDescripcion", query = "SELECT t FROM TImpuestos t WHERE t.descripcion = :descripcion")})
public class TImpuestos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CodImpuestos")
    private Integer codImpuestos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Monto")
    private double monto;
    @Size(max = 100)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "codImpuesto")
    private List<TPuesto> tPuestoList;

    public TImpuestos() {
    }

    public TImpuestos(Integer codImpuestos) {
        this.codImpuestos = codImpuestos;
    }

    public TImpuestos(Integer codImpuestos, double monto) {
        this.codImpuestos = codImpuestos;
        this.monto = monto;
    }

    public Integer getCodImpuestos() {
        return codImpuestos;
    }

    public void setCodImpuestos(Integer codImpuestos) {
        this.codImpuestos = codImpuestos;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (codImpuestos != null ? codImpuestos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TImpuestos)) {
            return false;
        }
        TImpuestos other = (TImpuestos) object;
        if ((this.codImpuestos == null && other.codImpuestos != null) || (this.codImpuestos != null && !this.codImpuestos.equals(other.codImpuestos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TImpuestos[ codImpuestos=" + codImpuestos + " ]";
    }
    
}
