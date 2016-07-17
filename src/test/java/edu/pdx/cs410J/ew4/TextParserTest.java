package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for {@link TextParser} class.
 */
public class TextParserTest {
  private AppointmentBook testBook;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    testBook = new AppointmentBookTest().getTestBook();
  }

  /**
   * Can load an appointment book from a file.
   */
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

  /**
   * Bad formatting not enough lines of text in file.
   */
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

  /**
   * Bad formatting missing quotes on description.
   */
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

  /**
   * Bad formatting of date.
   */
  @Test
  public void badFormattingOfDate() {
    String testFileName = "badDateFormat.txt";
    File testFile = new File(testFileName);
    assertThat("Existence of test file", testFile.exists(), is(true));
    assertThat("Can read test file", testFile.canRead(), is(true));
    TextParser textParser = new TextParser(testFileName, "Evan");
    boolean caughtException = false;
    try {
      AbstractAppointmentBook parsedBook = textParser.parse();
    } catch (ParserException e) {
      caughtException = true;
//      System.out.println(e.getMessage());
      assertThat(e.getMessage(), containsString("Invalid formatting"));
      assertThat(e.getMessage(), containsString("Bad Date"));
      assertThat(e.getMessage(), containsString("7"));
    }
    assertThat("Exception was not caught", caughtException);
  }

  /**
   * Bad formatting of time.
   */
  @Test
  public void badFormattingOfTime() {
    String testFileName = "badTimeFormat.txt";
    File testFile = new File(testFileName);
    assertThat("Can read test file", testFile.canRead(), is(true));
    assertThat("Existence of test file", testFile.exists(), is(true));
    TextParser textParser = new TextParser(testFileName, "Evan");
    boolean caughtException = false;
    try {
      AbstractAppointmentBook parsedBook = textParser.parse();
    } catch (ParserException e) {
      caughtException = true;
//      System.out.println(e.getMessage());
      assertThat(e.getMessage(), containsString("Invalid formatting"));
      assertThat(e.getMessage(), containsString("Bad Time"));
      assertThat(e.getMessage(), containsString("9"));
    }
    assertThat("Exception was not caught", caughtException);
  }

  /**
   * Parsing ended suddenly missing data but correct formatting.
   */
  @Test
  public void parsingEndedSuddenlyMissingDataButCorrectFormatting() {
    String testFileName = "parseEndsSoonerThanExpected.txt";
    File testFile = new File(testFileName);
    assertThat("Existence of test file", testFile.exists(), is(true));
    assertThat("Can read test file", testFile.canRead(), is(true));
    TextParser textParser = new TextParser(testFileName, "Evan");
    boolean caughtException = false;
    try {
      AbstractAppointmentBook parsedBook = textParser.parse();
    } catch (ParserException e) {
      caughtException = true;
      System.out.println(e.getMessage());
    }
    assertThat("Exception was not caught", caughtException);
  }

  /**
   * Invalid owner name results in parsing exception.
   */
  @Test
  public void invalidOwnerNameResultsInParsingException() {
    String testFileName = "badTimeFormat.txt";
    File testFile = new File(testFileName);
    assertThat("Can read test file", testFile.canRead(), is(true));
    assertThat("Existence of test file", testFile.exists(), is(true));
    TextParser textParser = new TextParser(testFileName, "Not Evan");
    boolean caughtException = false;
    try {
      AbstractAppointmentBook parsedBook = textParser.parse();
    } catch (ParserException e) {
      caughtException = true;
      assertThat(e.getMessage(), containsString("Owners name does not match."));
    }
    assertThat("Exception was not caught", caughtException);
  }

}