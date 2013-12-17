package com.example.myfirstapp;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ComputeGrade extends Activity {
	private TextView currGrade;
	private TextView currWeight;
	private EditText inputDesiredGrade;
	private Intent returnIntent = new Intent();
	private final Context context = this;
	private float numberGrade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compute_grade);

		final ImageButton compute = (ImageButton) findViewById(R.id.compute);
		final ImageButton back = (ImageButton) findViewById(R.id.back);
		currGrade = (TextView) findViewById(R.id.grade);
		currWeight = (TextView) findViewById(R.id.weight);
		inputDesiredGrade = (EditText) findViewById(R.id.inputDesiredGrade);
		
		Intent intent = getIntent();
		char letterGrade = intent.getCharExtra("letterGrade", 'E');
		numberGrade = intent.getFloatExtra("numberGrade", 0);
		currGrade.setText(letterGrade + " - " + Float.toString(numberGrade) + "%");
		currWeight.setText(Float.toString(Data.totalWeight));
		
		// Back button
		back.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
		    	finish();
			}
		});
				
		// Compute possible grade button
		compute.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				// Update in memory
				String grade = inputDesiredGrade.getText().toString();
            					
            	if (grade.equals("")) {
            		Toast.makeText(getApplicationContext(), "You need to enter a desired grade.", Toast.LENGTH_SHORT).show();
            	}
            	else {
	            	float neededGrade = (Float.parseFloat(grade) - (Data.getOverallGrade()/100)) / (1 - Data.getWeightSum()/100);
	                BigDecimal neededGradeRounded = new BigDecimal(Float.toString(neededGrade));
	                neededGradeRounded = neededGradeRounded.setScale(2, BigDecimal.ROUND_HALF_UP);
	            	String message = "Average grade needed on all future assignments to achieve a grade of " + grade + "% is " + Float.toString(neededGradeRounded.floatValue()) + "%.";
	            	if (neededGrade > 100 || neededGrade < 0) {
	            		message = "Desired grade is not possible to achieve.";
	            	}
		            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		            alertDialog.setMessage(message);
		            alertDialog.setPositiveButton("OK", null);
		            alertDialog.show();
            	}
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compute_grade, menu);
		return true;
	}

}
