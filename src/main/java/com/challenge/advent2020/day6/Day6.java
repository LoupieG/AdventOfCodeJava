package com.challenge.advent2020.day6;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.utilities.Strings.stringContains;

public class Day6 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day6.class);
   private              List<String> inputData;
   private              List<Pool>   answerPool;
   private              String       inputPath;

   private class Pool {
      int people;
      int questions;
      int answered;

      Pool() {
         people = 0;
         questions = 0;
         answered = 0;
      }
   }

   private static class Pool2 {
      List<String> questions;
      int          people;

      Pool2() {
         questions = new ArrayList<>();
         people = 0;
      }
   }

   public Day6(String filePath) {
      inputPath = filePath;
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
      try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
         String line;
         answerPool = new ArrayList<>();
         Pool          pool         = new Pool();
         StringBuilder questionPool = new StringBuilder();

         while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
               answerPool.add(pool);
               pool = new Pool();
               questionPool = new StringBuilder();
            }
            else {
               ++pool.people;
               for (int index = 0; index < line.length(); ++index) {
                  if (!stringContains(questionPool, line.charAt(index))) {
                     ++pool.questions;
                     questionPool.append(line.charAt(index));
                  }
               }
            }
         }
         answerPool.add(pool);
      }
      catch (IOException ex) {
         logger.error("Could not access the file {}", inputPath);
         ex.printStackTrace();
      }

      int answered = 0;
      for (Pool record : answerPool) {
         answered += record.questions;
      }

      return result.append(answered).toString();
   }

   @Override
   public String partB() {
      StringBuilder result = new StringBuilder("Part B answer: ");

      List<Pool2> poolList = new ArrayList<>();

      loadPool(poolList);

      int answer = countAnswers(poolList);
      return result.append(answer).toString();
   }

   private <T> Set<T> intersection(Set<String> list1, List<String> list2) {
      Set<T> list = new HashSet<>();

      for (String t: list1) {
         if (list2.contains(t)) {
            list.add((T) t);
         }
      }
      return list;
   }

   private int countAnswers(List<Pool2> poolList) {
      int result = 0;


      for (Pool2 record : poolList) {
         Set<String> set = new HashSet<>();
         int counter = 0;

         for (String question : record.questions) {
            List<String> str = new ArrayList<>();

            for (int index = 0; index < question.length(); ++index) {
               str.add(String.valueOf(question.charAt(index)));
            }

            if (record.people == 1) {
               result += str.size();
            }
            else {
               if (counter == 0) {
                  set.addAll(str);
                  ++counter;
               } else {
                  Set<String> set2 = new HashSet<>();
                  set2.addAll(str);
                  set = intersection(set, str);
               }
            }
         }
         result += set.size();
         counter = 0;
      }

      return result;
   }
   private void loadPool(List<Pool2> poolList) {
      try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
         String line;
         answerPool = new ArrayList<>();
         Pool2 pool = new Pool2();

         while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
               poolList.add(pool);
               pool = new Pool2();
            }
            else {
               ++pool.people;
               pool.questions.add(line);
            }
         }
         poolList.add(pool);
      }
      catch (IOException ex) {
         logger.error("Could not access the file {}", inputPath);
         ex.printStackTrace();
      }
   }
}
