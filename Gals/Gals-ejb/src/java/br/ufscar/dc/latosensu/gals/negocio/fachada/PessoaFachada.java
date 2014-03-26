/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.fachada;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import br.ufscar.dc.latosensu.gals.persistencia.PessoaDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author phnicezio
 */
@Stateless
public class PessoaFachada {

    @EJB
    private PessoaDAO pessoaDAO;

    public void alterar(Pessoa pessoa) {
        pessoaDAO.alterar(pessoa);
    }

    public void excluir(Pessoa pessoa) {
        pessoaDAO.excluir(pessoa);
    }

    public void inserir(Pessoa pessoa) {
        pessoaDAO.inserir(pessoa);
    }

    public List<Pessoa> listar() {
        return pessoaDAO.recuperarTodos();
    }

    public List<Pessoa> listarCoordenadores() {
        return pessoaDAO.recuperarCoordenadores();
    }

    public List<Pessoa> listarProfessores() {
        return pessoaDAO.recuperarProfessores();
    }

    public Pessoa consultarPessoaPorCpf(String cpf) {
        return pessoaDAO.consultarPessoaPorCpf(cpf);
    }
    
    public Pessoa recuperarPessoa(int id) {
        return pessoaDAO.findPessoa(id);
    }

    public List<Pessoa> recuperarPorCursoDisciplina(Integer idCurso, Integer idDisciplina) {
        return pessoaDAO.recuperarPorCursoDisciplina(idCurso, idDisciplina);
    }

    public Boolean validarUsuario(String usuario, String senha) {
        return pessoaDAO.validarUsuario(usuario, senha);
    }
}