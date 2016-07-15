package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for {@link PrettyPrinter} class.
 */
public class PrettyPrinterTest {
  private AppointmentBook testBook;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    testBook = new AppointmentBookTest().getTestBook();
  }

  /**
   * Can print to a file.
   */
  @Test
  public void canPrintToAFile() {

    try {
      PrettyPrinter prettyPrinter = new PrettyPrinter("prettyTest.txt");
      prettyPrinter.dump(testBook);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}