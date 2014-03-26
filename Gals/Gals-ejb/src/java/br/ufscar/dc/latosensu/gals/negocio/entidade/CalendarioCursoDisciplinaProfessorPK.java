/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.latosensu.gals.negocio.entidade;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author matheus
 */
public class CalendarioCursoDisciplinaProfessorPK implements Serializable {
    private int curso;
    private int disciplina;
    private int pessoa;
    private int calendario;
    private Date dataAula;

    public CalendarioCursoDisciplinaProfessorPK() {
    }

    public CalendarioCursoDisciplinaProfessorPK(int disciplina, int curso) {
        this.disciplina = disciplina;
        this.curso = curso;
        this.pessoa = pessoa;
        this.calendario = calendario;
        this.dataAula = dataAula;
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

    public int getCalendario() {
        return calendario;
    }

    public void setCalendario(int calendario) {
        this.calendario = calendario;
    }

    public Date getDataAula() {
        return dataAula;
    }

    public void setDataAula(Date dataAula) {
        this.dataAula = dataAula;
    }

    /*@Override
    public int hashCode() {
    int hash = 7;
    hash = 67 * hash + this.curso;
    hash = 67 * hash + this.disciplina;
    hash = 67 * hash + this.pessoa;
    hash = 67 * hash + this.calendario;
    hash = 67 * hash + Objects.hashCode(this.dataAula);
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
    final CalendarioCursoDisciplinaProfessorPK other = (CalendarioCursoDisciplinaProfessorPK) obj;
    if (this.curso != other.curso) {
    return false;
    }
    if (this.disciplina != other.disciplina) {
    return false;
    }
    if (this.pessoa != other.pessoa) {
    return false;
    }
    if (this.calendario != other.calendario) {
    return false;
    }
    if (!Objects.equals(this.dataAula, other.dataAula)) {
    return false;
    }
    return true;
    }*/
    @Override
    public int hashCode() {
        int hash = 7;
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
        final CalendarioCursoDisciplinaProfessorPK other = (CalendarioCursoDisciplinaProfessorPK) obj;
        return true;
    }
    
    @Override
    public String toString() {
        return "CalendarioCursoDisciplinaProfessorPK{" + "curso=" + curso + ", disciplina=" + disciplina + ", pessoa=" + pessoa + ", calendario=" + calendario + ", dataAula=" + dataAula + '}';
    }
    
}
