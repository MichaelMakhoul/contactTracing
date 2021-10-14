/**
 * Visit class defines all the required details to know about the visits. 
 * It includes data about the venue's id, individual's id and the visit date and time. 
 * The class has a constructor, mutators and accessors methods to allow other classes to interact with visit objects. 
 * 
 * Author: Michael Makhoul
 */
 
public class Visit {
  private String venueID;
  private String individualID;
  private String dateTime;
  
  // Constructor
  /**
   * Class constructor. Helps to with creating a new visit object
   * @param vID - Venue's ID
   * @param iID - Individual's ID
   * @param dTime - Visit DateTime
   */
  public Visit(String vID, String iID, String dTime) {
    venueID = vID;
    individualID = iID;
    dateTime = dTime;
  }
  
  // Mutators 
  /**
   * @param vID - Venue's ID
   */
  public void setVenueId(String vID) {
    venueID = vID;
  }
  
  /**
   * @param iID - Individual's ID
   */
  public void setIndividualId(String iID) {
    individualID = iID;
  }
  
  /**
   * @param dTime - Visit's DateTime
   */
  public void setDateTime(String dTime) {
    dateTime = dTime;
  }
  
  // Accessors
  /**
   * @return - Venue's ID
   */
  public String getVenueId() {
    return venueID;
  }
  
  /**
   * @return - Individual's ID
   */
  public String getIndividualId() {
    return individualID;
  }
  
  /**
   * @return - Visit Date and Time
   */
  public String getDateTime() {
    return dateTime;
  }
}