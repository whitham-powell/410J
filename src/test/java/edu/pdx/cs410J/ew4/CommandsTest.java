package edu.pdx.cs410J.ew4;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by edub629 on 7/5/16.
 */
public class CommandsTest {

  @Test
  public void commandsClassCanBeBuiltUsingVarArgOptions() {
    Option optionWithOutArgs = new Option("testNoArg", false, "this test option takes no arguments");
    Option optionWithArg = new Option("testArg", true, "this option takes arguments");
    Options emptyOptionsList = new Options();
    Commands commands;
    Command c1 = new Command(optionWithArg.getName(), optionWithArg.hasArgs());
    Command c2 = new Command(optionWithOutArgs.getName(), optionWithOutArgs.hasArgs());
    commands = new Commands(c1, c2);
    assertThat(commands.hasOption("testNoArg"), is(true));
  }

  //TODO if option has an argument what is it?

}