/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.persistencia;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PessoaDAO {

    @PersistenceContext
    private EntityManager em;

    public void alterar(Pessoa pessoa) {
        em.merge(pessoa);
    }

    public void excluir(Pessoa pessoa) {
        Pessoa pessoaASerExcluida = findPessoa(pessoa.getId());
        em.remove(pessoaASerExcluida);
    }

    public void inserir(Pessoa pessoa) {
        em.persist(pessoa);
    }
    
    public Pessoa findPessoa(int id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> recuperarTodos() {
        return em.createQuery("select pes from Pessoa as pes order by pes.nome").getResultList();
    }

    public List<Pessoa> recuperarCoordenadores() {
        return em.createQuery("select pes from Pessoa as pes WHERE pes.tipoPessoaCoordenador = true order by pes.nome").getResultList();
    }

    public List<Pessoa> recuperarProfessores() {
        return em.createQuery("select pes from Pessoa as pes WHERE pes.tipoPessoaProfessor = true order by pes.nome").getResultList();
    }

    public Pessoa consultarPessoaPorCpf(String cpf) {
        Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.cpf = :cpf", Pessoa.class);
        query.setParameter("cpf", cpf);

        Pessoa pessoaRetorno = null;
        try {
            pessoaRetorno = (Pessoa) query.getSingleResult();
        } catch (NoResultException nre) {
            //System.out.println("Não encontrou nenhum registro.");
        } catch (NonUniqueResultException nure) {
            //System.out.println("Encontrou mais de um registro.");
        }
        return pessoaRetorno;
    }

    public Boolean validarUsuario(String usuario, String senha) {
            Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.userName = :userName and p.password = :password", Pessoa.class);
            query.setParameter("userName", usuario);
            query.setParameter("password", senha);
            List<?> list = query.getResultList();
            return list != null && !list.isEmpty();
    }

    public List<Pessoa> recuperarPorCursoDisciplina(Integer idCurso, Integer idDisciplina) {
        return em.createQuery("select pes from Pessoa as pes, CursoDisciplinaProfessor as cur where pes.tipoPessoaProfessor = TRUE and pes.id = cur.pessoa.id and cur.curso.id = " + idCurso + " and cur.disciplina.id = " + idDisciplina + " order by pes.nome").getResultList();
    }
}