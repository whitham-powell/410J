package edu.pdx.cs410J.ew4;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by edub629 on 7/3/16.
 */
public class CommandLineParserTest {
  private Options validOptions;

  @Test
  public void emptyValidOptionsWillNotRaiseNullPointerException() {
    String[] args = {"-option", "-another", "-help", "an argument", "notAnOption"};
    CommandLineParser clp = new CommandLineParser(validOptions, args);
  }

  @Test
  public void canSeparateArgumentsFromOptions() {
    String[] args = {"-option", "-another", "-help", "an argument", "notAnOption"};
    validOptions = new Options();
    validOptions.addOption("option", false, "option description");
    validOptions.addOption("another", false, "another option description");
    validOptions.addOption("help", false, "prints help/usage");
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    clp.parse(3, 2);
    assertThat(clp.getProvidedArgs().contains("an argument"), is(true));
    assertThat(clp.getProvidedArgs().contains("notAnOption"), is(true));
    assertThat(clp.getProvidedOptions().contains("option"), is(true));
    assertThat(clp.getProvidedOptions().contains("another"), is(true));
    assertThat(clp.getProvidedOptions().contains("help"), is(true));
  }

  //TODO not enough arguments

  //TODO too many arguments

  //TODO invalid options

  //TODO empty argument list
}
