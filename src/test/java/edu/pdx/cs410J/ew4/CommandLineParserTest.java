package edu.pdx.cs410J.ew4;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * TODO Document
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

  @Test
  public void notEnoughArgumentsReturnsAnError() {
    String[] args = {"-option", "-another", "an argument", "notAnOption"};
    validOptions = new Options();
    validOptions.addOption("option", false, "option description");
    validOptions.addOption("another", false, "another option description");
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    assertThat(clp.parse(2, 6).hasError(), is(true));
    assertThat(clp.parse(2, 6).getErrorMessage(), containsString("Not enough arguments provided"));
  }


  @Test
  public void tooManyArgumentsProvided() {
    String[] args = {"-option", "-another", "-help", "an argument", "notAnOption"};
    validOptions = new Options();
    validOptions.addOption("option", false, "option description");
    validOptions.addOption("another", false, "another option description");
    validOptions.addOption("help", false, "prints help/usage");
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    assertThat(clp.parse(3, 1).hasError(), is(true));
  }

  @Test
  public void invalidOptionsAreDetectedAndResultInAnError() {
    String[] args = {"-option", "-another", "-help", "an argument", "notAnOption"};
    validOptions = new Options();
    validOptions.addOption("option", false, "option description");
    validOptions.addOption("another", false, "another option description");
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    Commands line = clp.parse(3, 2);
    assertThat(line.hasError(), is(true));
    assertThat(line.getErrorMessage(), containsString("help <-Invalid"));
  }

  @Test
  public void emptyArgumentListResultsInError() {
    String[] args = {};
    validOptions = new Options();
    validOptions.addOption("option", false, "option description");
    validOptions.addOption("another", false, "another option description");
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    assertThat(clp.parse(1, 2).hasError(), is(true));
    assertThat(clp.parse(1, 2).getErrorMessage(), containsString("Missing command line arguments: "));
  }

  //TODO usage printer

  @Test
  public void theParserReturnsASetOfCommandsToExecute() {
    String[] args = {"-testNoArg", "-testArg", "an argument", "notAnOption"};
    validOptions = new Options();
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "this option takes arguments");
    validOptions.addOption(optionWithArg);
    validOptions.addOption(optionWithOutArgs);
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    Commands parsed = clp.parse(2, 2);
    assertThat(parsed.hasOption("testNoArg"), is(true));
    assertThat(parsed.hasOption("testArg"), is(true));
    assertThat(parsed.getOptionValue("testArg"), containsString("an argument"));
  }

  @Test
  public void commandLineParserGetArgumentsReturnsCorrectSizedList() {
    String[] args = {"-testNoArg", "-testArg", "an argument", "notAnOption", "another non option argument"};
    validOptions = new Options();
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "this option takes arguments");
    validOptions.addOption(optionWithArg);
    validOptions.addOption(optionWithOutArgs);
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    Commands parsed = clp.parse(2, 3);
    assertThat(clp.getProvidedArgs().isEmpty(), is(false));
    assertThat(clp.getToParse().size(), equalTo(5));
    assertThat(clp.getProvidedOptions().size(), equalTo(2));
    assertThat(clp.getClaimedArgs().size(), equalTo(1));
    assertThat(clp.getProvidedArgs().size(), equalTo(2));
  }

  @Test
  public void commandLineParserCanDecideHowManyArgumentsShouldExistBasedOnNumberOfOptionsWithArguments() {
    String[] args = {"-testNoArg", "notAnOption", "another non option argument"};
    validOptions = new Options();
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "this option takes arguments");
    validOptions.addOption(optionWithArg);
    validOptions.addOption(optionWithOutArgs);
//    System.out.println(validOptions.count());
//    System.out.println(validOptions.numWArgs());
    CommandLineParser clp = new CommandLineParser(validOptions, args);
    Commands parsed = clp.parse(2, 3);
    assertThat(clp.getProvidedArgs().isEmpty(), is(false));
//    System.out.print(parsed.getErrorMessage());
    assertThat(parsed.hasError(), is(false));
    assertThat(parsed.hasOption("testNoArg"), is(true));
    assertThat(parsed.hasOption("testArg"), is(false));
  }
}
