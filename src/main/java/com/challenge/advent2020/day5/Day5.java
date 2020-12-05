package com.challenge.advent2020.day5;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day5 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day5.class);
   private              List<String> inputData;

   private static final int ROW_COUNT = 128;
   private static final int SEAT_COUNT = 8;

   public Day5(String filePath) {
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
   public String partA() {
      StringBuilder result = new StringBuilder("Part A answer: ");
      int maxId = 0;

      for (String boardingPass : inputData) {
         int seatId = (getRowId(boardingPass) * 8) + getColId(boardingPass);

         maxId = (seatId > maxId) ? seatId : maxId;
      }

      return result.append(maxId).toString();
   }

   @Override
   public String partB() {
      String[][] seatingChart = new String[ROW_COUNT][SEAT_COUNT];

      for (int rowid = 0; rowid < ROW_COUNT; ++rowid) {
         for (int colid = 0; colid < SEAT_COUNT; ++colid) {
            seatingChart[rowid][colid] = "x";
         }
      }

      for (String boardingPass : inputData) {
         int row = getRowId(boardingPass);
         int col = getColId(boardingPass);

         seatingChart[row][col] = "O";
      }

      for (int rowid = 0; rowid < ROW_COUNT; ++rowid) {
         for (int colid = 0; colid < SEAT_COUNT; ++colid) {
            if (seatingChart[rowid][colid].equals("x")) {
               logger.info("Seat available at {} {}", rowid, colid);
            }
         }
      }

      return "NOT COMPLETED";
   }

   private int getRowId(String boardingPass) {
      int result = 0;
      int rowCount = ROW_COUNT;
      int upperRow = ROW_COUNT - 1;
      int lowerRow = 0;
      int midPoint = 1;

      for (int index = 0; index < 7; ++index) {
         if (boardingPass.charAt(index) == 'F') {
            midPoint = rowCount / 2;
            rowCount -= midPoint;
            upperRow -= midPoint;
            //logger.info("char {} mid {} row {} upper {} lower {}", boardingPass.charAt(index), midPoint, rowCount, upperRow, lowerRow);
         } else if (boardingPass.charAt(index) == 'B') {
            midPoint = rowCount / 2;
            rowCount -= midPoint;
            lowerRow += midPoint;
            //logger.info("char {} mid {} row {} upper {} lower {}", boardingPass.charAt(index), midPoint, rowCount, upperRow, lowerRow);
         }
      }
      return lowerRow;
   }

   private int getColId(String boardingPass) {
      int result = 0;
      int rowCount = SEAT_COUNT;
      int upperRow = SEAT_COUNT - 1;
      int lowerRow = 0;
      int midPoint = 1;

      for (int index = 7; index < boardingPass.length(); ++index) {
         if (boardingPass.charAt(index) == 'L') {
            midPoint = rowCount / 2;
            rowCount -= midPoint;
            upperRow -= midPoint;
            //logger.info("SEAT char {} mid {} row {} upper {} lower {}", boardingPass.charAt(index), midPoint, rowCount, upperRow, lowerRow);
         } else if (boardingPass.charAt(index) == 'R') {
            midPoint = rowCount / 2;
            rowCount -= midPoint;
            lowerRow += midPoint;
            //logger.info("SEAT char {} mid {} row {} upper {} lower {}", boardingPass.charAt(index), midPoint, rowCount, upperRow, lowerRow);
         }
      }
      return lowerRow;
   }
}
