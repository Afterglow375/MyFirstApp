package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class GradeScaleActivity extends Activity {
	private EditText editA;
	private EditText editB;
	private EditText editC;
	private EditText editD;
	private EditText editF;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grade_scale);
		
		final ImageButton save = (ImageButton) findViewById(R.id.save);
		final ImageButton back = (ImageButton) findViewById(R.id.back);
		editA = (EditText) findViewById(R.id.edita);
		editB = (EditText) findViewById(R.id.editb);
		editC = (EditText) findViewById(R.id.editc);
		editD = (EditText) findViewById(R.id.editd);
		editF = (EditText) findViewById(R.id.editf);
		
		editA.setText(Float.toString(Data.getA()));
		editB.setText(Float.toString(Data.getB()));
		editC.setText(Float.toString(Data.getC()));
		editD.setText(Float.toString(Data.getD()));
		editF.setText(Float.toString(Data.getF()));
		
		// Back button
		back.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
		    	finish();
			}
		});
				
		// Save button
		save.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				// Update in memory
				Data.editA(Float.parseFloat(editA.getText().toString()));
				Data.editB(Float.parseFloat(editB.getText().toString()));
				Data.editC(Float.parseFloat(editC.getText().toString()));
				Data.editD(Float.parseFloat(editD.getText().toString()));
				Data.editF(Float.parseFloat(editF.getText().toString()));
					
				Toast toast = Toast.makeText(getApplicationContext(), "Changes saved.", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grade, menu);
		return true;
	}
}
