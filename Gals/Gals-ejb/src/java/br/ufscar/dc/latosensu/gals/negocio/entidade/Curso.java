/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "curso", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"descricao"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id"),
    @NamedQuery(name = "Curso.findByDescricao", query = "SELECT c FROM Curso c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Curso.findByCargaHorariaTotal", query = "SELECT c FROM Curso c WHERE c.cargaHorariaTotal = :cargaHorariaTotal"),
    @NamedQuery(name = "Curso.findByCargaHorariaDistancia", query = "SELECT c FROM Curso c WHERE c.cargaHorariaDistancia = :cargaHorariaDistancia"),
    @NamedQuery(name = "Curso.findByDataInicial", query = "SELECT c FROM Curso c WHERE c.dataInicial = :dataInicial"),
    @NamedQuery(name = "Curso.findByDataFinal", query = "SELECT c FROM Curso c WHERE c.dataFinal = :dataFinal")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name="Curso_Generator", sequenceName="curso_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Curso_Generator")
    private Integer id;
    @Size(max = 200)
    @Column(name = "descricao", length = 200)
    private String descricao;
    @Column(name = "carga_horaria_total")
    private Integer cargaHorariaTotal;
    @Column(name = "carga_horaria_distancia")
    private Integer cargaHorariaDistancia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicial", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_final", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private Collection<CursoDisciplina> cursoDisciplinaCollection;

    @OneToMany(mappedBy = "curso")
    private List<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorList;
    
    @Transient
    private int totalCargaHoraria;
    @Transient
    private int totalCargaHorariaDistancia;
    
    public Curso() {
    }

    public Curso(Integer id) {
        this.id = id;
    }

    public Curso(Integer id, Date dataInicial, Date dataFinal) {
        this.id = id;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }

    public void setCargaHorariaTotal(Integer cargaHorariaTotal) {
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    public Integer getCargaHorariaDistancia() {
        return cargaHorariaDistancia;
    }

    public void setCargaHorariaDistancia(Integer cargaHorariaDistancia) {
        this.cargaHorariaDistancia = cargaHorariaDistancia;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @XmlTransient
    public Collection<CursoDisciplina> getCursoDisciplinaCollection() {
        return cursoDisciplinaCollection;
    }

    public void setCursoDisciplinaCollection(Collection<CursoDisciplina> cursoDisciplinaCollection) {
        this.cursoDisciplinaCollection = cursoDisciplinaCollection;
    }

    public List<CalendarioCursoDisciplinaProfessor> getCalendarioCursoDisciplinaProfessorList() {
        return calendarioCursoDisciplinaProfessorList;
    }

    public void setCalendarioCursoDisciplinaProfessorList(List<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorList) {
        this.calendarioCursoDisciplinaProfessorList = calendarioCursoDisciplinaProfessorList;
    }
    
    public int getTotalCargaHoraria() {
        return totalCargaHoraria;
    }

    public void setTotalCargaHoraria(int totalCargaHoraria) {
        this.totalCargaHoraria = totalCargaHoraria;
    }

    public int getTotalCargaHorariaDistancia() {
        return totalCargaHorariaDistancia;
    }

    public void setTotalCargaHorariaDistancia(int totalCargaHorariaDistancia) {
        this.totalCargaHorariaDistancia = totalCargaHorariaDistancia;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.Curso[ id=" + id + " ]";
    }
    
}
