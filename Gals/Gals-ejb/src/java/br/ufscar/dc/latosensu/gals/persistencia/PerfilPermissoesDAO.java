/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PerfilPermissoesDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(PerfilPermissoes perfilPermissoes) {
        System.out.println("passou no alterar perfilPermissoes");
        em.merge(perfilPermissoes);
    }

    public void excluir(PerfilPermissoes perfilPermissoes) {
        PerfilPermissoes perfilPermissoesASerExcluida = em.merge(perfilPermissoes);
        em.remove(perfilPermissoesASerExcluida);
    }

    public void inserir(PerfilPermissoes perfilPermissoes) {
        em.persist(perfilPermissoes);
    }

    public List<PerfilPermissoes> recuperarTodos() {
        return em.createQuery("select per from PerfilPermissoes as per").getResultList();
    }
    
    public List<PerfilPermissoes> recuperarIdPerfil(Perfil perfil) {
         TypedQuery<PerfilPermissoes> query = em.createQuery(
                 "select per from PerfilPermissoes as per where per.perfil = :perfil", PerfilPermissoes.class);
          return query.setParameter("perfil", perfil).getResultList();  
    }
}