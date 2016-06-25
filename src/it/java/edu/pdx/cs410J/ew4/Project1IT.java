package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

  public static final String README = "usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n";

  /**
   * Invokes the main method of {@link Project1} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project1.class, args );
  }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void testNoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Missing command line arguments"));
  }

  @Test
  public void testOnlyReadMeCommand() {
    MainMethodResult result = invokeMain("-README");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getOut(), result.getOut().equalsIgnoreCase(README));
  }

}