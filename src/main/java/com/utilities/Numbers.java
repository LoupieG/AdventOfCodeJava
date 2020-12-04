package com.utilities;

public class Numbers {
   private Numbers() {
   }

   public static int parseInteger(String value) {
      return parseInteger(value, 0);
   }

   public static int parseInteger(String value, int defaultValue) {
      int result = defaultValue;
      if (isInteger(value)) {
         result = Integer.parseInt(value);
      }

      return result;
   }

   public static boolean isPositiveInteger(String value) {
      return isPositiveInteger(value, 0);
   }

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

   public static boolean isBetween(int value, int low, int high) {
      return (low <= value && value <= high);
   }
}
