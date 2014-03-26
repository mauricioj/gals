package br.ufscar.sigam.model;

import java.io.Serializable;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nomeCurso;
	
	public Curso(int id, String nome) {
		this.id = id;
		this.nomeCurso = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	@Override
	public String toString() {
		return this.nomeCurso;
	}
}
