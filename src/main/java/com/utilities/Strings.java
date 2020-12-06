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

   public static <T> Set<T> intersection(Set<T> list1, Set<T> list2) {
      Set<T> list = new HashSet<>();

      for (T t : list1) {
         if (list2.contains(t)) {
            list.add(t);
         }
      }
      return list;
   }

   public static List<String> getStringArray(String question) {
      List<String> result = new ArrayList<>();

      for (int index = 0; index < question.length(); ++index) {
         result.add(String.valueOf(question.charAt(index)));
      }

      return result;
   }

   public static String[][] initialize2dArray(int row, int col, char value) {
      String[][] result = new String[row][col];

      for (int rowId = 0; rowId < row; ++rowId) {
         for (int colId = 0; colId < col; ++colId) {
            result[rowId][colId] = String.valueOf(value);
         }
      }

      return result;
   }
}
