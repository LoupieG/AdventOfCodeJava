package com.challenge.advent2020.day11;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day11 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day11.class);
   private              List<String> inputData;

   private static final char TAKEN = '#';
   private static final char EMPTY = 'L';
   private static final char AISLE = '.';

   public Day11(String filePath) {
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

      List<String> test = playLife();

      int count = 0;
      for (String line : test) {
         for (int index = 0; index < line.length(); ++index) {
            if (line.charAt(index) == TAKEN) {
               ++count;
            }
         }
      }
      return "Part A answer: " + count;
   }

   private List<String> playLife() {
      List<String> tempData = new ArrayList<>(inputData);

      List<String> iterationData = new ArrayList<>();

      while (true) {
         for (int arrayIndex = 0; arrayIndex < tempData.size(); ++arrayIndex) {
            String        line    = tempData.get(arrayIndex);
            StringBuilder newLine = new StringBuilder();

            for (int index = 0; index < line.length(); ++index) {
               char value = tempData.get(arrayIndex).charAt(index);
               if (tempData.get(arrayIndex).charAt(index) != AISLE && isAdjacentEmpty(tempData, arrayIndex, index)) {
                  value = TAKEN;
               }
               else if (tempData.get(arrayIndex).charAt(index) == TAKEN && countOccupied(tempData, arrayIndex, index) >= 4) {
                  value = EMPTY;
               }

               newLine.append(value);
            }
            iterationData.add(newLine.toString());
         }

         if (tempData.equals(iterationData)) {
            break;
         }
         tempData = iterationData;
         iterationData = new ArrayList<>();

      }

      return iterationData;
   }

   private boolean isOccpied(char value) {
      return value == TAKEN;
   }

   private int countOccupied(List<String> tempData, int row, int col) {
      int result = 0;

      if (row == 0) {
         if (col > 0 && col < tempData.get(row).length() - 1) {
            if (isOccpied(tempData.get(row).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col))) {
               ++result;
            }
         }
         else if (col == 0) {
            if (isOccpied(tempData.get(row).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col))) {
               ++result;
            }
         }
         else if (col == tempData.get(row).length() - 1) {
            if (isOccpied(tempData.get(row).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col))) {
               ++result;
            }
         }
      }
      else if (row > 0 && row < tempData.size() - 1) {
         if (col > 0 && col < tempData.get(row).length() - 1) {
            if (isOccpied(tempData.get(row).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col))) {
               ++result;
            }
         }
         else if (col == 0) {
            if (isOccpied(tempData.get(row).charAt(col + 1))) {
               ++result;
            }

            if (isOccpied(tempData.get(row + 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col))) {
               ++result;
            }

         }
         else if (col == tempData.get(row).length() - 1) {
            if (isOccpied(tempData.get(row).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col))) {
               ++result;
            }
            if (isOccpied(tempData.get(row + 1).charAt(col))) {
               ++result;
            }
         }
      }
      else if (row == tempData.size() - 1) {
         if (col > 0 && col < tempData.get(row).length() - 1) {
            if (isOccpied(tempData.get(row).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col))) {
               ++result;
            }
         }
         else if (col == 0) {
            if (isOccpied(tempData.get(row).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col + 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col))) {
               ++result;
            }

         }
         else if (col == tempData.get(row).length() - 1) {
            if (isOccpied(tempData.get(row).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col - 1))) {
               ++result;
            }
            if (isOccpied(tempData.get(row - 1).charAt(col))) {
               ++result;
            }
         }
      }

      return result;
   }

   private boolean isAdjacentEmpty(List<String> tempData, int row, int col) {
      boolean result = false;

      if (row == 0) {
         if (col > 0 && col < tempData.get(row).length() - 1) {
            if (!isOccpied(tempData.get(row).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col))) {
               result = true;
            }
         }
         else if (col == 0) {
            if (!isOccpied(tempData.get(row).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col))) {
               result = true;
            }

         }
         else if (col == tempData.get(row).length() - 1 &&
                 (!isOccpied(tempData.get(row).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row + 1).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row + 1).charAt(col)))) {
            result = true;
         }
      }
      else if (row > 0 && row < tempData.size() - 1) {
         if (col > 0 && col < tempData.get(row).length() - 1) {
            if (!isOccpied(tempData.get(row).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col))) {
               result = true;
            }
         }
         else if (col == 0) {
            if (!isOccpied(tempData.get(row).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col)) &&
                    !isOccpied(tempData.get(row + 1).charAt(col))) {
               result = true;
            }

         }
         else if (col == tempData.get(row).length() - 1 &&
                 (!isOccpied(tempData.get(row).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row + 1).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row - 1).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row - 1).charAt(col)) &&
                         !isOccpied(tempData.get(row + 1).charAt(col)))) {
            result = true;
         }
      }

      else if (row == tempData.size() - 1) {
         if (col > 0 && col < tempData.get(row).length() - 1) {
            if (!isOccpied(tempData.get(row).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col - 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col))) {
               result = true;
            }
         }
         else if (col == 0) {
            if (!isOccpied(tempData.get(row).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col + 1)) &&
                    !isOccpied(tempData.get(row - 1).charAt(col))) {
               result = true;
            }

         }
         else if (col == tempData.get(row).length() - 1 &&
                 (!isOccpied(tempData.get(row).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row - 1).charAt(col - 1)) &&
                         !isOccpied(tempData.get(row - 1).charAt(col)))) {
            result = true;
         }
      }

      return result;
   }
}
