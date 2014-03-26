/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PerfilDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(Perfil perfil) {
        em.merge(perfil);
    }

    public void excluir(Perfil perfil) {
        Perfil perfilASerExcluida = em.merge(perfil);
        em.remove(perfilASerExcluida);
    }

    public void inserir(Perfil perfil) {
        em.persist(perfil);
    }

    public List<Perfil> recuperarTodos() {
        return em.createQuery("select per from Perfil as per order by per.descricao").getResultList();
    }
    
    public Perfil getPerfil(Integer id) {
        return em.find(Perfil.class, id);
    }
}