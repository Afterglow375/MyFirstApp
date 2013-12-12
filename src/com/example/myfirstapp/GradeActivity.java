package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

public class GradeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grade);
		Intent intent = getIntent();
		
		EditText editNameView = (EditText) findViewById(R.id.editName);
		EditText editGradeView = (EditText) findViewById(R.id.editGrade);
		EditText editWeightView = (EditText) findViewById(R.id.editWeight);
		final ImageButton save = (ImageButton) findViewById(R.id.save);
		final ImageButton delete = (ImageButton) findViewById(R.id.delete);
		
		editNameView.setText(intent.getStringExtra("name"));
		editGradeView.setText(Float.toString(intent.getFloatExtra("grade", 0)));
		editWeightView.setText(Float.toString(intent.getFloatExtra("weight", 0)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grade, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
