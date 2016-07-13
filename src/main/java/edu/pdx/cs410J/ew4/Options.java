package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Manages a collection of options to look for while parsing the command line.
 *
 * @author Elijah Whitham-Powell
 */
class Options {
  private ArrayList<Option> validOptions;
  private int numWArgs = 0;
  private int numWOArgs = 0;

  /**
   * Instantiates a new Options.
   */
  public Options() {
    validOptions = new ArrayList<Option>();
  }


  /**
   * Instantiates a new Options.
   *
   * @param options the options
   */
  public Options(Option... options) {
    this.validOptions = new ArrayList<Option>();
    for (Option o : options) {
      if (o.hasArgs()) {
        ++numWArgs;
      } else {
        ++numWOArgs;
      }
      this.validOptions.add(o);
    }
  }

  /**
   * Gets list.
   *
   * @return the list
   */
  public ArrayList<String> getList() {
    ArrayList<String> listOfOptionsString = new ArrayList<>();
    if (this.validOptions.isEmpty()) {
      return listOfOptionsString;
    } else {
      listOfOptionsString.addAll(this.validOptions.stream().map(Option::getName).collect(Collectors.toList()));
      return listOfOptionsString;
    }
  }

  /**
   * Count int.
   *
   * @return the int
   */
  public int count() {
    return this.validOptions.size();
  }

  /**
   * Add option boolean.
   *
   * @param optionName  the option
   * @param hasArg      the has argName
   * @param arg         the arg
   * @param description the description  @return the boolean
   * @return the boolean
   */
  public boolean addOption(String optionName, boolean hasArg, String arg, String description) {
    if (validOptions.add(new Option(optionName, hasArg, arg, description))) {
      if (hasArg) {
        ++numWArgs;
      } else {
        ++numWOArgs;
      }
      return true;
    }
    return false;
  }

  /**
   * Add option boolean.
   *
   * @param optionName  the option name
   * @param hasArg      the has arg
   * @param description the description
   * @return the boolean
   */
  public boolean addOption(String optionName, boolean hasArg, String description) {
    if (validOptions.add(new Option(optionName, hasArg, description))) {
      if (hasArg) {
        ++numWArgs;
      } else {
        ++numWOArgs;
      }
      return true;
    }
    return false;
  }

  /**
   * Add option boolean.
   *
   * @param option the option to be added.
   * @return the boolean
   */
  public boolean addOption(Option option) {
    if (validOptions.add(option)) {
      if (option.hasArgs()) {
        ++numWArgs;
      } else {
        ++numWOArgs;
      }
      return true;
    }
    return false;
  }

  /**
   * Gets option.
   *
   * @param s the option name to get.
   * @return the option
   */
  public Option getOption(String s) {
    int index = validOptions.indexOf(new Option(s));
    if (index != -1)
      return validOptions.get(index);
    else
      return null;
  }

  @Override
  public String toString() {
    if (validOptions.isEmpty()) {
      return "Options list is empty.\n";
    }
    StringBuilder sb = new StringBuilder(" options are (may appear in any order):\n");
    for (Option option : validOptions) {
      sb.append("   ").append(option.toString()).append("\n");
    }
    return sb.toString();
  }

  /**
   * Number of options with arguments.
   *
   * @return the int
   */
  public int numWArgs() {
    return this.numWArgs;
  }

  /**
   * Number of options without arguments.
   *
   * @return the int
   */
  public int numWOArgs() {
    return this.numWOArgs;
  }

  /**
   * Inner class of <code>Options</code> manages the information for a single <code>Option</code>.
   * Each option has a name, boolean of whether it takes an argument or not and a description.
   *
   * @author Elijah Whitham-Powell
   */
  public static class Option {
    private String name;
    private boolean hasArgs;
    private String description;
    private String argName;

    /**
     * Instantiates a new Option.
     */
    public Option() {
      this.name = null;
      this.hasArgs = false;
      this.description = null;
      this.argName = null;
    }

    /**
     * Instantiates a new Option.
     *
     * @param optionName the option name
     */
    public Option(String optionName) {
      this.name = optionName;
    }

    /**
     * Instantiates a new Option.
     *
     * @param optionName  the option name
     * @param hasArgs     the has args
     * @param description the description
     */
    public Option(String optionName, boolean hasArgs, String description) {
      this.name = optionName;
      this.hasArgs = hasArgs;
      this.description = description;
    }

    /**
     * Instantiates a new Option.
     *
     * @param name        the name
     * @param hasArgs     the has args
     * @param argName     the argument name
     * @param description the description
     */
    public Option(String name, boolean hasArgs, String argName, String description) {
      this.name = name;
      this.hasArgs = hasArgs;
      this.argName = argName;
      this.description = description;
    }

    /**
     * Instantiates a new Option.
     *
     * @param optionName the option name
     * @param hasArgs    the has args
     */
    public Option(String optionName, boolean hasArgs) {
      this.name = optionName;
      this.hasArgs = hasArgs;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Option option = (Option) o;
      return Objects.equals(name, option.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }

    @Override
    public String toString() {
      if (name == null || description == null) {
        return null;
      } else {
        StringBuilder sb = new StringBuilder("-");
        sb.append(name);
        if (hasArgs) {
          sb.append(" <").append(this.argName).append(">\t");
        } else {
          sb.append("\t\t\t");
        }
        sb.append("\t");
        sb.append(description);
        return sb.toString();
      }
    }

    /**
     * Has arguments boolean.
     *
     * @return the boolean
     */
    public boolean hasArgs() {
      return this.hasArgs;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
      return this.name;
    }

    /**
     * Gets argument name.
     *
     * @return the argName
     */
    public String getArgName() {
      return this.argName;
    }
  }
}
