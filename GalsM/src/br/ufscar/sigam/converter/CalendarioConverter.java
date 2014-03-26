package br.ufscar.sigam.converter;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufscar.sigam.model.Calendario;

public class CalendarioConverter {
	public static Calendario toObject(JSONObject json) throws JSONException {
		@SuppressWarnings("deprecation")
		Date dataAula = new Date(json.getString("dataAula"));
		String disciplina = json.getString("disciplina");
		String professor = json.getString("professor");
		boolean aulaDistancia = json.getBoolean("aulaDistancia");
		
		String informacoes;
		try {
			informacoes = json.getString("informacoes");
		} catch (Exception e) {
			informacoes = "";
		}
		
		return new Calendario(dataAula, disciplina, professor, aulaDistancia, informacoes);
	}
}
