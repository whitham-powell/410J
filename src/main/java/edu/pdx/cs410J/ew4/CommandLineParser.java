package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TODO Document Class Methods
 */


public class CommandLineParser {
//  private String usage;
//  private String readMe;

  private ArrayList<String> toParse;
  private ArrayList<String> providedOptions;
  private ArrayList<String> providedArgs;
  private ArrayList<String> invalidOptions;
  private int validNumberOfArgs;
  private Options validOptions;

  public CommandLineParser(Options validOptions, String[] args) {
    this.validOptions = validOptions;
    toParse = new ArrayList<>();
    Collections.addAll(this.toParse, args);
    this.providedOptions = new ArrayList<>();
    this.providedArgs = new ArrayList<>();
    this.invalidOptions = new ArrayList<>();
  }

  /**
   * TODO Document parse function
   *
   * @param maxNumberOfOptions
   * @param validNumberOfArgs
   * @return
   */
  public Commands parse(int maxNumberOfOptions, int validNumberOfArgs) {
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
    if (toParse.isEmpty()) {
      return new Commands(true, "Missing command line arguments: None Provided");
    }
    findInvalidOptions();
    if (!invalidOptions.isEmpty()) {
      return new Commands(true, "Invalid option detected: \n" + errOut());
    }
    if (providedArgs.size() < this.validNumberOfArgs) {
      return new Commands(true, "Not enough arguments provided: \n" + errOut());
    }
    if (providedArgs.size() > this.validNumberOfArgs)
      return new Commands(true, "Too many arguments provided: \n" + errOut());
    if (providedArgs.size() == this.validNumberOfArgs) {
      return getCommands();
    }
    // Unknown error catcher
    return new Commands(true, "unknown error");
  }

  // TODO Document getCommands()
  private Commands getCommands() {
    int i;
    Commands commands = new Commands(false, "no error");
    Option fromOptions;
    Command toCommands = null;
    for (i = 0; i < providedOptions.size(); ++i) {
      fromOptions = validOptions.getOption(providedOptions.get(i));
      if (fromOptions.hasArgs()) {
        toCommands = new Command(fromOptions.getName(), fromOptions.hasArgs(), toParse.get(i + 1));
      } else {
        toCommands = new Command(fromOptions.getName(), fromOptions.hasArgs());
      }
      commands.add(toCommands);
    }
    return commands;
  }

  // TODO document findInvalidOptions()
  private void findInvalidOptions() {
    for (String option : providedOptions) {
      if (!validOptions.getList().contains(option)) {
        invalidOptions.add(option);
      }
    }
  }

  // TODO document errOut()
  private String errOut() {
    StringBuilder errMsg = new StringBuilder("Detected:");
    errMsg.append("\n\tDetected Options:");
    providedOptions
            .forEach(option -> errMsg
                    .append("\n\t\t")
                    .append(option)
                    .append(invalidOptions.contains(option) ? " <-Invalid" : ""));

    errMsg.append("\n\tArguments Expected: ").append(this.validNumberOfArgs)
            .append("Found: ").append(providedArgs.size());

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




