package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.ArrayList;


/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {
  // TODO can i make this a method?
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
    StringBuilder errMessage = new StringBuilder("Bad formatting: \n");
    int err = 0;
    // Check date formatting of beginTimeString
    if (!appointmentInfo[1].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errMessage.append(" Date - beginTime");
      err = 1;
    }

    // Check time formatting of beginTimeString
    if (!appointmentInfo[2].matches("\\d{1,2}\\:\\d{2}")) {
      errMessage.append(" Time - beginTime");
      err = 1;
    }

    // Check date formatting of endTimeString
    if (!appointmentInfo[3].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errMessage.append(" Date - endTime");
      err = 1;
    }

    // Check time formatting of endTimeString
    if (!appointmentInfo[4].matches("\\d{1,2}\\:\\d{2}")) {
      errMessage.append(" Time - endTime");
      err = 1;
    }

    System.err.println(errMessage.toString());
    return err;
  }

  public static void main(String[] args) {
//    Options options = new Options();
//    Option option = new Option("-print", false, "prints the appointment added");
//    options.addOption(option);

    Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    boolean doReadMe = false;
    boolean doPrint = false;
    int exitCode = 0;
    String appointmentOwner = null;
    String[] appointmentInfo = null;
    ArrayList<String> providedOptions = new ArrayList<>();
    ArrayList<String> providedArgs = new ArrayList<>();
//    String[] validOptions = {"-print", "-README"};
    ArrayList<String> validOptions = new ArrayList<>();

    AbstractAppointmentBook<AbstractAppointment> appointmentBook = null;
    AbstractAppointment appointment = null;

    // Commandline Argument Parsing
    for (String arg : args) {
      if (arg.startsWith("-")) {
        providedOptions.add(arg);
      } else {
        providedArgs.add(arg);
      }
    }


//    for (String option : validOptions) {
//      if(!providedOptions.contains(option)) {
//        providedOptions.remove(option);
//      }
//    }

    if (providedOptions.contains("-print")) {
      doPrint = true;
    }

    if (providedOptions.contains("-README")) {
      doReadMe = true;
    }

    if (providedArgs.isEmpty() && providedOptions.isEmpty()) {
      System.err.println("Missing command line arguments: None Provided");
      doReadMe = true;
      exitCode = 1;
    }

    if (providedArgs.size() < 6) {
      if (providedOptions.size() != 1 || !providedOptions.contains("-README")) {
        System.err.println("Missing command line arguments: " + args.length + " provided: \n");
        for (String arg : args) {
          System.out.println("\t" + arg + "\n");
        }
        exitCode = 1;
      } else {
        exitCode = 0;
      }
      doReadMe = true;
    }

    if(providedArgs.size() > 6) {
      doReadMe = true;
      exitCode = 1;
      System.err.println("Too many command line arguments: " + args.length + " provided: \n");
      for (String arg : args) {
        System.out.println("\t"+ arg + "\n");
      }
    }

    if (providedArgs.size() == 6) {
      appointmentOwner = providedArgs.get(0);
      appointmentInfo = providedArgs.subList(1, 6).toArray(new String[providedArgs.size()]);
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
        System.err.println(e.getMessage() + "appointmentInfo String[] failed to copy correctly");
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


