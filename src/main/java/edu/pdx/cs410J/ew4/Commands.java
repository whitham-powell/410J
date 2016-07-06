package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by edub629 on 7/4/16.
 */
public class Commands {
  private boolean error = false;
  private String errorMessage = "";
  private ArrayList<Command> commands = new ArrayList<>();

  public Commands(boolean error, String errorMessage) {
    this.error = error;
    this.errorMessage = errorMessage;
  }

  public Commands(Command... commandsToAdd) {
    Collections.addAll(this.commands, commandsToAdd);
  }

  public Commands(boolean error, String errorMessage, Command... commandsToAdd) {
    this.error = error;
    this.errorMessage = errorMessage;
    Collections.addAll(this.commands, commandsToAdd);
  }

  public boolean hasError() {
    return this.error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public boolean hasOption(String optionName) {
    return this.commands.contains(new Command(optionName));
  }

  public boolean add(Command command) {
    return this.commands.add(command);
  }

  public String getOptionValue(String optionName) {
    int index = this.commands.indexOf(new Command(optionName));
    return this.commands.get(index).getArgument();
  }
}
