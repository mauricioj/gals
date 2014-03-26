/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Permissoes;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PermissoesFachada;
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
@ManagedBean(name = "permissoesManagedBean")
@ViewScoped
public class PermissoesManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;
        
    private Permissoes permissoes;
    private List<Permissoes> permissoess = new ArrayList<Permissoes>();

    @EJB
    private PermissoesFachada permissoesFachada;

    public PermissoesManagedBean() {}

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public List<Permissoes> getPermissoess() {
        return permissoess;
    }

    public void setPermissoess(List<Permissoes> permissoes) {
        this.permissoess = permissoes;
    }
    
    public String alterar() {
        permissoesFachada.alterar(this.getPermissoes());
        this.recuperarPermissoess();
        return "/permissoes/ListarPermissoess";
    }

    public String excluir() {
        permissoesFachada.excluir(this.getPermissoes());
        this.recuperarPermissoess();
        return "/permissoes/ListarPermissoess";
    }

    public String inserir() {
        permissoesFachada.inserir(this.getPermissoes());
        this.recuperarPermissoess();
        return "/permissoes/ListarPermissoess";
    }

    public void listar() {
        this.recuperarPermissoess();
//        return "/permissoes/ListarPermissoess";
    }

    public String montarPaginaParaAlteracao() {
        return "/permissoes/AlterarPermissoes";
    }

    public String montarPaginaParaExclusao() {
        return "/permissoes/ExcluirPermissoes";
    }

    public String montarPaginaParaInsercao() {
        this.permissoes = new Permissoes();
        return "/permissoes/InserirPermissoes";
    }

    private void recuperarPermissoess() {
        this.permissoess = permissoesFachada.listar();
    }
}