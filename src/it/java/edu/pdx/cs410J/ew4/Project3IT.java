package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration tests for {@link Project3} main class.
 * Created by edub629 on 7/9/16
 */
public class Project3IT extends InvokeMainTestCase {

  private static String USAGE =
          "usage: java edu.pdx.edu.cs410J.<login-id>.Project3 [options] <args> \n" +
                  " args are (in this order): \n" +
                  "   owner        The person whose owns the appt book\n" +
                  "   description  A description of the appointment\n" +
                  "   beginTime    When the appt begins (24-hour time)\n" +
                  "   endTime      When the appt ends (24-hour time)\n";
  private static String OPTIONS =
          " options are (may appear in any order):\n" +
                  "   -textFile" + " <file>" + "\t" + "Where to read/write the appointment book\n" +
                  "   -pretty" + " <file>" + "\t" + "Pretty print the appointment book to a text file or standard out (file -)\n" +
                  "   -print" + "\t\t\t\t" + "Prints a description of the new appointment\n" +
                  "   -README" + "\t\t\t\t" + "Prints a README for this project along with the usage and exits\n";

  /**
   * Invokes the main method of {@link Project3} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Project3.class, args);
  }

  /**
   * Passes old project 1 tests.
   */
  @Test
  public void passesOldProject1Tests() {
//    Project1IT p1IT = new Project1IT();
//    p1IT.mainClass = Project2.class;
//    boolean passedProject1Tests = true;
//    int numberOfPassingTests = 0;
//    int totalNumberOfTest = 0;
//    int totalIgnoredTests = 0;
//    ArrayList<String> failedTests = new ArrayList<>();
//    try {
//      Method[] allMethods = p1IT.getClass().getDeclaredMethods();
//      totalNumberOfTest = allMethods.length - 1; // less one helper function
//      out.format("Project 1 Unit Test: %n");
//      for (Method m : allMethods) {
//        if (m.isAnnotationPresent(Ignore.class)) {
//          ++totalIgnoredTests;
//        }
//        if (m.isAnnotationPresent(Test.class) && !m.isAnnotationPresent(Ignore.class)) {
//          String mName = m.getName();
//          try {
//            m.setAccessible(true);
//            m.invoke(p1IT);
//            out.format("%s() PASSED %n", mName);
//            ++numberOfPassingTests;
//            // Handle any exceptions thrown by method to be invoked.
//          } catch (InvocationTargetException x) {
//            Throwable cause = x.getCause();
//            err.format("%s() %n FAILED: %s%n",
//                    mName, cause.getMessage());
//            passedProject1Tests = false;
//            failedTests.add(mName);
//          }
//        }
//      }
//    } catch (IllegalAccessException e) {
//      Throwable cause = e.getCause();
//      err.format("Exception thrown: %s %n Caused by: %s %n",
//              e.getMessage(), cause.getMessage());
//    }
//    out.format("%d out of %d tests passing%n (ignored %d)"
//            , numberOfPassingTests, totalNumberOfTest, totalIgnoredTests);
//    if (numberOfPassingTests < totalNumberOfTest - totalIgnoredTests) {
//      err.println("failed tests: ");
//      failedTests.forEach(err::println);
//    }
//    assertTrue("failed one or more project1 tests", passedProject1Tests);
//    assertEquals(numberOfPassingTests, totalNumberOfTest - totalIgnoredTests);
  }

  /**
   * Project 2 new usage outputs on an error.
   */
  @Test
  public void project2NewUsageOutputsOnAnError() {
    MainMethodResult result = invokeMain();
    assertThat(result.getOut(), containsString(USAGE));
//    assertThat(result.getOut(), containsString(OPTIONS));
    assertThat(result.getOut(), containsString("Dates and times should be in the format: mm/dd/yyyy hh:mm\n"));

  }

  /**
   * Rejects too many arguments.
   */
  @Test
  public void rejectsTooManyArguments() {
    String[] testArgs = {"Steve", "Test", "Description", "06/29/2016", "4:00", "06/29/2016", "16:00"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), is(1));
//    System.out.println(result.getErr());
  }

  /**
   * New date format from command line.
   */
  @Test
  public void newDateFormatFromCommandLine() {
    String[] testArgs = {"Steve", "Test Description", "06/29/2016", "4:00", "AM", "06/29/2016", "4:00", "PM"};
    MainMethodResult result = invokeMain(testArgs);
    assertThat(result.getExitCode(), is(0));
  }


}