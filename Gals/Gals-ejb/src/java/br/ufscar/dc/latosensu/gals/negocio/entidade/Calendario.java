/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "calendario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c"),
    @NamedQuery(name = "Calendario.findById", query = "SELECT c FROM Calendario c WHERE c.id = :id"),
    @NamedQuery(name = "Calendario.findByInformacoesCalendario", query = "SELECT c FROM Calendario c WHERE c.informacoesCalendario = :informacoesCalendario")})
public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "informacoes_calendario", length = 2147483647)
    private String informacoesCalendario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendario")
    private Collection<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorCollection;

    public Calendario() {
    }

    public Calendario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInformacoesCalendario() {
        return informacoesCalendario;
    }

    public void setInformacoesCalendario(String informacoesCalendario) {
        this.informacoesCalendario = informacoesCalendario;
    }

    @XmlTransient
    public Collection<CalendarioCursoDisciplinaProfessor> getCalendarioCursoDisciplinaProfessorCollection() {
        return calendarioCursoDisciplinaProfessorCollection;
    }

    public void setCalendarioCursoDisciplinaProfessorCollection(Collection<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorCollection) {
        this.calendarioCursoDisciplinaProfessorCollection = calendarioCursoDisciplinaProfessorCollection;
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
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Calendario[ id=" + id + " ]";
    }
    
}
