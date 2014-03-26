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
import javax.persistence.FetchType;
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
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findById", query = "SELECT p FROM Perfil p WHERE p.id = :id"),
    @NamedQuery(name = "Perfil.findByDescricao", query = "SELECT p FROM Perfil p WHERE p.descricao = :descricao")})
public class Perfil implements Serializable {
    @JoinTable(name = "pessoa_perfil", joinColumns = {
        @JoinColumn(name = "id_perfil", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Pessoa> pessoaCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @SequenceGenerator(name="Perfil_Generator", sequenceName="perfil_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Perfil_Generator")
    private Integer id;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;
    @JoinTable(name = "usuario_perfil", joinColumns = {
        @JoinColumn(name = "id_perfil", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id")})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil", fetch = FetchType.LAZY)
    private Collection<PerfilPermissoes> perfilPermissoesCollection;

    public Perfil() {
    }

    public Perfil(Integer id) {
        this.id = id;
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

    @XmlTransient
    public Collection<PerfilPermissoes> getPerfilPermissoesCollection() {
        return perfilPermissoesCollection;
    }

    public void setPerfilPermissoesCollection(Collection<PerfilPermissoes> perfilPermissoesCollection) {
        this.perfilPermissoesCollection = perfilPermissoesCollection;
    }
    
    @XmlTransient
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
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
        final Perfil other = (Perfil) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Perfil{" + "id=" + id + ", descricao=" + descricao + ", perfilPermissoesCollection=" + perfilPermissoesCollection + " pessoaCollection=" + pessoaCollection + '}';
    }
}
