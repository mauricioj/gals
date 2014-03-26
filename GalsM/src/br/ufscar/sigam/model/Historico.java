package br.ufscar.sigam.model;

public class Historico {
	private String disciplina;
	private String nota;
	private String frequencia;
	
	public Historico(String disciplina, String nota, String frequencia) {
		this.disciplina = disciplina;
		this.nota = nota;
		this.frequencia = frequencia;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public String getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}
}
