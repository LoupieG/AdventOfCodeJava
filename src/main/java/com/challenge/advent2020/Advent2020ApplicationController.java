package com.challenge.advent2020;

import com.advent.datagrabber.DataGrabber;
import com.challenge.advent2020.day1.Day1;
import com.challenge.advent2020.day2.Day2;
import com.challenge.advent2020.day3.Day3;
import com.challenge.advent2020.day4.Day4;
import com.challenge.advent2020.day5.Day5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class Advent2020ApplicationController {
   Logger    logger      = LoggerFactory.getLogger(Advent2020ApplicationController.class);
   LocalDate currentDate = LocalDate.now();
   private static final String USER_DIR  = System.getProperty("user.dir") + "/";
   private static final String AOC_TOKEN = "/Users/kmurray/.aoc/token";

   DataGrabber dataGrabber = new DataGrabber(AOC_TOKEN);

   @GetMapping(value = "/day1")
   public ResponseEntity<List<String>> day1() {
      String inputFile = "target/classes/inputs/day1input.txt";
      String filename = USER_DIR + inputFile;
      File   file     = new File(filename);
      if (!file.exists()) {
         dataGrabber.getPuzzleInput(filename, 2020, 1);
      }
      var problem = new Day1(inputFile);
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value = "/day2")
   public ResponseEntity<List<String>> day2() {
      String inputFile = "target/classes/inputs/day2input.txt";
      String filename = USER_DIR + inputFile;
      File   file     = new File(filename);
      if (!file.exists()) {
         dataGrabber.getPuzzleInput(filename, 2020, 2);
      }
      var problem = new Day2(inputFile);
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value = "/day3")
   public ResponseEntity<List<String>> day3() {
      String inputFile = "target/classes/inputs/day3input.txt";
      String filename = USER_DIR + inputFile;
      File   file     = new File(filename);
      if (!file.exists()) {
         dataGrabber.getPuzzleInput(filename, 2020, 3);
      }

      var problem = new Day3(inputFile);
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value = "/day4")
   public ResponseEntity<List<String>> day4() {
      String inputFile = "target/classes/inputs/day4input.txt";
      String filename = USER_DIR + inputFile;
      File   file     = new File(filename);
      if (!file.exists()) {
         dataGrabber.getPuzzleInput(filename, 2020, 4);
      }
      var problem = new Day4(inputFile);
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value = "/day5")
   public ResponseEntity<List<String>> day5() {
      String inputFile = "target/classes/inputs/day5input.txt";
      //String inputFile = "target/classes/inputs/testData.txt";
      String filename = USER_DIR + inputFile;
      File   file     = new File(filename);
      if (!file.exists()) {
         dataGrabber.getPuzzleInput(filename, 2020, 5);
      }
      var problem = new Day5(inputFile);
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

}
