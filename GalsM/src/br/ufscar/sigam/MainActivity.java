package br.ufscar.sigam;

import java.util.List;

import br.ufscar.sigam.controller.LoginController;
import br.ufscar.sigam.interfaces.TaskInterface;
import br.ufscar.sigam.model.Curso;
import br.ufscar.sigam.model.User;
import br.ufscar.sigam.task.FetchCursoTask;
import br.ufscar.sigam.util.ModalDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements TaskInterface<List<Object>> {
	private static final int FETCH_CURSO = 0;
	private static final int FETCH_HISTORICO = 1;
	
	private User user;
	private ProgressDialog loadingDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        loadingDialog = new ProgressDialog(this);
		loadingDialog.setCancelable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.menu_logout:
    	  LoginController lc = new LoginController(this);
    	  lc.doLogout();
    	  
    	  break;
      }
      return super.onOptionsItemSelected(item);
    }
    
    public void btViewCalendarsClick(View v) {
    	LoginController lc = new LoginController(this);
    	if (!lc.doLogin()) {
    		Toast.makeText(this, R.string.invalid_login, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	user = new User(lc.getUser(), lc.getPass());

    	loadingDialog.setMessage("Buscando Cursos...");
    	loadingDialog.show();
		new FetchCursoTask(this, user, FETCH_CURSO).execute(); 
    }
    
    public void btViewHistoryClick(View v) {
    	LoginController lc = new LoginController(this);
    	if (!lc.doLogin()) {
    		Toast.makeText(this, R.string.invalid_login, Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	user = new User(lc.getUser(), lc.getPass());

    	loadingDialog.setMessage("Buscando Cursos...");
    	loadingDialog.show();
		new FetchCursoTask(this, user, FETCH_HISTORICO).execute(); 
    }

	public void executeAfterAsyncTask(List<Object> result, Exception exception, int id) {
		if (id == FETCH_CURSO) {
			loadingDialog.dismiss();
	
			if (exception != null) {
				new ModalDialog().showAlertDialog(this, exception.getMessage());
			} else {
				if (result == null) {
					Toast.makeText(this, R.string.nothing_course, Toast.LENGTH_LONG).show();	
				} else {
					selectCourse(result, id);
				}
			}
			
		} else if (id == FETCH_HISTORICO) {
			loadingDialog.dismiss();
	
			if (exception != null) {
				new ModalDialog().showAlertDialog(this, exception.getMessage());
			} else {
				if (result == null) {
					Toast.makeText(this, R.string.nothing_course, Toast.LENGTH_LONG).show();	
				} else {
					selectCourse(result, id);
				}
			}
		}
	}
	
	private void selectCourse(List<Object> cals, final int id) {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle(R.string.course_select);
        final ArrayAdapter<Curso> calendarios = new ArrayAdapter<Curso>(this,android.R.layout.select_dialog_singlechoice);

        for (Object calendario : cals) {
        	calendarios.add((Curso) calendario);	
		}
        
        builderSingle.setNegativeButton(R.string.course_cancel,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        builderSingle.setAdapter(calendarios,
	        new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	if (id == FETCH_CURSO) {
	 	                Curso curso = calendarios.getItem(which);
		                
		                Intent it = new Intent(MainActivity.this, ListCalendarActivity.class);
		                it.putExtra("curso", curso);
		                it.putExtra("user", user);
						startActivity(it);
	            	} else if (id == FETCH_HISTORICO) {
	 	                Curso curso = calendarios.getItem(which);
		                
		                Intent it = new Intent(MainActivity.this, ListHistoryActivity.class);
		                it.putExtra("curso", curso);
		                it.putExtra("user", user);
						startActivity(it);
	            	}
	            }
	        });
        builderSingle.show();
	}
}
