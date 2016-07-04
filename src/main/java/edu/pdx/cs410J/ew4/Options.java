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

  public Collection<Option> getList() {
    if (validOptions.isEmpty()) {
      return null;
    } else {
      return validOptions;
    }
  }


  public int count() {
    return this.validOptions.size();
  }
}
