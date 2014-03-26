/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.PerfilPermissoes;
import br.ufscar.dc.latosensu.gals.persistencia.PerfilPermissoesDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author phnicezio
 */
@Stateless
public class PerfilPermissoesFachada {

    @EJB
    private PerfilPermissoesDAO perfilPermissoesDAO;

    public void alterar(PerfilPermissoes perfilPermissoes) {
        perfilPermissoesDAO.alterar(perfilPermissoes);
    }

    public void excluir(PerfilPermissoes perfilPermissoes) {
        perfilPermissoesDAO.excluir(perfilPermissoes);
    }

    public void inserir(PerfilPermissoes perfilPermissoes) {
        perfilPermissoesDAO.inserir(perfilPermissoes);
    }

    public List<PerfilPermissoes> listar() {
        return perfilPermissoesDAO.recuperarTodos();
    }
    
    public List<PerfilPermissoes> listar(Perfil perfil) {
        return perfilPermissoesDAO.recuperarIdPerfil(perfil);
    }
}