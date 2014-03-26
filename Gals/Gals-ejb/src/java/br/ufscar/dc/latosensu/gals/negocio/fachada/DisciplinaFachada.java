/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.persistencia.DisciplinaDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author phnicezio
 */
@Stateless
public class DisciplinaFachada {

    @EJB
    private DisciplinaDAO disciplinaDAO;

    public void alterar(Disciplina disciplina) {
        disciplinaDAO.alterar(disciplina);
    }

    public void excluir(Disciplina disciplina) {
        disciplinaDAO.excluir(disciplina);
    }

    public void inserir(Disciplina disciplina) {
        disciplinaDAO.inserir(disciplina);
    }

    public List<Disciplina> listar() {
        return disciplinaDAO.recuperarTodos();
    }
    
    public List<Disciplina> recuperarPorCurso(Integer idCurso) {
        return disciplinaDAO.recuperarPorCurso(idCurso);
    }
}