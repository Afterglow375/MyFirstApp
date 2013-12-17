package com.example.myfirstapp;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

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
		
		File file = new File(getApplicationContext().getFilesDir(), "grades.dat");
		try {
			if (!file.isFile()) { // Creates new file
				file.createNewFile();
				PrintStream writer = new PrintStream(file);
				writer.println(Integer.toString(0));
				writer.println(Float.toString(90));
				writer.println(Float.toString(80));
				writer.println(Float.toString(70));
				writer.println(Float.toString(60));
				writer.println(Float.toString(0));
				writer.close();
				CustomAdapter.load(file);
			}
			else if (Data.isEmpty()) {
				CustomAdapter.load(file);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		setListAdapter(adapter);
		showGrade();
		
		final ImageButton editGradeScale = (ImageButton) findViewById(R.id.editGradeScale);
		final ImageButton computeGrade = (ImageButton) findViewById(R.id.computeGrade);
		final ImageButton addGrade = (ImageButton) findViewById(R.id.addGrade);
		
		// Add grade button
		addGrade.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				Intent myIntent = new Intent(context, AddGradeActivity.class);
				startActivityForResult(myIntent, ADD_GRADE);
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
	
	public void showGrade() {
		TextView finalGrade = (TextView) findViewById(R.id.currentGrade);
		letterGrade = Data.getLetterGrade();
		numberGrade = Data.getNumberGrade();
		finalGrade.setText(letterGrade + ": " + Float.toString(numberGrade) + "%");
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == EDIT_GRADE) {
			if (resultCode == RESULT_OK) {
				adapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "Grade edited.", Toast.LENGTH_SHORT).show();
				showGrade();
			}
			else if (resultCode == RESULT_CANCELED) {
				adapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "Grade deleted.", Toast.LENGTH_SHORT).show();
				showGrade();
			}
		}
		else if (requestCode == ADD_GRADE) {
			if (resultCode == RESULT_OK) {
				adapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "Grade added.", Toast.LENGTH_SHORT).show();
				showGrade();
			}
		}
	}

}
