/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "perfil_permissoes")
@IdClass(PerfilPermissoesPK.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilPermissoes.findAll", query = "SELECT p FROM PerfilPermissoes p"),
    @NamedQuery(name = "PerfilPermissoes.findByPermite", query = "SELECT p FROM PerfilPermissoes p WHERE p.permite = :permite"),
    @NamedQuery(name = "PerfilPermissoes.findByInclui", query = "SELECT p FROM PerfilPermissoes p WHERE p.inclui = :inclui"),
    @NamedQuery(name = "PerfilPermissoes.findByEdita", query = "SELECT p FROM PerfilPermissoes p WHERE p.edita = :edita"),
    @NamedQuery(name = "PerfilPermissoes.findByExclui", query = "SELECT p FROM PerfilPermissoes p WHERE p.exclui = :exclui")})
public class PerfilPermissoes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne()
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_permissao")
    private Permissoes permissoes;
    @Column(name = "permite")
    private Boolean permite;
    @Column(name = "inclui")
    private Boolean inclui;
    @Column(name = "edita")
    private Boolean edita;
    @Column(name = "exclui")
    private Boolean exclui;

    public PerfilPermissoes() {
    }

    public Boolean getPermite() {
        return permite;
    }

    public void setPermite(Boolean permite) {
        this.permite = permite;
    }

    public Boolean getInclui() {
        return inclui;
    }

    public void setInclui(Boolean inclui) {
        this.inclui = inclui;
    }

    public Boolean getEdita() {
        return edita;
    }

    public void setEdita(Boolean edita) {
        this.edita = edita;
    }

    public Boolean getExclui() {
        return exclui;
    }

    public void setExclui(Boolean exclui) {
        this.exclui = exclui;
    }

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((perfil != null && permissoes != null) ? perfil.hashCode() + permissoes.hashCode() : 0) ;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilPermissoes)) {
            return false;
        }
        PerfilPermissoes other = (PerfilPermissoes) object;
        if ((this.getPerfil() == null && other.getPerfil() != null) ||
                (this.getPermissoes() == null && other.getPermissoes() != null) ||
                (this.getPerfil() != null && !this.perfil.equals(other.perfil)) ||
                (this.getPermissoes() != null && !this.permissoes.equals(other.permissoes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.PerfilPermissoes[ perfilId=" + this.getPerfil().getId() + ", permissoesId=" + this.getPermissoes().getId() + " ]";
    }
    
}
