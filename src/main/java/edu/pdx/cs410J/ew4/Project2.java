package edu.pdx.cs410J.ew4;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * TODO dodcument class
 * Created by edub629 on 7/7/16.
 */
public class Project2 extends Project1 {

  private static String USAGE =
          "usage: java edu.pdx.edu.cs410J.<login-id>.Project2 [options] <args> \n" +
                  " args are (in this order): \n" +
                  "   owner        The person whose owns the appt book\n" +
                  "   description  A description of the appointment\n" +
                  "   beginTime    When the appt begins (24-hour time)\n" +
                  "   endTime      When the appt ends (24-hour time)\n";

  private static String README =
          "CS410J Project 2: Storing an Appointment Book in a Text File\n" +
                  "Author: Elijah Whitham-Powell\n" +
                  "Date Completed: July 13th, 2016\n" +
                  "Description: This project is a commandline application that creates an Appointment\n" +
                  "\t\t\t and adds it to an AppointmentBook based on the arguments provided via the\n" +
                  "\t\t\t command line. Appointment descriptions should be provided in double quotes. \n" +
                  "\t\t\t The rest of the arguments should be provided separated by spaces.\n"
                  + USAGE;


  public static void main(String[] args) {
    Project2 p2 = new Project2();

    // Set up list of Options
    Options options = new Options();
    options.addOption("textFile", true, "Where to read/write the appointment book");
    options.addOption("print", false, "Prints a description of the new appointment");
    options.addOption("README", false, "Prints a README for this project along with the usage and exits");
    int numberOfOptions = options.count();
    int numberOfExpectedArguments = 6;

    // Parse the command line and get the commands
    CommandLineParser clp = new CommandLineParser(options, args);
    Commands commands = clp.parse(numberOfOptions, numberOfExpectedArguments);

    if (commands.hasError()) {
      err.print(commands.getErrorMessage());
      out.print(USAGE + options + "Dates and times should be in the format: mm/dd/yyyy hh:mm\n");
      System.exit(1);
    }

    if (commands.hasOption("README")) {
      out.print(README + USAGE);
      out.print(options);
      System.exit(0);
    }


    //TODO ALL OF THE THINGS

  }

}