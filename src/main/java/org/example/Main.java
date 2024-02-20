package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter the calculation that you would want to perform.");

        Scanner sc = new Scanner(System.in);
        String calculation = sc.nextLine();
        calculation = calculation.replaceAll("\\s+", "");

        if (checkValidInput(calculation)) {
            System.out.println(calculation);
        } else {
            System.out.println("Invalid Operation requested. Please use the 4 basic operators at this time.");
            return;
        }
    }

    //This is to check if the input the user provided is clean and what is expected
    public static boolean checkValidInput(String userInput) {
        return userInput.matches(".*[+\\-*/].*");
    }
}