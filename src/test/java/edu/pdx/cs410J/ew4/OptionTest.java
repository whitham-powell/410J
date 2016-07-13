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

  @Before
  public void setUp() {
    optionWithArgs = new Options.Option("testArg", true, "this option takes an argument");
    optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
  }

  @Test
  public void optionCreatedViaConstructorWithoutArgumentsToStringMethodReturnsNull() {
    Options.Option option = new Options.Option();
    assertThat(option.toString(), is(nullValue()));
  }

  @Test
  public void optionCreatedViaConstructorWithArgumentsToStringMethodReturnsNotNull() {
    Options.Option option = new Options.Option("test", true, "this test arguments description");
    assertThat(option.toString(), is(notNullValue()));
  }

  @Test
  public void optionCreatedCanMatchViaOverridesEqualsCommand() {
    Options.Option optionWithArgs = new Options.Option("test", true, "this test option takes an argument");
    assertThat(optionWithArgs.equals(new Options.Option("test", false, "this option takes no arguments but should match by name")), is(true));
  }

  @Test
  public void aCollectionOfObjectsCanUseContainsToFindOptionByNameOnly() {
    ArrayList<Options.Option> ArrayListOfOptions = new ArrayList<>();
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
    Options.Option option = new Options.Option("justAName");
    assertThat(option.toString(), is(nullValue()));
  }

  @Test
  public void hashOfAnOptionReturnsTheSameAsAHashOfTheNameAsAString() {
    Options.Option optionOne = new Options.Option("testFoo");
    Options.Option optionTwo = new Options.Option("testFoo");
    assertThat(optionTwo.hashCode(), is(optionOne.hashCode()));
  }

  @Test
  public void optionHasArgsReturnsTrueOrFalse() {
    assertThat(optionWithOutArgs.hasArgs(), is(false));
    assertThat(optionWithArgs.hasArgs(), is(true));
  }

  @Test
  public void canSetAArgumentNameForOptionsThatTakeArguments() {
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "arg", "this option takes arguments");
    assertThat(optionWithArg.getArgName(), is("arg"));
    assertThat(optionWithOutArgs.getArgName(), is(nullValue()));
  }
}