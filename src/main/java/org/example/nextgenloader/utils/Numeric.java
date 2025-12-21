package org.example.nextgenloader.utils;

public class Numeric {
    public static boolean isACorrectNumber(String input) {

        System.out.println(input);
        try {
            int result = Integer.parseInt(input);
            System.out.println("#: " + result);
            return result > 0;
        } catch (NumberFormatException e) {

            return false;
        }
    }
}
