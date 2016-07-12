package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Objects;

/**
 * TODO Document Class Methods of Options
 * Created by edub629 on 7/3/16.
 */
class Options {
  private ArrayList<Option> validOptions;
  private int numWArgs = 0;
  private int numWOArgs = 0;

  public Options() {
    validOptions = new ArrayList<Option>();
  }


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
//    Collections.addAll(this.validOptions, options);
  }

  public ArrayList<String> getList() {
    ArrayList<String> listOfOptionsString = new ArrayList<>();
    if (this.validOptions.isEmpty()) {
      return listOfOptionsString;
    } else {
      for (Option thatValid : this.validOptions) {
        listOfOptionsString.add(thatValid.getName());
      }
      return listOfOptionsString;
    }
  }

  public int count() {
    return this.validOptions.size();
  }

  public boolean addOption(String option, boolean hasArg, String description) {
    if (validOptions.add(new Option(option, hasArg, description))) {
      if (hasArg) {
        ++numWArgs;
      } else {
        ++numWOArgs;
      }
      return true;
    }
    return false;
  }

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

  public int numWArgs() {
    return this.numWArgs;
  }

  public int numWOArgs() {
    return this.numWOArgs;
  }

  /**
   * TODO Document Class Methods of Option
   * Created by edub629 on 7/3/16.
   */
  public static class Option {
    private String name;
    private boolean hasArgs;
    private String description;

    public Option() {
      this.name = null;
      this.hasArgs = false;
      this.description = null;
    }

    public Option(String optionName) {
      this.name = optionName;
    }

    public Option(String optionName, boolean hasArgs, String description) {
      this.name = optionName;
      this.hasArgs = hasArgs;
      this.description = description;
    }

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
          sb.append("\t< val >");
        } else {
          sb.append("\t\t\t");
        }
        sb.append("\t");
        sb.append(description);
        return sb.toString();
      }
    }

    public boolean hasArgs() {
      return this.hasArgs;
    }

    public String getName() {
      return this.name;
    }
  }
}
