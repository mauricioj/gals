package br.ufscar.sigam.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.ufscar.sigam.R;
import br.ufscar.sigam.model.User;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LoginDialog {
	private User user;
	
	private boolean mChoice = false;
	private boolean mQuitModal = false;

	private Method mMsgQueueNextMethod = null;
	private Field mMsgTargetFiled = null;

	public LoginDialog() {
	}

	public boolean showLoginDialog(Context context, User user) {
		this.user = user;
		
		if (!prepareModal()) {
			return false;
		}

		mChoice = false;

		LayoutInflater factory = LayoutInflater.from(context);            
        final View loginView = factory.inflate(R.layout.login_dialog, null);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(loginView);

		builder.setTitle(R.string.login_title); 
		builder.setMessage(R.string.login_message); 

        final EditText edUser = (EditText) loginView.findViewById(R.id.login_username);
        final EditText edPass = (EditText) loginView.findViewById(R.id.login_password);
        edUser.setText(this.user.getUser());
        edPass.setText(this.user.getPass());
		
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.login_confirm, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				LoginDialog.this.mQuitModal = true;
				LoginDialog.this.mChoice = true;
				
				LoginDialog.this.user.setUser(edUser.getText().toString());
				LoginDialog.this.user.setPass(edPass.getText().toString());
				
				dialog.dismiss();
			}
		});

		builder.setNegativeButton(R.string.login_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						LoginDialog.this.mQuitModal = true;
						LoginDialog.this.mChoice = false;
						dialog.cancel();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();

		doModal();
		return mChoice;
	}

	private boolean prepareModal() {
		Class<?> clsMsgQueue = null;
		Class<?> clsMessage = null;

		try {
			clsMsgQueue = Class.forName("android.os.MessageQueue");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try {
			clsMessage = Class.forName("android.os.Message");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try {
			mMsgQueueNextMethod = clsMsgQueue.getDeclaredMethod("next",
					new Class[] {});
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}

		mMsgQueueNextMethod.setAccessible(true);

		try {
			mMsgTargetFiled = clsMessage.getDeclaredField("target");
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return false;
		}

		mMsgTargetFiled.setAccessible(true);
		return true;
	}

	private void doModal() {
		mQuitModal = false;

		MessageQueue queue = Looper.myQueue();
		while (!mQuitModal) {
			Message msg = null;
			try {
				msg = (Message) mMsgQueueNextMethod.invoke(queue,
						new Object[] {});
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			if (null != msg) {
				Handler target = null;
				try {
					target = (Handler) mMsgTargetFiled.get(msg);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				if (target == null) {
					mQuitModal = true;
				}

				target.dispatchMessage(msg);
				msg.recycle();
			}
		}
	}
}