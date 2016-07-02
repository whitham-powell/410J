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
 * The main class for the CS410J appointment book Project
 */
public class Project1 {
  // TODO can i make this a method?
  // TODO add a brief description of the project including name
  private static final String README = "usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n" +
          "\targs are (in this order): \n" +
          "\t\towner The person whose owns the appt book\n" +
          "\t\tdescription A description of the appointment\n" +
          "\t\tbeginTime When the appt begins (24-hour time)\n" +
          "\t\tendTime When the appt ends (24-hour time)\n" +
          "\toptions are (options may appear in any order):\n" +
          "\t\t-print Prints a description of the new appointment\n" +
          "\t\t-README Prints a README for this project and exits\n" +
          "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n";


  private static int appointmentInfoValidator(String[] appointmentInfo) {
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

  private static String appointmentCleaner(String timeAndDate) {
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
//  private static void appointmentCleaner(String sDate, String sTime) {
//    // The date provided is correct but we want it to be consistent
//    if (!sDate.matches("\\d{2}\\/\\d{2}\\/\\d{4}")){
//      String[] split = sDate.split("\\/");
//      split[0]= "0" + split[0];
//
//    }
//
//    if(!sTime.matches("\\d{2}\\:\\d{2}")) {
//      String[] split = sTime.split("\\:");
//      split[0]= "0" + split[0];
//    }
////TODO join strings return that instead of array
//  }

  /**
   * TODO document the main method of project1
   *
   * @param args
   */
  public static void main(String[] args) {

    Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean doReadMe = false;
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
      for (String option: providedArgs) {
        System.out.println(option);
      }
      System.exit(1);
    }
    providedOptions.trimToSize();


    if (providedInfo.isEmpty() && providedOptions.isEmpty()) {
      System.err.println("Missing command line arguments: None Provided");
      doReadMe = true;
      exitCode = 1;
    }

    // Check options to execute after cleaning
    if (providedOptions.contains("-README")) {
      doReadMe = true;
      // Special case to exit if -README is provided, ignoring everything else
      System.out.print(README);
      System.exit(0);
    }

    if (providedOptions.contains("-print")) {
      doPrint = true;
    }


    if (providedInfo.size() < 6) {
//      if (providedOptions.size() != 1 || !providedOptions.contains("-README")) {
      System.err.println("Missing command line arguments: " + args.length + " provided: \n");
      for (String arg : args) {
        System.out.println("\t" + arg + "\n");
      }
      exitCode = 1;
//      }
//      } else {
//        exitCode = 0;
//      }
      doReadMe = true;
    }

    if (providedInfo.size() > 6) {
      doReadMe = true;
      exitCode = 1;
      System.err.println("Too many command line arguments: " + args.length + " provided: \n");
      for (String arg : args) {
        System.out.println("\t" + arg + "\n");
      }
    }

    if (providedInfo.size() == 6) {
      appointmentOwner = providedInfo.get(0);
      appointmentInfo = providedInfo.subList(1, 6).toArray(new String[providedInfo.size()]);
      exitCode = appointmentInfoValidator(appointmentInfo);
    }

    // Carrying out routine based on argument parse
    if (doReadMe) {
      System.out.print(README);
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


