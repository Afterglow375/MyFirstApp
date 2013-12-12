package com.example.myfirstapp;

import java.util.ArrayList;

public class Data {
	public static ArrayList<Data> entries = new ArrayList<Data>();
	public String name;
	public float grade;
	public float weight;
	
	public Data(String name, float grade, float weight) {
		this.name = name;
		this.grade = grade;
		this.weight = weight;
		entries.add(this);
	}
	
	public void removeEntry(int index) {
		entries.remove(index);
	}
	
	public void editName(int index, String inName) {
		entries.get(index).name = inName;
	}
	
	public void editGrade(int index, float inGrade) {
		entries.get(index).grade = inGrade;
	}

	public void editWeight(int index, float inWeight) {
		entries.get(index).weight = inWeight;
	}
}
