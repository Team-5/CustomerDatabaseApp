package util;

import java.util.Scanner;

/**
 * Prompts for and then validates console based input. Based on code written by
 * Professor John Phillips, who based his code in part on Murach's Java SE 6 by
 * Joel Murach, et. al.
 *
 * @author Jason Whiting
 * @version 2016-10-20
 */
public class Validator {

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * a String.
     *
     * @param sc
     * @param prompt
     * @return
     */
    public static String getLine(Scanner sc, String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * a String. The 'regex' is a String that contains a regular expression. The
     * message will repeat until the user enters a correct value.
     *
     * @param sc
     * @param prompt
     * @param regex
     * @return
     */
    public static String getLine(Scanner sc, String prompt, String regex) {
        boolean isValid = false;
        String s = "";
        while (isValid == false) {
            System.out.println(prompt);
            if (sc.hasNextLine()) {
                s = sc.nextLine();
                if (s.matches(regex)) {
                    isValid = true;
                } else {
                    System.out.println("\nERROR! Invalid input. Try Again.\n");
                }
            }
        }
        return s;
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * an integer.
     *
     * @param sc
     * @param prompt
     * @return
     */
    public static int getInt(Scanner sc, String prompt) {
        boolean isValid = false;
        int i = 0;
        while (isValid == false) {
            System.out.println(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("\nERROR! Invalid integer value. Try Again.\n");
            }
            sc.nextLine();
        }
        return i;
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * an integer and checks that it is within a proper range.
     *
     * @param sc
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public static int getInt(Scanner sc, String prompt, int min, int max) {
        boolean isValid = false;
        int i = 0;
        while (isValid == false) {
            i = getInt(sc, prompt);
            if (i < min) {
                System.out.println("\nERROR! Must be greater than " + min + ".\n");
            } else if (i > max) {
                System.out.println("\nERROR! Must be less than " + max + ".\n");
            } else {
                isValid = true;
            }
        }
        return i;
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * an long data type.
     *
     * @param sc
     * @param prompt
     * @return
     */
    public static long getLong(Scanner sc, String prompt) {
        boolean isValid = false;
        long i = 0;
        while (isValid == false) {
            System.out.println(prompt);
            if (sc.hasNextLong()) {
                i = sc.nextLong();
                isValid = true;
            } else {
                System.out.println("\nERROR! Invalid long value. Try Again.\n");
            }
            sc.nextLine();
        }
        return i;
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * an long data type and checks that it is within a proper range.
     *
     * @param sc
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public static long getLong(Scanner sc, String prompt, int min, int max) {
        boolean isValid = false;
        long i = 0;
        while (isValid == false) {
            i = getLong(sc, prompt);
            if (i < min) {
                System.out.println("\nERROR! Must be greater than " + min + ".\n");
            } else if (i > max) {
                System.out.println("\nERROR! Must be less than " + max + ".\n");
            } else {
                isValid = true;
            }
        }
        return i;
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * an double data type.
     *
     * @param sc
     * @param prompt
     * @return
     */
    public static double getDouble(Scanner sc, String prompt) {
        boolean isValid = false;
        double d = 0;
        while (isValid == false) {
            System.out.println(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else {
                System.out.println("\nERROR! Invalid double value. Try Again.\n");
            }
            sc.nextLine();
        }
        return d;
    }

    /**
     * Prompts the user with a message and then retrieves what the user types as
     * an double data type and checks that it is within a proper range.
     *
     * @param sc
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public static double getLDouble(Scanner sc, String prompt, int min, int max) {
        boolean isValid = false;
        double d = 0;
        while (isValid == false) {
            d = getDouble(sc, prompt);
            if (d < min) {
                System.out.println("\nERROR! Must be greater than " + min + ".\n");
            } else if (d > max) {
                System.out.println("\nERROR! Must be less than " + max + ".\n");
            } else {
                isValid = true;
            }
        }
        return d;
    }
}
