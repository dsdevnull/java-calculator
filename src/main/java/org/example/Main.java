package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter the calculation that you would want to perform.");
        Scanner sc = new Scanner(System.in);
        boolean choiceFlag = true;
        String choice;

        while (choiceFlag) {
            String calculation = sc.nextLine();
            calculation = calculation.replaceAll("\\s+", "");

            if (checkValidInput(calculation)) {
                double result = splitAndCalculate(calculation);
                String formattedResult = formatResult(result);
                System.out.println("The result of your calculation is: " + formattedResult);
            } else {
                System.out.println("We are unable to calculate that at this time. Please use the 4 basic operators at this time.");
            }

            System.out.print("Do you want to continue? (y/n): ");
            choice = sc.nextLine();

            if (choice.equalsIgnoreCase("n")) {
                choiceFlag = false;
            }
        }
    }

    //This is to check if the input the user provided is clean and what is expected
    public static boolean checkValidInput(String userInput) {
        return userInput.matches(".*[+\\-*/].*");
    }

    public static double splitAndCalculate(String userInput) {
        String regex = "([+-]?\\d*\\.?\\d+)([+\\-*/])([+-]?\\d*\\.?\\d+)";
        double result = 0;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            double num1 = Double.parseDouble(matcher.group(1));
            String operator = matcher.group(2);
            double num2 = Double.parseDouble(matcher.group(3));

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        System.out.println("Error: Division by zero");
                    }
                    break;
                default:
                    System.out.println("Something went wrong, please try again.");
            }
        }
        return result;
    }

    public static int countDecimalPlaces(double number) {
        String[] parts = String.valueOf(number).split("\\.");
        return parts.length > 1 ? parts[1].length() : 0;
    }

    public static String formatResult(double result) {
        // Check if the result is a whole number
        if (result % 1 == 0) {
            return String.format("%.0f", result);
        } else {
            // If the result has decimal places, format it with the appropriate number of decimal places
            int decimalPlaces = countDecimalPlaces(result);
            return String.format("%." + decimalPlaces + "f", result);
        }
    }
}