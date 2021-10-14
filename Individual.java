/**
 * Individual class defines all the required details to know about the individuals. Such as, 
 * the individual's id, name, residential address, phone number and email address. 
 * The class has a constructor, mutators and accessors methods to allow other classes to interact with it. 
 * 
 * Author: Michael Makhoul
 */

public class Individual {
  private String individualID;        // Individual's ID Number
  private String individualName;      // Individual's Full Name
  private String individualAddress;   // Individual's Residential Address
  private String individualPhone;     // Individual's Phone Number
  private String individualEmail;     // Individual's Email Address
  
  // Constructor
  /**
   * Individual class constructor. Helps to create new individual objects
   * @param ID - Individual's ID
   * @param name - Individual's Name
   * @param address - Individual's Address
   * @param phoneNumber - Individual's Phone Number
   * @param email - Individual's Email Address
   */
  public Individual(String ID, String name, String address, String phoneNumber, String email) {
    individualID = ID;
    individualName = name;
    individualAddress = address;
    individualPhone = phoneNumber;
    individualEmail = email;
  }
  
  // Mutators
  /**
   * @param ID - Individual's ID Number
   */
  public void setIndividualID(String ID) {
    individualID = ID;
  }
  
  /**
   * @param name -  Individual's Full Name
   */
  public void setIndividualName(String name) {
    individualName = name;
  }
  
  /**
   * @param address -  Individual's Residential Address
   */
  public void setIndividualAddress(String address) {
    individualAddress = address;
  }
  
  /**
   * @param phone - Individual's Phone Number
   */
  public void setIndividualPhone(String phone) {
    individualPhone = phone;
  }
  
  /**
   * @param email - Individual's Email Address
   */
  public void setIndividualEmail(String email) {
    individualEmail = email;
  }
  
  // Accessors
  /**
   * @return - Individual's ID
   */
  public String getIndividualID() {
    return individualID;
  }
  
  /**
   * @return - Individual's Name
   */
  public String getIndividualName() {
    return individualName;
  }
  
  /**
   * @return - Individual's Address
   */
  public String getIndividualAddress() {
    return individualAddress;
  }
  
  /**
   * @return - Individual's Phone Number
   */
  public String getIndividualPhone() {
    return individualPhone;
  }
  
  /**
   * @return - Individual's Email Address
   */
  public String getIndividualEmail() {
    return individualEmail;
  }
}