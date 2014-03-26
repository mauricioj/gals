/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;

/**
 *
 * @author Junior
 */
public class PerfilPermissoesPK implements Serializable {
    private int perfil;
    private int permissoes;

    public PerfilPermissoesPK() {
    }

    public PerfilPermissoesPK(int perfil, int permissoes) {
        this.perfil = perfil;
        this.permissoes = permissoes;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public int getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(int permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) perfil;
        hash += (int) permissoes;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilPermissoesPK)) {
            return false;
        }
        PerfilPermissoesPK other = (PerfilPermissoesPK) object;
        if (this.perfil != other.perfil) {
            return false;
        }
        if (this.permissoes != other.permissoes) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.entidade.PerfilPermissoesPK[ perfilId=" + perfil + ", permissoesId=" + permissoes + " ]";
    }
    
}
