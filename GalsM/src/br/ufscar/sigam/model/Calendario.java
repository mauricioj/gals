package br.ufscar.sigam.model;

import java.util.Date;

public class Calendario {
	private Date dataAula;
	private String disciplina;
	private String professor;
	private boolean aulaDistancia;
	private String informacoes;
	
	public Calendario(Date dataAula, String disciplina, String professor, boolean aulaDistancia, String informacoes) {
		this.dataAula = dataAula;
		this.disciplina = disciplina;
		this.professor = professor;
		this.aulaDistancia = aulaDistancia;
		this.informacoes = informacoes;
	}
	
	public Date getDataAula() {
		return dataAula;
	}
	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}
	
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public boolean isAulaDistancia() {
		return aulaDistancia;
	}
	public void setAulaDistancia(boolean aulaDistancia) {
		this.aulaDistancia = aulaDistancia;
	}
	
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

}
