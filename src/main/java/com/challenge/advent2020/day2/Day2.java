package com.challenge.advent2020.day2;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 implements Day {
   private static final Logger         logger    = LoggerFactory.getLogger(Day2.class);
   private static final List<Password> inputData = new ArrayList<>();

   private static class Password {
      int low;
      int high;
      char value;
      String pass;
   }

   public Day2(String filePath) {
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = reader.readLine()) != null) {
            String regex = "(\\d+)-(\\d+) (\\w): ([a-z]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            matcher.find();

            Password record = new Password();

            record.low = Integer.parseInt(matcher.group(1));
            record.high = Integer.parseInt(matcher.group(2));
            record.value = matcher.group(3).charAt(0);
            record.pass = matcher.group(4);

            inputData.add(record);
         }
      }
      catch (IOException ex) {
         logger.error("Could not access the file {}", filePath);
         ex.printStackTrace();
      }
   }

   public List<String> solve() {
      List<String> result = new ArrayList<>();

      result.add(partA());
      result.add(partB());

      return result;
   }

   @Override
   public String partA() {
      StringBuilder result = new StringBuilder("Part A answer: ");
      int validPassword = 0;

      for (Password record : inputData) {
         long charCount = record.pass.codePoints().filter(ch -> ch == record.value).count();
         if (charCount >= record.low && charCount <= record.high) {
            ++validPassword;
         }
      }
      return result.append(validPassword).toString();
   }

   @Override
   public String partB() {
      StringBuilder result = new StringBuilder("Part B answer: ");
      int validPassword = 0;
      for (Password record : inputData) {

         if ((record.pass.charAt(record.low - 1) == record.value ||
                 record.pass.charAt(record.high - 1) == record.value) &&
                 record.pass.charAt(record.low - 1) != record.pass.charAt(record.high - 1)) {
               ++validPassword;
         }
      }
      return result.append(validPassword).toString();
   }
}
