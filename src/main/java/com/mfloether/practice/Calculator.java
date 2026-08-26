package com.mfloether.practice;

public class Calculator {

    private double num1, num2, result;

    public Calculator() {
        num1 = 0;
        num2 = 0;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public double getResult() {
        return result;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public static double add(double num1, double num2){
        return num1 + num2;
    }

    public static double subtract(double num1, double num2){
        return num1 - num2;
    }

    public static double multiple(double num1, double num2){
        return num1 * num2;
    }

    public static double divide(double num1, double num2){
        return num1 / num2;
    }
}
