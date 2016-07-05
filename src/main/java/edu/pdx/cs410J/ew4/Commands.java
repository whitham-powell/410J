package edu.pdx.cs410J.ew4;

/**
 * Created by edub629 on 7/4/16.
 */
public class Commands {
  private boolean error = false;
  private String errorMessage = "";

  public Commands(boolean error, String errorMessage) {
    this.error = error;
    this.errorMessage = errorMessage;
  }

  public Commands() {

  }

  public boolean hasError() {
    return this.error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
