package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO Document OptionsTest
 * Created by edub629 on 7/3/16.
 */
public class OptionsTest {
  private Options emptyOptionsList;
  private Option optionWithArg;
  private Option optionWithOutArgs;


  @Before
  public void setUp() {
    optionWithOutArgs = new Option("testNoArg", false, "this test option takes no arguments");
    optionWithArg = new Option("testArg", true, "this option takes arguments");
    emptyOptionsList = new Options();
  }

  @Test
  public void canAddOptionsToTheListViaConstructor() {
    Options options = new Options(optionWithArg, optionWithOutArgs);
    assertThat(options.getList(), is(notNullValue()));
  }

  @Test
  public void getListEmptyListAddingAnOptionToOptions() {
    assertThat(emptyOptionsList.getList().isEmpty(), is(true));
  }

  @Test
  public void optionsCountReturnsCorrectNumberOfOptions() {
    Options options = new Options(optionWithArg, optionWithOutArgs, new Option("optionThree"));
    assertThat(emptyOptionsList.count(), is(0));
    assertThat(options.count(), is(3));
  }

  @Test
  public void canAddOptionsViaMethodAddOptions() {
    Options options = new Options();
    options.addOption(optionWithArg);
    options.addOption("option", true, "description of 'option'");
    assertThat(options.count(), is(2));
  }


  //TODO print out list of options in the form of a usage list

  @Test
  public void canPrintAListOfOptionsWithTheirDescription() {
    Options options = new Options(optionWithOutArgs, optionWithArg);
    assertThat(options.toString(), containsString(optionWithArg.toString()));
    assertThat(options.toString(), containsString(optionWithOutArgs.toString()));
    System.out.print(options.toString());
  }

  @Test
  public void attemptingToPrintAnEmptyOptionsListReturnsStringStatingItsEmpty() {
    assertThat(emptyOptionsList.toString(), containsString("Options list is empty"));
  }
}
