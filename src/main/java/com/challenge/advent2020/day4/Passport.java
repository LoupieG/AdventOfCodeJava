package com.challenge.advent2020.day4;

import static com.utilities.Numbers.isBetween;
import static com.utilities.Numbers.isInteger;
import static com.utilities.Strings.matchesPattern;

public class Passport {
   int    byr;
   int    iyr;
   int    eyr;
   String hgt;
   String hcl;
   String ecl;
   String pid;
   String cid;

   Passport() {
      byr = -1;
      iyr = -1;
      eyr = -1;
      hgt = null;
      hcl = null;
      ecl = null;
      pid = null;
      cid = null;
   }

   public boolean validDetails() {
      return (validByr() && validIyr() && validEyr() &&
              validHgt() && validHcl() && validEcl() && validPid());
   }

   public boolean validPassport() {
      boolean result = false;

      if (byr >= 0 && iyr >= 0 && eyr >= 0 &&
              hgt != null && hcl != null && ecl != null &&
              pid != null) {
         result = true;
      }
      return result;
   }

   private boolean validByr() {
      return isBetween(byr, 1920,2002);
   }

   private boolean validIyr() {
      return isBetween(iyr, 2010 ,2020);
   }

   private boolean validEyr() {
      return isBetween(eyr, 2020, 2030);
   }

   private boolean validEcl() {
      boolean result = false;

      if (ecl != null && (ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn") ||
              ecl.equals("gry") || ecl.equals("grn") || ecl.equals("hzl") ||
              ecl.equals("oth"))) {
         result = true;
      }

      return result;
   }

   private boolean validHcl() {
      boolean result = false;

      if (hcl != null) {
         result = matchesPattern(hcl, "^#[a-f0-9]{2,7}$");
      }
      return result;
   }

   private boolean validPid() {
      return (pid != null && pid.length() == 9 && isInteger(pid));
   }


   private boolean validHgt() {
      boolean result = false;
      int     value;

      if (hgt != null) {
         if (hgt.contains("in")) {
            value = getMeasurement("in");
            if (isBetween(value,59,76)) {
               result = true;
            }
         }
         else if (hgt.contains("cm")) {
            value = getMeasurement("cm");
            if (isBetween(value,150,193)) {
               result = true;
            }
         }
      }

      return result;
   }

   private int getMeasurement(String type) {
      int result = 0;

      StringBuilder number = new StringBuilder();

      for (int index = 0; index < hgt.length() && hgt.charAt(index) != type.charAt(0); ++index) {
         number.append(hgt.charAt(index));
      }

      if (isInteger(number.toString())) {
         result = Integer.parseInt(number.toString());
      }
      return result;
   }
}
