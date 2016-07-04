package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO Document OptionTest
 * Created by edub629 on 7/3/16.
 */
public class OptionTest {

  private Option optionWithOutArgs;
  private Option optionWithArgs;

  @Before
  public void setUp() {
    optionWithArgs = new Option("testArg", true, "this option takes an argument");
    optionWithOutArgs = new Option("testNoArg", false, "this test option takes no arguments");
  }

  @Test
  public void optionCreatedViaConstructorWithoutArgumentsToStringMethodReturnsNull() {
    Option option = new Option();
    assertThat(option.toString(), is(nullValue()));
  }

  @Test
  public void optionCreatedViaConstructorWithArgumentsToStringMethodReturnsNotNull() {
    Option option = new Option("test", true, "this test arguments description");
    assertThat(option.toString(), is(notNullValue()));
  }

  @Test
  public void optionCreatedCanMatchViaOverridesEqualsCommand() {
    Option optionWithArgs = new Option("test", true, "this test option takes an argument");
    assertThat(optionWithArgs.equals(new Option("test", false, "this option takes no arguments but should match by name")), is(true));
  }

  @Test
  public void aCollectionOfObjectsCanUseContainsToFindOptionByNameOnly() {
    ArrayList<Option> ArrayListOfOptions = new ArrayList<>();
    ArrayListOfOptions.add(optionWithOutArgs);
    assertThat(ArrayListOfOptions.contains(optionWithArgs), is(false));
    assertThat(ArrayListOfOptions.contains(optionWithOutArgs), is(true));
  }

  @Test
  public void toStringReturnsOptionNameFollowedByTabFollowedByDescription() {
    assertThat(optionWithArgs.toString(), containsString("testArg\t"));
    assertThat(optionWithOutArgs.toString(), containsString("testNoArg\t"));
  }

  @Test
  public void optionCreatedWithOnlyANameHasANullToStringReturn() {
    Option option = new Option("justAName");
    assertThat(option.toString(), is(nullValue()));
  }

  @Test
  public void hashOfAnOptionReturnsTheSameAsAHashOfTheNameAsAString() {
    Option optionOne = new Option("testFoo");
    Option optionTwo = new Option("testFoo");
    assertThat(optionTwo.hashCode(), is(optionOne.hashCode()));
  }

  @Test
  public void optionHasArgsReturnsTrueOrFalse() {
    assertThat(optionWithOutArgs.hasArgs(), is(false));
    assertThat(optionWithArgs.hasArgs(), is(true));
  }
}