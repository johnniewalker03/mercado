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
@Table(name = "t_zona", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TZona.findAll", query = "SELECT t FROM TZona t"),
    @NamedQuery(name = "TZona.findByEcodZona", query = "SELECT t FROM TZona t WHERE t.ecodZona = :ecodZona"),
    @NamedQuery(name = "TZona.findByUbicacion", query = "SELECT t FROM TZona t WHERE t.ubicacion = :ubicacion")})
public class TZona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EcodZona")
    private Integer ecodZona;
    @Size(max = 45)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @JoinColumn(name = "EcodCobrador", referencedColumnName = "EcodCobrador")
    @ManyToOne
    private TCobrador ecodCobrador;

    public TZona() {
    }

    public TZona(Integer ecodZona) {
        this.ecodZona = ecodZona;
    }

    public Integer getEcodZona() {
        return ecodZona;
    }

    public void setEcodZona(Integer ecodZona) {
        this.ecodZona = ecodZona;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public TCobrador getEcodCobrador() {
        return ecodCobrador;
    }

    public void setEcodCobrador(TCobrador ecodCobrador) {
        this.ecodCobrador = ecodCobrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecodZona != null ? ecodZona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TZona)) {
            return false;
        }
        TZona other = (TZona) object;
        if ((this.ecodZona == null && other.ecodZona != null) || (this.ecodZona != null && !this.ecodZona.equals(other.ecodZona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TZona[ ecodZona=" + ecodZona + " ]";
    }
    
}
