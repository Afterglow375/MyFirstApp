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
		setContentView(R.layout.activity_grade_scale);
		
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
				
				float a = Float.parseFloat(editA.getText().toString());
				float b = Float.parseFloat(editB.getText().toString());
				float c = Float.parseFloat(editC.getText().toString());
				float d = Float.parseFloat(editD.getText().toString());
				float f = Float.parseFloat(editF.getText().toString());
				
				// Check for bad input
				if (a > 100) 
					Toast.makeText(getApplicationContext(), "All grades must be below 100!", Toast.LENGTH_SHORT).show();
				else if (b >= a || c >= a || d >= a || f >= a || c >= b || d >= b || f >= b || d >= c || f >= c || f >= d) {
					Toast.makeText(getApplicationContext(), "Invalid grade scale.", Toast.LENGTH_SHORT).show();
				}
				else { // Save if good input
					Data.editA(a);
					Data.editB(b);
					Data.editC(c);
					Data.editD(d);
					Data.editF(f);
					Toast.makeText(getApplicationContext(), "Changes saved.", Toast.LENGTH_SHORT).show();
					CustomAdapter.save(); // Update in memory
				}
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
