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
@Table(name = "t_estructura", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TEstructura.findAll", query = "SELECT t FROM TEstructura t"),
    @NamedQuery(name = "TEstructura.findById", query = "SELECT t FROM TEstructura t WHERE t.id = :id"),
    @NamedQuery(name = "TEstructura.findByGalera", query = "SELECT t FROM TEstructura t WHERE t.galera = :galera"),
    @NamedQuery(name = "TEstructura.findByCajon", query = "SELECT t FROM TEstructura t WHERE t.cajon = :cajon"),
    @NamedQuery(name = "TEstructura.findByMadera", query = "SELECT t FROM TEstructura t WHERE t.madera = :madera"),
    @NamedQuery(name = "TEstructura.findByMetal", query = "SELECT t FROM TEstructura t WHERE t.metal = :metal")})
public class TEstructura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
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
    @JoinColumn(name = "EcodPuesto", referencedColumnName = "Correlativo")
    @ManyToOne(optional = false)
    private TPuesto ecodPuesto;

    public TEstructura() {
    }

    public TEstructura(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TPuesto getEcodPuesto() {
        return ecodPuesto;
    }

    public void setEcodPuesto(TPuesto ecodPuesto) {
        this.ecodPuesto = ecodPuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TEstructura)) {
            return false;
        }
        TEstructura other = (TEstructura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TEstructura[ id=" + id + " ]";
    }
    
}
