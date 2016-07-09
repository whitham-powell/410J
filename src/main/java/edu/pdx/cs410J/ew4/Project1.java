package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


/**
 * @author Elijah Whitham-Powell
 *
 * The main class for the CS410J appointment book Project takes in command line arguments and
 * builds an {@link Appointment} and {@link AppointmentBook} based on the supplied arguments.
 */
public class Project1 {

  private static final String USAGE =
          "usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n" +
                  "\targs are (in this order): \n" +
                  "\t\towner The person whose owns the appt book\n" +
                  "\t\tdescription A description of the appointment\n" +
                  "\t\tbeginTime When the appt begins (24-hour time)\n" +
                  "\t\tendTime When the appt ends (24-hour time)\n" +
                  "\toptions are (options may appear in any order):\n" +
                  "\t\t-print Prints a description of the new appointment\n" +
                  "\t\t-README Prints a README for this project and exits\n" +
                  "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n";

  private static final String README =
          "CS410J Project 1: Designing an Appointment Book Application\n" +
                  "Author: Elijah Whitham-Powell\n" +
                  "Date Completed: July 6th, 2016\n" +
                  "Description: This project is a commandline application that creates an Appointment\n" +
                  "\t\t\t and adds it to an AppointmentBook based on the arguments provided via the\n" +
                  "\t\t\t command line. Appointment descriptions should be provided in double quotes. \n" +
                  "\t\t\t The rest of the arguments should be provided separated by spaces.\n"
                  + USAGE;

  /**
   * Takes in an array of appointment dates and times as strings and verifies
   * their formatting.
   *
   * @param appointmentInfo - array of Strings to validate.
   * @return int error code used to determine.
   */
  protected static int appointmentInfoValidator(String[] appointmentInfo) {
    StringBuilder errReason = new StringBuilder();
    int err = 0;

    // Check date formatting of beginTimeString
    if (!appointmentInfo[1].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Date - beginTime\n");
      err = 1;
    }

    // Check time formatting of beginTimeString
    if (!appointmentInfo[2].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Time - beginTime\n");
      err = 1;
    }

    // Check date formatting of endTimeString
    if (!appointmentInfo[3].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Date - endTime\n");
      err = 1;
    }

    // Check time formatting of endTimeString
    if (!appointmentInfo[4].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Time - endTime\n");
      err = 1;
    }
    if (err == 1) {
      System.err.println("Bad formatting: \n" + errReason.toString());
    }
    return err;
  }


  /**
   * Formats a supplied time and date string.
   * @param timeAndDate - date and time to format/clean
   * @return String - correctly formatted
   * @throws ParseException
   */
  protected static String appointmentCleaner(String timeAndDate) throws ParseException {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      Date date;
      date = df.parse(timeAndDate);
      return df.format(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Interprets and parses the command line arguments to do the work.
   * @param args - Command Line supplied arguments.
   */
  public static void main(String[] args) {

    Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean doUsage = false;
    boolean doPrint = false;
    int exitCode = 0;
    String appointmentOwner = null;
    String[] appointmentInfo = null;
    ArrayList<String> providedOptions = new ArrayList<>();
    ArrayList<String> providedInfo = new ArrayList<>();
    ArrayList<String> providedArgs = new ArrayList<>();

    // Convert String[] args to ArrayList
    Collections.addAll(providedArgs, args);

    // Build ArrayList of valid options
    ArrayList<String> validOptions = new ArrayList<>();
    Collections.addAll(validOptions, "-print", "-README");

    AbstractAppointmentBook<AbstractAppointment> appointmentBook = null;
    AbstractAppointment appointment = null;

    // Commandline Argument Parsing
    int i = 0;
    int argsLength = providedArgs.size();
    for (; i < 2 && i < argsLength; i++) {
      String arg = providedArgs.get(i);
      if (arg.startsWith("-")) {
        providedOptions.add(arg);
      } else {
        break;
      }
    }
    for (; i < argsLength; i++) {
      providedInfo.add(providedArgs.get(i));
    }

    // Clean up options and if this is needed exit and print invalid option error
    if (providedOptions.retainAll(validOptions)) {
      System.err.println("Invalid option: ");
      providedArgs.forEach(System.out::println);
      doUsage = true;
      exitCode = 1;
    }

    if (providedInfo.isEmpty() && providedOptions.isEmpty()) {
      System.err.println("Missing command line arguments: None Provided");
      doUsage = true;
      exitCode = 1;
    }

    // Check options to execute after cleaning
    if (providedOptions.contains("-README")) {
      doUsage = true;
      // Special case to exit if -README is provided, ignoring everything else
      System.out.print(README);
      System.exit(0);
    }

    if (providedOptions.contains("-print")) {
      doPrint = true;
    }

    if (providedInfo.size() < 6 && providedInfo.size() > 0) {
      System.err.println("Missing command line arguments: " + args.length + " provided: \n");
      for (String arg : args) {
        System.out.println("\t" + arg + "\n");
      }
      exitCode = 1;
      doUsage = true;
    }

    if (providedInfo.size() > 6) {
      doUsage = true;
      exitCode = 1;
      System.err.println("Too many command line arguments: " + args.length + " provided: \n");
      for (String arg : args) {
        System.out.println("\t" + arg + "\n");
      }
    }

    if (providedInfo.size() == 6) {
      appointmentOwner = providedInfo.get(0);
      appointmentInfo = providedInfo.subList(1, 6).toArray(new String[providedInfo.size()]);
      exitCode += appointmentInfoValidator(appointmentInfo);
    }

    // Carrying out routine based on argument parse
    if (doUsage) {
      System.out.print(USAGE);
    }
    if (exitCode == 0) {
      try {
        appointmentBook = new AppointmentBook(appointmentOwner);
      } catch (NullPointerException e) {
        System.err.println(e.getMessage() + " null appointmentOwner");
      }
      try {
        appointment = new Appointment(appointmentInfo);
      } catch (NullPointerException e) {
        System.err.println(e.getMessage() + " appointmentInfo String[] failed to copy correctly");
      }
      try {
        ((Appointment) appointment).setBeginTimeString(appointmentCleaner(appointment.getBeginTimeString()));
        ((Appointment) appointment).setEndTimeString(appointmentCleaner(appointment.getEndTimeString()));
      } catch (NullPointerException e) {
        System.err.println(e.getMessage() + " appointment date and time string was null before cleaning date and time");
      } catch (ParseException e) {
        System.err.println(e.getMessage() + " error attempting to clean appointment times");
      }
      if (appointmentBook != null && appointment != null) {
        if (doPrint) {
          System.out.println(appointmentBook.getOwnerName());
          System.out.println(appointment);
        }
        appointmentBook.addAppointment(appointment);
      }
    }
    System.exit(exitCode);
  }
}


