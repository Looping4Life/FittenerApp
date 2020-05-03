package com.example.fittenerapp;

import android.util.Log;

/**
 * Class for the user's details
 * @author Jan Buben
 * @version 0.1 27/4/2020
 */
public class Person {
    public float height;
    public float weight;
    public float bmi;

    /**
     * Constructor that defines all the variables and their values
     * @param height int, the height of the person (integer)
     * @param weight int, the weight of the person (integer)
     * @param bmi float, the BMI-value of the person. (float)
     */
    public Person(float height, float weight, float bmi) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    /**
     * Method for setting an updated weight to the person
     * @param newWeight int, sets the new value for weight (integer)
     */
    public void setWeight(float newWeight) {
        this.weight = newWeight;
    }

    /**
     * Method for setting on updated height to the person
     * @param newHeight int, sets the value for height (integer)
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
        this.bmi = this.weight / (height * height);//
        return Float.toString(Math.round(this.bmi * 100.0f) / 100.0f);
    }

    public String checkBMI() {
        if (this.bmi <= 18) {
            return "Underweight";
        } else if (this.bmi >= 25) {
            return "Overweight";
        } else {
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
