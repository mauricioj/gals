package br.ufscar.sigam.converter;

import org.json.JSONException;
import org.json.JSONObject;
import br.ufscar.sigam.model.Historico;

public class HistoricoConverter {
	public static Historico toObject(JSONObject json) throws JSONException {
		String disciplina = json.getString("disciplina");
		String nota = json.getString("nota");
		String frequencia = json.getString("frequencia");
		
		return new Historico(disciplina, nota, frequencia);
	}
}
