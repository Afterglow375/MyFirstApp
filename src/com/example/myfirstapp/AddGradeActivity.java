package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddGradeActivity extends Activity {
	private EditText editNameView;
	private EditText editGradeView;
	private EditText editWeightView;
	private Intent returnIntent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_grade);
		
		final ImageButton save = (ImageButton) findViewById(R.id.save);
		final ImageButton back = (ImageButton) findViewById(R.id.back);
		editNameView = (EditText) findViewById(R.id.editName);
		editGradeView = (EditText) findViewById(R.id.editGrade);
		editWeightView = (EditText) findViewById(R.id.editWeight);
		
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
				String name = editNameView.getText().toString();
				String grade = editGradeView.getText().toString();
				String weight = editWeightView.getText().toString();
				
				if (name.equals("")) {
            		Toast toast = Toast.makeText(getApplicationContext(), "You need to enter a name", Toast.LENGTH_SHORT);
                	toast.show();
            	}
            	else if (grade.equals("")) {
            		Toast toast = Toast.makeText(getApplicationContext(), "You need to enter a grade", Toast.LENGTH_SHORT);
                	toast.show();
            	}
            	else if (weight.equals("")) {
            		Toast toast = Toast.makeText(getApplicationContext(), "You need to enter a weight", Toast.LENGTH_SHORT);
                	toast.show();
            	}
            	else if (Float.parseFloat(weight) + Data.totalWeight > 100) {
            		Toast toast = Toast.makeText(getApplicationContext(), "Sum of weights is above 100!", Toast.LENGTH_SHORT);
                	toast.show();
            	}
            	else {
					new Data(name, Float.parseFloat(grade), Float.parseFloat(weight));
						
					setResult(RESULT_OK, returnIntent);
					finish();
            	}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_grade, menu);
		return true;
	}

}
