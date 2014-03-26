/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.persistencia.PerfilDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author phnicezio 
 */
@Stateless
public class PerfilFachada {

    @EJB
    private PerfilDAO perfilDAO;

    public void alterar(Perfil perfil) {
        perfilDAO.alterar(perfil);
    }

    public void excluir(Perfil perfil) {
        perfilDAO.excluir(perfil);
    }

    public void inserir(Perfil perfil) {
        perfilDAO.inserir(perfil);
    }

    public List<Perfil> listar() {
        return perfilDAO.recuperarTodos();
    }
    
    public Perfil getPerfil(Integer id) {
        return perfilDAO.getPerfil(id);
    }
}