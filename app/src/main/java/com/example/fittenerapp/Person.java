package com.example.fittenerapp;

import android.util.Log;
import static java.lang.Float.isNaN;

/**
 * Class for the user's details
 * @author Jan Buben
 * @version 0.2 05/05/2020
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
     * Method calculates the BMI using the weight and height of the person (height has to be centimeters!)
     * @return returns the BMI-value, returns 0 and asks for valid input if calculation leads to NaN
     */
    public String getBMI() {
        // Gets the centimeter value by dividing with 100
        float height = this.height / 100f;
        // The calculation for the BMI
        this.bmi = this.weight / (height * height);
        // Checks if the BMI is not a number and returns 0 if it is
        if (isNaN(this.bmi)) {
            return "0";
        }
        // Returns the value with the accuracy of 2 decimals
        return Float.toString(Math.round(this.bmi * 100.0f) / 100.0f);
    }

    /**
     * Method checks the BMI value and gives feedback to the user on if they are overweight, underweight OR the right weight. Also checks that the input is valid.
     * @return returns the feedback as a String (String)
     */
    public String checkBMI() {
        // If the BMI calculator gives a Non number value, returns the following line. Otherwise check with the other values.
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
     * Method gets the person's values and prints them out
     * @return returns the values as String
     */
    public String getPerson() {
        return Float.toString(this.height) + " cm" + " " + Float.toString(this.weight) + "kg"
        + " " + Float.toString(this.bmi);
    }
}
