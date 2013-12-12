package com.example.myfirstapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		//Intent intent = getIntent();
		
		setListAdapter(new CustomAdapter(this));
//		final ListView listview = (ListView) findViewById(R.id.listView);
		
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
		startActivity(intent);
		
	}
	

}
