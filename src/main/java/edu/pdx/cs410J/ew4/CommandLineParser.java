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
  private int validNumberOfArgs;
  private Options validOptions;

  public CommandLineParser(Options validOptions, String[] args) {
    this.validOptions = validOptions;
    toParse = new ArrayList<>();
    Collections.addAll(this.toParse, args);
    this.providedOptions = new ArrayList<>();
    this.providedArgs = new ArrayList<>();
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
    if (providedOptions.retainAll(validOptions.getList())) {
      return new Commands(true, "Invalid option detected: \n" + errOut());
    }
    if (providedArgs.size() < this.validNumberOfArgs) {
      return new Commands(true, "Not enough arguments provided: \n" + errOut());
    }
    if (providedArgs.size() > this.validNumberOfArgs)
      return new Commands(true, "Too many arguments provided: \n" + errOut());
    if (providedArgs.size() == this.validNumberOfArgs) {
      return new Commands(false, "no error");
    }
    // Unknown error catcher
    return new Commands(true, "unknown error");
  }

  // TODO docutment errOut()
  private String errOut() {
    StringBuilder errMsg = new StringBuilder("Detected:\n\t\t");
    providedArgs.forEach(arg -> errMsg.append(arg).append("\n\t\t"));
    errMsg.append("From parsed command line:\n\t\t");
    toParse.forEach(parsed -> errMsg.append(parsed).append("\n\t\t"));
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




