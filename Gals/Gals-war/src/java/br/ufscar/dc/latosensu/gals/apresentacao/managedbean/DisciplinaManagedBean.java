package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Params;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.fachada.DisciplinaFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "disciplinaManagedBean")
@ViewScoped
public class DisciplinaManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Disciplina disciplina;
    private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    @EJB
    private DisciplinaFachada disciplinaFachada;

    public DisciplinaManagedBean() {
        limpar();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    private void limpar() {
        this.disciplina = new Disciplina();
    }

    public void inserir() {
        try {
            disciplinaFachada.inserir(this.getDisciplina());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar a disciplina!", ""));
        }
    }

    public void alterar() {
        try {
            disciplinaFachada.alterar(this.getDisciplina());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro alterado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao alterar o cadastro da disciplina!", ""));
        }
    }

    public void excluir() {
        try {
            disciplinaFachada.excluir(this.getDisciplina());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro deletado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao deletar a disciplina!", ""));
        }
    }

    public void listarDisciplinas() {
        List<Disciplina> listaDisciplinaTemp = disciplinaFachada.listar();
        for (Disciplina p : listaDisciplinaTemp) {
            this.disciplinas.add(p);
        }
    }

    public String listar() {
        return "/disciplina/ListarDisciplinas";
    }

    public String montarPaginaParaAlteracao(Disciplina paramDisciplina) {
        Params.setDisciplina(paramDisciplina);
        return "/disciplina/AlterarDisciplina";
    }

    public String montarPaginaParaExclusao(Disciplina paramDisciplina) {
        Params.setDisciplina(paramDisciplina);
        return "/disciplina/ExcluirDisciplina";
    }

    public String montarPaginaParaInsercao() {
        return "/disciplina/InserirDisciplina";
    }

    private void recuperarDisciplinas() {
        this.disciplinas = disciplinaFachada.listar();
    }

    public Disciplina getDisciplinaById(int id) {
        for (Disciplina d : disciplinas) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public void obterDisciplinas() {
        if (Params.getDisciplina() != null) {
            setDisciplina(Params.getDisciplina());
        }
        Params.setDisciplina(null);
    }
}
