/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Permissoes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PermissoesDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(Permissoes permissoes) {
        System.out.println("passou no alterar permissoes");
        em.merge(permissoes);
    }

    public void excluir(Permissoes permissoes) {
        Permissoes permissoesASerExcluida = em.merge(permissoes);
        em.remove(permissoesASerExcluida);
    }

    public void inserir(Permissoes permissoes) {
        em.persist(permissoes);
    }

    public List<Permissoes> recuperarTodos() {
        return em.createQuery("select per from Permissoes as per order by per.descricao").getResultList();
    }
}