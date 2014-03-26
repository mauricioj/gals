package br.ufscar.sigam;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.sigam.adapter.HistoricoAdapter;
import br.ufscar.sigam.interfaces.TaskInterface;
import br.ufscar.sigam.model.Curso;
import br.ufscar.sigam.model.Historico;
import br.ufscar.sigam.model.User;
import br.ufscar.sigam.task.FetchHistoryTask;
import br.ufscar.sigam.util.ModalDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ListHistoryActivity extends ListActivity implements TaskInterface<List<Object>> {
	private static final int FETCH_HISTORY = 1;

	private ProgressDialog loadingDialog;
	private HistoricoAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_history);

        Intent i = getIntent();
        Curso curso = (Curso) i.getSerializableExtra("curso");
        User user = (User) i.getSerializableExtra("user");
        
        loadingDialog = new ProgressDialog(this);
		loadingDialog.setCancelable(true);
        
		loadingDialog.setMessage(getResources().getString(R.string.find_history));
    	loadingDialog.show();
		new FetchHistoryTask(ListHistoryActivity.this, user, curso, FETCH_HISTORY).execute();
    }

	public void executeAfterAsyncTask(List<Object> result, Exception exception,	int id) {
		if (id == FETCH_HISTORY) {
			loadingDialog.dismiss();
			
			if (exception != null) {
				new ModalDialog().showAlertDialog(this, exception.getMessage());
			} else {
				if (result == null) {
					Toast.makeText(this, R.string.nothing_history, Toast.LENGTH_LONG).show();	
				} else {
					List<Historico> list = new ArrayList<Historico>();
					for (Object obj : result) {
						list.add((Historico) obj);
					}
					
					adapter = new HistoricoAdapter(this, list);
					setListAdapter(adapter);
				}
			}		
		}
	}
}
