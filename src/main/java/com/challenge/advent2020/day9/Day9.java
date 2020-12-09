package com.challenge.advent2020.day9;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day9.class);
   private              List<Long> inputData;
   private static final int          RANGE  = 25;

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
      StringBuilder result = new StringBuilder("Part A answer: ");

      int lowIndex = 0;
      int upperIndex = RANGE - 1;

      for (int index = RANGE; index < inputData.size(); ++index) {
         if (!isSumOfRange(lowIndex++, upperIndex++, inputData.get(index))) {
            result.append(inputData.get(index));
            break;
         }
      }
      return result.toString();
   }

   @Override
   public String partB() {
      StringBuilder result = new StringBuilder("Part B answer: ");

      int lowIndex = 0;
      int upperIndex = RANGE - 1;

      for (int index = RANGE; index < inputData.size(); ++index) {
         if (!isSumOfRange(lowIndex++, upperIndex++, inputData.get(index))) {
            result.append(findWeakness(inputData.get(index), index));
            break;
         }
      }

      return result.toString();
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

     return (getMax(endLower, endUpper) + getMin(endLower, endUpper));
   }

   private long getMax(int low, int high) {
      long result = inputData.get(low);

      for (int index = low + 1; index < high; ++index) {

         if (inputData.get(index) > result) {
            result = inputData.get(index);
         }
      }

      return result;
   }

   private long getMin(int low, int high) {
      long result = inputData.get(low);

      for (int index = low + 1; index < high; ++index) {

         if (inputData.get(index) < result) {
            result = inputData.get(index);
         }
      }

      return result;
   }
}
