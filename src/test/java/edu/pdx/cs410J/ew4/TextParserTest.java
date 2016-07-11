package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * TODO Document Test
 * Created by edub629 on 7/8/16.
 */
public class TextParserTest {
  private AppointmentBook testBook;

  @Before
  public void setUp() {
    testBook = new AppointmentBookTest().getTestBook();
  }

  @Test
  public void canLoadAnAppointmentBookFromAFile() {
    TextParser textParser = new TextParser("test.txt", "Evan");
    AbstractAppointmentBook appointmentBook = null;
    String thisString = null;
    try {
      appointmentBook = textParser.parse();
    } catch (ParserException e) {
      System.out.print(e.getCause() + e.getMessage());
    }
    assert appointmentBook != null;
    assertThat(appointmentBook.getOwnerName(), equalTo("Evan"));
    assert appointmentBook.getAppointments() != null;
    assert !appointmentBook.getAppointments().isEmpty();
    assertThat(appointmentBook.getAppointments().toString(), equalTo(testBook.getAppointments().toString()));
  }

  @Test
  public void badFormattingNotEnoughLinesOfTextInFile() {
    try {
      BufferedReader fr = new BufferedReader(new FileReader(new File("tooFewLinesTest.txt")));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    boolean caughtException;
    File testFile = new File("tooFewLinesTest.txt");
    assertThat("Existence of test file", testFile.exists(), is(true));
    assertThat("Can read test file", testFile.canRead(), is(true));

    TextParser textParser = new TextParser("tooFewLinesTest.txt", "Evan");
    try {
      AbstractAppointmentBook parsedBook = textParser.parse();
      caughtException = false;
    } catch (ParserException e) {
      caughtException = true;
      assertThat(e.getMessage(), containsString("Bad Formatting"));
    }
    assertThat(caughtException, is(true));
  }

  @Test
  public void badFormattingMissingQuotesOnDescription() {
    File testFile = new File("missingQuotesOnDescription.txt");
    assertThat("Existence of test file", testFile.exists(), is(true));
    assertThat("Can read test file", testFile.canRead(), is(true));
    TextParser textParser = new TextParser("missingQuotesOnDescription.txt", "Evan");
    boolean caughtException = false;
    try {
      AbstractAppointmentBook parsedBook = textParser.parse();
    } catch (ParserException e) {
      caughtException = true;
      assertThat(e.getMessage(), containsString("Bad Formatting"));
    }
    assertThat("Exception was not caught", caughtException);
  }
}