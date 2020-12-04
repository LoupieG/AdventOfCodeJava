package com.challenge.advent2020.day4;

import java.util.regex.Pattern;

import static com.utilities.Numbers.isPositiveInteger;

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
      return (byr >= 1920 && byr <= 2002);
   }

   private boolean validIyr() {
      return (iyr >= 2010 && iyr <= 2020);
   }

   private boolean validEyr() {
      return (eyr >= 2020 && eyr <= 2030);
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
         var regex   = "^#[a-f0-9]{2,7}$";
         var pattern = Pattern.compile(regex);
         var matcher = pattern.matcher(hcl);
         result = matcher.matches();
      }
      return result;
   }

   private boolean validPid() {
      return (pid != null && pid.length() == 9 && isPositiveInteger(pid));
   }


   private boolean validHgt() {
      boolean result = false;
      int     value  = 0;

      if (hgt != null) {
         if (hgt.contains("in")) {
            value = getMeasurement("in");
            if (value >= 59 && value <= 76) {
               result = true;
            }
         }
         else if (hgt.contains("cm")) {
            value = getMeasurement("cm");
            if (value >= 150 && value <= 193) {
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

      if (isPositiveInteger(number.toString())) {
         result = Integer.parseInt(number.toString());
      }
      return result;
   }


}
