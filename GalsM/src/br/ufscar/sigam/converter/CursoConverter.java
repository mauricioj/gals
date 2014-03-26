package br.ufscar.sigam.converter;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufscar.sigam.model.Curso;

public class CursoConverter {
	public static Curso toObject(JSONObject json) throws JSONException {
		int id = json.getInt("id");
		String nome = json.getString("nome");
		return new Curso(id, nome);
	}
}
