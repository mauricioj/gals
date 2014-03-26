/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CursoDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(Curso curso) {
        em.merge(curso);
    }

    public void excluir(Curso curso) {
        Curso cursoASerExcluida = em.merge(curso);
        em.remove(cursoASerExcluida);
    }

    public void inserir(Curso curso) {
        em.persist(curso);
    }

    public List<Curso> recuperarTodos() {
        return em.createQuery("select cur from Curso as cur order by cur.descricao").getResultList();
    }

}