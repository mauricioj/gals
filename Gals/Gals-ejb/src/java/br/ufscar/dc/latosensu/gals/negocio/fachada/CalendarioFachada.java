/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Calendario;
import br.ufscar.dc.latosensu.gals.persistencia.CalendarioDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author matheus
 */
@Stateless
public class CalendarioFachada {

    @EJB
    private CalendarioDAO calendarioDAO;

    public void alterar(Calendario calendario) {
        calendarioDAO.alterar(calendario);
    }

    public void excluir(Calendario calendario) {
        calendarioDAO.excluir(calendario);
    }

    public void inserir(Calendario calendario) {
        calendarioDAO.inserir(calendario);
    }

    public List<Calendario> listar() {
        return calendarioDAO.recuperarTodos();
    }
    
}
