package edu.pdx.cs410J.ew4;

import java.util.Objects;

/** TODO Document Class Methods
 * Created by edub629 on 7/3/16.
 */
public class Option {
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
    if(name == null || description == null) {
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
