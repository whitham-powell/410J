package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Main class to run Project2 command line processes.
 */
public class Project3 extends Project1 {

  private static String USAGE =
          "usage: java edu.pdx.edu.cs410J.<login-id>.Project3 [options] <args> \n" +
                  " args are (in this order): \n" +
                  "   owner        The person whose owns the appt book\n" +
                  "   description  A description of the appointment\n" +
                  "   beginTime    When the appt begins (24-hour time)\n" +
                  "   endTime      When the appt ends (24-hour time)\n";

  private static String README =
          "CS410J Project 3: Storing an Appointment Book in a Text File\n" +
                  "Author: Elijah Whitham-Powell\n" +
                  "Date Completed: July 20th, 2016\n" +
                  "Description: This project is a commandline application that creates an Appointment\n" +
                  "\t\t\t and adds it to an AppointmentBook based on the arguments provided via the\n" +
                  "\t\t\t command line. Appointment descriptions should be provided in double quotes. \n" +
                  "\t\t\t The rest of the arguments should be provided separated by spaces. \n" +
                  "\t\t\t The appointment book can also be created from and saved to a text file provided\n" +
                  "\t\t\t via the options list. The Ability to \"pretty\" print a file to the screen to a " +
                  "\t\t\t supplied file.\n";

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    Project3 p2 = new Project3();

    String appointmentOwner;
    String[] appointmentInfo;
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = null;
    AbstractAppointment appointment;

    // Set up list of Options
    Options options = new Options();
    options.addOption("pretty", true, "file", "Pretty print the appointment book to a text file or standard out (file - )");
    options.addOption("textFile", true, "file", "Where to read/write the appointment book");
    options.addOption("print", false, "Prints a description of the new appointment");
    options.addOption("README", false, "Prints a README for this project along with the usage and exits");
    int numberOfOptions = options.count();
    int numberOfExpectedArguments = 8;

    // Parse the command line and get the commands
    CommandLineParser commandLine = new CommandLineParser(options, args);
    Commands commands = commandLine.parse(numberOfOptions, numberOfExpectedArguments);

//    err.println(numberOfExpectedArguments);
//    err.println(commandLine.getProvidedArgs().size());
//    err.println(commandLine.getProvidedOptions().size());

    // Check for README flag special case to exit
    if (commands.hasOption("README")) {
      out.print(README + "\n" + USAGE + "Dates and times should be in the format: mm/dd/yyyy hh:mm\n");
      out.print(options);
      System.exit(0);
    }

    int exitCode = 0;
    // Check for errors
    if (commands.hasError()) {
      err.print(commands.getErrorMessage());
      out.print(USAGE + options + "Dates and times should be in the format: mm/dd/yyyy hh:mm\n");
//      exitCode = 1;
      System.exit(1);
    }

    // No errors in creating of commands set action flags
    boolean doPrint = commands.hasOption("print");
    boolean useFile = commands.hasOption("textFile");

    // Grab Appointment Owner and Appointment from Command Line
    ArrayList<String> pArgs = commandLine.getProvidedArgs();
    appointmentOwner = pArgs.get(0);
    appointmentInfo = pArgs.subList(1, 6).toArray(new String[5]);

    // Validate Appointment Dates and Times
    InfoValidator validator = new InfoValidator(appointmentInfo);
    exitCode = validator.getErrCode();
    if (validator.hasFailed()) {
      err.print(validator.getErrMsg());
    }

    // Build new Appointment
    appointment = new Appointment(appointmentInfo);

    if (useFile) {
      String fileName = commands.getOptionValue("textFile");
      TextParser tp = new TextParser(fileName, appointmentOwner);

      try {
        appointmentBook = (AppointmentBook) tp.parse();
        appointmentBook.addAppointment(appointment);
      } catch (ParserException e) {
        err.println(e.getMessage());
      }
      try {
        TextDumper td = new TextDumper(fileName);
        td.dump(appointmentBook);
      } catch (IOException e) {
        err.println(e.getMessage());
      }
    } else {

      // Add Appointment to Owner's book
      appointmentBook = new AppointmentBook(appointmentOwner);
      appointmentBook.addAppointment(appointment);

    }
    if (doPrint) {
      out.format("Owner: %s %nNewly Added Appointment: %n %s", appointmentBook.getOwnerName(), appointment);
    }

    // TODO add pretty print feature in main
    System.exit(exitCode);
  }

}