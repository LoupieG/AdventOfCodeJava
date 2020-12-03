package com.challenge.advent2020;

import com.challenge.advent2020.day1.Day1;
import com.challenge.advent2020.day2.Day2;
import com.challenge.advent2020.day3.Day3;
import com.challenge.advent2020.day4.Day4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class Advent2020ApplicationController {
   Logger logger = LoggerFactory.getLogger(Advent2020ApplicationController.class);

   @GetMapping(value="/day1")
   public ResponseEntity<List<String>> day1() {
      Day1 problem = new Day1("target/classes/inputs/day1input.txt");
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value="/day2")
   public ResponseEntity<List<String>> day2() {
      Day2 problem = new Day2("target/classes/inputs/day2input.txt");
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value="/day3")
   public ResponseEntity<List<String>> day3() {
      Day3 problem = new Day3("target/classes/inputs/day3input.txt");
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }

   @GetMapping(value="/day4")
   public ResponseEntity<List<String>> day4() {
      Day4 problem = new Day4("target/classes/inputs/day4input.txt");
      //Day4 problem = new Day4("target/classes/inputs/testData.txt");
      return new ResponseEntity<>(problem.solve(), HttpStatus.OK);
   }
}
