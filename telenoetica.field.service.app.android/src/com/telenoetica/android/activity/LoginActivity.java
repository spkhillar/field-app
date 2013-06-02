package com.telenoetica.android.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.widget.Button;
import android.view.View.OnClickListener;


public class LoginActivity extends Activity {
	Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
	addListenerOnButton();
}

public void addListenerOnButton() {

	final Context context = this;

	button = (Button) findViewById(R.id.btnLogin);

	button.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {

		    Intent intent = new Intent(context, MainMenu.class);
                        startActivity(intent);   

		}

	});

}

}
