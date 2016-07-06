package edu.pdx.cs410J.ew4;

/**
 * Created by edub629 on 7/4/16.
 */
public class Command extends Option {
  private String argument;

  public Command(String optionName, boolean hasArgs, String description, String argument) {
    super(optionName, hasArgs, description);
    this.argument = argument;
  }

  public Command(String optionName, boolean hasArgs, String argument) {
    super(optionName, hasArgs);
    this.argument = argument;
  }

  public Command(String optionName, boolean hasArgs) {
    super(optionName, hasArgs);
  }

  public Command(String optionName, String argument) {
    super(optionName);
    this.argument = argument;
  }

  public Command(String optionName) {
    super(optionName);
  }

  public Command() {

  }


  public String getArgument() {
    if (hasArgs()) {
      return argument;
    } else {
      return null;
    }
  }
}
