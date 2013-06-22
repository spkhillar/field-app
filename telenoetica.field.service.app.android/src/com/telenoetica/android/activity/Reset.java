package com.telenoetica.android.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Reset {
	
	public void clearForm(ViewGroup group) {
		for (int i = 0, count = group.getChildCount(); i < count; ++i) {
			View view = group.getChildAt(i);
			if (view instanceof EditText) {
				((EditText) view).setText("");
			//	atsfunctionalgroup.clearCheck();

			}

			if (view instanceof ViewGroup
					&& (((ViewGroup) view).getChildCount() > 0)) {

				clearForm((ViewGroup) view);
			}

		}
	}

}
