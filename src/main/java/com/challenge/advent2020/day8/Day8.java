package com.challenge.advent2020.day8;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day8 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day8.class);
   private              List<String> inputData;
   private List<Code> inputCode;

   public Day8(String filePath) {
      try {
         inputData = Files.readAllLines(Paths.get(filePath));
      }
      catch (IOException ex) {
         logger.error("Could not access the file {}", filePath);
         ex.printStackTrace();
      }

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         inputCode = new ArrayList<>();
         Code code = new Code();

         while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");

            code.instruction = split[0];
            code.value = Integer.parseInt(split[1]);
            inputCode.add(code);
            code = new Code();
         }
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static class Code {
      String instruction;
      int value;
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

      int accumulator = 0;
      List<Integer> completed = new ArrayList<>();

      int index = 0;

      do {

         completed.add(index);
         String[] instruction = inputData.get(index).split(" ");

         switch (instruction[0]) {
            case "nop":
               ++index;
               break;
            case "acc":
               accumulator += Integer.parseInt(instruction[1]);
               ++index;
               break;
            case "jmp":
               index += Integer.parseInt(instruction[1]);
               break;
         }

      } while (index <= inputData.size() && !completed.contains(index));


      return result.append(accumulator).toString();
   }

   @Override
   public String partB() {
      StringBuilder result = new StringBuilder("Part B answer: ");

      int accumulator = -1;

      for (int index = 0; index < inputCode.size() && accumulator == -1 ; ++index) {
         String instruction = inputCode.get(index).instruction;
         if (instruction.equals("jmp")) {
            inputCode.get(index).instruction = "nop";
         } else if (instruction.equals("nop")) {
            inputCode.get(index).instruction = "jmp";
         }

         accumulator = instructionSolve();

         if (instruction.equals("jmp")) {
            inputCode.get(index).instruction = "jmp";
         } else if (instruction.equals("nop")) {
            inputCode.get(index).instruction = "nop";
         }
      }
      return result.append(accumulator).toString();
   }

   private int instructionSolve() {
      int           accumulator = 0;
      List<Integer> completed   = new ArrayList<>();

      int     index    = 0;

      while (true) {

         completed.add(index);

         switch (inputCode.get(index).instruction) {
            case "nop":
               ++index;
               break;
            case "acc":
               accumulator += inputCode.get(index).value;
               ++index;
               break;
            case "jmp":
               index += inputCode.get(index).value;
               break;
         }

         if (completed.contains(index) || index > inputCode.size()) {
            accumulator = -1;
            break;
         }
         if (index == inputCode.size()) {
            break;
         }
      }

      return accumulator;
   }
}
