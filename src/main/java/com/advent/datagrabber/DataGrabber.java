package com.advent.datagrabber;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataGrabber {
   private static List<String>       cookies = new ArrayList<>();
   private        HttpsURLConnection conn;
   private static String             cookieLocation;
   private        String             outputFile;

   public DataGrabber(String session) {
      cookieLocation = session;
   }

   public DataGrabber(String cookieLocation, String outputFile) {
      this.outputFile = outputFile;
      this.cookieLocation = cookieLocation;
   }

   public void getPuzzleInput(String outputFile, int year, int day) {
      this.outputFile = outputFile;
      getPuzzleInput(year, day);
   }

   public void getPuzzleInput(int year, int day) {
      try {
         cookies.add(Files.readAllLines(Paths.get(cookieLocation)).get(0));
      }
      catch (IOException e) {
         e.printStackTrace();
      }
      makeConnection(year, day);

      createInputFile(getPuzzleData());
      cookies.remove(0);
   }

   private void createInputFile(List<String> data) {
      try {
         File resultFile = new File(outputFile);

         //We don't want to overwrite the data if it already exists
         if (resultFile.createNewFile()) {
            FileWriter fileWriter = new FileWriter(outputFile);
            for (String line : data) {
               fileWriter.write(line + "\n");
            }
            fileWriter.close();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   private List<String> getPuzzleData() {
      List<String> response = new ArrayList<>();

      try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
         String inputLine;

         while ((inputLine = in.readLine()) != null) {
            response.add(inputLine);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

      return response;
   }

   private void makeConnection(int year, int day) {
      String url = String.format("https://adventofcode.com/%d/day/%d/input", year, day);

      try {
         URL urlObj = new URL(url);
         conn = (HttpsURLConnection) urlObj.openConnection();
         conn.setRequestMethod("GET");

         for (String cookie : cookies) {
            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
         }
      }

      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
