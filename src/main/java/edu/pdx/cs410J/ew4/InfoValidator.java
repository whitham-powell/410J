package edu.pdx.cs410J.ew4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class validates the date and time strings for use with:
 * {@link Appointment}
 * {@link TextParser}
 * {@link Project2}
 *
 * @author Elijah Whitham-Powell
 */
public class InfoValidator {
  private int errCode = 0;
  private String errMsg = "";
  private boolean hasFailed = false;
  private String[] info;


  /**
   * Instantiates a new Info validator.
   *
   * @param info the <code>Appointment</code> information to validate.
   */
  public InfoValidator(String[] info) {
    this.info = info;
    StringBuffer errReason = new StringBuffer();

    // Check date formatting of beginTimeString
    if (!info[1].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Date - beginTime\n");
      this.errCode = 1;
    }

    // Check time formatting of beginTimeString
    if (!info[2].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Time - beginTime\n");
      this.errCode = 1;
    }

    // Check date formatting of endTimeString
    if (!info[3].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Date - endTime\n");
      this.errCode = 1;
    }

    // Check time formatting of endTimeString
    if (!info[4].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Time - endTime\n");
      this.errCode = 1;
    }
    if (this.errCode == 1) {
      this.hasFailed = true;
      this.errMsg = ("Bad formatting: \n" + errReason.toString());
    }
  }

  /**
   * Instantiates a new Info validator.
   */
  public InfoValidator() {
  }

  /**
   * Returns the <code>Appointment</code> end time after being converted to a <code>Date</code> object.
   *
   * @return Date
   */
  private Date getBeginDate() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      Date date;
      date = df.parse(info[1] + " " + info[2]);
      return date;
    } catch (NullPointerException e) {
      return null;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Returns the <code>Appointment</code> end time after being converted to a <code>Date</code> object.
   *
   * @return Date
   */
  private Date getEndDate() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      Date date;
      date = df.parse(info[3] + " " + info[4]);
      return date;
    } catch (NullPointerException e) {
      return null;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Gets errCode.
   *
   * @return the error code
   */
  public int getErrCode() {
    return errCode;
  }

  /**
   * Gets errMsg.
   *
   * @return the error message
   */
  public String getErrMsg() {
    return errMsg;
  }

  /**
   * Has failed boolean.
   *
   * @return the boolean
   */
  public boolean hasFailed() {
    return hasFailed;
  }

  /**
   * Determines if a string provided in the correct format
   *
   * @param toValidate the string to validate.
   * @return an instance of an InfoValidator.
   */
  public InfoValidator validate(String toValidate) {
    StringBuffer errReason = new StringBuffer("Invalid formatting :");

    // Check date formatting of beginTimeString
    if (!(toValidate.split(" "))[0].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Bad Date \n");
      this.errCode = 1;
      this.hasFailed = true;
    }

    // Check time formatting of beginTimeString
    if (!(toValidate.split(" "))[1].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Bad Time \n");
      this.errCode = 1;
      this.hasFailed = true;
    }

    this.errMsg = errReason.toString();

    return this;
  }
}
