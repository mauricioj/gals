/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Params;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CursoFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.DisciplinaFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author phnicezio
 */
@ManagedBean(name = "cursoManagedBean")
@ViewScoped
public class CursoManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private CursoFachada cursoFachada;
    private boolean skip;
    private boolean desabilitaBotao;
    private Curso curso;
    private Pessoa professor;
    private List<Curso> cursos;
    private List<Pessoa> coordenadores;
    private List<Pessoa> professores;
    private DualListModel<Pessoa> professoresList;
    private List<Disciplina> disciplinas;
    private CursoDisciplina cursoDisciplina;
    private List<CursoDisciplina> cursoDisciplinas = new ArrayList<CursoDisciplina>();
    @EJB
    private PessoaFachada pessoaFachada;
    @EJB
    private DisciplinaFachada disciplinaFachada;

    public CursoManagedBean() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Pessoa> getCoordenadores() {
        return coordenadores;
    }

    public void setCoordenadores(List<Pessoa> coordenadores) {
        this.coordenadores = coordenadores;
    }

    public CursoDisciplina getCursoDisciplina() {
        return cursoDisciplina;
    }

    public void setCursoDisciplina(CursoDisciplina cursoDisciplina) {
        this.cursoDisciplina = cursoDisciplina;
    }

    public List<CursoDisciplina> getCursoDisciplinas() {
        return cursoDisciplinas;
    }

    public void setCursoDisciplinas(List<CursoDisciplina> cursoDisciplinas) {
        this.cursoDisciplinas = cursoDisciplinas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Pessoa getProfessor() {
        return professor;
    }

    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    public List<Pessoa> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Pessoa> professores) {
        this.professores = professores;
    }

    public DualListModel<Pessoa> getProfessoresList() {
        return professoresList;
    }

    public void setProfessoresList(DualListModel<Pessoa> professoresList) {
        this.professoresList = professoresList;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean isDesabilitaBotao() {
        return desabilitaBotao;
    }

    public void setDesabilitaBotao(boolean desabilitaBotao) {
        this.desabilitaBotao = desabilitaBotao;
    }
    
    @PostConstruct
    private void limpar() {
        this.curso = new Curso();
        this.cursoDisciplina = new CursoDisciplina();
        this.cursoDisciplina.setPessoaCollection(new ArrayList<Pessoa>());
        this.cursoDisciplinas = new ArrayList();
    }
    
    public void carregarListas() {
        this.coordenadores = pessoaFachada.listarCoordenadores();
        this.professores = pessoaFachada.listarProfessores();
        this.disciplinas = disciplinaFachada.listar();
        obterProfessores();
        
        if (Params.getCurso() != null) {
            setCurso(Params.getCurso());
            this.cursoDisciplinas.addAll(this.curso.getCursoDisciplinaCollection());
            this.curso = cursoFachada.somarCargaHoraria(this.curso, this.cursoDisciplinas);
        }
        Params.setCurso(null);
    }
    
    public void obterProfessores() {
        List<Pessoa> professorSource = new ArrayList<Pessoa>();
        List<Pessoa> professorTarget = new ArrayList<Pessoa>();

        if (!professores.isEmpty()) {
            for (Pessoa p : professores) {
                professorSource.add(p);
            }
        }
        professoresList = new DualListModel<Pessoa>(professorSource, professorTarget);
    }

    public String montarPaginaParaInsercao() {        
        return "/curso/AlterarCurso";
    }

    public String montarPaginaParaAlteracao(Curso paramCurso) {
        Params.setCurso(paramCurso);
        return "/curso/AlterarCurso";
    }

    public String listar() {
        return "/curso/ListarCursos";
    }

    public void listarCursos() {
        curso = new Curso();
        cursos = new ArrayList<Curso>();

        List<Curso> listaCursoTemp = cursoFachada.listar();
        for (Curso c : listaCursoTemp) {
            this.cursos.add(c);
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void inserir() {
        try {
            insereDisciplinas();

            if (cursoFachada.validarCargaHoraria(this.getCurso())) {
                cursoFachada.inserir(this.getCurso());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga horária e/ou Carga horária a Distancia das disciplinas não preenche o total da carga horária e/ou Carga horária a Distancia do curso.", ""));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar a curso!", ""));
        }
    }

    public void alterar() {
        try {
            insereDisciplinas();

            if (cursoFachada.validarCargaHoraria(this.getCurso())) {
                cursoFachada.alterar(this.getCurso()); // altera curso
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro alterado com sucesso!", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga horária e/ou Carga horária a Distancia das disciplinas não preenche o total da carga horária e/ou Carga horária a Distancia do curso.", ""));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao alterar o cadastro de curso!", ""));
        }
    }

    public void reinitCursoDisciplina() {
        for (CursoDisciplina cd : cursoDisciplinas) {
            if (cd.getDisciplina().equals(this.cursoDisciplina.getDisciplina())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Esta disciplina já foi cadastrada!", ""));
                return;
            }
        }

        int totalCargaHoraria = 0;
        int totalCargaHorariaDistancia = 0;
        for (CursoDisciplina cd : cursoDisciplinas) {
            totalCargaHoraria += cd.getCargaHoraria();
            totalCargaHorariaDistancia += cd.getCargaHorariaDistancia();
        }
        totalCargaHoraria += this.cursoDisciplina.getCargaHoraria();
        totalCargaHorariaDistancia += this.cursoDisciplina.getCargaHorariaDistancia();

        if (totalCargaHoraria > this.curso.getCargaHorariaTotal()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "A soma da carga horária execedeu a carga horária do curso! Carga horário do curso: " + this.curso.getCargaHorariaTotal() + " horas.", ""));
            return;
        }
        if (totalCargaHorariaDistancia > this.curso.getCargaHorariaDistancia()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "A soma da carga horária a distancia execedeu a carga horária a distancia do curso! Carga horário a distancia do curso: " + this.curso.getCargaHorariaDistancia() + " horas.", ""));
            return;
        }

        this.cursoDisciplina.setCurso(curso);
        this.cursoDisciplina.setPessoaCollection(professoresList.getTarget());
        this.cursoDisciplinas.add(this.cursoDisciplina); // add a disciplina na lista
        this.curso = cursoFachada.somarCargaHoraria(this.curso, this.cursoDisciplinas);
        this.cursoDisciplina = new CursoDisciplina();
        obterProfessores();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Disciplina adicionada com sucesso!", ""));
    }

    public void removeCursoDisciplina() {
        this.cursoDisciplinas.remove(cursoDisciplina);
        this.curso = cursoFachada.somarCargaHoraria(this.curso, this.cursoDisciplinas);
        this.cursoDisciplina = new CursoDisciplina();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Disciplina excluída com sucesso!", ""));
    }

    private void insereDisciplinas() {
        if (this.curso.getCursoDisciplinaCollection() == null) {
            this.curso.setCursoDisciplinaCollection(new ArrayList<CursoDisciplina>());
        }
        this.curso.getCursoDisciplinaCollection().clear();
        this.curso.getCursoDisciplinaCollection().addAll(this.cursoDisciplinas);
    }

    public List<Pessoa> getCursoDisciplinaProfessores(CursoDisciplina cd) {
        List<Pessoa> retorno = new ArrayList<Pessoa>();
        for (Pessoa p : cd.getPessoaCollection()) {
            retorno.add(p);
        }
        return retorno;
    }

    public void removeCursoDisciplinaProfessor(CursoDisciplina cd, Pessoa professor) {
        cd.getPessoaCollection().remove(professor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor excluído com sucesso!", ""));
    }

    public void carregarListaDisciplinas(Curso curso) {
        this.cursoDisciplinas.clear();  
        this.cursoDisciplinas.addAll(curso.getCursoDisciplinaCollection());
        this.curso = cursoFachada.somarCargaHoraria(this.curso, this.cursoDisciplinas);
    }
    
    public void validarDataDeInicioEFim() {
        if (this.curso.getDataInicial() != null && this.curso.getDataFinal() != null) {
            boolean valida = cursoFachada.validarDataDeInicioEFim(this.curso.getDataInicial(), this.curso.getDataFinal());
            if (!valida) {
                desabilitaBotao = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Final deve ser maior que data Inicial!", ""));
            } else {
                desabilitaBotao = false;
            }
        }
        
    }
}
