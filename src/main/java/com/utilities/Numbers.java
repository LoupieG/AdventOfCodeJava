package com.utilities;

public class Numbers {
   public static boolean isPositiveInteger(String value) {
      boolean result = true;

      if (value == null || value.length() == 0) {
         result = false;
      }
      else {
         for (int index = 0; index < value.length() && result; ++index) {
            char ch = value.charAt(index);
            if (ch < '0' || ch > '9') {
               result = false;
            }
         }
      }

      return result;
   }
}
