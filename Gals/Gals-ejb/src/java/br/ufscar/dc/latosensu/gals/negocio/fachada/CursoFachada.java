/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.CursoDisciplina;
import br.ufscar.dc.latosensu.gals.persistencia.CursoDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author phnicezio
 */
@Stateless
public class CursoFachada {

    @EJB
    private CursoDAO cursoDAO;

    public void alterar(Curso curso) {
        cursoDAO.alterar(curso);
    }

    public void excluir(Curso curso) {
        cursoDAO.excluir(curso);
    }

    public void inserir(Curso curso) {
        cursoDAO.inserir(curso);
    }

    public List<Curso> listar() {
        return cursoDAO.recuperarTodos();
    }
    
    public boolean validarCargaHoraria(Curso curso) {
        if (curso.getCargaHorariaTotal() == curso.getTotalCargaHoraria() || curso.getCargaHorariaDistancia() == curso.getTotalCargaHorariaDistancia()) {
            return true;
        }
        return false;
    }
    
    public Curso somarCargaHoraria(Curso curso, List<CursoDisciplina> cursoDisciplinas) {
        curso.setTotalCargaHoraria(0);
        curso.setTotalCargaHorariaDistancia(0);
        for (CursoDisciplina cd : cursoDisciplinas) {
            curso.setTotalCargaHoraria(curso.getTotalCargaHoraria() + cd.getCargaHoraria());
            curso.setTotalCargaHorariaDistancia(curso.getTotalCargaHorariaDistancia() + cd.getCargaHorariaDistancia());
        }
        return curso;
    }
    
    public boolean validarDataDeInicioEFim(Date dataInicial, Date dataFinal) {
        return dataInicial.before(dataFinal);
    }
}