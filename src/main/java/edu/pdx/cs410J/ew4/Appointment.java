package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;

/**
 * This class extends <code>AbstractAppointment</code> and implements a concrete <code>Appointment</code>.
 */
public class Appointment extends AbstractAppointment {
  private String description = "< empty >";
  private String beginTimeString;
  private String endTimeString;

  public Appointment() {
  }

  /**
   * Creates a new <code>Appointment</code>
   *
   * @param appointmentInfo - an array of strings that will be stored into data fields
   */
  public Appointment(String[] appointmentInfo) {
    this.description = appointmentInfo[0];
    this.beginTimeString = appointmentInfo[1] + " " + appointmentInfo[2];
    this.endTimeString = appointmentInfo[3] + " " + appointmentInfo[4];
  }

  /**
   * Creates a new <code>Appointment</code>
   *
   * @param description     The appointment description, defaults to <code>" empty "</code>
   * @param beginTimeString The time and date the appointment begins as a <code>String</code>.
   * @param endTimeString   The time and date the appointment ends as a <code>String</code>.
   */
  public Appointment(String description, String beginTimeString, String endTimeString) {
    this.description = description;
    this.beginTimeString = beginTimeString;
    this.endTimeString = endTimeString;
  }

  @Override
  /**
   * Returns the beginning time and date of an <code>Appointment</code> as a <code>String</code>
   * @param none
   * @return      <code>this.beginTimeString</code>
   */
  public String getBeginTimeString() {
    return this.beginTimeString;
  }

  /**
   * Sets this <code>Appointment</code> beginning time as a <code>String</code>
   *
   * @param beginTimeString - the time of an <code>Appointment</code> as a <code>String</code>
   * @throws IllegalArgumentException - if a null is passed in it raises exception.
   */
  void setBeginTimeString(String beginTimeString) {
    if (beginTimeString == null)
      throw new IllegalArgumentException("beginTimeString cannot be null");
    this.beginTimeString = beginTimeString;
  }

  @Override
  /**
   * Returns the ending time and date of an <code>Appointment</code> as a <code>String</code>
   * @param none
   * @return      <code>this.endTimeString</code>
   */
  public String getEndTimeString() {
    return this.endTimeString;
  }

  /**
   * Sets this <code>Appointment</code> end time as a <code>String</code>
   *
   * @param endTimeString - the end time of an <code>Appointment</code> as a <code>String</code>
   * @throws IllegalArgumentException - if a null is passed in it raises exception.
   */
  void setEndTimeString(String endTimeString) {
    if (endTimeString == null)
      throw new IllegalArgumentException("endTimeString cannot be null");
    this.endTimeString = endTimeString;
  }

  @Override
  /**
   * Returns the description of an <code>Appointment</code> as a <code>String</code>
   * @param none
   * @return      <code>this.description</code>
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets this <code>Appointment</code> end time as a <code>String</code>
   *
   * @param description - the description of an <code>Appointment</code> as a <code>String</code>
   * @throws IllegalArgumentException - if a null is passed in it raises exception.
   */
  void setDescription(String description) {
    if (description == null)
      throw new IllegalArgumentException("description cannot be null");
    this.description = description;
  }

//  @Override
//  public Date getBeginTime() {
//    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//    try {
//      Date date;
//      date = df.parse(this.beginTimeString);
//      return date;
//    } catch (ParseException e) {
//      e.printStackTrace();
//      return null;
//    }
//  }
//
//  @Override
//  public Date getEndTime() {
//    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//    try {
//      Date date;
//      date = df.parse(this.endTimeString);
//      return date;
//    } catch (ParseException e) {
//      e.printStackTrace();
//      return null;
//    }
//  }


}
