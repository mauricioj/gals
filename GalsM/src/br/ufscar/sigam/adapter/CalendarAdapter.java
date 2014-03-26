package br.ufscar.sigam.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import br.ufscar.sigam.R;
import br.ufscar.sigam.model.Calendario;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CalendarAdapter extends ArrayAdapter<Calendario>{

private LayoutInflater inflater;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public CalendarAdapter(Activity activity, List<Calendario> objects) {
		super(activity,R.layout.calendar_list_item, objects);
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.calendar_list_item, null);
			holder = new ViewHolder();
			holder.data = (TextView) convertView.findViewById(R.id.edCalendarItemDate);
			holder.disciplina = (TextView) convertView.findViewById(R.id.edCalendarItemDisciplina);
			holder.professor = (TextView) convertView.findViewById(R.id.edCalendarItemProfessor);
			holder.distancia = (TextView) convertView.findViewById(R.id.edCalendarItemDistancia);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Calendario c = (Calendario) getItem(position);
		holder.data.setText(sdf.format(c.getDataAula()));
		holder.disciplina.setText(c.getDisciplina());
		holder.professor.setText(c.getProfessor());
		holder.distancia.setText(c.isAulaDistancia() ? "Distancia" : "");

		if (position % 2 == 0){
			holder.data.setBackgroundResource(R.drawable.altercolor1);
			holder.disciplina.setBackgroundResource(R.drawable.altercolor1);
			holder.professor.setBackgroundResource(R.drawable.altercolor1);
			holder.distancia.setBackgroundResource(R.drawable.altercolor1);
		} else {
			holder.data.setBackgroundResource(R.drawable.altercolor2);
			holder.disciplina.setBackgroundResource(R.drawable.altercolor2);
			holder.professor.setBackgroundResource(R.drawable.altercolor2);
			holder.distancia.setBackgroundResource(R.drawable.altercolor2);
		}
		
		return convertView;
	}
	
	static class ViewHolder {
		public TextView data;
		public TextView disciplina;
		public TextView professor;
		public TextView distancia;
	}
	
}