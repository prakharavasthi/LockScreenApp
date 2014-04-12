/* 
 * 
 * Copyright 2014 Prakhar Avasthi
 * All Rights Reserved
 * developer: Prakhar Avasthi
 */

package com.prakhar.lockapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LockAppActivity extends Activity {

    /** Called when the activity is first created. */
    EditText password;	
    Button submit;
    public static int setting=0;
    public static String OnlyPassword=null;
   // public static String showSetupScreen ;
    SharedPreferences sp;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("savepref", 0);
        OnlyPassword = sp.getString("password", "");
        if(sp.getBoolean("showSetupScreen",true)==true){
        	Intent in = new Intent(LockAppActivity.this, SetupActivity.class);
        	startActivityForResult(in,1);
        }
        else{
        	setContentView(R.layout.main);
        	password = (EditText) findViewById(R.id.pass);
        	submit = (Button) findViewById(R.id.submit);
        	submitaction(submit);
        }
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == RESULT_OK && requestCode == 1) {
      	setContentView(R.layout.main);
      	password = (EditText) findViewById(R.id.pass);
      	submit = (Button) findViewById(R.id.submit);
      	submitaction(submit);
	    Toast.makeText(this, "BACK",Toast.LENGTH_SHORT).show();
	  }
	} 
	
	/*
	 *  Disables keypress, home and back keys
	 */
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
    
    /*
     * Locks the screen,sets it as a keyguard
     */
    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
        super.onAttachedToWindow();
    }
    
    /*
     * Method to implement action to be carried out when Submit is clicked
     * Checks password and unlocks if password is correct
     */

    private void submitaction(Button submit)
    {
    	submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {			
				if (password.getText().toString().equals(OnlyPassword))
		        {
				LockAppActivity.this.finish();
		        }
				else
				{
				Toast.makeText(getApplicationContext(), "Incorrect password : " + password.getText().toString(), Toast.LENGTH_SHORT).show();	
				}
				}
				}
        );
    }
    
    public void removePass(View view){
    	sp = getSharedPreferences("savepref", 0);
    	sp.edit().remove("password").commit();
    	Intent in = new Intent(LockAppActivity.this, SetupActivity.class);
    	startActivityForResult(in,1);   	
    	
    }

}