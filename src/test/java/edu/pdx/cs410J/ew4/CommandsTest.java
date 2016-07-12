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
    Options.Option optionWithOutArgs = new Options.Option("testNoArg", false, "this test option takes no arguments");
    Options.Option optionWithArg = new Options.Option("testArg", true, "this option takes arguments");
    Options emptyOptionsList = new Options();
    Commands commands;
    Commands.Command c1 = new Commands.Command(optionWithArg.getName(), optionWithArg.hasArgs());
    Commands.Command c2 = new Commands.Command(optionWithOutArgs.getName(), optionWithOutArgs.hasArgs());
    commands = new Commands(c1, c2);
    assertThat(commands.hasOption("testNoArg"), is(true));
  }

  //TODO if option has an argument what is it?

}