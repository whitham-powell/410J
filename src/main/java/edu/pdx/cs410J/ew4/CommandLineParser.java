package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TODO Document Class Methods
 */


public class CommandLineParser {
  private String usage;
  private String readMe;

  private ArrayList<String> toParse;
  private ArrayList<String> providedOptions;
  private ArrayList<String> providedArgs;
  private int validNumberOfArgs;
  private Options validOptions;

  public CommandLineParser(Options validOptions, String[] args) {
    this.validOptions = validOptions;
    toParse = new ArrayList<>();
    Collections.addAll(this.toParse, args);
    this.providedOptions = new ArrayList<>();
    this.providedArgs = new ArrayList<>();
  }

  public void parse(int maxNumberOfOptions, int validNumberOfArgs) {
    // Commandline Argument Parsing
    int i = 0;
    int argsLength = toParse.size();
    for (; i < maxNumberOfOptions && i < argsLength; i++) {
      String arg = toParse.get(i);
      if (arg.startsWith("-")) {
        providedOptions.add(arg.substring(1));
      } else {
        break;
      }
    }
    for (; i < argsLength; i++) {
      providedArgs.add(toParse.get(i));
    }
    this.validNumberOfArgs = validNumberOfArgs;
  }

  public ArrayList<String> getProvidedArgs() {
    return providedArgs;
  }

  public ArrayList<String> getProvidedOptions() {
    return providedOptions;
  }

}
//  public boolean addValidOption(String option, boolean hasArgs) {
//    if(hasArgs) {
//      return validOptionsWithArgs.add(option) && validOptions.add(option);
//    } else {
//      return validOptions.add(option);
//    }
//  }
//
//  public boolean isOptionValid(String option){
//    return validOptions.contains(option);
//  }
//
//  public boolean isReadMe() {
//    return providedOptions.contains("-README");
//  }
//
//  public boolean isPrint() {
//    return providedOptions.contains("-print");
//  }
//
//  public ArrayList<String> getValidOptions() {
//    return validOptions;
//  }
//
//  public ArrayList<String> getValidOptionsWithArgs() {
//    return validOptionsWithArgs;
//  }
//
//  public void invoke() {
//
//    // Commandline Argument Parsing
//    int i = 0;
//    int argsLength = toParse.size();
//    for (; i < 2 && i < argsLength; i++) {
//      String arg = toParse.get(i);
//      if (arg.startsWith("-")) {
//        providedOptions.add(arg);
//      } else {
//        break;
//      }
//    }
//    for (; i < argsLength; i++) {
//        providedArgs.add(toParse.get(i));
//    }
//
//    grabProvided(providedOptions, providedArgs);
//
//
//    // Clean up options
//    providedOptions.retainAll(validOptions);
//    providedOptions.trimToSize();
//
////    if (providedOptions.contains("-print")) {
////      doPrint = true;
////    }
////
////
////    if (providedArgs.isEmpty() && providedOptions.isEmpty()) {
////      System.err.println("Missing command line arguments: None Provided");
////      doReadMe = true;
////      exitCode = 1;
////    }
////
////    if (providedArgs.size() < validNumberOfArguments) {
////      if (providedOptions.size() != 1 || !providedOptions.contains("-README")) {
////        System.err.println("Missing command line arguments: " + args.length + " provided: \n");
////        for (String arg : args) {
////          System.out.println("\t" + arg + "\n");
////        }
////        exitCode = 1;
////      } else {
////        exitCode = 0;
////      }
////      doReadMe = true;
////    }
////
////    if (providedArgs.size() > validNumberOfArguments) {
////      doReadMe = true;
////      exitCode = 1;
////      System.err.println("Too many command line arguments: " + args.length + " provided: \n");
////      for (String arg : args) {
////        System.out.println("\t" + arg + "\n");
////      }
////    }
////
////    if (providedArgs.size() == validNumberOfArguments) {
////      appointmentOwner = providedArgs.get(0);
////      appointmentInfo = providedArgs.subList(1, validNumberOfArguments).toArray(new String[providedArgs.size()]);
////      exitCode = appointmentInfoValidator(appointmentInfo);
////    }
//  }
//




