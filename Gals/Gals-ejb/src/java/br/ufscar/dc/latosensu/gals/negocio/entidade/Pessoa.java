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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id"),
    @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByEMail", query = "SELECT p FROM Pessoa p WHERE p.eMail = :eMail")})
public class Pessoa implements Serializable {
    @Size(max = 30)
    @Column(name = "user_name", length = 30)
    private String userName;
    @Size(max = 40)
    @Column(name = "password", length = 40)
    private String password;
    @JoinTable(name = "pessoa_perfil", joinColumns = {
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_perfil", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Perfil> perfilCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Collection<CursoDisciplinaProfessor> cursoDisciplinaProfessorCollection;
    @ManyToMany(mappedBy = "pessoaCollection")
    private Collection<CursoDisciplina> cursoDisciplinaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Collection<Curso> cursoCollection;
    @Column(name = "tipo_pessoa_aluno")
    private Boolean tipoPessoaAluno;
    @Column(name = "tipo_pessoa_professor")
    private Boolean tipoPessoaProfessor;
    @Column(name = "tipo_pessoa_coordenador")
    private Boolean tipoPessoaCoordenador;
    @Size(max = 50)
    @Column(name = "matricula")
    private String matricula;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name="Pessoa_Generator", sequenceName="pessoa_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Pessoa_Generator")
    private Integer id;
    @Size(max = 11)
    @Column(name = "cpf")
    private String cpf;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "e_mail")
    private String eMail;

    public Pessoa() {
    }

    public Pessoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @XmlTransient
    public Collection<CursoDisciplina> getCursoDisciplinaCollection() {
        return cursoDisciplinaCollection;
    }

    public void setCursoDisciplinaCollection(Collection<CursoDisciplina> cursoDisciplinaCollection) {
        this.cursoDisciplinaCollection = cursoDisciplinaCollection;
    }

    @XmlTransient
    public Collection<Curso> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<Curso> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    public Boolean getTipoPessoaAluno() {
        return tipoPessoaAluno;
    }

    public void setTipoPessoaAluno(Boolean tipoPessoaAluno) {
        this.tipoPessoaAluno = tipoPessoaAluno;
    }

    public Boolean getTipoPessoaProfessor() {
        return tipoPessoaProfessor;
    }

    public void setTipoPessoaProfessor(Boolean tipoPessoaProfessor) {
        this.tipoPessoaProfessor = tipoPessoaProfessor;
    }

    public Boolean getTipoPessoaCoordenador() {
        return tipoPessoaCoordenador;
    }

    public void setTipoPessoaCoordenador(Boolean tipoPessoaCoordenador) {
        this.tipoPessoaCoordenador = tipoPessoaCoordenador;
    }

    @XmlTransient
    public Collection<CursoDisciplinaProfessor> getCursoDisciplinaProfessorCollection() {
        return cursoDisciplinaProfessorCollection;
    }

    public void setCursoDisciplinaProfessorCollection(Collection<CursoDisciplinaProfessor> cursoDisciplinaProfessorCollection) {
        this.cursoDisciplinaProfessorCollection = cursoDisciplinaProfessorCollection;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Perfil> getPerfilCollection() {
        return perfilCollection;
    }

    public void setPerfilCollection(Collection<Perfil> perfilCollection) {
        this.perfilCollection = perfilCollection;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "userName=" + userName + ", password=" + password + ", perfilCollection=" + perfilCollection + ", cursoDisciplinaProfessorCollection=" + cursoDisciplinaProfessorCollection + ", cursoDisciplinaCollection=" + cursoDisciplinaCollection + ", cursoCollection=" + cursoCollection + ", tipoPessoaAluno=" + tipoPessoaAluno + ", tipoPessoaProfessor=" + tipoPessoaProfessor + ", tipoPessoaCoordenador=" + tipoPessoaCoordenador + ", matricula=" + matricula + ", id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", eMail=" + eMail + '}';
    }
    
    
}
