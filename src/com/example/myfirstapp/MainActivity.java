package com.example.myfirstapp;

import android.app.ListActivity;
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
		
		new Data("alex", 14, 10);
		new Data("peter", 100, 20);
		
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
 
		//get selected items
		Data entry = (Data) getListAdapter().getItem(position);
		Toast.makeText(this, entry.name, Toast.LENGTH_SHORT).show();
 
	}
	

}
