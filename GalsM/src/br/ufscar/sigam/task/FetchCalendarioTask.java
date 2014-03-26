package br.ufscar.sigam.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.ufscar.sigam.config.Config;
import br.ufscar.sigam.converter.CalendarioConverter;
import br.ufscar.sigam.interfaces.TaskInterface;
import br.ufscar.sigam.model.Calendario;
import br.ufscar.sigam.model.Curso;
import br.ufscar.sigam.model.User;
import br.ufscar.sigam.util.Constants;
import br.ufscar.sigam.util.HTTPUtil;

import android.os.AsyncTask;

public class FetchCalendarioTask extends AsyncTask<String, String, List<Object>> {

	private TaskInterface<List<Object>> activity;
	private Exception exception;
	private User user;
	private Curso curso;
	private int id;

	public FetchCalendarioTask(TaskInterface<List<Object>> activity, User user, Curso curso, int id) {
		this.activity = activity;
		this.user = user;
		this.curso = curso;
		this.id = id;
	}

	@Override
	protected List<Object> doInBackground(String... args) {
		try {
			return doLogin();
		} catch (Exception e) {
			e.printStackTrace();
			exception = e;
		}
		return null;
	}

	@Override 
	protected void onPostExecute(List<Object> result) {
		activity.executeAfterAsyncTask(result, exception, id);
	}

	private List<Object> doLogin() throws Exception {
		List<Object> contatos = new ArrayList<Object>();
		String url = Config.URL_BASE + Config.getServiceListaCalendario(user.getUser(), user.getPass(), curso.getId());
		String jsonResponse = HTTPUtil.doGet(url); 
		JSONObject response = new JSONObject(jsonResponse); 
		if (response.getInt("status") == Constants.HTTP_OK) { 
			JSONArray array = response.getJSONArray("aulas");
			for (int i = 0; i < array.length(); i++) {
				Calendario c = CalendarioConverter.toObject(new JSONObject(array.getString(i)));
				contatos.add(c);
			}
		}
		return contatos;
	}
}