/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Permissoes;
import br.ufscar.dc.latosensu.gals.persistencia.PermissoesDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author phnicezio
 */
@Stateless
public class PermissoesFachada {

    @EJB
    private PermissoesDAO permissoesDAO;

    public void alterar(Permissoes permissoes) {
        permissoesDAO.alterar(permissoes);
    }

    public void excluir(Permissoes permissoes) {
        permissoesDAO.excluir(permissoes);
    }

    public void inserir(Permissoes permissoes) {
        permissoesDAO.inserir(permissoes);
    }

    public List<Permissoes> listar() {
        return permissoesDAO.recuperarTodos();
    }
}