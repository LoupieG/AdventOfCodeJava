package com.challenge.advent2020.day1;

import com.challenge.advent2020.Advent2020ApplicationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
   static Logger logger = LoggerFactory.getLogger(Advent2020ApplicationController.class);

   public static List<String> getDay1() {
      List<String> result = new ArrayList<>();

      try {
         List<String> inputList = Files.readAllLines(Paths.get("target/classes/inputs/day1input.txt"));
         result.add(findSum(inputList));
         result.add(findTripleSum(inputList));
      }
      catch (IOException e) {
         e.printStackTrace();
         result.add("Exception occurred");
      }
      return result;
   }

   private static String findTripleSum(List<String> inputList) {
      StringBuilder result = new StringBuilder("Part B answer: ");
      for (int index = 0; index < inputList.size() / 2; ++index) {
         int left = Integer.parseInt(inputList.get(index));
         for (int m = index + 1; m < inputList.size() - 2; ++m) {
            int middle = Integer.parseInt(inputList.get(m));

            for (int r = m + 1; r < inputList.size(); ++r) {
               int right = Integer.parseInt(inputList.get(r));
               if (left + middle + right == 2020) {
                  result.append(left * middle * right);
                  index = inputList.size();
                  break;
               }
            }
         }
      }
      return result.toString();
   }

   private static String findSum(List<String> inputList) {
      StringBuilder result = new StringBuilder("Part A answer: ");
      for (int index = 0; index < inputList.size() / 2; ++index) {
         int left = Integer.parseInt(inputList.get(index));
         for (int test = index + 1; test < inputList.size(); ++ test) {
            int right = Integer.parseInt(inputList.get(test));
            if (left + right == 2020) {
               result.append(left * right);
               index = inputList.size();
               break;
            }
         }
      }
      return result.toString();
   }
}
