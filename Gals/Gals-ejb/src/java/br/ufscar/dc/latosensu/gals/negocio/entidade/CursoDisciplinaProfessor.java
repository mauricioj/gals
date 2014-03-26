/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "curso_disciplina_professor")
@IdClass(CursoDisciplinaProfessorPK.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoDisciplinaProfessor.findAll", query = "SELECT c FROM CursoDisciplinaProfessor c")})
public class CursoDisciplinaProfessor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    @JoinColumns({
        @JoinColumn(name = "id_disciplina", referencedColumnName = "id_disciplina", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CursoDisciplina cursoDisciplina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoDisciplinaProfessor")
    private Collection<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorCollection;

    public CursoDisciplinaProfessor() {
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public CursoDisciplina getCursoDisciplina() {
        return cursoDisciplina;
    }

    public void setCursoDisciplina(CursoDisciplina cursoDisciplina) {
        this.cursoDisciplina = cursoDisciplina;
    }

    @XmlTransient
    public Collection<CalendarioCursoDisciplinaProfessor> getCalendarioCursoDisciplinaProfessorCollection() {
        return calendarioCursoDisciplinaProfessorCollection;
    }

    public void setCalendarioCursoDisciplinaProfessorCollection(Collection<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorCollection) {
        this.calendarioCursoDisciplinaProfessorCollection = calendarioCursoDisciplinaProfessorCollection;
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

    /*@Override
    public int hashCode() {
    int hash = 5;
    hash = 79 * hash + Objects.hashCode(this.curso);
    hash = 79 * hash + Objects.hashCode(this.disciplina);
    hash = 79 * hash + Objects.hashCode(this.pessoa);
    hash = 79 * hash + Objects.hashCode(this.cursoDisciplina);
    hash = 79 * hash + Objects.hashCode(this.calendarioCursoDisciplinaProfessorCollection);
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
    final CursoDisciplinaProfessor other = (CursoDisciplinaProfessor) obj;
    if (!Objects.equals(this.curso, other.curso)) {
    return false;
    }
    if (!Objects.equals(this.disciplina, other.disciplina)) {
    return false;
    }
    if (!Objects.equals(this.pessoa, other.pessoa)) {
    return false;
    }
    if (!Objects.equals(this.cursoDisciplina, other.cursoDisciplina)) {
    return false;
    }
    if (!Objects.equals(this.calendarioCursoDisciplinaProfessorCollection, other.calendarioCursoDisciplinaProfessorCollection)) {
    return false;
    }
    return true;
    }*/
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final CursoDisciplinaProfessor other = (CursoDisciplinaProfessor) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CursoDisciplinaProfessor{" + "curso=" + curso + ", disciplina=" + disciplina + ", pessoa=" + pessoa + ", cursoDisciplina=" + cursoDisciplina + ", calendarioCursoDisciplinaProfessorCollection=" + calendarioCursoDisciplinaProfessorCollection + '}';
    }

}
