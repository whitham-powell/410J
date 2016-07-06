package edu.pdx.cs410J.ew4;

import java.util.ArrayList;
import java.util.Collections;

/** TODO Document Class Methods
 * Created by edub629 on 7/3/16.
 */
class Options {
  private ArrayList<Option> validOptions;

  public Options () {
    validOptions = new ArrayList<Option>();
  }


  public Options(Option... options) {
    this.validOptions = new ArrayList<Option>();
    Collections.addAll(this.validOptions, options);
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
    return validOptions.add(new Option(option, hasArg, description));
  }

  public boolean addOption(Option option) {
    return validOptions.add(option);
  }

  public Option getOption(String s) {
    int index = validOptions.indexOf(new Option(s));
    return validOptions.get(index);
  }
}
