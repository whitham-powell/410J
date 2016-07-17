package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The <code>Commands</code> class manages commands parsed from the command line and any errors that may have been
 * encountered. Used in conjunction with:
 * {@link CommandLineParser}
 * {@link Options}
 * {@link Command}
 *
 * @author Elijah Whitham-Powell
 */
public class Commands {
  private boolean error = false;
  private String errorMessage = "";
  private ArrayList<Command> commands = new ArrayList<>();

  /**
   * Instantiates a new Commands.
   *
   * @param error        the error.
   * @param errorMessage the error message.
   */
  public Commands(boolean error, String errorMessage) {
    this.error = error;
    this.errorMessage = errorMessage;
  }

  /**
   * Instantiates a new Commands.
   *
   * @param commandsToAdd the commands to add
   */
  public Commands(Command... commandsToAdd) {
    Collections.addAll(this.commands, commandsToAdd);
  }

  /**
   * Instantiates a new Commands.
   *
   * @param error         the error
   * @param errorMessage  the error message
   * @param commandsToAdd the commands to add
   */
  public Commands(boolean error, String errorMessage, Command... commandsToAdd) {
    this.error = error;
    this.errorMessage = errorMessage;
    Collections.addAll(this.commands, commandsToAdd);
  }

  /**
   * Has error boolean.
   *
   * @return the boolean
   */
  public boolean hasError() {
    return this.error;
  }

  /**
   * Gets error message.
   *
   * @return the error message
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Has option boolean.
   *
   * @param optionName the option name
   * @return the boolean
   */
  public boolean hasOption(String optionName) {
    return this.commands.contains(new Command(optionName));
  }

  /**
   * Add boolean.
   *
   * @param command the command
   * @return the boolean
   */
  public boolean add(Command command) {
    return this.commands.add(command);
  }

  /**
   * Gets option value.
   *
   * @param optionName the option name
   * @return the option value
   */
  public String getOptionValue(String optionName) {
    int index = this.commands.indexOf(new Command(optionName));
    return this.commands.get(index).getArgument();
  }

  /**
   * Inner class of <code>Commands</code> extends {@link edu.pdx.cs410J.ew4.Options.Option}
   *
   * @author Elijah Whitham-Powell
   */
  public static class Command extends Options.Option {
    private String argument;

    /**
     * Instantiates a new Command.
     *
     * @param optionName  the option name
     * @param hasArgs     the has args
     * @param description the description
     * @param argument    the argument
     */
    public Command(String optionName, boolean hasArgs, String description, String argument) {
      super(optionName, hasArgs, description);
      this.argument = argument;
    }

    /**
     * Instantiates a new Command.
     *
     * @param optionName the option name
     * @param hasArgs    the has args
     * @param argument   the argument
     */
    public Command(String optionName, boolean hasArgs, String argument) {
      super(optionName, hasArgs);
      this.argument = argument;
    }

    /**
     * Instantiates a new Command.
     *
     * @param optionName the option name
     * @param hasArgs    the has args
     */
    public Command(String optionName, boolean hasArgs) {
      super(optionName, hasArgs);
    }

    /**
     * Instantiates a new Command.
     *
     * @param optionName the option name
     * @param argument   the argument
     */
    public Command(String optionName, String argument) {
      super(optionName);
      this.argument = argument;
    }

    /**
     * Instantiates a new Command.
     *
     * @param optionName the option name
     */
    public Command(String optionName) {
      super(optionName);
    }

    /**
     * Instantiates a new Command.
     */
    public Command() {

    }

    /**
     * Gets argument.
     *
     * @return the argument
     */
    public String getArgument() {
      if (hasArgs()) {
        return argument;
      } else {
        return null;
      }
    }
  }
}
