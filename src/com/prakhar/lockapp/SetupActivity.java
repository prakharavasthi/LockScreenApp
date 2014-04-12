package com.prakhar.lockapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetupActivity extends Activity {
	EditText pass1, pass2;
	Button ok;
	SharedPreferences sp;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup);
		pass1=(EditText)findViewById(R.id.editText1);
		pass2=(EditText)findViewById(R.id.editText2);
		ok=(Button)findViewById(R.id.button1);
	}
	
	public void setupPassword(View view){
		if(pass1.getText().toString().equals(pass2.getText().toString())){
			LockAppActivity.OnlyPassword=pass1.getText().toString();
			sp = getSharedPreferences("savepref", 0);
			Editor ed = sp.edit();
			ed.putBoolean("showSetupScreen", false);
			ed.putString("password", pass1.getText().toString());
			ed.commit();
			//LockAppActivity.setting=1;
			Toast.makeText(this, "Password set", Toast.LENGTH_LONG).show();
			Intent in = new Intent();
        	setResult(RESULT_OK, in);
        	finish();
		}
		else{
			Toast.makeText(this, "Password doesn't match", Toast.LENGTH_LONG).show();
		}
		
	}
}
