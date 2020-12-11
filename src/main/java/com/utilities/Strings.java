package com.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Strings {
   private Strings() {
   }

   public static boolean matchesPattern(String value, String regex) {
      var pattern = Pattern.compile(regex);
      var matcher = pattern.matcher(value);

      return matcher.matches();
   }

   public static boolean stringContains(StringBuilder line, Character ch) {
      return line.indexOf(ch.toString()) > -1;
   }

   /**
    * Get the intersection of two sets
    *
    * @param list1 First set
    * @param list2 Second set
    * @param <T>   Set type Integer, String, etc...
    * @return A set of values that occur in both input sets
    */
   public static <T> Set<T> intersection(Set<T> list1, Set<T> list2) {
      Set<T> list = new HashSet<>();

      for (T element : list1) {
         if (list2.contains(element)) {
            list.add(element);
         }
      }
      return list;
   }

   /**
    * Convert a String into an Array of Strings
    *
    * @param string String to split/convert
    * @return Resultant array of characters as String
    */
   public static List<String> getStringArray(String string) {
      List<String> result = new ArrayList<>();

      for (int index = 0; index < string.length(); ++index) {
         result.add(String.valueOf(string.charAt(index)));
      }

      return result;
   }

   /**
    * Create and initialize a 2d String array
    *
    * @param row   The number of rows
    * @param col   The number of columns
    * @param value The value to put in each element
    * @return The initialized 2d array
    */
   public static String[][] initialize2dArray(int row, int col, char value) {
      String[][] result = new String[row][col];

      for (int rowId = 0; rowId < row; ++rowId) {
         for (int colId = 0; colId < col; ++colId) {
            result[rowId][colId] = String.valueOf(value);
         }
      }

      return result;
   }

   /**
    * Count the number of times a word occurs in a String
    *
    * @param line The string to count against
    * @param word The word to find and count
    * @return The number of occurrences of the word
    */
   public static int wordCount(String line, String word) {
      int      result = 0;
      String[] split  = line.split(" ");

      for (String string : split) {
         if (string.equals(word)) {
            ++result;
         }
      }
      return result;
   }

   /**
    * Split a String on a word
    *
    * @param line The string to split
    * @param word The word(string) to split on
    * @return The split string(s)
    */
   public static String[] splitOnWord(String line, String word) {
      String[] result = new String[wordCount(line, word) + 1];

      String[] spaceSplit  = line.split(" ");
      int      resultIndex = 0;
      boolean  pushed      = false;

      StringBuilder strPush = new StringBuilder();

      for (String s : spaceSplit) {
         if (s.equals(word)) {
            result[resultIndex++] = strPush.toString();
            strPush.delete(0, strPush.length());
            pushed = true;
         }
         else {
            strPush.append(" ").append(s);
            pushed = false;
         }
      }

      if (!pushed) {
         result[resultIndex] = strPush.toString();
      }

      return result;
   }
}
