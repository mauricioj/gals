package br.ufscar.sigam.controller;

import org.json.JSONObject;

import br.ufscar.sigam.R;
import br.ufscar.sigam.config.Config;
import br.ufscar.sigam.model.User;
import br.ufscar.sigam.util.Constants;
import br.ufscar.sigam.util.HTTPUtil;
import br.ufscar.sigam.util.LoginDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


public class LoginController {
	public static final String LOGIN_PREFS_NAME = "LoginFile";
	
	private User user;
	private Context ctx;
	
	public LoginController(Context ctx) {
		this.ctx = ctx;
		this.user = new User();
		
		SharedPreferences settings = this.ctx.getSharedPreferences(LOGIN_PREFS_NAME, 0);
	    setUser(settings.getString("userName", ""));
	    setPass(settings.getString("password", ""));
	}

	public String getUser() {
		return user.getUser();
	}

	public void setUser(String user) {
		this.user.setUser(user);
		
		SharedPreferences settings = ctx.getSharedPreferences(LOGIN_PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("userName", user);
		editor.commit();
	}

	public String getPass() {
		return user.getPass();
	}

	public void setPass(String pass) {
		this.user.setPass(pass);

		SharedPreferences settings = ctx.getSharedPreferences(LOGIN_PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("password", pass);
		editor.commit();
	}
	
	public boolean doLogin() {
		if ((user.getUser().trim() == "") || (user.getPass().trim() == "")) {
			if (!showLoginDialog()) {
				return false;
			} else {
				setUser(user.getUser());
				setPass(user.getPass());
			}
		}
		
		try {
			String url = Config.URL_BASE + Config.getServiceLogin(user.getUser(), user.getPass());
			
			String jsonResponse = HTTPUtil.doGet(url); 
			JSONObject response = new JSONObject(jsonResponse); 
			if (response.getInt("status") == Constants.HTTP_OK) {
				user = new User();
				setUser(response.getString("user"));
				setPass(response.getString("pass"));
			} else {
	    		Toast.makeText(ctx, R.string.invalid_login, Toast.LENGTH_SHORT).show();
				if (!showLoginDialog()) {
					return false;
				} else {
					setUser(user.getUser());
					setPass(user.getPass());
				}
				return doLogin();
			}
		} catch (Exception e) {
			return false;
		}
		
		return true; 
	}
	
	public boolean showLoginDialog() {
		LoginDialog ld = new LoginDialog();
		
		return ld.showLoginDialog(ctx, this.user);
	}

	public void doLogout() {
		setUser("");
		setPass("");
	}
}
