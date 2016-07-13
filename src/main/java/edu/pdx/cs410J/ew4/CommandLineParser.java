package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * This handle the parsing of the command line and manages them information gathered in
 * <code>ArrayList</code> collections.
 *
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
  private int argsEndedHere;
  private int optionsEndedHere;

  /**
   * Instantiates a new Command line parser.
   *
   * @param validOptions the valid options
   * @param args         the args
   */
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
   *
   * @param maxNumberOfOptions controls the loop to find options in the command line.
   * @param maxNumberOfArgs    controls the loop to find arguments in the command line.
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
        optionsEndedHere = i;
        break;
      }
    }
    for (; /*i < maxNumberOfArgs &&*/ i < argsLength; i++) {
      providedArgs.add(toParse.get(i));
      argsEndedHere = i;
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
        theCommands = getCommands();
        if (theCommands.hasError()) {
          return theCommands;
        }
        if (providedArgs.size() + claimedArgs.size() < this.minNumOfArgs) {
          theCommands = new Commands(true, "Not enough arguments provided: \n" + errOut());
        }
        if (providedArgs.size() + claimedArgs.size() > this.maxNumOfArgs) {
          theCommands = new Commands(true, "Too many arguments provided: \n" + errOut());
        } else if (providedArgs.size() + claimedArgs.size() >= this.minNumOfArgs) {
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
    int i = 0;
    Commands commands = new Commands(false, "no error");
    Options.Option fromOptions;
    Commands.Command toCommands;
    for (; i < providedOptions.size(); ++i) {
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
    if ((providedArgs.size() + providedOptions.size() + claimedArgs.size()) < toParse.size()) {
      StringBuilder sb = new StringBuilder();
      List<String> extraCmdLineStrings = toParse.subList(0, toParse.size());
      ListIterator<String> li = extraCmdLineStrings.listIterator(argsEndedHere);
      while (li.hasNext()) {
        sb.append(li.next());
      }
      return new Commands(true, "Erroneous option or argument at end of command line\n" + sb.toString());
    }
    return commands;
  }

  /**
   * Helper method to traverse the providedOptions looking for invalid options.
   *
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
   *
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

  /**
   * Gets provided args.
   *
   * @return the provided args
   */
  public ArrayList<String> getProvidedArgs() {
    return providedArgs;
  }

  /**
   * Gets provided options.
   *
   * @return the provided options
   */
  public ArrayList<String> getProvidedOptions() {
    return providedOptions;
  }

  /**
   * Gets to parse.
   *
   * @return the to parse
   */
  public ArrayList<String> getToParse() {
    return toParse;
  }

  /**
   * Gets claimed args.
   *
   * @return the claimed args
   */
  public ArrayList<String> getClaimedArgs() {
    return claimedArgs;
  }
}






