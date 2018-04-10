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
 * @author migue
 */
@Entity
@Table(name = "t_rolmercado", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TRolmercado.findAll", query = "SELECT t FROM TRolmercado t"),
    @NamedQuery(name = "TRolmercado.findByEcodrol", query = "SELECT t FROM TRolmercado t WHERE t.ecodrol = :ecodrol"),
    @NamedQuery(name = "TRolmercado.findByCnombre", query = "SELECT t FROM TRolmercado t WHERE t.cnombre = :cnombre"),
    @NamedQuery(name = "TRolmercado.findByCapellido", query = "SELECT t FROM TRolmercado t WHERE t.capellido = :capellido"),
    @NamedQuery(name = "TRolmercado.findByCfechanacimiento", query = "SELECT t FROM TRolmercado t WHERE t.cfechanacimiento = :cfechanacimiento"),
    @NamedQuery(name = "TRolmercado.findByCdui", query = "SELECT t FROM TRolmercado t WHERE t.cdui = :cdui"),
    @NamedQuery(name = "TRolmercado.findByCdireccion", query = "SELECT t FROM TRolmercado t WHERE t.cdireccion = :cdireccion"),
    @NamedQuery(name = "TRolmercado.findByCtefono", query = "SELECT t FROM TRolmercado t WHERE t.ctefono = :ctefono"),
    @NamedQuery(name = "TRolmercado.findByCrol", query = "SELECT t FROM TRolmercado t WHERE t.crol = :crol")})
public class TRolmercado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Ecodrol")
    private Integer ecodrol;
    @Size(max = 40)
    @Column(name = "Cnombre")
    private String cnombre;
    @Size(max = 40)
    @Column(name = "Capellido")
    private String capellido;
    @Column(name = "Cfechanacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfechanacimiento;
    @Size(max = 12)
    @Column(name = "Cdui")
    private String cdui;
    @Size(max = 45)
    @Column(name = "Cdireccion")
    private String cdireccion;
    @Size(max = 12)
    @Column(name = "Ctefono")
    private String ctefono;
    @Size(max = 40)
    @Column(name = "Crol")
    private String crol;

    public TRolmercado() {
    }

    public TRolmercado(Integer ecodrol) {
        this.ecodrol = ecodrol;
    }

    public Integer getEcodrol() {
        return ecodrol;
    }

    public void setEcodrol(Integer ecodrol) {
        this.ecodrol = ecodrol;
    }

    public String getCnombre() {
        return cnombre;
    }

    public void setCnombre(String cnombre) {
        this.cnombre = cnombre;
    }

    public String getCapellido() {
        return capellido;
    }

    public void setCapellido(String capellido) {
        this.capellido = capellido;
    }

    public Date getCfechanacimiento() {
        return cfechanacimiento;
    }

    public void setCfechanacimiento(Date cfechanacimiento) {
        this.cfechanacimiento = cfechanacimiento;
    }

    public String getCdui() {
        return cdui;
    }

    public void setCdui(String cdui) {
        this.cdui = cdui;
    }

    public String getCdireccion() {
        return cdireccion;
    }

    public void setCdireccion(String cdireccion) {
        this.cdireccion = cdireccion;
    }

    public String getCtefono() {
        return ctefono;
    }

    public void setCtefono(String ctefono) {
        this.ctefono = ctefono;
    }

    public String getCrol() {
        return crol;
    }

    public void setCrol(String crol) {
        this.crol = crol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecodrol != null ? ecodrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TRolmercado)) {
            return false;
        }
        TRolmercado other = (TRolmercado) object;
        if ((this.ecodrol == null && other.ecodrol != null) || (this.ecodrol != null && !this.ecodrol.equals(other.ecodrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TRolmercado[ ecodrol=" + ecodrol + " ]";
    }
    
}
