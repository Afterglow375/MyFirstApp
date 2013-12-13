package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Data> {
	private Context context;
	
	public CustomAdapter(Context context) {
		super(context, R.layout.list_view_item, Data.entries);
		
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		if (convertView == null) {
//			
//		}
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		convertView = (RelativeLayout) inflater.inflate(R.layout.list_view_item, parent, false);
		Data entry = (Data) getItem(position);
		
		TextView name = (TextView) convertView.findViewById(R.id.inputName);
		TextView grade = (TextView) convertView.findViewById(R.id.inputGrade);
		TextView weight = (TextView) convertView.findViewById(R.id.inputWeight);
		
		name.setText(entry.name);
		grade.setText(Float.toString(entry.grade));
		weight.setText(Float.toString(entry.weight));
		
		return convertView;
	}

}
