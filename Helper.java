/**
 * The Helper class contains methods to help the ContactTracing class. Such as validation, searching and sorting methods
 * and read data from text files.
 * When reading the values from a file, the method checks if the file exists and has valid data
 * 
 * Author: Michael Makhoul
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Helper {
  static Scanner kb = new Scanner(System.in);  // Keyboard input scanner
  
  // Creating new arraylists to save the read data from files
  static ArrayList <Venue> venue = new ArrayList<>();
  static ArrayList <Individual> individual = new ArrayList<>();
  static ArrayList <Visit> visit = new ArrayList<>();
  
  /**
   * Reads data from a text file to add them to the venue object.
   * The method checks if the file exists and has valid data. If not it will ask the user to provide an alternative text file.
   * @param fileName - The name of the file to read the data from. The file must be exists.
   * @return - The venue object
   */
  public static ArrayList<Venue> readVenue(String fileName) {
    File myFile =  new File(fileName + ".txt");
    try {
      Scanner inputFile = new Scanner(myFile);
      while(inputFile.hasNext()) {
        String[] line = inputFile.nextLine().split(",");
        if(line.length != 3) {    // Validates the file's data
          System.out.println("Bad data in file " + fileName + ".txt! Please provide an alternative file with no '.txt' extention: ");
          fileName = kb.nextLine();
          readVenue(fileName);
        } else {
        venue.add(new Venue(line[0], line[1], line[2]));
        } 
      }
      inputFile.close();
      return venue;
    } catch(FileNotFoundException e) {    // Checks if the file exists
      System.out.println("File \"" + fileName + "\" was not found. Please provide an alternative file with no '.txt' extention to get data from: ");
      fileName = kb.nextLine();
      readVenue(fileName);
    }
    return venue;
  }
  
  /**
   * Reads data from a text file to add them to the individual object.
   * The method checks if the file exists and has valid data. If not it will ask the user to provide an alternative text file.
   * @param fileName - The name of the file to read the data from. The file must be exists.
   * @return The individual object
   */
  public static ArrayList<Individual> readIndividual(String fileName) {
    File myFile =  new File(fileName + ".txt");
    try {
      Scanner inputFile = new Scanner(myFile);
      while(inputFile.hasNext()) {
        String[] line = inputFile.nextLine().split(",");
        if(line.length != 5) {    // Validates the file's data
          System.out.println("Bad data in file " + fileName + ".txt! Please provide an alternative file with no '.txt' extention: ");
          fileName = kb.nextLine();
          readIndividual(fileName);
        } else {
         individual.add(new Individual(line[0], line[1], line[2], line[3], line[4]));
        }
      }
      inputFile.close();
      return individual;
    } catch(FileNotFoundException e) {    // Checks if the file exists
      System.out.println("File \"" + fileName + "\" was not found. Please provide an alternative file with no '.txt' extention to get data from: ");
      fileName = kb.nextLine();
      readIndividual(fileName);
    }
    return individual;
  }
  
  /**
   * Reads data from a text file to add them to the visit object.
   * The method checks if the file exists and has valid data. If not it will ask the user to provide an alternative text file.
   * @param fileName - The name of the file to read the data from. The file must be exists.
   * @return The individual object
   */
  public static ArrayList<Visit> readVisits(String fileName) {
    File myFile =  new File(fileName + ".txt");
    try {
      Scanner inputFile = new Scanner(myFile);
      while(inputFile.hasNext()) {
        String[] line = inputFile.nextLine().split(",");
        if(line.length != 3) {    // Validates the file's data
          System.out.println("Bad data in file " + fileName + ".txt! Please provide an alternative file with no '.txt' extention: ");
          fileName = kb.nextLine();
          readVisits(fileName);
        } else {
         visit.add(new Visit(line[0], line[1], line[2]));
        }
      }
      inputFile.close();
      return visit;
    } catch(FileNotFoundException e) {    // Checks if the file exists
      System.out.println("File \"" + fileName + "\" was not found. Please provide an alternative file with no '.txt' extention to get data from: ");
      fileName = kb.nextLine();
      readVisits(fileName);
    }
    return visit;
  }
  
  /**
   * This method validates if the input numeric or includes any white space. It also validates the input length.
   * It is used to validate the IDs and menu option input 
   * @param input - The input number to be validated. Cannot be null.
   * @param length - The required length of the input
   * @return - A boolean value indicates if the was input accepted or rejected 
   */
  public static boolean filterNumbers(String input, int length) { 
    boolean rejected = false;
    char theCharacter;
    if(input.length() != length) {
        System.out.println("Must be " + length +  " characters. Enter valid ID: ");
      return rejected = true;
    } else {
      for (int i = 0; i < input.length(); i++) {
        theCharacter = input.charAt(i);
        if (Character.isWhitespace(theCharacter)) {
          System.out.println("Do not use spaces. Enter valid ID: ");
          return rejected = true;
        } else if (!Character.isDigit(theCharacter)) {
          System.out.println("Use numbers only. Enter valid ID: ");
          return rejected = true;
        } 
      }
    }
    return rejected;
  }
  
  /**
   * Filter the input to check if it contains any comma. 
   * @param input - The input value to be validated. Must have a value
   * @return - A boolean value indicates if the input was accepted or rejected 
   */
  public static boolean filterComma(String input) { 
    boolean rejected = false;
    if (input.contains(",")) {
      System.out.println("Do not use commas \",\" please: ");
      return rejected = true;
    }
    return rejected;
  }
  
  /**
   * Validates the individual's phone number.
   * The number cannot have any letter or comma.
   * @param number - The phone number to be validated. Cannot be null
   * @return - A boolean value indicates if the input was accepted or rejected
   */
  public static boolean validatePhoneNumber(String number) {
   boolean rejected = false;
   char theCharacter;
   if(number.contains(",")) {
     System.out.println("Do not use commas \",\" please:");
     return rejected = true;
   }else {
     for (int i = 0; i < number.length(); i++) {
       theCharacter = number.charAt(i);
       if (Character.isLetter(theCharacter)) {
         System.out.println("Enter a valid phone number: ");
         return rejected = true;
       } 
     }
   }
   return rejected;
  }
   
  /**
   * Search the visits object to check if an individual's id exists. 
   * @param visits - The visits object to be searched 
   * @param individualID - The individual's id value to be validated
   * @return - A boolean value indicates if the id was found or not 
   */
  public static boolean searchVisitsIndividualArray(ArrayList<Visit> visits, String individualID) {

    boolean found = false;
    for(Visit visit : visits){
      if(visit.getIndividualId().contains(individualID)) {
         found = true;
      }
    }
    return found;
  }
  
  /**
   * Searches the visits object to check if a venue's id exists. 
   * @param visits - The visits object to be searched 
   * @param visitID - The venue's id to be validated
   * @return - A boolean value indicates if the id was found or not
   */
  public static boolean searchVisitsVenueArray(ArrayList<Visit> visits, String visitID) {
    boolean found = false;
    for(Visit visit : visits){
      if(visit.getVenueId().contains(visitID)) {
         found = true;
      }
    }
    return found;
  }
  
  /**
   * Searches the individual object to check if a individual's id exists. 
   * @param individuals - The individuals object to be searched
   * @param individualID - The individual's id value to be validated. Cannot be null
   * @return - A boolean value indicates if the id was found or not
   */
  public static boolean searchIndividualsArray(ArrayList<Individual> individuals, String individualID) {
    boolean found = false;
      for(Individual individual : individuals){
        if(individual.getIndividualID().contains(individualID)) {
           found = true;
        }
      }
      return found;
    }

  /**
   * Searches the venues object to check if a individual's id exists. 
   * @param venues - The venues object to be searched
   * @param venueID - The venue's id value to be validated. Cannot be null
   * @return - A boolean value indicates if the id was found or not
   */
  public static boolean searchVenuesArray(ArrayList<Venue> venues, String venueID) {
    boolean found = false;
    for(Venue venue : venues){
      if(venue.getVenueID().contains(venueID)) {
        found = true;
      }
    }
    return found;
  }
  
  /**
   * Validates the venue's id and checks if it is exists or not
   * The method uses the filter method to validate the id length and the search method to check if it is exists
   * @param ID - The id to be validates. Cannot be null
   * @param venue - The venue object.
   * @param length - The required length of the input
   * @return - The validated ID
   */
  public static String validateVenueID(String ID, ArrayList<Venue> venue, int length) {
    
    while(filterNumbers(ID, length)) {
      ID = kb.nextLine();
    }
      
    while(searchVenuesArray(venue, ID)) {
       System.out.println("Venue's ID is already exists. Enter a new ID: ");
       ID = kb.nextLine();
       while(filterNumbers(ID, length)) {
         ID = kb.nextLine();
       }
    }      
    return ID;
  }
  
  /**
   * Validates the individual's id and checks if it is exists or not
   * The method uses the filter method to validate the id length and the search method to check if it is exists 
   * @param ID - The id value to be validates. Cannot be null
   * @param individual - Individual object
   * @param length - The required length
   * @return - The validated ID value
   */
  public static String validateIndividualID(String ID, ArrayList<Individual> individual, int length) {

    while(filterNumbers(ID, length)) {
      ID = kb.nextLine();
    }
    
    while(searchIndividualsArray(individual, ID)) {
       System.out.println("Individual's ID is already exists. Enter a new ID: ");
       ID = kb.nextLine();
       while(filterNumbers(ID, length)) {
         ID = kb.nextLine();
       }    
    }
    return ID;
  }
  
  /**
   * Validates the individual's email address.
   * It checks if the email has the '@' sign and if does not contains any comma.
   * @param email - The email to be validated.
   * @return - The validated email address.
   */
  public static String validateEmailAddress(String email) {
    while(!email.contains("@") || email.contains(",")) {
      if(!email.contains("@")) {
        System.out.println("Invalid email address. Please enter a valid email address: ");
        email = kb.nextLine();
      } else if(email.contains(",")) {
        System.out.println("Do not use commas \",\" please: ");
        email = kb.nextLine();
      }
    }
    return email;
  }

  /**
   * This method sort the visit object in an ascending order by date and time to use it to display the venue and individual reports
   * The method makes a copy of the visit object to avoid sorting the data in the actual "visits.txt" file when we 
   * use the visit object to save the data back into the text file.  
   * @param visit - The visit object to be sorted.
   * @return - A copy of the visit object, sorted by date and time 
   */
  public static ArrayList<Visit> sortDate(ArrayList<Visit> visit) {
    ArrayList <Visit> visits = new ArrayList<>();
    
    for(int j = 0; j < visit.size(); j++) {   // Copy the visit object into visits
      visits.add(new Visit(visit.get(j).getVenueId(), visit.get(j).getIndividualId(), visit.get(j).getDateTime()));
    }
    
    for(int i = 0; i < visits.size(); i++) {  // Sorting the visit object
      for(int j = i + 1; j < visits.size(); j++) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   // Converting from a string to datetime format
        LocalDateTime date = LocalDateTime.parse(visits.get(i).getDateTime(), formatter);
        LocalDateTime date1 = LocalDateTime.parse(visits.get(j).getDateTime(), formatter);
        if(date.compareTo(date1) > 0) {  
          Collections.swap(visits, i, j);
        }
      }
    }
    return visits;
  }
}