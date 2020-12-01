package com.challenge.advent2020;

import com.challenge.advent2020.day1.Day1;
import com.challenge.advent2020.day2.Day2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class Advent2020ApplicationController {
   Logger logger = LoggerFactory.getLogger(Advent2020ApplicationController.class);

   @RequestMapping(value="/day1", method = RequestMethod.GET)
   public ResponseEntity<List<String>> day1() {
      return new ResponseEntity<>(Day1.getDay1(), HttpStatus.OK);
   }

   @RequestMapping(value="/day2", method = RequestMethod.GET)
   public ResponseEntity<List<String>> day2() {
      return new ResponseEntity<>(Day2.getDay2(), HttpStatus.OK);
   }
}
