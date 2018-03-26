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
 * @author Yo
 */
@Entity
@Table(name = "t_sanciones", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSanciones.findAll", query = "SELECT t FROM TSanciones t"),
    @NamedQuery(name = "TSanciones.findByEcodSanciones", query = "SELECT t FROM TSanciones t WHERE t.ecodSanciones = :ecodSanciones"),
    @NamedQuery(name = "TSanciones.findByCtipoSancion", query = "SELECT t FROM TSanciones t WHERE t.ctipoSancion = :ctipoSancion"),
    @NamedQuery(name = "TSanciones.findByCobservaciones", query = "SELECT t FROM TSanciones t WHERE t.cobservaciones = :cobservaciones"),
    @NamedQuery(name = "TSanciones.findByFFechaAplica", query = "SELECT t FROM TSanciones t WHERE t.fFechaAplica = :fFechaAplica")})
public class TSanciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EcodSanciones")
    private Integer ecodSanciones;
    @Size(max = 50)
    @Column(name = "CtipoSancion")
    private String ctipoSancion;
    @Size(max = 200)
    @Column(name = "Cobservaciones")
    private String cobservaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FFechaAplica")
    @Temporal(TemporalType.DATE)
    private Date fFechaAplica;
    @JoinColumn(name = "EcodPuesto", referencedColumnName = "Correlativo")
    @ManyToOne(optional = false)
    private TPuesto ecodPuesto;

    public TSanciones() {
    }

    public TSanciones(Integer ecodSanciones) {
        this.ecodSanciones = ecodSanciones;
    }

    public TSanciones(Integer ecodSanciones, Date fFechaAplica) {
        this.ecodSanciones = ecodSanciones;
        this.fFechaAplica = fFechaAplica;
    }

    public Integer getEcodSanciones() {
        return ecodSanciones;
    }

    public void setEcodSanciones(Integer ecodSanciones) {
        this.ecodSanciones = ecodSanciones;
    }

    public String getCtipoSancion() {
        return ctipoSancion;
    }

    public void setCtipoSancion(String ctipoSancion) {
        this.ctipoSancion = ctipoSancion;
    }

    public String getCobservaciones() {
        return cobservaciones;
    }

    public void setCobservaciones(String cobservaciones) {
        this.cobservaciones = cobservaciones;
    }

    public Date getFFechaAplica() {
        return fFechaAplica;
    }

    public void setFFechaAplica(Date fFechaAplica) {
        this.fFechaAplica = fFechaAplica;
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
        hash += (ecodSanciones != null ? ecodSanciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSanciones)) {
            return false;
        }
        TSanciones other = (TSanciones) object;
        if ((this.ecodSanciones == null && other.ecodSanciones != null) || (this.ecodSanciones != null && !this.ecodSanciones.equals(other.ecodSanciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TSanciones[ ecodSanciones=" + ecodSanciones + " ]";
    }
    
}
