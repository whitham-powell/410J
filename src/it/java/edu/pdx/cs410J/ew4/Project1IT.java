package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

  private static final String README = "usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n" +
          "\targs are (in this order): \n" +
          "\t\towner The person whose owns the appt book\n" +
          "\t\tdescription A description of the appointment\n" +
          "\t\tbeginTime When the appt begins (24-hour time)\n" +
          "\t\tendTime When the appt ends (24-hour time)\n" +
          "\toptions are (options may appear in any order):\n" +
          "\t\t-print Prints a description of the new appointment\n" +
          "\t\t-README Prints a README for this project and exits\n" +
          "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n";

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
  public void testNoCommandLineArgumentsPrintsErrorFollowedByUsage() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Missing command line arguments"));
    assertThat(result.getOut(), containsString(README));
  }

  @Test
  public void testOnlyReadMeCommand() {
    MainMethodResult result = invokeMain("-README");
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getOut(), containsString(README));
  }

  @Test
  public void mainClassCanDetectCorrectNumberOfArguments() {
  }

  @Test
  public void testNotEnoughCommandLineArgumentsIssuesAnErrorAndDisplaysReadme() {
    MainMethodResult result = invokeMain("Steve", "06/29/2016", "14:00", "06/29/2016", "16:00");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Missing command line arguments: 5 provided"));
  }

  @Test
  public void testValidCommandLineArgumentListLengthExitIsZero() {
    MainMethodResult result = invokeMain("Steve", "Test Description", "06/29/2016", "14:00", "06/29/2016", "16:00");
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void mainClassCanPrintANewlyAddedAppointmentWithThePRINTCommand() {
    String[] testArgs = {"-print", "Steve", "Test Description", "06/29/2016", "14:00", "06/29/2016", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getOut(), containsString("Steve"));
    assertThat(result.getOut(), containsString("Test Description"));
    assertThat(result.getOut(), containsString("06/29/2016 14:00"));
    assertThat(result.getOut(), containsString("06/29/2016 16:00"));
  }

  @Test
  public void mainClassRejectsBadlyFormattedDates () {
    String[] testArgs = {"-print", "Steve", "Test Description", "6/29/16", "14:00", "06/29/16", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Bad formatting: "));
    assertThat(result.getErr(), containsString(" Date - beginTime"));
    assertThat(result.getErr(), containsString(" Date - endTime"));
    assertThat(result.getErr(), CoreMatchers.not(containsString(" Time")));
  }

  @Test
  public void mainClassRejectsBadlyFormattedBeginDate() {
    String[] testArgs = {"-print", "Steve", "Test Description", "06/29/16", "14:00", "6/29/2016", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Bad formatting:"));
    assertThat(result.getErr(), containsString("Date - beginTime"));
    assertThat(result.getErr(), CoreMatchers.not(containsString(" endTime")));
  }

  @Test
  public void mainClassRejectsBadlyFormattedEndDate() {
    String[] testArgs = {"-print", "Steve", "Test Description", "6/29/2016", "14:00", "06/29/16", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Bad formatting:"));
    assertThat(result.getErr(), containsString(" Date - endTime"));
    assertThat(result.getErr(), CoreMatchers.not(containsString(" beginTime")));
  }

  @Test
  public void mainClassRejectsBadlyFormattedTimes() {
    String[] testArgs = {"-print", "Steve", "Test Description", "06/29/2016", "400", "06/29/2016", "1600"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Bad formatting:"));
    assertThat(result.getErr(), containsString(" Time - beginTime"));
    assertThat(result.getErr(), containsString(" Time - endTime"));
    assertThat(result.getErr(), CoreMatchers.not(containsString(" Date")));
  }

  @Test
  public void mainClassRejectsBadlyFormattedEndTime() {
    String[] testArgs = {"-print", "Steve", "Test Description", "06/29/2016", "4:00", "06/29/2016", "1600"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Bad formatting:"));
    assertThat(result.getErr(), containsString(" Time - endTime"));
    assertThat(result.getErr(), CoreMatchers.not(containsString(" Date")));
  }

  @Test
  public void mainClassRejectsBadlyFormattedBeginTime() {
    String[] testArgs = {"-print", "Steve", "Test Description", "06/29/2016", "400", "06/29/2016", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Bad formatting:"));
    assertThat(result.getErr(), containsString(" Time - beginTime"));
    assertThat(result.getErr(), CoreMatchers.not(containsString(" Date")));
  }

  @Test
  public void tooManyArgumentsProvidedGetsRejected() {
    String[] testArgs = {"Steve", "Test", "Description", "06/29/2016", "400", "06/29/2016", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getErr(), containsString("Too many command line arguments: 7 provided: "));
  }


//  @Test
//  public void mainClassDetectsMultipleOptionsArguments() {
//    String[] testArgs = {"-print", "-README", "Steve", "Test Description", "06/29/2016", "14:00", "06/29/2016", "16:00"};
//    MainMethodResult result = invokeMain(testArgs);
//    assertThat(result.getExitCode(), equalTo(0));
//    assertThat(result.getOut(), containsString("Steve"));
//    assertThat(result.getOut(), containsString("Test Description"));
//    assertThat(result.getOut(), containsString("06/29/2016 14:00"));
//    assertThat(result.getOut(), containsString("06/29/2016 16:00"));
//    assertThat(result.getOut(), containsString(README));
//  }

//  Unnecessary Test
//  @Test
//  public void theNumberOfProvidedOptionsAndArgumentsSumsToSizeOfArgList () {
//    String[] testArgs = {"-print", "-README", "Steve", "Test Description", "06/29/2016", "14:00", "06/29/2016", "16:00"};
//    MainMethodResult result = invokeMain(testArgs);
//    assertThat(result.getExitCode(), equalTo(0));
//    assertThat(result.getOut(), containsString("Steve"));
//    assertThat(result.getOut(), containsString("Test Description"));
//    assertThat(result.getOut(), containsString("06/29/2016 14:00"));
//    assertThat(result.getOut(), containsString("06/29/2016 16:00"));
//    assertThat(result.getOut(), containsString(README));
//  }


}



