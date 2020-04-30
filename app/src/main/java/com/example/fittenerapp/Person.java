package com.example.fittenerapp;

import static java.lang.Float.isNaN;

/**
 * Class for the user's details
 * @author Jan Buben
 * @version 0.1 27/04/2020
 */
public class Person {
    public static boolean isNaN;
    private float height;
    private float weight;
    private float bmi;

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
        this.height = this.height / 100;
        this.bmi = this.weight / (this.height * this.height);
        if (isNaN(this.bmi)) {
            return "0";
        }
        return Float.toString(this.bmi);
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
