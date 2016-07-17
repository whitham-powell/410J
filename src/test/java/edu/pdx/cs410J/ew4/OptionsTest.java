package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for {@link Options} class.
 */
public class OptionsTest {
  private Options emptyOptionsList;
  private Options.Option optionWithArg;
  private Options.Option optionWithOutArgs;


  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    optionWithArg = new Options.Option("testArg", true, "this option takes arguments");
    emptyOptionsList = new Options();
  }

  /**
   * Can add options to the list via constructor.
   */
  @Test
  public void canAddOptionsToTheListViaConstructor() {
    Options options = new Options(optionWithArg, optionWithOutArgs);
    assertThat(options.getList(), is(notNullValue()));
  }

  /**
   * Gets list empty list adding an option to options.
   */
  @Test
  public void getListEmptyListAddingAnOptionToOptions() {
    assertThat(emptyOptionsList.getList().isEmpty(), is(true));
  }

  /**
   * Options count returns correct number of options.
   */
  @Test
  public void optionsCountReturnsCorrectNumberOfOptions() {
    Options options = new Options(optionWithArg, optionWithOutArgs, new Options.Option("optionThree"));
    assertThat(emptyOptionsList.count(), is(0));
    assertThat(options.count(), is(3));
  }

  /**
   * Can add options via method add options.
   */
  @Test
  public void canAddOptionsViaMethodAddOptions() {
    Options options = new Options();
    options.addOption(optionWithArg);
    options.addOption("option", true, "description of 'option'");
    assertThat(options.count(), is(2));
  }


  /**
   * Can print a list of options with their description.
   */
  @Test
  public void canPrintAListOfOptionsWithTheirDescription() {
    Options options = new Options(optionWithOutArgs, optionWithArg);
    assertThat(options.toString(), containsString(optionWithArg.toString()));
    assertThat(options.toString(), containsString(optionWithOutArgs.toString()));
//    System.out.print(options.toString());
  }

  /**
   * Attempting to print an empty options list returns string stating its empty.
   */
  @Test
  public void attemptingToPrintAnEmptyOptionsListReturnsStringStatingItsEmpty() {
    assertThat(emptyOptionsList.toString(), containsString("Options list is empty"));
  }

  /**
   * Can tell how many options have arguments.
   */
  @Test
  public void canTellHowManyOptionsHaveArguments() {
    Options options = new Options(optionWithOutArgs, optionWithArg);
    Options validOptions = new Options();
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "", "this option takes arguments");
    validOptions.addOption(optionWithArg);
    validOptions.addOption(optionWithOutArgs);
    assertThat(emptyOptionsList.numWArgs(), equalTo(0));
    assertThat(emptyOptionsList.numWOArgs(), equalTo(0));
    assertThat(options.numWArgs(), equalTo(1));
    assertThat(options.numWOArgs(), equalTo(1));
    assertThat(validOptions.numWArgs(), equalTo(1));
    assertThat(validOptions.numWOArgs(), equalTo(1));
  }

}
