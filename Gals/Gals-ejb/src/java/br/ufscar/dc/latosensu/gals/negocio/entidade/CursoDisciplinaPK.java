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
public class CursoDisciplinaPK implements Serializable {
    private int curso;
    private int disciplina;

    public CursoDisciplinaPK() {
    }

    public CursoDisciplinaPK(int disciplina, int curso) {
        this.disciplina = disciplina;
        this.curso = curso;
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

    @Override
    public int hashCode() {
        int hash = 3;
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
        final CursoDisciplinaPK other = (CursoDisciplinaPK) obj;
        if (this.curso != other.curso) {
            return false;
        }
        if (this.disciplina != other.disciplina) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CursoDisciplinaPK{" + "curso=" + curso + ", disciplina=" + disciplina + '}';
    }

}
