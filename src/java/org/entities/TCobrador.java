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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_cobrador", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCobrador.findAll", query = "SELECT t FROM TCobrador t"),
    @NamedQuery(name = "TCobrador.findByEcodCobrador", query = "SELECT t FROM TCobrador t WHERE t.ecodCobrador = :ecodCobrador"),
    @NamedQuery(name = "TCobrador.findByCnomCobra", query = "SELECT t FROM TCobrador t WHERE t.cnomCobra = :cnomCobra"),
    @NamedQuery(name = "TCobrador.findByCapeCobra", query = "SELECT t FROM TCobrador t WHERE t.capeCobra = :capeCobra"),
    @NamedQuery(name = "TCobrador.findByFFechaNac", query = "SELECT t FROM TCobrador t WHERE t.fFechaNac = :fFechaNac"),
    @NamedQuery(name = "TCobrador.findByCdui", query = "SELECT t FROM TCobrador t WHERE t.cdui = :cdui"),
    @NamedQuery(name = "TCobrador.findByCTelefono", query = "SELECT t FROM TCobrador t WHERE t.cTelefono = :cTelefono"),
    @NamedQuery(name = "TCobrador.findByCdireccion", query = "SELECT t FROM TCobrador t WHERE t.cdireccion = :cdireccion")})
public class TCobrador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EcodCobrador")
    private Integer ecodCobrador;
    @Size(max = 40)
    @Column(name = "CnomCobra")
    private String cnomCobra;
    @Size(max = 40)
    @Column(name = "CapeCobra")
    private String capeCobra;
    @Column(name = "FFechaNac")
    @Temporal(TemporalType.DATE)
    private Date fFechaNac;
    @Size(max = 12)
    @Column(name = "Cdui")
    private String cdui;
    @Size(max = 10)
    @Column(name = "CTelefono")
    private String cTelefono;
    @Size(max = 140)
    @Column(name = "Cdireccion")
    private String cdireccion;

    public TCobrador() {
    }

    public TCobrador(Integer ecodCobrador) {
        this.ecodCobrador = ecodCobrador;
    }

    public Integer getEcodCobrador() {
        return ecodCobrador;
    }

    public void setEcodCobrador(Integer ecodCobrador) {
        this.ecodCobrador = ecodCobrador;
    }

    public String getCnomCobra() {
        return cnomCobra;
    }

    public void setCnomCobra(String cnomCobra) {
        this.cnomCobra = cnomCobra;
    }

    public String getCapeCobra() {
        return capeCobra;
    }

    public void setCapeCobra(String capeCobra) {
        this.capeCobra = capeCobra;
    }

    public Date getFFechaNac() {
        return fFechaNac;
    }

    public void setFFechaNac(Date fFechaNac) {
        this.fFechaNac = fFechaNac;
    }

    public String getCdui() {
        return cdui;
    }

    public void setCdui(String cdui) {
        this.cdui = cdui;
    }

    public String getCTelefono() {
        return cTelefono;
    }

    public void setCTelefono(String cTelefono) {
        this.cTelefono = cTelefono;
    }

    public String getCdireccion() {
        return cdireccion;
    }

    public void setCdireccion(String cdireccion) {
        this.cdireccion = cdireccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecodCobrador != null ? ecodCobrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCobrador)) {
            return false;
        }
        TCobrador other = (TCobrador) object;
        if ((this.ecodCobrador == null && other.ecodCobrador != null) || (this.ecodCobrador != null && !this.ecodCobrador.equals(other.ecodCobrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TCobrador[ ecodCobrador=" + ecodCobrador + " ]";
    }
    
}
