/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.apresentacao.utils;

import br.ufscar.dc.latosensu.gals.negocio.entidade.Curso;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Disciplina;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Perfil;
import br.ufscar.dc.latosensu.gals.negocio.entidade.Pessoa;

/**
 *
 * @author matheus
 */
public class Params {
    private static Pessoa pessoa;
    private static Perfil perfil;
    private static Disciplina disciplina;
    private static Curso curso;

    /**
     * @return the pessoa
     */
    public static Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param aPessoa the pessoa to set
     */
    public static void setPessoa(Pessoa aPessoa) {
        pessoa = aPessoa;
    }

    /**
     * @return the perfil
     */
    public static Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param aPerfil the perfil to set
     */
    public static void setPerfil(Perfil aPerfil) {
        perfil = aPerfil;
    }

    /**
     * @return the disciplina
     */
    public static Disciplina getDisciplina() {
        return disciplina;
    }

    /**
     * @param aDisciplina the disciplina to set
     */
    public static void setDisciplina(Disciplina aDisciplina) {
        disciplina = aDisciplina;
    }

    public static Curso getCurso() {
        return curso;
    }

    public static void setCurso(Curso curso) {
        Params.curso = curso;
    }
}
