package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * TODO document test class
 * Created by edub629 on 7/9/16
 */
public class Project2IT extends InvokeMainTestCase {

  private static String USAGE =
          "usage: java edu.pdx.edu.cs410J.<login-id>.Project2 [options] <args> \n" +
                  " args are (in this order): \n" +
                  "   owner        The person whose owns the appt book\n" +
                  "   description  A description of the appointment\n" +
                  "   beginTime    When the appt begins (24-hour time)\n" +
                  "   endTime      When the appt ends (24-hour time)\n";
  private static String OPTIONS =
          " options are (may appear in any order):\n" +
                  "   -textFile" + "\t" + "< val >" + "\t" + "Where to read/write the appointment book\n" +
                  "   -print" + "\t\t\t\t" + "Prints a description of the new appointment\n" +
                  "   -README" + "\t\t\t\t" + "Prints a README for this project along with the usage and exits\n";

  /**
   * Invokes the main method of {@link Project2} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Project2.class, args);
  }

  @Test
  public void passesOldProject1Tests() {
    Project1IT p1IT = new Project1IT();
    p1IT.mainClass = Project2.class;
    boolean passedProject1Tests = true;
    int numberOfPassingTests = 0;
    int totalNumberOfTest = 0;
    try {
      Method[] allMethods = p1IT.getClass().getDeclaredMethods();
      totalNumberOfTest = allMethods.length - 1; // less one helper function
      for (Method m : allMethods) {
        if (m.isAnnotationPresent(Test.class)) {
          String mName = m.getName();
          out.format("Project 1 Unit Test: %s()%n", mName);
          try {
            m.setAccessible(true);
            m.invoke(p1IT);
            out.format("%s passed %n", mName);
            ++numberOfPassingTests;
            // Handle any exceptions thrown by method to be invoked.
          } catch (InvocationTargetException x) {
            Throwable cause = x.getCause();
            err.format("%s failed: %s%n",
                    mName, cause.getMessage());
            passedProject1Tests = false;
          }
        }
        out.format("%d out of %d tests passing%n", numberOfPassingTests, totalNumberOfTest);
      }
    } catch (IllegalAccessException e) {
      Throwable cause = e.getCause();
      err.format("Exception thrown: %s %n Caused by: %s %n",
              e.getMessage(), cause.getMessage());
    }
    assertTrue("failed one or more project1 tests", passedProject1Tests);
    assertEquals(numberOfPassingTests, totalNumberOfTest);
  }

  @Test
  public void project2NewUsageOutputsOnAnError() {
    MainMethodResult result = invokeMain();
    assertThat(result.getOut(), containsString(USAGE));
    assertThat(result.getOut(), containsString(OPTIONS));
    assertThat(result.getOut(), containsString("Dates and times should be in the format: mm/dd/yyyy hh:mm\n"));

  }
}