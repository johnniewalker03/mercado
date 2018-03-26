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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_mejorasdetalle", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMejorasdetalle.findAll", query = "SELECT t FROM TMejorasdetalle t"),
    @NamedQuery(name = "TMejorasdetalle.findPuestos", query = "SELECT t FROM TMejorasdetalle t WHERE t.ecodMejora=:mejora"),
    @NamedQuery(name = "TMejorasdetalle.findByCorrelativo", query = "SELECT t FROM TMejorasdetalle t WHERE t.correlativo = :correlativo"),
    @NamedQuery(name = "TMejorasdetalle.findByCmejora", query = "SELECT t FROM TMejorasdetalle t WHERE t.cmejora = :cmejora")})
public class TMejorasdetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Correlativo")
    private Integer correlativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Cmejora")
    private String cmejora;
    @JoinColumn(name = "EcodMejora", referencedColumnName = "EcodMejoras")
    @ManyToOne(optional = false)
    private TMejoras ecodMejora;

    public TMejorasdetalle() {
    }

    public TMejorasdetalle(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public TMejorasdetalle(Integer correlativo, String cmejora) {
        this.correlativo = correlativo;
        this.cmejora = cmejora;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public String getCmejora() {
        return cmejora;
    }

    public void setCmejora(String cmejora) {
        this.cmejora = cmejora;
    }

    public TMejoras getEcodMejora() {
        return ecodMejora;
    }

    public void setEcodMejora(TMejoras ecodMejora) {
        this.ecodMejora = ecodMejora;
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
        if (!(object instanceof TMejorasdetalle)) {
            return false;
        }
        TMejorasdetalle other = (TMejorasdetalle) object;
        if ((this.correlativo == null && other.correlativo != null) || (this.correlativo != null && !this.correlativo.equals(other.correlativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TMejorasdetalle[ correlativo=" + correlativo + " ]";
    }
    
}
