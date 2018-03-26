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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yo
 */
@Entity
@Table(name = "t_traslados", catalog =  "mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTraslados.findAll", query = "SELECT t FROM TTraslados t"),
    @NamedQuery(name = "TTraslados.findById", query = "SELECT t FROM TTraslados t WHERE t.id = :id"),
    @NamedQuery(name = "TTraslados.findByFechaTraslado", query = "SELECT t FROM TTraslados t WHERE t.fechaTraslado = :fechaTraslado"),
    @NamedQuery(name = "TTraslados.findByTodo", query = "SELECT t FROM TTraslados t WHERE t.ecodPuesto.numPuesto LIKE :puesto"),
    @NamedQuery(name = "TTraslados.findByNumRecibo", query = "SELECT t FROM TTraslados t WHERE t.numRecibo = :numRecibo"),
    @NamedQuery(name = "TTraslados.findByComentarios", query = "SELECT t FROM TTraslados t WHERE t.comentarios = :comentarios")})
public class TTraslados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "FechaTraslado")
    @Temporal(TemporalType.DATE)
    private Date fechaTraslado;
    @Size(max = 12)
    @Column(name = "NumRecibo")
    private String numRecibo;
    @Size(max = 250)
    @Column(name = "Comentarios")
    private String comentarios;
    @JoinColumn(name = "EcodArrendaAnte", referencedColumnName = "EcodArrenda")
    @ManyToOne(optional = false)
    private TArrendatario ecodArrendaAnte;
    @JoinColumn(name = "EcodPuesto", referencedColumnName = "Correlativo")
    @ManyToOne(optional = false)
    private TPuesto ecodPuesto;
    @JoinColumn(name = "EcodArrendaNuevo", referencedColumnName = "EcodArrenda")
    @ManyToOne(optional = false)
    private TArrendatario ecodArrendaNuevo;

    public TTraslados() {
    }

    public TTraslados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public String getNumRecibo() {
        return numRecibo;
    }

    public void setNumRecibo(String numRecibo) {
        this.numRecibo = numRecibo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public TArrendatario getEcodArrendaAnte() {
        return ecodArrendaAnte;
    }

    public void setEcodArrendaAnte(TArrendatario ecodArrendaAnte) {
        this.ecodArrendaAnte = ecodArrendaAnte;
    }

    public TPuesto getEcodPuesto() {
        return ecodPuesto;
    }

    public void setEcodPuesto(TPuesto ecodPuesto) {
        this.ecodPuesto = ecodPuesto;
    }

    public TArrendatario getEcodArrendaNuevo() {
        return ecodArrendaNuevo;
    }

    public void setEcodArrendaNuevo(TArrendatario ecodArrendaNuevo) {
        this.ecodArrendaNuevo = ecodArrendaNuevo;
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
        if (!(object instanceof TTraslados)) {
            return false;
        }
        TTraslados other = (TTraslados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entities.TTraslados[ id=" + id + " ]";
    }
    
}
