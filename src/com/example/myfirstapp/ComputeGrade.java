package com.example.myfirstapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ComputeGrade extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compute_grade);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compute_grade, menu);
		return true;
	}

}
