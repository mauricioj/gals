/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import br.ufscar.dc.latosensu.gals.persistencia.CalendarioCursoDisciplinaProfessorDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author phnicezio
 */
@Stateless
public class CalendarioCursoDisciplinaProfessorFachada {
    
    @EJB
    private CalendarioCursoDisciplinaProfessorDAO calendarioCursoDisciplinaProfessorDAO;
    
    public void inserir(CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessor) {
        calendarioCursoDisciplinaProfessorDAO.inserir(calendarioCursoDisciplinaProfessor);
    }
    
    public void alterar(CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessor) {
        calendarioCursoDisciplinaProfessorDAO.alterar(calendarioCursoDisciplinaProfessor);
    }

    public void excluir(CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessor) {
        calendarioCursoDisciplinaProfessorDAO.excluir(calendarioCursoDisciplinaProfessor);
    }

    public List<CalendarioCursoDisciplinaProfessor> listar() {
        return calendarioCursoDisciplinaProfessorDAO.recuperarTodos();
    }
    
    public List<CalendarioCursoDisciplinaProfessor> listar(Integer idCurso) {
        return calendarioCursoDisciplinaProfessorDAO.recuperarPorCurso(idCurso);
    }
}