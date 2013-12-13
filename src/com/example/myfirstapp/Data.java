package com.example.myfirstapp;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Data {
	public static float totalWeight = 0;
	public static float overallGrade = 0;
	public static float[] gradeScale = {0, 60, 70, 80, 90};
	public static ArrayList<Data> entries = new ArrayList<Data>();
	public String name;
	public float grade;
	public float weight;
	
	public Data(String name, float grade, float weight) {
		this.name = name;
		this.grade = grade;
		this.weight = weight;
		entries.add(this);
		overallGrade += grade*weight;
		totalWeight += weight;
	}
	
	public static float getNumberGrade() {
		//return (overallGrade / totalWeight);
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
	
	public static void removeEntry(int index) {
		entries.remove(index);
	}
	
	public static void editName(int index, String inName) {
		entries.get(index).name = inName;
	}
	
	public static void editGrade(int index, float inGrade) {
		overallGrade += inGrade - entries.get(index).grade;
		entries.get(index).grade = inGrade;
	}

	public static void editWeight(int index, float inWeight) {
		overallGrade += inWeight - entries.get(index).weight;
		entries.get(index).weight = inWeight;
	}
}
