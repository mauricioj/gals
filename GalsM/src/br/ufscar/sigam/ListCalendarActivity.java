package br.ufscar.sigam;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.sigam.adapter.CalendarAdapter;
import br.ufscar.sigam.interfaces.TaskInterface;
import br.ufscar.sigam.model.Calendario;
import br.ufscar.sigam.model.Curso;
import br.ufscar.sigam.model.User;
import br.ufscar.sigam.task.FetchCalendarioTask;
import br.ufscar.sigam.util.ModalDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ListCalendarActivity extends ListActivity implements TaskInterface<List<Object>> {
	private static final int FETCH_CALENDARIO = 1;

	private ProgressDialog loadingDialog;
	private CalendarAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_calendar);

        Intent i = getIntent();
        Curso curso = (Curso) i.getSerializableExtra("curso");
        User user = (User) i.getSerializableExtra("user");
        
        loadingDialog = new ProgressDialog(this);
		loadingDialog.setCancelable(true);
        
        loadingDialog.setMessage(getResources().getString(R.string.find_calendar));
    	loadingDialog.show();
		new FetchCalendarioTask(ListCalendarActivity.this, user, curso, FETCH_CALENDARIO).execute();
    }

	public void executeAfterAsyncTask(List<Object> result, Exception exception,	int id) {
		if (id == FETCH_CALENDARIO) {
			loadingDialog.dismiss();
			
			if (exception != null) {
				new ModalDialog().showAlertDialog(this, exception.getMessage());
			} else {
				if (result == null) {
					Toast.makeText(this, R.string.nothing_calendar, Toast.LENGTH_LONG).show();	
				} else {
					List<Calendario> list = new ArrayList<Calendario>();
					for (Object obj : result) {
						list.add((Calendario) obj);
					}
					
					adapter = new CalendarAdapter(this, list);
					setListAdapter(adapter);
				}
			}		
		}
	}
}
