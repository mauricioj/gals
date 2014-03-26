/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.CalendarioCursoDisciplinaProfessor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author phnicezio
 */
@Stateless
public class CalendarioCursoDisciplinaProfessorDAO {
    
    @PersistenceContext
    private EntityManager em;

    public void alterar(CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessor) {
        em.merge(calendarioCursoDisciplinaProfessor);
    }

    public void excluir(CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessor) {
        CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessorExcluir = em.merge(calendarioCursoDisciplinaProfessor);
        em.remove(calendarioCursoDisciplinaProfessorExcluir);
    }

    public void inserir(CalendarioCursoDisciplinaProfessor calendarioCursoDisciplinaProfessor) {
        em.persist(calendarioCursoDisciplinaProfessor);
    }

    public List<CalendarioCursoDisciplinaProfessor> recuperarTodos() {
        return em.createQuery("select ccdp from CalendarioCursoDisciplinaProfessor ccdp order by ccdp.curso.descricao desc").getResultList();
    }
    
    public List<CalendarioCursoDisciplinaProfessor> recuperarPorCurso(Integer idCurso) {
        return em.createQuery("select ccdp from CalendarioCursoDisciplinaProfessor ccdp where ccdp.curso.id = " + idCurso).getResultList();
    }
}
