package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;


/**
 * The main class for the CS410J appointment book Project
 */
//public class CopyOfProject1 {
//TODO can i make this a method?
//  private static final String README = "usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n" +
//          "\targs are (in this order): \n" +
//          "\t\towner The person whose owns the appt book\n" +
//          "\t\tdescription A description of the appointment\n" +
//          "\t\tbeginTime When the appt begins (24-hour time)\n" +
//          "\t\tendTime When the appt ends (24-hour time)\n" +
//          "\toptions are (options may appear in any order):\n" +
//          "\t\t-print Prints a description of the new appointment\n" +
//          "\t\t-README Prints a README for this project and exits\n" +
//          "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n";


//  private static int appointmentInfoValidator(String[] appointmentInfo) {
//    StringBuilder errMessage = new StringBuilder("Bad formatting: \n");
//    int err = 0;
//    // Check date formatting of beginTimeString
//    if (!appointmentInfo[1].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
//      errMessage.append(" Date - beginTime");
//      err = 1;
//    }
//
//    // Check time formatting of beginTimeString
//    if (!appointmentInfo[2].matches("\\d{1,2}\\:\\d{2}")) {
//      errMessage.append(" Time - beginTime");
//      err = 1;
//    }
//
//    // Check date formatting of endTimeString
//    if (!appointmentInfo[3].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
//      errMessage.append(" Date - endTime");
//      err = 1;
//    }
//
//    // Check time formatting of endTimeString
//    if (!appointmentInfo[4].matches("\\d{1,2}\\:\\d{2}")) {
//      errMessage.append(" Time - endTime");
//      err = 1;
//    }
//
//    System.err.println(errMessage.toString());
//    return err;
//  }

//  private static void appointmentCleaner(String sDate, String sTime) {
//    // The date provided is correct but we want it to be consistent
//    if (!sDate.matches("\\d{2}\\/\\d{2}\\/\\d{4}")){
//      String[] split = sDate.split("\\/");
//      split[0]= "0" + split[0];
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
//  public static void main(String[] args) {
//
//    new Parser(args).invoke();
//  }

public class CommandLineParser {
  private ArrayList<String> toParse;
  private ArrayList<String> providedOptions;
  private ArrayList<String> providedArgs;
  private ArrayList<String> validOptions;
  private ArrayList<String> validOptionsWithArgs;
  private int validNumberOfArguments;

  public CommandLineParser(String [] args) {
    Collections.addAll(this.toParse, args);
    this.providedOptions = new ArrayList<>();
    this.providedArgs = new ArrayList<>();
    this.validOptions = new ArrayList<>();
    this.validOptionsWithArgs = new ArrayList<>();
    this.validNumberOfArguments = 6;
  }

  public boolean addValidOption(String option, boolean hasArgs) {
    if(hasArgs) {
      return validOptionsWithArgs.add(option);
    } else {
      return validOptions.add(option);
    }
  }

  public boolean isReadMe() {
    return providedOptions.contains("-README");
  }

  public boolean isPrint() {
    return providedOptions.contains("-print");
  }

  public void invoke() {

    // Commandline Argument Parsing
    int i = 0;
    int argsLength = toParse.size();
    for (; i < 2 && i < argsLength; i++) {
      String arg = toParse.get(i);
      if (arg.startsWith("-")) {
        providedOptions.add(arg);
      } else {
        break;
      }
    }
    for (; i < argsLength; i++) {
        providedArgs.add(toParse.get(i));
    }

    grabProvided(providedOptions, providedArgs);


    // Clean up options
    providedOptions.retainAll(validOptions);
    providedOptions.trimToSize();

//    if (providedOptions.contains("-print")) {
//      doPrint = true;
//    }
//
//
//    if (providedArgs.isEmpty() && providedOptions.isEmpty()) {
//      System.err.println("Missing command line arguments: None Provided");
//      doReadMe = true;
//      exitCode = 1;
//    }
//
//    if (providedArgs.size() < validNumberOfArguments) {
//      if (providedOptions.size() != 1 || !providedOptions.contains("-README")) {
//        System.err.println("Missing command line arguments: " + args.length + " provided: \n");
//        for (String arg : args) {
//          System.out.println("\t" + arg + "\n");
//        }
//        exitCode = 1;
//      } else {
//        exitCode = 0;
//      }
//      doReadMe = true;
//    }
//
//    if (providedArgs.size() > validNumberOfArguments) {
//      doReadMe = true;
//      exitCode = 1;
//      System.err.println("Too many command line arguments: " + args.length + " provided: \n");
//      for (String arg : args) {
//        System.out.println("\t" + arg + "\n");
//      }
//    }
//
//    if (providedArgs.size() == validNumberOfArguments) {
//      appointmentOwner = providedArgs.get(0);
//      appointmentInfo = providedArgs.subList(1, validNumberOfArguments).toArray(new String[providedArgs.size()]);
//      exitCode = appointmentInfoValidator(appointmentInfo);
//    }
  }

  private void grabProvided(ArrayList<String> providedOptions, ArrayList<String> providedArgs) {
    // Commandline Argument Parsing
    for (String arg : toParse) {
      if (arg.startsWith("-")) {
        providedOptions.add(arg);
      } else {
        providedArgs.add(arg);
      }
    }
  }
}



