package com.example.myfirstapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Data> {
	private Context context;
	private static File file;
	
	public CustomAdapter(Context context) {
		super(context, R.layout.list_view_item, Data.getData());
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) { // Populate the ListView
		View myView = convertView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			myView = (RelativeLayout) inflater.inflate(R.layout.list_view_item, parent, false);
		}

		Data entry = (Data) getItem(position);
			
		TextView name = (TextView) myView.findViewById(R.id.inputName);
		TextView grade = (TextView) myView.findViewById(R.id.inputGrade);
		TextView weight = (TextView) myView.findViewById(R.id.inputWeight);
			
		name.setText(entry.name);
		grade.setText(Float.toString(entry.grade));
		weight.setText(Float.toString(entry.weight));
		
		return myView;
	}
	
	public static void load(File inFile) { // Loading from internal memory
		file = inFile;
		try {
			Scanner scanner = new Scanner(file);
			int numLines = Integer.parseInt(scanner.nextLine());
			float a = Float.parseFloat(scanner.nextLine());
			float b = Float.parseFloat(scanner.nextLine());
			float c = Float.parseFloat(scanner.nextLine());
			float d = Float.parseFloat(scanner.nextLine());
			float f = Float.parseFloat(scanner.nextLine());
			Data.editA(a);
			Data.editB(b);
			Data.editC(c);
			Data.editD(d);
			Data.editF(f);
			for (int i = 0; i < numLines; i++) {
				String name = scanner.nextLine();
				float grade = Float.parseFloat(scanner.nextLine());
				float weight = Float.parseFloat(scanner.nextLine());
				new Data(name, grade, weight);
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void save() { // Saving to internal memory
		try {
			PrintStream ps = new PrintStream(file);
			int numLines = Data.getSize();
			ps.println(Integer.toString(numLines));
			ps.println(Float.toString(Data.getA()));
			ps.println(Float.toString(Data.getB()));
			ps.println(Float.toString(Data.getC()));
			ps.println(Float.toString(Data.getD()));
			ps.println(Float.toString(Data.getF()));
			for (int i = 0; i < numLines; i++) {
				Data entry = Data.getEntry(i);
				ps.println(entry.name);
				ps.println(Float.toString(entry.grade));
				ps.println(Float.toString(entry.weight));
			}
			ps.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
