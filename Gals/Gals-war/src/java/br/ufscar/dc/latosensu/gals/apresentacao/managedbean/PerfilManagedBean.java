package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Params;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Permissoes;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PerfilFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "perfilManagedBean")
@ViewScoped
public class PerfilManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{perfilPermissoesManagedBean}")
    private PerfilPermissoesManagedBean perfilPermissoesManagedBean;
    @ManagedProperty(value = "#{permissoesManagedBean}")
    private PermissoesManagedBean permissoesManagedBean;
    private Perfil perfil;
    private List<Perfil> perfis = new ArrayList<Perfil>();
    @EJB
    private PerfilFachada perfilFachada;

    public PerfilManagedBean() {
        limpar();
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfilPermissoesManagedBean(PerfilPermissoesManagedBean perfilPermissoesManagedBean) {
        this.perfilPermissoesManagedBean = perfilPermissoesManagedBean;
    }

    public void setPermissoesManagedBean(PermissoesManagedBean permissoesManagedBean) {
        this.permissoesManagedBean = permissoesManagedBean;
    }

    private void limpar() {
        this.perfil = new Perfil();
    }

    public void obterPerfis() {
        permissoesManagedBean.listar();

        if (Params.getPerfil() != null) {
            setPerfil(Params.getPerfil());
            perfilPermissoesManagedBean.listar(perfil);
            perfil.setPerfilPermissoesCollection(perfilPermissoesManagedBean.getPerfilPermissoess());
        }

        if (perfil.getPerfilPermissoesCollection() == null || (perfil.getPerfilPermissoesCollection() != null && perfil.getPerfilPermissoesCollection().isEmpty())) {
            //Insere as permissoes
            List<Permissoes> permissoesList = permissoesManagedBean.getPermissoess();
            List<PerfilPermissoes> perfilPermissoesList = new ArrayList<PerfilPermissoes>();
            for (Permissoes permissao : permissoesList) {
                PerfilPermissoes perfilPermissoes = new PerfilPermissoes();
                perfilPermissoes.setPerfil(this.perfil);
                perfilPermissoes.setPermissoes(permissao);
                perfilPermissoes.setPermite(Boolean.FALSE);
                perfilPermissoes.setEdita(Boolean.FALSE);
                perfilPermissoes.setExclui(Boolean.FALSE);
                perfilPermissoes.setInclui(Boolean.FALSE);
                perfilPermissoesList.add(perfilPermissoes);
            }
            perfil.setPerfilPermissoesCollection(perfilPermissoesList);
        }
        Params.setPerfil(null);
    }

    public void listarPerfis() {
        this.perfis = perfilFachada.listar();
    }
    
    public void inserir() {
        try {
            perfilFachada.inserir(this.getPerfil());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar o perfil!", ""));
        }
    }

    public void alterar() {
        try {
            perfilFachada.alterar(this.getPerfil());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro alterado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao alterar o perfil!", ""));
        }
    }

    public String excluir() {
        permissoesManagedBean.listar();
        perfilPermissoesManagedBean.listar();
        perfil.getPerfilPermissoesCollection();
        perfilFachada.excluir(this.getPerfil());
        return "/perfil/ListarPerfis";
    }

    public String listar() {
        return "/perfil/ListarPerfis";
    }
    
    public String montarPaginaParaAlteracao(Perfil paramPerfil) {
        Params.setPerfil(paramPerfil);
        return "/perfil/AlterarPerfil";
    }

    public String montarPaginaParaExclusao(Perfil paramPerfil) {
        Params.setPerfil(paramPerfil);
        return "/perfil/ExcluirPerfil";
    }

    public String montarPaginaParaInsercao() {
        return "/perfil/InserirPerfil";
    }

    public Perfil getPerfilById(int id) {
        for (Perfil p : perfis) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
