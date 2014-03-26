/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DisciplinaDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(Disciplina disciplina) {
        em.merge(disciplina);
    }

    public void excluir(Disciplina disciplina) {
        Disciplina disciplinaASerExcluida = em.merge(disciplina);
        em.remove(disciplinaASerExcluida);
    }

    public void inserir(Disciplina disciplina) {
        em.persist(disciplina);
    }

    public List<Disciplina> recuperarTodos() {
        return em.createQuery("select dis from Disciplina as dis").getResultList();
    }
    
    public List<Disciplina> recuperarPorCurso(Integer idCurso) {
        return em.createQuery("select dis from Disciplina as dis, CursoDisciplina as cur where dis.id = cur.disciplina.id and cur.curso.id = " + idCurso + " order by dis.descricao").getResultList();
    }
}