package com.example.myfirstapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private CustomAdapter adapter;
	private static final int EDIT_GRADE = 0;
	private static final int ADD_GRADE = 1;
	private char letterGrade;
	private float numberGrade;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adapter = new CustomAdapter(this);
		
		new Data("Quiz #1", 15, 10);
		new Data("Quiz #2", 20, 10);
		new Data("Quiz #3", 10, 10);
		new Data("Quiz #4", 10, 10);
		new Data("Quiz #5", 10, 10);
		new Data("Quiz #6", 120, 10);
		new Data("Quiz #7", 10, 10);
		
		setListAdapter(adapter);
		
		TextView finalGrade = (TextView) findViewById(R.id.currentGrade);
		letterGrade = Data.getLetterGrade();
		numberGrade = Data.getNumberGrade();
		finalGrade.setText(letterGrade + ": " + Float.toString(numberGrade) + "%");
		
		Toast.makeText(getApplicationContext(), Float.toString(Data.getNumberGrade()), Toast.LENGTH_SHORT).show();
		
		final ImageButton editGradeScale = (ImageButton) findViewById(R.id.editGradeScale);
		final ImageButton computeGrade = (ImageButton) findViewById(R.id.computeGrade);
		final ImageButton addGrade = (ImageButton) findViewById(R.id.addGrade);
		
		// Add grade button
		addGrade.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				Intent myIntent = new Intent(context, AddGradeActivity.class);
				startActivity(myIntent);
            }
		});	
		
		// Edit grade scale
		editGradeScale.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				Intent myIntent = new Intent(context, GradeScaleActivity.class);
				startActivity(myIntent);
            }
		});
		
		// Compute grade button
		computeGrade.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				Intent intent = new Intent(context, ComputeGrade.class);
				intent.putExtra("letterGrade", letterGrade);
				intent.putExtra("numberGrade", numberGrade);
				startActivity(intent);
            }
		});
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//Get the selected entry
		Data entry = (Data) getListAdapter().getItem(position);
		Intent intent = new Intent(getBaseContext(), EditGradeActivity.class);
		intent.putExtra("name", entry.name);
		intent.putExtra("grade", entry.grade);
		intent.putExtra("weight", entry.weight);
		intent.putExtra("id", (int) id);
		startActivityForResult(intent, EDIT_GRADE);
	}	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == EDIT_GRADE) {
			if (resultCode == RESULT_OK) {
				adapter.notifyDataSetChanged();
			}
		}
		else if (resultCode == ADD_GRADE) {
			if (resultCode == RESULT_OK) {
				adapter.notifyDataSetChanged();
			}
		}
	}

}
