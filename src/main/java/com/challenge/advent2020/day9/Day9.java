package com.challenge.advent2020.day9;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.utilities.Numbers.getMaxFromRangedList;
import static com.utilities.Numbers.getMinFromRangedList;

public class Day9 implements Day {
   private static final Logger     logger = LoggerFactory.getLogger(Day9.class);
   private              List<Long> inputData;
   private static final int        RANGE  = 25;

   public Day9(String filePath) {
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         inputData = new ArrayList<>();
         String line;

         while ((line = reader.readLine()) != null) {
            inputData.add(Long.parseLong(line));
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

      return "Part A answer: " + testValues(1);
   }

   @Override
   public String partB() {

      return "Part B answer: " + testValues(2);
   }

   private String testValues(int part) {
      String result = "";

      int lowIndex   = 0;
      int upperIndex = RANGE - 1;

      for (int index = RANGE; index < inputData.size(); ++index) {
         if (!isSumOfRange(lowIndex++, upperIndex++, inputData.get(index))) {
            result = (part == 1) ? inputData.get(index).toString() : String.valueOf(findWeakness(inputData.get(index), index));
            break;
         }
      }

      return result;
   }

   private boolean isSumOfRange(int low, int high, long value) {
      boolean result = false;
      for (int left = low; left < high && !result; ++left) {
         for (int right = left + 1; right <= high; ++right) {
            if ((inputData.get(left) + inputData.get(right)) == value) {
               result = true;
               break;
            }
         }
      }
      return result;
   }

   private long findWeakness(long endValue, int indexMax) {
      int endLower = 0;
      int endUpper = 0;

      for (int index = 0; index < indexMax - 1 && (endLower == 0 && endUpper == 0); ++index) {
         long sumValue = inputData.get(index);

         for (int next = index + 1; next < indexMax; ++next) {
            sumValue += inputData.get(next);

            if (sumValue == endValue) {
               endLower = index;
               endUpper = next;
               break;
            }
         }
      }

      return (getMaxFromRangedList(endLower, endUpper, inputData) + getMinFromRangedList(endLower, endUpper, inputData));
   }
}
