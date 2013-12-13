package com.example.myfirstapp;

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
import android.widget.Toast;

public class EditGradeActivity extends Activity {
	private EditText editNameView;
	private EditText editGradeView;
	private EditText editWeightView;
	private int index;
	private Intent returnIntent = new Intent();
	private final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grade);
		Intent intent = getIntent();
		
		editNameView = (EditText) findViewById(R.id.editName);
		editGradeView = (EditText) findViewById(R.id.editGrade);
		editWeightView = (EditText) findViewById(R.id.editWeight);
		
		final ImageButton save = (ImageButton) findViewById(R.id.save);
		final ImageButton delete = (ImageButton) findViewById(R.id.delete);
		final ImageButton back = (ImageButton) findViewById(R.id.back);
		
		editNameView.setText(intent.getStringExtra("name"));
		editGradeView.setText(Float.toString(intent.getFloatExtra("grade", 0)));
		editWeightView.setText(Float.toString(intent.getFloatExtra("weight", 0)));
		
		index = intent.getIntExtra("id", -1);
		
		// Back button
		back.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }
        });
		
		// Save button
		save.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
            	float weight = Float.parseFloat(editWeightView.getText().toString());
            	
            	if (weight + Data.totalWeight > 100) {
            		Toast toast = Toast.makeText(getApplicationContext(), "Sum of weights is above 100!", Toast.LENGTH_SHORT);
                	toast.show();
            	}
            	else {
	            	// Update in memory
	            	Data.editName(index, editNameView.getText().toString());
	            	Data.editGrade(index, Float.parseFloat(editGradeView.getText().toString()));
	            	Data.editWeight(index, Float.parseFloat(editWeightView.getText().toString()));            	
	            	
	            	setResult(RESULT_OK, returnIntent);
	            	
	            	Toast toast = Toast.makeText(getApplicationContext(), "Changes saved.", Toast.LENGTH_SHORT);
	            	toast.show();
            	}
            }
        });
		
		// Delete button
		delete.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
            	AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            	alertDialog.setTitle("Delete this grade?");
            	             	 
            	// Yes button
            	alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            		public void onClick(DialogInterface dialog, int which) {
            			Data.removeEntry(index);
            			setResult(RESULT_OK, returnIntent);
            			finish();
            		}
            	});
            	
            	// No button
            	alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            		public void onClick(DialogInterface dialog, int which) {
	            	    dialog.cancel();
            	    }
            	});
            	alertDialog.show();
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
