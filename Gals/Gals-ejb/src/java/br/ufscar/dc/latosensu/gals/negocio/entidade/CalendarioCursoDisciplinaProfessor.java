/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matheus
 */
@Entity
@Table(name = "calendario_curso_disciplina_professor")
@IdClass(CalendarioCursoDisciplinaProfessorPK.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalendarioCursoDisciplinaProfessor.findAll", query = "SELECT c FROM CalendarioCursoDisciplinaProfessor c"),
    @NamedQuery(name = "CalendarioCursoDisciplinaProfessor.findByDataAula", query = "SELECT c FROM CalendarioCursoDisciplinaProfessor c WHERE c.dataAula = :dataAula"),
    @NamedQuery(name = "CalendarioCursoDisciplinaProfessor.findByAulaDistancia", query = "SELECT c FROM CalendarioCursoDisciplinaProfessor c WHERE c.aulaDistancia = :aulaDistancia"),
    @NamedQuery(name = "CalendarioCursoDisciplinaProfessor.findByInformacoesAula", query = "SELECT c FROM CalendarioCursoDisciplinaProfessor c WHERE c.informacoesAula = :informacoesAula")})
public class CalendarioCursoDisciplinaProfessor implements Serializable {
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
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_calendario")
    private Calendario calendario;
    
    @Id
    @Future(message="Data precisa ser maior que a data atual")
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_aula", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAula;
    
    @Column(name = "aula_distancia")
    private Boolean aulaDistancia;
    @Size(max = 2147483647)
    @Column(name = "informacoes_aula", length = 2147483647)
    private String informacoesAula;
    @JoinColumns({
        @JoinColumn(name = "id_disciplina", referencedColumnName = "id_disciplina", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CursoDisciplinaProfessor cursoDisciplinaProfessor;
    
    @Transient
    private String aulaDistanciaDescricao;

    public CalendarioCursoDisciplinaProfessor() {
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    public Boolean getAulaDistancia() {
        return aulaDistancia;
    }

    public void setAulaDistancia(Boolean aulaDistancia) {
        this.aulaDistancia = aulaDistancia;
    }

    public String getInformacoesAula() {
        return informacoesAula;
    }

    public void setInformacoesAula(String informacoesAula) {
        this.informacoesAula = informacoesAula;
    }

    public CursoDisciplinaProfessor getCursoDisciplinaProfessor() {
        return cursoDisciplinaProfessor;
    }

    public void setCursoDisciplinaProfessor(CursoDisciplinaProfessor cursoDisciplinaProfessor) {
        this.cursoDisciplinaProfessor = cursoDisciplinaProfessor;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
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
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getAulaDistanciaDescricao() {
        return aulaDistanciaDescricao;
    }

    public void setAulaDistanciaDescricao(String aulaDistanciaDescricao) {
        this.aulaDistanciaDescricao = aulaDistanciaDescricao;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    /*@Override
    public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.curso);
    hash = 89 * hash + Objects.hashCode(this.disciplina);
    hash = 89 * hash + Objects.hashCode(this.pessoa);
    hash = 89 * hash + Objects.hashCode(this.calendario);
    hash = 89 * hash + Objects.hashCode(this.cursoDisciplinaProfessor);
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
    final CalendarioCursoDisciplinaProfessor other = (CalendarioCursoDisciplinaProfessor) obj;
    if (!Objects.equals(this.curso, other.curso)) {
    return false;
    }
    if (!Objects.equals(this.disciplina, other.disciplina)) {
    return false;
    }
    if (!Objects.equals(this.pessoa, other.pessoa)) {
    return false;
    }
    if (!Objects.equals(this.calendario, other.calendario)) {
    return false;
    }
    if (!Objects.equals(this.cursoDisciplinaProfessor, other.cursoDisciplinaProfessor)) {
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
        final CalendarioCursoDisciplinaProfessor other = (CalendarioCursoDisciplinaProfessor) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CalendarioCursoDisciplinaProfessor{" + "curso=" + curso + ", disciplina=" + disciplina + ", pessoa=" + pessoa + ", calendario=" + calendario + ", dataAula=" + dataAula + ", aulaDistancia=" + aulaDistancia + ", informacoesAula=" + informacoesAula + ", cursoDisciplinaProfessor=" + cursoDisciplinaProfessor + '}';
    }

}
