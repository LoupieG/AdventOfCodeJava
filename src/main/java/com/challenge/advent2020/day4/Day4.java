package com.challenge.advent2020.day4;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.utilities.Numbers.isInteger;
import static com.utilities.Numbers.parseInteger;

public class Day4 implements Day {
   private static final Logger         logger = LoggerFactory.getLogger(Day4.class);
   private final        List<Passport> inputData;

   public Day4(String filePath) {
      inputData = new ArrayList<>();
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String   line;
         Passport passport = new Passport();

         while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
               inputData.add(passport);
               passport = new Passport();
            }
            else {
               String[] split = line.split(" ");
               addPassportData(split, passport);
            }
         }
         inputData.add(passport);
      }
      catch (IOException ex) {
         logger.error("Could not access the file {}", filePath);
         ex.printStackTrace();
      }
   }

   private void addPassportData(String[] line, Passport passport) {
      for (String data : line) {
         String[] part = data.split(":");

         switch (part[0]) {
            case "byr":
               if (isInteger(part[1])) {
                  passport.byr = parseInteger(part[1]);
               }
               break;
            case "iyr":
               if (isInteger(part[1])) {
                  passport.iyr = parseInteger(part[1]);
               }
               break;
            case "eyr":
               if (isInteger(part[1])) {
                  passport.eyr = parseInteger(part[1]);
               }
               break;
            case "hgt":
               passport.hgt = part[1];
               break;
            case "hcl":
               passport.hcl = part[1];
               break;
            case "ecl":
               passport.ecl = part[1];
               break;
            case "pid":
               passport.pid = part[1];
               break;
            case "cid":
               passport.cid = part[1];
               break;
            default:
               break;
         }
      }
   }

   public List<String> solve() {
      var result = new ArrayList<String>();

      result.add(partA());
      result.add(partB());

      return result;
   }

   @Override
   public String partA() {
      StringBuilder result     = new StringBuilder("Part A answer: ");
      var           validCount = 0;

      for (Passport record : inputData) {
         if (record.validPassport()) {
            ++validCount;
         }
      }
      return result.append(validCount).toString();
   }

   @Override
   public String partB() {
      StringBuilder result     = new StringBuilder("Part B answer: ");
      var           validCount = 0;

      for (Passport record : inputData) {
         if (record.validDetails()) {
            ++validCount;
         }
      }
      return result.append(validCount).toString();
   }
}
