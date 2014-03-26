/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class CursoDisciplinaProfessorPK implements Serializable {
    private int curso;
    private int disciplina;
    private int pessoa;

    public CursoDisciplinaProfessorPK() {
    }

    public CursoDisciplinaProfessorPK(int disciplina, int curso) {
        this.disciplina = disciplina;
        this.curso = curso;
        this.pessoa = pessoa;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.curso;
        hash = 23 * hash + this.disciplina;
        hash = 23 * hash + this.pessoa;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CursoDisciplinaProfessorPK other = (CursoDisciplinaProfessorPK) obj;
        if (this.curso != other.curso) {
            return false;
        }
        if (this.disciplina != other.disciplina) {
            return false;
        }
        if (this.pessoa != other.pessoa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CursoDisciplinaProfessorPK{" + "curso=" + curso + ", disciplina=" + disciplina + ", pessoa=" + pessoa + '}';
    }

    
}
