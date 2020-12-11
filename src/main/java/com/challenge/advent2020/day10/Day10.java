package com.challenge.advent2020.day10;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day10.class);
   private              List<Integer> inputData;

   public Day10(String filePath) {
      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         inputData = new ArrayList<>();
         String line;

         while ((line = reader.readLine()) != null) {
            inputData.add(Integer.parseInt(line));
         }
         Collections.sort(inputData);
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

      return "Part A answer: " + countDiffs();
   }

   @Override
   public String partB() {
      return "Part B answer: " + getPermutationCount();
   }

   private int countDiffs() {
      int diffOne = 1;
      int diffThree = 1;

      for (int index = 0; index < inputData.size() - 1; ++index) {
         int diff = inputData.get(index + 1) - inputData.get(index);

         diffOne += (diff == 1) ? 1 : 0;
         diffThree += (diff == 3) ? 1 : 0;
      }

      logger.info("DiffOne {} DiffThree {}", diffOne, diffThree);
      return (diffOne * diffThree);

   }

   private int getPermutationCount() {
      int result = 1;

      int lowBound = 0;
      int maxBound = inputData.get(inputData.size() - 1) + 3;

      for (int index = inputData.size(); index >= 0; --index) {

         logger.info("maxBound set to {}", maxBound);
         for (int innerIndex = index - 2; innerIndex > 0; --innerIndex) {
              if (inputData.get(innerIndex) >= maxBound - 3) {
                 ++result;
              }
              else {
                 break;
              }
         }

         maxBound = ((index - 1) < 0) ? 0 : inputData.get(index - 1);
      }

      logger.info("result = {}", result);


      return result;
   }
}
