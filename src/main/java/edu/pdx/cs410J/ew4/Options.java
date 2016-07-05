package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/** TODO Document Class Methods
 * Created by edub629 on 7/3/16.
 */
class Options {
  private Collection<Option> validOptions;

  public Options () {
    validOptions = new ArrayList<Option>();
  }


  public Options(Option... options) {
    validOptions = new ArrayList<Option>();
    Collections.addAll(this.validOptions, options);
  }

  public Collection<String> getList() {
    Collection<String> listOfOptionsString = new ArrayList<>();
    if (validOptions.isEmpty()) {
      return listOfOptionsString;
    } else {
      validOptions.forEach(validOption -> listOfOptionsString.add(validOption.getName()));
      return listOfOptionsString;
    }
  }

  public int count() {
    return this.validOptions.size();
  }

  public boolean addOption(String option, boolean hasArg, String description) {
    return validOptions.add(new Option(option, hasArg, description));
  }

  public boolean addOption(Option option) {
    return validOptions.add(option);
  }

}
