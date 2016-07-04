package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
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
  public void getListReturnsNullWithOutAddingAnOptionToOptions() {
    assertThat(emptyOptionsList.getList(), is(nullValue()));
  }

  @Test
  public void optionsCountReturnsCorrectNumberOfOptions() {
    Options options = new Options(optionWithArg, optionWithOutArgs, new Option("optionThree"));
    assertThat(emptyOptionsList.count(), is(0));
    assertThat(options.count(), is(3));
  }
}