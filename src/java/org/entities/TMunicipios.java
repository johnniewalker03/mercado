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
 * @author migue
 */
@Entity
@Table(name = "t_municipios", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TMunicipios.findAll", query = "SELECT t FROM TMunicipios t"),
    @NamedQuery(name = "TMunicipios.findById", query = "SELECT t FROM TMunicipios t WHERE t.id = :id"),
    @NamedQuery(name = "TMunicipios.findByIdDepto", query = "SELECT t FROM TMunicipios t WHERE t.idDepto = :idDepto"),
    @NamedQuery(name = "TMunicipios.findByMunicipio", query = "SELECT t FROM TMunicipios t WHERE t.municipio = :municipio"),
    @NamedQuery(name = "TMunicipios.findByDepto", query = "SELECT t FROM TMunicipios t WHERE t.idDepto.id = :depto")})    
public class TMunicipios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "municipio")
    private String municipio;
    @JoinColumn(name = "id_depto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDepartamentos idDepto;

    public TMunicipios() {
    }

    public TMunicipios(Integer id) {
        this.id = id;
    }

    public TMunicipios(Integer id, String municipio) {
        this.id = id;
        this.municipio = municipio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public TDepartamentos getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(TDepartamentos idDepto) {
        this.idDepto = idDepto;
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
        if (!(object instanceof TMunicipios)) {
            return false;
        }
        TMunicipios other = (TMunicipios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TMunicipios[ id=" + id + " ]";
    }
    
}
