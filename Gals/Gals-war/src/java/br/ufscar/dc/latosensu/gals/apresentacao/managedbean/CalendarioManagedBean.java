/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.managedbean;

import br.ufscar.dc.latosensu.gals.apresentacao.utils.Params;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Calendario;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioCursoDisciplinaProfessorFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CalendarioFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.CursoFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.DisciplinaFachada;
import br.ufscar.dc.latosensu.gals.negocio.fachada.PessoaFachada;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author matheus
 */
@ManagedBean(name = "calendarioManagedBean")
@ViewScoped
public class CalendarioManagedBean {

    private static final long serialVersionUID = 1L;
    @EJB
    private CalendarioFachada calendarioFachada;
    @EJB
    private CalendarioCursoDisciplinaProfessorFachada calendarioCursoDisciplinaProfessorFachada;
    @EJB
    private CursoFachada cursoFachada;
    @EJB
    private PessoaFachada pessoaFachada;
    @EJB
    private DisciplinaFachada disciplinaFachada;
    private boolean skip;
    private boolean existeCurso;
    private Calendario calendario;
    private Pessoa professor;
    private Curso curso;
    private Disciplina disciplina;
    private List<Calendario> calendarios;
    private List<Curso> cursos;
    private List<Curso> cursosList;
    private List<Pessoa> professores;
    private List<Disciplina> disciplinas;
    private CalendarioCursoDisciplinaProfessor calendarioCDP;
    private List<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorList;

    public CalendarioManagedBean() {
    }

    @PostConstruct
    private void limpar() {
        this.calendario = new Calendario();
        this.curso = new Curso();
        this.disciplina = new Disciplina();
        this.professor = new Pessoa();
        this.calendarioCDP = new CalendarioCursoDisciplinaProfessor();
        this.calendarioCursoDisciplinaProfessorList = new ArrayList<CalendarioCursoDisciplinaProfessor>();
        existeCurso = false;
    }

    public void obterCursos() {
        if (Params.getCurso() != null) {
            setCurso(Params.getCurso());
            existeCurso = true;
            this.calendarioCursoDisciplinaProfessorList = calendarioCursoDisciplinaProfessorFachada.listar(curso.getId());
            if (calendarioCursoDisciplinaProfessorList.size() > 0) {
                this.calendario = calendarioCursoDisciplinaProfessorList.get(0).getCalendario();
            }
            recuperarDisciplinaPorCurso();
        } else {
            this.calendarioCursoDisciplinaProfessorList = calendarioCursoDisciplinaProfessorFachada.listar();
            this.cursos = cursoFachada.listar();
            this.cursosList = cursoFachada.listar();
            for (CalendarioCursoDisciplinaProfessor ccdp : calendarioCursoDisciplinaProfessorList) { //carregar somente cursos que não possuem calendário
                if (!cursos.isEmpty()) {
                    for (Curso cur : cursosList) {
                        if (cur.getId() == ccdp.getCurso().getId()) {
                            cursos.remove(cur);
                        }
                    }
                }
            }
            if ((cursos.isEmpty()) && (calendarioCursoDisciplinaProfessorList.isEmpty())) {
                this.cursos = cursoFachada.listar();
            }
            this.calendarioCursoDisciplinaProfessorList.clear();
            this.cursosList = null;
        }
        Params.setCurso(null);
    }

    public String montarPaginaParaInsercao() {
        return "/calendario/Calendario";
    }

    public String montarPaginaParaAlteracao(Curso paramCurso) {
        Params.setCurso(paramCurso);
        return "/calendario/Calendario";
    }

