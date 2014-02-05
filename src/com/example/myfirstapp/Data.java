package com.example.myfirstapp;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Data {
	private static float totalWeight = 0;
	private static float overallGrade = 0;
	private static float[] gradeScale = {0, 60, 70, 80, 90};
	private static ArrayList<Data> entries = new ArrayList<Data>();
	public String name;
	public float grade;
	public float weight;
	
	public Data(String name, float grade, float weight) { // Store name, grade, and weight of each grade
		this.name = name;
		this.grade = grade;
		this.weight = weight;
		entries.add(this);
		overallGrade += grade*weight;
		totalWeight += weight;
	}
	
	public static boolean isEmpty() {
		return entries.isEmpty();
	}
	
	public static float getNumberGrade() {
		if (totalWeight == 0) {
			return 0;
		}
        BigDecimal grade = new BigDecimal(Float.toString(overallGrade / totalWeight));
        grade = grade.setScale(2, BigDecimal.ROUND_HALF_UP);
		return grade.floatValue();
	}

	public static char getLetterGrade() {
        float grade = getNumberGrade();
        char letterGrade;
        if (grade > gradeScale[4]) {
        	letterGrade = 'A';
        }
        else if (grade > gradeScale[3]) {
        	letterGrade = 'B';
        }
        else if (grade > gradeScale[2]) {
        	letterGrade = 'C';
        }
        else if (grade > gradeScale[1]) {
        	letterGrade = 'D';
        }
        else {
        	letterGrade = 'F';
        }
		return letterGrade;
	}
	
	public static float getA() {
		return gradeScale[4];
	}

	public static float getB() {
		return gradeScale[3];
	}
	
	public static float getC() {
		return gradeScale[2];
	}
	
	public static float getD() {
		return gradeScale[1];
	}
	
	public static float getF() {
		return gradeScale[0];
	}
	
	public static void editA(float grade) {
		gradeScale[4] = grade;
	}
	
	public static void editB(float grade) {
		gradeScale[3] = grade;
	}
	
	public static void editC(float grade) {
		gradeScale[2] = grade;
	}
	
	public static void editD(float grade) {
		gradeScale[1] = grade;
	}
	
	public static void editF(float grade) {
		gradeScale[0] = grade;
	}

	public static float[] getGradeScale() {
		return gradeScale;
	}
	
	public static float getWeightSum() {
		return totalWeight;
	}
	
	public static float getOverallGrade() {
		return overallGrade;
	}
	
	public static void removeEntry(int index) {
		Data entry = entries.get(index);
		overallGrade -= entry.grade*entry.weight;
		totalWeight -= entry.weight;
		entries.remove(index);
	}
	
	public static int getSize() {
		return entries.size();
	}
	
	public static Data getEntry(int index) {
		return entries.get(index);
	}
	
	public static ArrayList<Data> getData() {
		return entries;
	}
	
	public static void editName(int index, String inName) {
		entries.get(index).name = inName;
	}
	
	public static void editWeightGrade(int index, float inGrade, float inWeight) {
		overallGrade += inGrade*inWeight - entries.get(index).grade*entries.get(index).weight;
		entries.get(index).grade = inGrade;
		entries.get(index).weight = inWeight;
	}
}
