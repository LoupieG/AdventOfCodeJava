package com.utilities;

import java.util.List;

public class Numbers {
   private Numbers() {
   }

   /**
    * Parse an integer and return its value or 0
    *
    * @param value string value to turn into an int
    * @return the int value of the input or 0
    */
   public static int parseInteger(String value) {
      return parseInteger(value, 0);
   }

   /**
    * Parse an integer and return its value or a default value if not an integer
    *
    * @param value        string value to turn into an int
    * @param defaultValue default value to return if value is not an integer
    * @return the int value of the input or the default value
    */
   public static int parseInteger(String value, int defaultValue) {
      int result = defaultValue;
      if (isInteger(value)) {
         result = Integer.parseInt(value);
      }

      return result;
   }

   /**
    * Checks to see if a string value is a positive integer (>= 0)
    *
    * @param value string value to test
    * @return true / false
    */
   public static boolean isPositiveInteger(String value) {
      return isPositiveInteger(value, 0);
   }

   /**
    * Checks to see if a string value is a positive integer (>= 0)
    *
    * @param value      string value to test
    * @param startIndex the starting character position of the string
    * @return true / false
    */
   public static boolean isPositiveInteger(String value, int startIndex) {
      boolean result = true;

      if (value == null || value.length() == 0) {
         result = false;
      }
      else {
         for (int index = startIndex; index < value.length(); ++index) {
            char ch = value.charAt(index);
            if (!isBetween(ch, '0', '9')) {
               result = false;
               break;
            }
         }
      }

      return result;
   }

   /**
    * Check if a string value is an integer to prevent exceptions being thrown
    *
    * @param value value to check
    * @return true (is an integer) / false (not an integer)
    */
   public static boolean isInteger(String value) {
      boolean result;

      if (value.charAt(0) == '-') {
         result = isPositiveInteger(value, 1);
      }
      else {
         result = isPositiveInteger(value);
      }
      return result;
   }

   /**
    * Check if a value is between two values
    *
    * @param value Number to check
    * @param low   Lower bound
    * @param high  Upper Bound
    * @return true/false
    */
   public static <T extends Comparable<? super T>> boolean isBetween(T value, T low, T high) {
      return (low.compareTo(value) <= 0 && high.compareTo(value) >= 0);
   }

   /**
    * Get the max value in a range from an ArrayList
    *
    * @param low  lower bound of List (Start)
    * @param high upper bound of List (End)
    * @param list the ArrayList
    * @param <T>  Integer, Long, Double
    * @return The max value in the bounded list
    */
   public static <T extends Comparable<? super T>> T getMaxFromRangedList(int low, int high, List<T> list) {
      T result = list.get(low);

      for (int index = low + 1; index < high; ++index) {
         result = (list.get(index).compareTo(result) > 0) ? list.get(index) : result;
      }

      return result;
   }

   /**
    * Get the min value in a range from an ArrayList
    *
    * @param low  lower bound of list (Start)
    * @param high upper bound of list (End)
    * @param list the ArrayList
    * @param <T>  Integer, Long, Double, etc...
    * @return The minimum value in the bounded list
    */
   public static <T extends Comparable<? super T>> T getMinFromRangedList(int low, int high, List<T> list) {
      T result = list.get(low);

      for (int index = low + 1; index < high; ++index) {
         result = (list.get(index).compareTo(result) < 0) ? list.get(index) : result;
      }

      return result;
   }
}
