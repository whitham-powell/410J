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

  public Option(String name, boolean hasArgs, String description) {
    this.name = name;
    this.hasArgs = hasArgs;
    this.description = description;
  }

  public Option(boolean hasArgs, String name) {
    this.hasArgs = hasArgs;
    this.name = name;
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
      sb.append("\t");
      sb.append(description);
      return sb.toString();
    }
  }

  public boolean hasArgs() {
    return this.hasArgs;
  }

  public String getName() {
    return name;
  }
}
