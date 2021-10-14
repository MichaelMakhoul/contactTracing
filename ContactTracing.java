/**
 * This Project was developed for uni training purpose.
 * ContactTracing class helps the public health with tracing Covid cases by tracking an infected individual if they have attend any venue,
 * such as shops, restaurants or any other venues so that, they will be able to know what venues they attended, and what day and time. 
 * As well as, listing all the attendees for a specific venue in case a Covid case was recorded in this place. 
 * The program allows the user to add new individuals and their information, add new venues, and record any visit.
 * When recording a new visit, the date and time will be automatically assigned at the current time.
 * The program asks the user to enter an indevedual's ID number to list their information and all the visited places.
 * Or they can choose to list all the visitors for a certain venue by entering the venue's ID.
 * The program reads data from saved text files that contains venues, individuals, and visits information, and write new data into the same files. 
 * The program validates the user's inputs and the data read from the files.
 * 
 * Author: Micheal Makhoul
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactTracing {
static Scanner kb = new Scanner(System.in);   // Keyboard scanner

  // Main Method
  public static void main(String[] args) {
    displayMenu();   // The menu to choose an option from
  }
  
  static ArrayList <Venue> venue = Helper.readVenue("venues");                      // Read values from "venues.txt" file and assign them to the venue object
  static ArrayList <Individual> individual = Helper.readIndividual("individuals");  // Read values from "individuals.txt" file and assign them to the individual object
  static ArrayList <Visit> visit = Helper.readVisits("visits");                     // Read values from "visits.txt" file and assign them to the visit object
  
  static boolean unsavedVenue, unsavedIndividual, unsavedVisit = false;   // To check if the changes saved before exiting the program after adding new venues, individuals or visits
  
  /**
   * Adds a new venue to the venues object.
   * The method asks for venue's ID, name and address.
   * The method contains some validation methods to validate the inputs before adding them to the venue object
   */
  public static void addVenue() {
    System.out.println("Enter the venue's ID: ");
    String ID = kb.nextLine();    // Collects venue's ID from the user
    ID = Helper.validateVenueID(ID, venue, 8);   // Validates if the ID not exists
    
    System.out.print("Enter the venue's name: \n");
    String venueName = kb.nextLine();   // Collects venue's name
    while(Helper.filterComma(venueName)) { // Checks if the input has comma
      venueName = kb.nextLine();
    }
    
    System.out.println("Enter the venue's address: ");
    String venueAddress = kb.nextLine();    // Collects venue's address
    while(Helper.filterComma(venueAddress)) {   // Checks if the input has comma
      venueAddress = kb.nextLine();
    }
    
    venue.add(new Venue(ID, venueName, venueAddress)); // Adds values to the venue object 
    unsavedVenue = true;  // Changes was made
  }
  
  /**
   * Adds a new individual to the individuals object.
   * The method asks for individual's ID, name, address, phone number and email address.
   * The method contains some validation methods to validate the inputs before adding them to the individual object
   */
  public static void addIndividual() { 
    System.out.println("Enter the individual's ID: ");
    String ID = kb.nextLine();    // Adds new individual's ID
    ID = Helper.validateIndividualID(ID, individual, 8); // Validates if the ID not exists
    
    System.out.println("Enter the individual's name: ");
    String individualName = kb.nextLine();   // Collects individual's name
    while(Helper.filterComma(individualName)) {   // Checks if the input has comma
      individualName = kb.nextLine();
    }
        
    System.out.println("Enter the individual's address: ");
    String individualAddress = kb.nextLine();   // Collects individual's residential address
    while(Helper.filterComma(individualAddress)){    // Checks if the input has comma
      individualAddress = kb.nextLine();
    }
        
    System.out.println("Enter the individual's phone number: ");
    String individualPhone = kb.nextLine();   // Collects individual's phone number
    while(Helper.validatePhoneNumber(individualPhone)) {   // Validates phone number 
      individualPhone = kb.nextLine();
    }
        
    System.out.println("Enter the individual's email address: ");
    String individualEmail = kb.nextLine();   // Collects individual's email address
    individualEmail = Helper.validateEmailAddress(individualEmail);     // Validates email address 
    
    individual.add(new Individual(ID, individualName, individualAddress, individualPhone, individualEmail)); // Adds values to the individual object 
    unsavedIndividual = true;   // Indicates that changes was made
  }
  
  /**
   * Records a new visit and adds it to the visit object.
   * The method asks for individual's ID and venue's ID.
   * individual's and venue's ID must be exists
   */
  public static void recordVisit() {
    System.out.println("Enter the individual's ID");
    String individualID = kb.nextLine();    // Collects individulal's ID
    
    while(Helper.filterNumbers(individualID, 8)) {   // Validates the if the ID is valid
      individualID = kb.nextLine();
    }
    
    while(Helper.searchIndividualsArray(individual, individualID) == false) {    // To check if the ID exists
      System.out.println("This individual is not exists! Enter an exists individual's ID: ");
      individualID = kb.nextLine();   
      while(Helper.filterNumbers(individualID, 8)) {   // Validates the if the ID is valid
        individualID = kb.nextLine();
      }
    }
    
    System.out.println("Enter the venues's ID");
    String venueID = kb.nextLine();   // To collect venue's ID
    
    while(Helper.filterNumbers(venueID, 8)) {    // Validates the if the ID is valid
      venueID = kb.nextLine();
    }
    
    while(Helper.searchVenuesArray(venue, venueID) == false) {   // To check if the ID exists
      System.out.println("This venue is not exists! Enter an exists venue's ID: ");
      venueID = kb.nextLine();
      while(Helper.filterNumbers(venueID, 8)) {    // Validates the if the ID is valid
        venueID = kb.nextLine();
      }
    }
    
    LocalDateTime now = LocalDateTime.now();    // Get the current date and time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   // Choose the datetime format
    String formatDateTime = now.format(formatter);    // Set the current datetime to the chosen format
    String dateTime = formatDateTime;
    
    visit.add(new Visit(venueID, individualID, dateTime));   // Adds values to the visit object 
  }
  
  /**
   * This method generates a report for a chosen individual.
   * It shows the individual's information and all the visited venues they attended.
   * The venues are sorted in an ascending order by the visit datetime.
   * The method asks the user to enter the required individual's ID and check if it's exists and valid.
   */
  public static void individualReport() {  
    ArrayList <Visit>  visits = Helper.sortDate(visit);  // Sort the visit ArrayList ascending by DateTime
    
    System.out.println("Enter the individual's ID: ");
    String ID = kb.nextLine();    // Collects individual's ID
    
    while(Helper.filterNumbers(ID, 8)) {   // Validates the if the ID is valid
      ID = kb.nextLine();
    }
    
    while(Helper.searchIndividualsArray(individual, ID) == false) {    // Checks if the ID exists
      System.out.println("This individual is not exists. Enter an exists individual's ID: ");
      ID = kb.nextLine();
      while(Helper.filterNumbers(ID, 8)) {    // Validates the if the ID is valid
        ID = kb.nextLine();
      }
    }
    
    //  Generates the individual's details output
    for(int i = 0; i < individual.size(); i++) {
      if(individual.get(i).getIndividualID().contains(ID)) {
        System.out.println("Individual:");
        System.out.printf("%-15s%-15s", "  Name:", individual.get(i).getIndividualName());
        System.out.printf("\n%-15s%-15s", "  Address:", individual.get(i).getIndividualAddress());
        System.out.printf("\n%-15s%-15s", "  Phone:", individual.get(i).getIndividualPhone());
        System.out.printf("\n%-15s%-15s", "  Email:", individual.get(i).getIndividualEmail());
        System.out.println();
      } 
    }
    
    // Creating new arraylists to store information about venues' IDs and datetime to be displayed in the report
    ArrayList<String> visID = new ArrayList<String>();    // IDs arraylist
    ArrayList<String> visDate = new ArrayList<String>();  // Ordered dateTime arraylist 
    
    // Adding values to the IDs arraylist
    for(int i = 0; i < visits.size(); i++) {   
      if(visits.get(i).getIndividualId().contains(ID)) {
        visID.add(visits.get(i).getVenueId()); 
      }
    }
    
    // Adding values to the DateTime arraylist
    for(int i = 0; i < visits.size(); i++) {
      if(visits.get(i).getIndividualId().contains(ID)) {
        visDate.add(visits.get(i).getDateTime()); 
      }
    }
    
    // Generating the report output
    System.out.println("\nPlaces visited:");
    
    if (Helper.searchVisitsIndividualArray(visits, ID) == false) {   // Check if this individual has not visited any venue
      System.out.println("  This individual has not visited any venue.");
      return;
    }
    
    for(int j = 0; j < visID.size(); j++) {
      for(int k = 0; k < venue.size(); k++) {
        if(venue.get(k).getVenueID().contains(visID.get(j))) {    // Searching the venue object for the attended venues
          System.out.printf("%-15s%-15s", "  Venue:", venue.get(k).getVenueName());
          System.out.printf("\n%-15s%-15s", "  Address:", venue.get(k).getVenueAddress());

          String visitDate = visDate.get(j);
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");     // The datetime format as it saved in the file
          LocalDateTime date = LocalDateTime.parse(visitDate, formatter);
          DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EE, d MMM yyyy HH:mm");  // Set the display format
          String formatDateTime = date.format(formatter1);
          
          System.out.printf("\n%-15s%-15s", "  Date & Time:", formatDateTime);
          System.out.println("\n");
        } 
      }
    }
  }

  /**
   * Generates a report for a chosen venue.
   * It return information about the desired venue and all of its attendees.
   * The output is sorted in an ascending order by the visit datetime.
   * The user will be asked to enter the required venue's ID and check if it's exists and valid.
   */
  public static void venueReport() {   
    ArrayList <Visit> visits = Helper.sortDate(visit);  // Sort the visit ArrayList ascending by DateTime
    
    System.out.println("Enter the venue's ID: ");
    String ID = kb.nextLine();    // Collects venue's ID
    
    while(Helper.filterNumbers(ID, 8)) {   // Validates the if the ID is valid
      ID = kb.nextLine();
    }
    while(Helper.searchVenuesArray(venue, ID) == false) {    // Checks the ID's existence
      System.out.println("This venue is not exists. Enter an exists venue's ID: ");
      ID = kb.nextLine();
      while(Helper.filterNumbers(ID, 8)) {
        ID = kb.nextLine();
      }
    }
    
    //  Generates the venue's details output
    for(int i = 0; i < venue.size(); i++) {
      if(venue.get(i).getVenueID().contains(ID)) {
        System.out.println("Venue:");
        System.out.printf("%-15s%-15s", "  Name:", venue.get(i).getVenueName());
        System.out.printf("\n%-15s%-15s", "  Address:", venue.get(i).getVenueAddress());
        System.out.println();
      }
    }
    
    // This arraylists to store information about individuals' IDs and datetime to be displayed in the report
    ArrayList<String> individualsID = new ArrayList<String>(); // Stores the IDs of all the individuals who attended this venue
    ArrayList<String> visitDate = new ArrayList<String>();     // Ordered dateTime arraylist
    
    // Adding values to the IDs arraylist
    for(int i = 0; i < visits.size(); i++) {
      if(visits.get(i).getVenueId().contains(ID)) {
        individualsID.add(visits.get(i).getIndividualId()); 
      }
    }
    
    // Adding values to the DateTime arraylist
    for(int i = 0; i < visits.size(); i++) {
      if(visits.get(i).getVenueId().contains(ID)) {
        visitDate.add(visits.get(i).getDateTime()); 
      }
    }
        
    // Generating the venue report
    System.out.println("\nVisitors:");
    
    if (Helper.searchVisitsVenueArray(visits, ID) == false) {  // Check if the venue has not had any visitor
      System.out.println("  This venue has no recorded visits!");
      return;
    }
    
    for(int j = 0; j < individualsID.size(); j++) {
      for(int k = 0; k < individual.size(); k++) {
        if(individual.get(k).getIndividualID().contains(individualsID.get(j))) {  // Searching the individual object for the attendees
          System.out.printf("%-15s%-15s", "  Name:", individual.get(k).getIndividualName());
          System.out.printf("\n%-15s%-15s", "  Address:", individual.get(k).getIndividualAddress());
          System.out.printf("\n%-15s%-15s", "  Phone:", individual.get(k).getIndividualPhone());
          System.out.printf("\n%-15s%-15s", "  Email:", individual.get(k).getIndividualEmail());

          String vDate = visitDate.get(j);
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");   // The datetime format as it saved in the file
          LocalDateTime date = LocalDateTime.parse(vDate, formatter);
          DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EE, d MMM yyyy HH:mm");   // Set the display format
          String formatDateTime = date.format(formatter1);
          
          System.out.printf("\n%-15s%-15s", "  Date & Time:", formatDateTime);
          System.out.println("\n");
        } 
      }
    }
  }
  
  /**
   * Writes the new added individuals from the individual object to a chosen text file.
   * The method adds each individual's information in one line in a CSV format.
   * @param file - The text file name to write the data into
   * @param individuals - The individuals object where the data will be copied from
   */
  public static void writeIndividuals(String file, ArrayList<Individual> individuals) {
    try {
      PrintWriter outFile = new PrintWriter(file + ".txt");
      
      for(int i = 0; i < individuals.size(); i++) {
        String ID = String.valueOf(individuals.get(i).getIndividualID());
        String name = individuals.get(i).getIndividualName();
        String address = individuals.get(i).getIndividualAddress();
        String phone = individuals.get(i).getIndividualPhone();
        String Email = individuals.get(i).getIndividualEmail();
       
       outFile.println(ID + "," + name + "," + address + "," + phone + "," + Email);
       }
       outFile.close();
    }catch(IOException e) {
      System.out.println("Error with file!");
    }
  }
  
  /**
   * Writes the new added venues from the venue object to a chosen text file.
   * The method adds each venue's information in one line in a CSV format. 
   * @param file - Text file name to add the venues' data to
   * @param venues - The venues object to get the data from
   */
  public static void writeVenues(String file, ArrayList<Venue> venues) {
    try {
      PrintWriter outFile = new PrintWriter(file + ".txt");
      
      for(int i = 0; i < venues.size(); i++) {
        String ID = String.valueOf(venues.get(i).getVenueID());
        String name = venues.get(i).getVenueName();
        String address = venues.get(i).getVenueAddress();
       
        outFile.println(ID + "," + name + "," + address);
       }
       outFile.close();
    }catch(IOException e) {
      System.out.println("Error with file!");
    }
  }
  
  /**
   * Writes the new recorded visits from the visit object to a chosen text file.
   * The method adds each visit's information in one line in a CSV format.
   * @param file - The text file name to write the data into
   * @param visits - The visits object where the data will be copied from
   */
  public static void writeVisits(String file, ArrayList<Visit> visits) {
    try {
      PrintWriter outFile = new PrintWriter(file + ".txt");
      
      for(int i = 0; i < visits.size(); i++) {
        String venID = String.valueOf(visits.get(i).getVenueId());
        String indID = String.valueOf(visits.get(i).getIndividualId());
        String dateTime = visits.get(i).getDateTime();
       
        outFile.println(venID + "," + indID + "," + dateTime);
       }
       outFile.close();
    }catch(IOException e) {
      System.out.println("Error with file!");
    }
  }
  
  /**
   * The options menu that allows the user to choose an option from to perform a particular action
   */
  public static void displayMenu() {
    // The menu options
    String[] menu = {"Add a Venue", "Add an Individual", "Record a Visit", "Generate Individual Report", "Generate Venue Report", "Save", "Exit Program"};
    
    String option;  // Holds the option input
    System.out.println();
    for (int i = 0; i < menu.length; i++) {
      System.out.println((i + 1) + ". " + menu[i]);
    }
    System.out.println("\nEnter your menu choice: ");
    option = kb.nextLine();
    
    do {
      if(option.equals("1")) {
        addVenue();
        unsavedVenue = true;
        displayMenu();
      } else if(option.equals("2")) {
        addIndividual();
        unsavedIndividual = true;
        displayMenu();
      } else if(option.equals("3")) {
        recordVisit();
        unsavedVisit = true;
        displayMenu();
      } else if(option.equals("4")) {
        individualReport();
        displayMenu();
      } else if(option.equals("5")) {
        venueReport();
        displayMenu();
      } else if(option.equals("6")) {
        writeIndividuals("individuals", individual);
        writeVenues("venues", venue);
        writeVisits("visits", visit);
        unsavedVenue = false;       // Venue changes saved
        unsavedIndividual = false;  // Individual changes saved
        unsavedVisit = false;       // Visit changes saved
        System.out.println("Changes saved");
        displayMenu();
      } else if(option.equals("7")){
         while(unsavedVenue || unsavedIndividual || unsavedVisit){  // Checks if the changes was saved 
           System.out.println("Do you want to save the changes? Y/N: ");
           String decision = kb.nextLine();
           while(decision != null) {
             if(decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {   // Ask the user if they want to save the changes
             writeIndividuals("individuals", individual);
             writeVenues("venues", venue);
             writeVisits("visits", visit);
             System.out.println("Changes saved");
             System.out.println("Program was ended");
             System.exit(0);
            } else if(decision.equalsIgnoreCase("N") || decision.equalsIgnoreCase("No")) {
              System.out.println("Program was ended");
              System.exit(0);
            } else {
              System.out.println("Please choose Y to save or N to exit without save: ");
              decision = kb.nextLine();
            }
           }
         }
         System.out.println("Program was ended");
         System.exit(0);
      } else {
        System.out.println("Please enter a valid menu selection (1 to 7): ");
        displayMenu();
      }
    } while(!option.equals("7"));
  }
}