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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_bitacora", catalog = "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TBitacora.findAll", query = "SELECT t FROM TBitacora t"),
    @NamedQuery(name = "TBitacora.findByEcodBitacora", query = "SELECT t FROM TBitacora t WHERE t.ecodBitacora = :ecodBitacora"),
    @NamedQuery(name = "TBitacora.findByUsuario", query = "SELECT t FROM TBitacora t WHERE t.codUsuario.user LIKE :usuario"),
    @NamedQuery(name = "TBitacora.findByFechaIngreso", query = "SELECT t FROM TBitacora t WHERE t.fechaIngreso = :fechaIngreso")})
public class TBitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EcodBitacora")
    private Integer ecodBitacora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaIngreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @JoinColumn(name = "CodUsuario", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuarios codUsuario;

    public TBitacora() {
    }

    public TBitacora(Integer ecodBitacora) {
        this.ecodBitacora = ecodBitacora;
    }

    public TBitacora(Integer ecodBitacora, Date fechaIngreso) {
        this.ecodBitacora = ecodBitacora;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getEcodBitacora() {
        return ecodBitacora;
    }

    public void setEcodBitacora(Integer ecodBitacora) {
        this.ecodBitacora = ecodBitacora;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Usuarios getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuarios codUsuario) {
        this.codUsuario = codUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecodBitacora != null ? ecodBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TBitacora)) {
            return false;
        }
        TBitacora other = (TBitacora) object;
        if ((this.ecodBitacora == null && other.ecodBitacora != null) || (this.ecodBitacora != null && !this.ecodBitacora.equals(other.ecodBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TBitacora[ ecodBitacora=" + ecodBitacora + " ]";
    }
    
}
