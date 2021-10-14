/**
 * Venue class defines all the required details to know about the venues. Such as, 
 * the venue's id, name, residential and address. 
 * The class has a constructor, mutators and accessors methods to allow other classes to interact with the venues objects. 
 * 
 * Author: Michael Makhoul
 */

public class Venue {
  private String venueID;
  private String venueName;
  private String venueAddress;
  
  // Constructor
  /**
   * Class constructor. Helps to create a new venue object
   * @param ID - Venue's ID
   * @param name - Venue's Name
   * @param address - Venue's Address
   */
  public Venue(String ID, String name, String address) {
    venueID = ID;
    venueName = name;
    venueAddress = address;
  }
  
  // Mutator methods
  /**
   * @param ID - Venue's ID Number
   */
  public void setVenueID(String ID) {
    venueID = ID;
  }
  
  /**
   * @param name - Venue's Name
   */
  public void setVenueName(String name) {
    venueName = name;
  }
  
  /**
   * @param address - Venue's Address
   */
  public void setVenueAddress(String address) {
    venueAddress = address;
  }
  
  // Accessor methods
  /**
   * @return - Venue's ID
   */
  public String getVenueID() {
    return venueID;
  }
  
  /**
   * @return - Venue's Name
   */
  public String getVenueName() {
    return venueName;
  }
  
  /**
   * @return - Venue's Address
   */
  public String getVenueAddress() {
    return venueAddress;
  }
}