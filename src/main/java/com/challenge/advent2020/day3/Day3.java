package com.challenge.advent2020.day3;

import com.challenge.advent2020.Advent2020ApplicationController;
import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day3 implements Day {
   private static final Logger                       logger    = LoggerFactory.getLogger(Advent2020ApplicationController.class);
   private static final Dictionary<Point, Character> inputData = new Hashtable<>();

   private static int maxX = 0;
   private static int maxY = 0;

   public Day3(String filePath) {
      try {
         BufferedReader reader = new BufferedReader(new FileReader(filePath));
         String line;
         int xCoord = 0;
         int yCoord = 0;
         while ((line = reader.readLine()) != null) {
            for (yCoord = 0; yCoord < line.length(); ++yCoord) {
               Point point = new Point(xCoord, yCoord);
               inputData.put(point, line.charAt(yCoord));
            }
            ++xCoord;
         }
         maxX = xCoord;
         maxY = yCoord;

         logger.info("Max x and y = {} {}", maxX, maxY);
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
      return "Part A answer: " + countTrees(1, 3);
   }

   @Override
   public String partB() {

      int slope1 = countTrees(1, 1);
      int slope2 = countTrees(1, 3);
      int slope3 = countTrees(1, 5);
      int slope4 = countTrees(1, 7);
      int slope5 = countTrees(2, 1);

      return "Part B answer: " + (slope1 * slope2 * slope3 * slope4 * slope5);
   }

   private static int countTrees(int xMove, int yMove) {
      int result = 0;

      int xCoord = 0;
      int yCoord = 0;

      while ((xCoord + 1) < maxX) {
         xCoord += xMove;
         yCoord = ((yCoord + yMove) >= maxY) ? ( (yCoord + yMove) - (maxY - 1) - 1) : yCoord + yMove;
         if (inputData.get(new Point(xCoord, yCoord)) == '#') {
            ++result;
         }
      }
      return result;
   }
}
