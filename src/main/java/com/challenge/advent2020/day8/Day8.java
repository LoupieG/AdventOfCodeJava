package com.challenge.advent2020.day8;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day8.class);
   private List<Code> inputCode;

   public Day8(String filePath) {

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
      return "Part A answer: " + executeCode(1);
   }

   @Override
   public String partB() {
      StringBuilder result = new StringBuilder("Part B answer: ");

      int accumulator = -1;

      for (int index = 0; index < inputCode.size() && accumulator == -1 ; ++index) {

         inputCode.get(index).instruction = switchInstruction(inputCode.get(index).instruction);
         accumulator = executeCode(2);

         inputCode.get(index).instruction = switchInstruction(inputCode.get(index).instruction);
      }

      return result.append(accumulator).toString();
   }

   private String switchInstruction(String instruction) {
      String result = instruction;

      if (instruction.equals("jmp")) {
         result = "nop";
      }
      else if (instruction.equals("nop")) {
         result = "jmp";
      }

      return result;
   }

   private int executeCode(int part) {
      int           accumulator = 0;
      List<Integer> completed   = new ArrayList<>();

      int     index    = 0;
      boolean exit = false;

      while (!exit) {

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

         if (part == 1 && (index <= inputCode.size() && completed.contains(index))) {
            exit = true;
         }
         else if (part == 2 && (completed.contains(index) || index > inputCode.size())) {
            accumulator = -1;
            exit = true;
         }
         else if (part == 2 && index == inputCode.size()) {
            exit = true;
         }
      }

      return accumulator;
   }
}
