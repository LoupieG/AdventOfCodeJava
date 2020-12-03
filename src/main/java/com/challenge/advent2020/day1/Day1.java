package com.challenge.advent2020.day1;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day1 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day1.class);
   private              List<String> inputData;

   public Day1(String filePath) {
      try {
         inputData = Files.readAllLines(Paths.get(filePath));
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
   public String partB() {
      StringBuilder result = new StringBuilder("Part B answer: ");
      for (int index = 0; index < inputData.size() / 2; ++index) {
         int left = Integer.parseInt(inputData.get(index));
         for (int m = index + 1; m < inputData.size() - 2; ++m) {
            int middle = Integer.parseInt(inputData.get(m));

            for (int r = m + 1; r < inputData.size(); ++r) {
               int right = Integer.parseInt(inputData.get(r));
               if (left + middle + right == 2020) {
                  result.append(left * middle * right);
                  index = inputData.size();
                  break;
               }
            }
         }
      }
      return result.toString();
   }

   @Override
   public String partA() {
      StringBuilder result = new StringBuilder("Part A answer: ");
      for (int index = 0; index < inputData.size() / 2; ++index) {
         int left = Integer.parseInt(inputData.get(index));
         for (int test = index + 1; test < inputData.size(); ++test) {
            int right = Integer.parseInt(inputData.get(test));
            if (left + right == 2020) {
               result.append(left * right);
               index = inputData.size();
               break;
            }
         }
      }
      return result.toString();
   }
}
