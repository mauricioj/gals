/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PerfilPermissoesFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author phnicezio
 */
@ManagedBean(name = "perfilPermissoesManagedBean")
@ViewScoped
public class PerfilPermissoesManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;
        
    private PerfilPermissoes perfilPermissoes;
    private List<PerfilPermissoes> perfilPermissoess = new ArrayList<PerfilPermissoes>();

    @EJB
    private PerfilPermissoesFachada perfilPermissoesFachada;

    public PerfilPermissoesManagedBean() {}

    public PerfilPermissoes getPerfilPermissoes() {
        return perfilPermissoes;
    }

    public void setPerfilPermissoes(PerfilPermissoes perfilPermissoes) {
        this.perfilPermissoes = perfilPermissoes;
    }

    public List<PerfilPermissoes> getPerfilPermissoess() {
        return perfilPermissoess;
    }

    public void setPerfilPermissoess(List<PerfilPermissoes> perfilPermissoes) {
        this.perfilPermissoess = perfilPermissoes;
    }

    public void alterar() {
        perfilPermissoesFachada.alterar(this.getPerfilPermissoes());
    }

    public void excluir() {
        perfilPermissoesFachada.excluir(this.getPerfilPermissoes());
    }

    public void inserir() {
        perfilPermissoesFachada.inserir(this.getPerfilPermissoes());
    }

    public void listar() {
        this.recuperarPerfilPermissoess();
    }
    
    public void listar(Perfil perfil) {
        this.recuperarPerfilPermissoess(perfil);
    }

    private void recuperarPerfilPermissoess() {
        this.perfilPermissoess = perfilPermissoesFachada.listar();
    }
    
    private void recuperarPerfilPermissoess(Perfil perfil) {
        this.perfilPermissoess = perfilPermissoesFachada.listar(perfil);
    }
}