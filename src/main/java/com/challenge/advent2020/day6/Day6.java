package com.challenge.advent2020.day6;

import com.challenge.advent2020.Day;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.utilities.Strings.getStringArray;
import static com.utilities.Strings.stringContains;
import static com.utilities.Strings.intersection;

public class Day6 implements Day {
   private static final Logger       logger = LoggerFactory.getLogger(Day6.class);
   private              List<Pool>   answerPool;

   private static class Pool {
      int          people;
      List<String> questions;

      Pool() {
         people = 0;
         questions = new ArrayList<>();
      }
   }

   public Day6(String filePath) {

      try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         String line;
         answerPool = new ArrayList<>();
         Pool pool = new Pool();

         while ((line = reader.readLine()) != null) {
            if (line.equals("")) {
               answerPool.add(pool);
               pool = new Pool();
            }
            else {
               ++pool.people;
               pool.questions.add(line);
            }
         }
         answerPool.add(pool);
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
      StringBuilder result   = new StringBuilder("Part A answer: ");
      int           answered = 0;

      for (Pool record : answerPool) {
         answered += questionCount(record);
      }

      return result.append(answered).toString();
   }

   private int questionCount(Pool record) {
      StringBuilder pool = new StringBuilder();

      for (String question : record.questions) {
         for (int index = 0; index < question.length(); ++index) {
            if (!stringContains(pool, question.charAt(index))) {
               pool.append(question.charAt(index));
            }
         }
      }

      return pool.length();
   }

   @Override
   public String partB() {
      return "Part B answer: " + countAnswers();
   }

   private int countAnswers() {
      int result = 0;

      for (Pool record : answerPool) {
         result += getQuestionIntersectCount(record);
      }

      return result;
   }

   private int getQuestionIntersectCount(Pool record) {
      Set<String> set    = new HashSet<>();
      boolean     first  = true;

      for (String question : record.questions) {
         List<String> str = getStringArray(question);

         if (record.people == 1) {
            set.addAll(str);
         }
         else {
            if (first) {
               set.addAll(str);
               first = false;
            }
            else {

               Set<String> set2 = new HashSet<>(str);
               set = intersection(set, set2);
            }
         }
      }

      return set.size();
   }
}