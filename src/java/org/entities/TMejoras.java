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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_mejoras", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMejoras.findAll", query = "SELECT t FROM TMejoras t"),
    @NamedQuery(name = "TMejoras.findByEcodMejoras", query = "SELECT t FROM TMejoras t WHERE t.ecodMejoras = :ecodMejoras"),
    @NamedQuery(name = "TMejoras.findByFfechaMejora", query = "SELECT t FROM TMejoras t WHERE t.ffechaMejora = :ffechaMejora"),
    @NamedQuery(name = "TMejoras.findByDatos", query = "SELECT t FROM TMejoras t WHERE t.ecodPuesto.ecodArrenda.nombreCompleto LIKE :nombre OR t.ecodPuesto.numPuesto LIKE :puesto OR t.ecodPuesto.ecodArrenda.dui LIKE :dui")})
public class TMejoras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EcodMejoras")
    private Integer ecodMejoras;
    @Column(name = "FfechaMejora")
    @Temporal(TemporalType.DATE)
    private Date ffechaMejora;
    @JoinColumn(name = "EcodPuesto", referencedColumnName = "Correlativo")
    @ManyToOne(optional = false)
    private TPuesto ecodPuesto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ecodMejora")
    private List<TMejorasdetalle> tMejorasdetalleList;

    public TMejoras() {
    }

    public TMejoras(Integer ecodMejoras) {
        this.ecodMejoras = ecodMejoras;
    }

    public Integer getEcodMejoras() {
        return ecodMejoras;
    }

    public void setEcodMejoras(Integer ecodMejoras) {
        this.ecodMejoras = ecodMejoras;
    }

    public Date getFfechaMejora() {
        return ffechaMejora;
    }

    public void setFfechaMejora(Date ffechaMejora) {
        this.ffechaMejora = ffechaMejora;
    }

    public TPuesto getEcodPuesto() {
        return ecodPuesto;
    }

    public void setEcodPuesto(TPuesto ecodPuesto) {
        this.ecodPuesto = ecodPuesto;
    }

    @XmlTransient
    public List<TMejorasdetalle> getTMejorasdetalleList() {
        return tMejorasdetalleList;
    }

    public void setTMejorasdetalleList(List<TMejorasdetalle> tMejorasdetalleList) {
        this.tMejorasdetalleList = tMejorasdetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ecodMejoras != null ? ecodMejoras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMejoras)) {
            return false;
        }
        TMejoras other = (TMejoras) object;
        if ((this.ecodMejoras == null && other.ecodMejoras != null) || (this.ecodMejoras != null && !this.ecodMejoras.equals(other.ecodMejoras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TMejoras[ ecodMejoras=" + ecodMejoras + " ]";
    }
    
}
