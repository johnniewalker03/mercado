/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author migue
 */
@Entity
@Table(name = "t_departamentos", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TDepartamentos.findAll", query = "SELECT t FROM TDepartamentos t"),
    @NamedQuery(name = "TDepartamentos.findById", query = "SELECT t FROM TDepartamentos t WHERE t.id = :id"),
    @NamedQuery(name = "TDepartamentos.findByDepartamento", query = "SELECT t FROM TDepartamentos t WHERE t.departamento = :departamento")})
public class TDepartamentos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "departamento")
    private String departamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepto")
    private List<TMunicipios> tMunicipiosList;

    public TDepartamentos() {
    }

    public TDepartamentos(Integer id) {
        this.id = id;
    }

    public TDepartamentos(Integer id, String departamento) {
        this.id = id;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @XmlTransient
    public List<TMunicipios> getTMunicipiosList() {
        return tMunicipiosList;
    }

    public void setTMunicipiosList(List<TMunicipios> tMunicipiosList) {
        this.tMunicipiosList = tMunicipiosList;
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
        if (!(object instanceof TDepartamentos)) {
            return false;
        }
        TDepartamentos other = (TDepartamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TDepartamentos[ id=" + id + " ]";
    }
    
}
