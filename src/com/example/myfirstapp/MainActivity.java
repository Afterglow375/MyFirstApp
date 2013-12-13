package com.example.myfirstapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private CustomAdapter adapter;
	private static final int EDIT_GRADE = 0;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adapter = new CustomAdapter(this);
		
		new Data("Quiz #1", 14, 10);
		new Data("Quiz #2", 12, 9);
		new Data("Quiz #3", 12, 9);
		new Data("Quiz #4", 11, 9);
		new Data("Quiz #5", 12, 9);
		new Data("Quiz #6", 120, 9);
		new Data("Quiz #7", 12, 9);
		
		setListAdapter(adapter);
		
		final ImageButton editGradeScale = (ImageButton) findViewById(R.id.editGradeScale);
		final ImageButton computeGrade = (ImageButton) findViewById(R.id.computeGrade);
		final ImageButton addGrade = (ImageButton) findViewById(R.id.addGrade);
		
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
            	
            }
		});
		
		// Add grade button
		addGrade.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
            	
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
		Intent intent = new Intent(getBaseContext(), GradeActivity.class);
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
	}

}
