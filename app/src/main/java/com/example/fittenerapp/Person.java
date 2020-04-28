package com.example.fittenerapp;

/**
 * Class for the user's details
 * @author Jan Buben
 * @version 0.1 27/4/2020
 */
public class Person {
    private int height;
    private int weight;
    private float bmi;

    /**
     * Constructor that defines all the variables and their values
     * @param height int, the height of the person (integer)
     * @param weight int, the weight of the person (integer)
     * @param bmi float, the BMI-value of the person. (float)
     */
    public Person(int height, int weight, float bmi) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    /**
     * Method for setting an updated weight to the person
     * @param newWeight int, sets the new value for weight (integer)
     */
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    /**
     * Method for setting on updated height to the person
     * @param newHeight int, sets the value for height (integer)
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    /**
     * Method calculates the BMI using the weight and height of the person (height has to be meters!)
     * @return returns the BMI-value
     */
    public String getBMI() {
        this.bmi = this.weight / this.height / this.height;
        return Float.toString(this.bmi);
    }



    /**
     * Method gets the person's values
     * @return returns the values as String
     */
    public String getPerson() {
        return Integer.toString(this.height) + " cm" + " " + Integer.toString(this.weight) + "kg"
        + " " + Float.toString(this.bmi);
    }
}
