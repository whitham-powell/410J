package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

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

    String appointmentOwner;
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = null;
    AbstractAppointment appointment;

    // Set up list of Options
    Options options = new Options();
    options.addOption("pretty", true, "file", "Pretty print the appointment book to a text file or standard out (file - )");
    options.addOption("textFile", true, "file", "Where to read/write the appointment book");
    options.addOption("print", false, "Prints a description of the new appointment");
    options.addOption("README", false, "Prints a README for this project along with the usage and exits");
    int numberOfOptions = options.count();
    int numberOfExpectedArguments = 10;

    // Parse the command line and get the commands
    CommandLineParser commandLine = new CommandLineParser(options, args);
    Commands commands = commandLine.parse(numberOfOptions, numberOfExpectedArguments);

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
      System.exit(1);
    }

    // No errors in creating of commands set action flags
    boolean doPrint = commands.hasOption("print");
    boolean useFile = commands.hasOption("textFile");
    boolean doPretty = commands.hasOption("pretty");

    // Grab Appointment Owner and Appointment from Command Line
    ArrayList<String> pArgs = commandLine.getProvidedArgs();
    ListIterator<String> infoIterator = pArgs.listIterator();
    appointmentOwner = infoIterator.next();
    String description = infoIterator.next();

    // Parse the date
    DateFormat df = new SimpleDateFormat("MM/dd/yyyyhh:mma");
    Date beginTime = null;
    Date endTime = null;
    try {
      beginTime = df.parse(infoIterator.next() + infoIterator.next() + infoIterator.next());
      endTime = df.parse(infoIterator.next() + infoIterator.next() + infoIterator.next());
    } catch (ParseException e) {
      err.println("Bad formatting of the time and or date");
      out.println("Dates and times should be in the format: mm/dd/yyyy hh:mm am/pm\n");
    }

    // Make new appointment
    appointment = new Appointment(description, beginTime, endTime);


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

    if (doPretty) {
      String prettyFile = commands.getOptionValue("pretty");
      PrettyPrinter pp;
      try {
        if (prettyFile.equalsIgnoreCase("-")) {
          pp = new PrettyPrinter();
        } else {
          pp = new PrettyPrinter(commands.getOptionValue("pretty"));
        }
        pp.dump(appointmentBook);
      } catch (IOException e) {
        err.println(e.getMessage());
      }

    }

    if (doPrint) {
      out.format("Owner: %s %nNewly Added Appointment: %n %s", appointmentBook.getOwnerName(), appointment);
    }

    // TODO add pretty print feature in main
    System.exit(exitCode);
  }

}