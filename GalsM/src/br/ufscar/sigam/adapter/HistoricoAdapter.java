package br.ufscar.sigam.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import br.ufscar.sigam.R;
import br.ufscar.sigam.model.Historico;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HistoricoAdapter extends ArrayAdapter<Historico>{

private LayoutInflater inflater;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public HistoricoAdapter(Activity activity, List<Historico> objects) {
		super(activity,R.layout.history_list_item, objects);
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.history_list_item, null);
			holder = new ViewHolder();
			holder.disciplina = (TextView) convertView.findViewById(R.id.edHistoryItemDisciplina);
			holder.nota = (TextView) convertView.findViewById(R.id.edHistoryItemNota);
			holder.frequencia = (TextView) convertView.findViewById(R.id.edHistoryItemFrequencia);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Historico h = (Historico) getItem(position);
		holder.disciplina.setText(h.getDisciplina());
		holder.nota.setText(h.getNota());
		holder.frequencia.setText(h.getFrequencia());

		if (position % 2 == 0){
			holder.disciplina.setBackgroundResource(R.drawable.altercolor1);
			holder.nota.setBackgroundResource(R.drawable.altercolor1);
			holder.frequencia.setBackgroundResource(R.drawable.altercolor1);
		} else {
			holder.disciplina.setBackgroundResource(R.drawable.altercolor2);
			holder.nota.setBackgroundResource(R.drawable.altercolor2);
			holder.frequencia.setBackgroundResource(R.drawable.altercolor2);
		}
		
		return convertView;
	}

	static class ViewHolder {
		public TextView disciplina;
		public TextView nota;
		public TextView frequencia;
	}
	
}