    public void exibirCalendarioPorCurso() {
        this.calendarioCursoDisciplinaProfessorList.clear();
        this.calendarioCursoDisciplinaProfessorList = calendarioCursoDisciplinaProfessorFachada.listar(curso.getId());
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public List<Disciplina> getDisciplinaPorCurso() {
        return this.disciplinas;
    }

    public void recuperarDisciplinaPorCurso() {
        this.disciplinas = disciplinaFachada.recuperarPorCurso(curso.getId());
    }

    public List<Pessoa> getProfessorPorCursoDisciplina() {
        this.professores = pessoaFachada.recuperarPorCursoDisciplina(curso.getId(), disciplina.getId());
        return professores;
    }

    public void recuperarProfessorPorCursoDisciplina() {
        this.getProfessorPorCursoDisciplina();
    }

    public void listarCalendarios() {
        int i;
        this.calendarioCursoDisciplinaProfessorList = calendarioCursoDisciplinaProfessorFachada.listar();
        this.cursos = new ArrayList<Curso>();
        i = 0;
        for (CalendarioCursoDisciplinaProfessor ccdp : calendarioCursoDisciplinaProfessorList) {
            if (i == 0) {
                this.curso = ccdp.getCurso();
                cursos.add(ccdp.getCurso());
                i = 1;
            } else {
                if (ccdp.getCurso() != curso) {
                    i = 0;
                }
            }
        }
    }

    public String listar() {
        return "/calendario/ListarCalendarios";
    }

    public void inserir() {
        try {
            calendarioFachada.inserir(this.getCalendario());
            inserirCalendarioCursoDisciplinaProfessor(); // chama o método para inserir as aulas
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Calendário letivo cadastrado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar o calendário!", ""));
        }        
    }

    public void alterar() {
        try {
            calendarioFachada.alterar(this.getCalendario());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro alterado com sucesso!", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao alterar o cadastro de calendário!", ""));
        }
    }

    public void inserirCalendarioCursoDisciplinaProfessor() {
        for (CalendarioCursoDisciplinaProfessor ccdp : calendarioCursoDisciplinaProfessorList) {
            ccdp.setCalendario(this.getCalendario());
            calendarioCursoDisciplinaProfessorFachada.inserir(ccdp);
        }
    }

    public void adicionaCalendarioCDP() {
        this.calendarioCDP.setCalendario(calendario);
        this.calendarioCDP.setCurso(curso);
        this.calendarioCDP.setDisciplina(disciplina);
        this.calendarioCDP.setPessoa(professor);
        this.calendarioCursoDisciplinaProfessorList.add(this.calendarioCDP);

        this.disciplina = new Disciplina();
        this.professor = new Pessoa();
        this.professores = new ArrayList<Pessoa>();
        this.calendarioCDP = new CalendarioCursoDisciplinaProfessor();
    }
    
    public void alterarCalendarioCDP() {
        System.out.println("alterar aula = " + calendarioCDP);
        
        this.calendarioCDP.setCalendario(calendario);
        this.calendarioCDP.setCurso(curso);
        this.calendarioCDP.setDisciplina(disciplina);
        this.calendarioCDP.setPessoa(professor);
        calendarioCursoDisciplinaProfessorFachada.alterar(this.calendarioCDP);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aula alterada com sucesso!", ""));
    }

    public void removerCalendarioCDP() {
        System.out.println("remover aula = " + calendarioCDP);
        calendarioCursoDisciplinaProfessorFachada.excluir(calendarioCDP);
        calendarioCursoDisciplinaProfessorList.remove(calendarioCDP);
        calendarioCDP = new CalendarioCursoDisciplinaProfessor();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aula excluída com sucesso!", ""));
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public List<Calendario> getCalendarios() {
        return calendarios;
    }

    public void setCalendarios(List<Calendario> calendarios) {
        this.setCalendarios(calendarios);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.setCursos(cursos);
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * @return the professor
     */
    public Pessoa getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    /**
     * @return the disciplina
     */
    public Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the professores
     */
    public List<Pessoa> getProfessores() {
        return professores;
    }

    /**
     * @param professores the professores to set
     */
    public void setProfessores(List<Pessoa> professores) {
        this.setProfessores(professores);
    }

    /**
     * @return the disciplinas
     */
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    /**
     * @param disciplinas the disciplinas to set
     */
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.setDisciplinas(disciplinas);
    }

    /**
     * @return the calendarioCDP
     */
    public CalendarioCursoDisciplinaProfessor getCalendarioCDP() {
        return calendarioCDP;
    }

    /**
     * @param calendarioCDP the calendarioCDP to set
     */
    public void setCalendarioCDP(CalendarioCursoDisciplinaProfessor calendarioCDP) {
        this.calendarioCDP = calendarioCDP;
    }

    /**
     * @return the calendarioCursoDisciplinaProfessorList
     */
    public List<CalendarioCursoDisciplinaProfessor> getCalendarioCursoDisciplinaProfessorList() {
        for (CalendarioCursoDisciplinaProfessor ccdp : calendarioCursoDisciplinaProfessorList) {
            if (ccdp.getAulaDistancia()) {
                ccdp.setAulaDistanciaDescricao("Sim");
            } else {
                ccdp.setAulaDistanciaDescricao("Não");
            }
        }

        return calendarioCursoDisciplinaProfessorList;
    }

    /**
     * @param calendarioCursoDisciplinaProfessorList the
     * calendarioCursoDisciplinaProfessorList to set
     */
    public void setCalendarioCursoDisciplinaProfessorList(List<CalendarioCursoDisciplinaProfessor> calendarioCursoDisciplinaProfessorList) {
        this.calendarioCursoDisciplinaProfessorList = calendarioCursoDisciplinaProfessorList;
    }

    public boolean isExisteCurso() {
        return existeCurso;
    }

    public void setExisteCurso(boolean existeCurso) {
        this.existeCurso = existeCurso;
    }
}