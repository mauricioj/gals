/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Calendario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author matheus
 */
@Stateless
public class CalendarioDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(Calendario calendario) {
        em.merge(calendario);
    }

    public void excluir(Calendario calendario) {
        Calendario calendarioASerExcluida = em.merge(calendario);
        em.remove(calendarioASerExcluida);
    }

    public void inserir(Calendario calendario) {
        em.persist(calendario);
    }

    public List<Calendario> recuperarTodos() {
        return em.createQuery("select cal from Calendario as cal order by cal.informacoesCalendario").getResultList();
    }
}
