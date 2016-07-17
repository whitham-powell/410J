package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link edu.pdx.cs410J.ew4.Options.Option} class.
 */
public class OptionTest {

  private Options.Option optionWithOutArgs;
  private Options.Option optionWithArgs;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    optionWithArgs = new Options.Option("testArg", true, "this option takes an argument");
    optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
  }

  /**
   * Option created via constructor without arguments to string method returns null.
   */
  @Test
  public void optionCreatedViaConstructorWithoutArgumentsToStringMethodReturnsNull() {
    Options.Option option = new Options.Option();
    assertThat(option.toString(), is(nullValue()));
  }

  /**
   * Option created via constructor with arguments to string method returns not null.
   */
  @Test
  public void optionCreatedViaConstructorWithArgumentsToStringMethodReturnsNotNull() {
    Options.Option option = new Options.Option("test", true, "this test arguments description");
    assertThat(option.toString(), is(notNullValue()));
  }

  /**
   * Option created can match via overrides equals command.
   */
  @Test
  public void optionCreatedCanMatchViaOverridesEqualsCommand() {
    Options.Option optionWithArgs = new Options.Option("test", true, "this test option takes an argument");
    assertThat(optionWithArgs.equals(new Options.Option("test", false, "this option takes no arguments but should match by name")), is(true));
  }

  /**
   * A collection of objects can use contains to find option by name only.
   */
  @Test
  public void aCollectionOfObjectsCanUseContainsToFindOptionByNameOnly() {
    ArrayList<Options.Option> ArrayListOfOptions = new ArrayList<>();
    ArrayListOfOptions.add(optionWithOutArgs);
    assertThat(ArrayListOfOptions.contains(optionWithArgs), is(false));
    assertThat(ArrayListOfOptions.contains(optionWithOutArgs), is(true));
  }

  /**
   * To string returns option name followed by tab followed by description.
   */
  @Test
  public void toStringReturnsOptionNameFollowedByTabFollowedByDescription() {
    assertThat(optionWithArgs.toString(), containsString("testArg\t"));
    assertThat(optionWithOutArgs.toString(), containsString("testNoArg\t"));
  }

  /**
   * Option created with only a name has a null to string return.
   */
  @Test
  public void optionCreatedWithOnlyANameHasANullToStringReturn() {
    Options.Option option = new Options.Option("justAName");
    assertThat(option.toString(), is(nullValue()));
  }

  /**
   * Hash of an option returns the same as a hash of the name as a string.
   */
  @Test
  public void hashOfAnOptionReturnsTheSameAsAHashOfTheNameAsAString() {
    Options.Option optionOne = new Options.Option("testFoo");
    Options.Option optionTwo = new Options.Option("testFoo");
    assertThat(optionTwo.hashCode(), is(optionOne.hashCode()));
  }

  /**
   * Option has args returns true or false.
   */
  @Test
  public void optionHasArgsReturnsTrueOrFalse() {
    assertThat(optionWithOutArgs.hasArgs(), is(false));
    assertThat(optionWithArgs.hasArgs(), is(true));
  }

  /**
   * Can set a argument name for options that take arguments.
   */
  @Test
  public void canSetAArgumentNameForOptionsThatTakeArguments() {
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "arg", "this option takes arguments");
    assertThat(optionWithArg.getArgName(), is("arg"));
    assertThat(optionWithOutArgs.getArgName(), is(nullValue()));
  }
}