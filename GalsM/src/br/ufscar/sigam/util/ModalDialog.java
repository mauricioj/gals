package br.ufscar.sigam.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

public class ModalDialog {

	private boolean mChoice = false;
	private boolean mQuitModal = false;

	private Method mMsgQueueNextMethod = null;
	private Field mMsgTargetFiled = null;

	public ModalDialog() {
	}

	public void showAlertDialog(Context context, String info) {
		if (!prepareModal()) {
			return;
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(info);
		builder.setCancelable(false);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				ModalDialog.this.mQuitModal = true;
				dialog.dismiss();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();

		doModal();
	}

	public boolean showConfirmDialog(Context context, String info) {
		if (!prepareModal()) {
			return false;
		}

		mChoice = false;

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(info);
		builder.setCancelable(false);
		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				ModalDialog.this.mQuitModal = true;
				ModalDialog.this.mChoice = true;
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						ModalDialog.this.mQuitModal = true;
						ModalDialog.this.mChoice = false;
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