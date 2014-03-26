/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Params;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PerfilFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author phnicezio
 */
@ManagedBean(name = "pessoaManagedBean")
@ViewScoped
public class PessoaManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private PessoaFachada pessoaFachada;
    private Pessoa pessoa;
    private List<Pessoa> pessoas;
    @EJB
    private PerfilFachada perfilFachada;
    private List<Perfil> listaPerfis;
    private DualListModel<Perfil> perfis;

    public PessoaManagedBean() {
        limpar();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public DualListModel<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(DualListModel<Perfil> perfis) {
        this.perfis = perfis;
    }

    private void limpar() {
        pessoa = new Pessoa();
        pessoas = new ArrayList<Pessoa>();
        listaPerfis = new ArrayList<Perfil>();
    }
    
    public void obterPerfis() {        
        listaPerfis = perfilFachada.listar();
        List<Perfil> perfisSource = new ArrayList<Perfil>();        
        List<Perfil> perfisTarget = new ArrayList<Perfil>();
        
        if (!listaPerfis.isEmpty()) {
            for (Perfil p : listaPerfis) {
                perfisSource.add(p);
            }
        }
        if (Params.getPessoa() != null) {
            Pessoa pessoaPesquisada = pessoaFachada.recuperarPessoa(Params.getPessoa().getId());            
            if (pessoaPesquisada != null) {
                setPessoa(pessoaPesquisada);
                if (!pessoa.getPerfilCollection().isEmpty()) {
                    for (Perfil perfil : pessoa.getPerfilCollection()) {
                        perfisTarget.add(perfil);
                    }
                    
                }
            }
        }
        // remove da lista, perfis que já existem para a pessoa
        for (Perfil pT : perfisTarget) {
            if (perfisSource.contains(pT)) {
                perfisSource.remove(pT);
            }
        }
        perfis = new DualListModel<Perfil>(perfisSource, perfisTarget);
        Params.setPessoa(null);
    }

    public String montarPaginaParaAlteracao(Pessoa paramPessoa) {
        Params.setPessoa(paramPessoa);
        return "/pessoa/AlterarPessoa";
    }

    public void listarPessoas() {
        List<Pessoa> listaPessoaTemp = pessoaFachada.listar();
        for (Pessoa p : listaPessoaTemp) {
            this.pessoas.add(p);
        }
    }

    public void inserir() {
        try {
            pessoa.setUserName(pessoa.getCpf());
            pessoa.setPassword(pessoa.getCpf());            
            this.getPessoa().setPerfilCollection(perfis.getTarget());
            pessoaFachada.inserir(this.getPessoa());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar a pessoa!", ""));
        }
    }

    public void alterar() {
        pessoa.setUserName(pessoa.getCpf());
        try {
            this.getPessoa().setPerfilCollection(perfis.getTarget());
                pessoaFachada.alterar(this.getPessoa());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro alterado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao alterar o cadastro de pessoa!", ""));
        }
    }

    public String montarPaginaParaInsercao() {
        return "/pessoa/AlterarPessoa";
    }

    public String listar() {
        return "/pessoa/ListarPessoas";
    }
}