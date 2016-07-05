package edu.pdx.cs410J.ew4;

/**
 * Created by edub629 on 7/4/16.
 */
public class Command extends Option {
  private String argument;

  public Command(String name, boolean hasArgs, String description, String argument) {
    super(name, hasArgs, description);
    this.argument = argument;
  }

  public Command(boolean hasArgs, String name, String argument) {
    super(hasArgs, name);
    this.argument = argument;
  }

  public Command(String optionName, String argument) {
    super(optionName);
    this.argument = argument;
  }

  public Command(String argument) {
    this.argument = argument;
  }

  public String getArgument() {
    if (hasArgs()) {
      return argument;
    } else {
      return null;
    }
  }
}
