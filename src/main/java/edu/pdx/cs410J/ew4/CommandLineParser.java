package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This handle the parsing of the command line and manages them information gathered in
 * <code>ArrayList</code> collections.
 * @author Elijah Whitham-Powell
 */


public class CommandLineParser {
//  private String usage;
//  private String readMe;

  private ArrayList<String> toParse;
  private ArrayList<String> providedOptions;
  private ArrayList<String> providedArgs;
  private ArrayList<String> claimedArgs;
  private ArrayList<String> invalidOptions;
  private int minNumOfArgs;
  private Options validOptions;
  private int maxNumOfArgs;

  public CommandLineParser(Options validOptions, String[] args) {
    this.validOptions = validOptions;
    toParse = new ArrayList<>();
    Collections.addAll(this.toParse, args);
    this.providedOptions = new ArrayList<>();
    this.providedArgs = new ArrayList<>();
    this.claimedArgs = new ArrayList<>();
    this.invalidOptions = new ArrayList<>();
  }

  /**
   * Does the actual work of parsing the command line. This method uses its parameters to decide where in the command
   * line, the options and arguments should be located.
   * @param maxNumberOfOptions controls the loop to find options in the command line.
   * @param maxNumberOfArgs controls the loop to find arguments in the command line.
   * @return {@link Commands} object either containing a collection of commands or the error that occurred while parsing.
   */
  public Commands parse(int maxNumberOfOptions, int maxNumberOfArgs) {
    Commands theCommands = new Commands(true, "never replaced within parse");
    // Commandline Argument Parsing
    int i = 0;
    int argsLength = toParse.size();
    this.minNumOfArgs = maxNumberOfArgs - validOptions.numWArgs();
    this.maxNumOfArgs = maxNumberOfArgs;
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
    if (toParse.isEmpty()) {
      theCommands = new Commands(true, "Missing command line arguments: None Provided");
      return theCommands;
    } else {
      if (providedOptions.contains("README")) {
        theCommands = new Commands(false, "-README found in options");
        theCommands.add(new Commands.Command("README"));
        return theCommands;
      } else {
        if (findInvalidOptions()) {
          theCommands = new Commands(true, "Invalid option detected: \n" + errOut());
          return theCommands;
        }
        if (providedArgs.size() < this.minNumOfArgs) {
          theCommands = new Commands(true, "Not enough arguments provided: \n" + errOut());
        }
        if (providedArgs.size() > this.maxNumOfArgs) {
          theCommands = new Commands(true, "Too many arguments provided: \n" + errOut());
        } else if (providedArgs.size() >= this.minNumOfArgs) {
          theCommands = getCommands();
        }
      }
      return theCommands;
    }
  }

  /**
   * Helper method to the main Parse method. builds a Commands object from the command line including grabbing Commands
   * that themselves take an argument
   *
   * @return {@link Commands} object either containing a collection of commands or the error that occurred while parsing.
   */
  private Commands getCommands() {
    int i;
    Commands commands = new Commands(false, "no error");
    Options.Option fromOptions;
    Commands.Command toCommands;
    for (i = 0; i < providedOptions.size(); ++i) {
      fromOptions = validOptions.getOption(providedOptions.get(i));
      if (fromOptions != null) {
        if (fromOptions.hasArgs()) {
          toCommands = new Commands.Command(fromOptions.getName(), fromOptions.hasArgs(), toParse.get(i + 1));
          claimedArgs.add(toParse.get(i + 1));
          providedArgs.remove(toParse.get(i + 1));
        } else {
          toCommands = new Commands.Command(fromOptions.getName(), fromOptions.hasArgs());
        }
        commands.add(toCommands);
      }
    }
    return commands;
  }

  /**
   * Helper method to traverse the providedOptions looking for invalid options.
   * @return boolean of found invalid options
   */
  private boolean findInvalidOptions() {
    invalidOptions.addAll(providedOptions
            .stream()
            .filter(option -> !validOptions.getList().contains(option))
            .collect(Collectors.toList()));
    return !invalidOptions.isEmpty();
  }

  /**
   * Builds a string indicating what went wrong while parsing the command line
   * @return String error message
   */
  private String errOut() {
    StringBuilder errMsg = new StringBuilder("Detected:");
    errMsg.append("\n\tDetected Options:");
    providedOptions.forEach(option -> errMsg
            .append("\n\t\t")
            .append(option)
            .append(invalidOptions.contains(option) ? " <-Invalid" : ""));

    errMsg.append("\n\tArguments Expected: ").append(this.minNumOfArgs)
            .append(" Found: ").append(providedArgs.size());

    providedArgs
            .forEach(arg -> errMsg
                    .append("\n\t\t").append(arg));

    errMsg.append("\n\tFrom parsed command line:");
    toParse
            .forEach(parsed -> errMsg
                    .append("\n\t\t")
                    .append(parsed));

    errMsg.append("\n");
    return errMsg.toString();
  }

  public ArrayList<String> getProvidedArgs() {
    return providedArgs;
  }

  public ArrayList<String> getProvidedOptions() {
    return providedOptions;
  }

  public ArrayList<String> getToParse() {
    return toParse;
  }

  public ArrayList<String> getClaimedArgs() {
    return claimedArgs;
  }
}

//
//  public boolean isReadMe() {
//    return providedOptions.contains("-README");
//  }
//
//  public boolean isPrint() {
//    return providedOptions.contains("-print");
//  }
//
//
//  public void invoke() {
//
//
//    if (providedOptions.contains("-print")) {
//      doPrint = true;
//    }
//
//




