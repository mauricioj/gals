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
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "curso_disciplina")
@IdClass(CursoDisciplinaPK.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoDisciplina.findAll", query = "SELECT c FROM CursoDisciplina c"),
    @NamedQuery(name = "CursoDisciplina.findByCargaHoraria", query = "SELECT c FROM CursoDisciplina c WHERE c.cargaHoraria = :cargaHoraria"),
    @NamedQuery(name = "CursoDisciplina.findByCargaHorariaDistancia", query = "SELECT c FROM CursoDisciplina c WHERE c.cargaHorariaDistancia = :cargaHorariaDistancia")})
public class CursoDisciplina implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoDisciplina")
    private Collection<CursoDisciplinaProfessor> cursoDisciplinaProfessorCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_horaria_distancia", nullable = false)
    private int cargaHorariaDistancia;
    @JoinTable(name = "curso_disciplina_professor", joinColumns = {
        @JoinColumn(name = "id_disciplina", referencedColumnName = "id_disciplina", nullable = false),
        @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Pessoa> pessoaCollection;
    
    public CursoDisciplina() {
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCargaHorariaDistancia() {
        return cargaHorariaDistancia;
    }

    public void setCargaHorariaDistancia(int cargaHorariaDistancia) {
        this.cargaHorariaDistancia = cargaHorariaDistancia;
    }

    @XmlTransient
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @XmlTransient
    public Collection<CursoDisciplinaProfessor> getCursoDisciplinaProfessorCollection() {
        return cursoDisciplinaProfessorCollection;
    }

    public void setCursoDisciplinaProfessorCollection(Collection<CursoDisciplinaProfessor> cursoDisciplinaProfessorCollection) {
        this.cursoDisciplinaProfessorCollection = cursoDisciplinaProfessorCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CursoDisciplina other = (CursoDisciplina) obj;
        return true;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CursoDisciplina other = (CursoDisciplina) obj;
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        return true;
    }*/

    @Override
    public String toString() {
        return "CursoDisciplina{" + "curso=" + curso + ", disciplina=" + disciplina + '}';
    }
}
