package com.example.fittenerapp;

import android.util.Log;

import static java.lang.Float.isNaN;

/**
 * Class for the user's details
 * @author Jan Buben
 * @version 0.1 27/04/2020
 */
public class Person {
    public static boolean isNaN;
    public float height;
    public float weight;
    public float bmi;

    /**
     * Constructor that defines all the variables and their values
     * @param height float, the height of the person (float)
     * @param weight float, the weight of the person (float)
     * @param bmi float, the BMI-value of the person. (float)
     */
    public Person(float height, float weight, float bmi) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    /**
     * Method for setting an updated weight to the person
     * @param newWeight float, sets the new value for weight (float)
     */
    public void setWeight(float newWeight) {
        this.weight = newWeight;
    }

    /**
     * Method for setting on updated height to the person
     * @param newHeight float, sets the value for height (float)
     */
    public void setHeight(float newHeight) {
        this.height = newHeight;
    }

    /**
     * Method calculates the BMI using the weight and height of the person (height has to be meters!)
     * @return returns the BMI-value
     */
    public String getBMI() {
        float height = this.height / 100f;
        this.bmi = this.weight / (height * height);
        if (isNaN(this.bmi)) {
            return "0";
        }
        return Float.toString(Math.round(this.bmi * 100.0f) / 100.0f);
    }

    /**
     * Method checks the BMI value and gives feedback to the user on if they are overweight, underweight OR the right weight. Also checks that the input is valid.
     * @return returns the feedback as a String (String)
     */
    public String checkBMI() {
        if (isNaN(this.bmi)) {
            return "Please input valid values";
        }
        else if (this.bmi <= 18) {
            return "Underweight. The ideal BMI is between the values of 18-25";
        } else if (this.bmi >= 25) {
            return "Overweight. The ideal BMI is between the values of 18-25";
        } else if (this.bmi <= 22 && this.bmi >= 18) {
            return "Ideal weight, but you're very close to being underweight, the ideal values are between 18-25";
        }
        else {
            return "Ideal weight";
        }
    }


    /**
     * Method gets the person's values
     * @return returns the values as String
     */
    public String getPerson() {
        return Float.toString(this.height) + " cm" + " " + Float.toString(this.weight) + "kg"
        + " " + Float.toString(this.bmi);
    }
}
