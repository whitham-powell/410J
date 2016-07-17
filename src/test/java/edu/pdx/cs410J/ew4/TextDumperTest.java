package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for {@link TextDumper} class.
 */
public class TextDumperTest {

  private AppointmentBook testBook;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    testBook = new AppointmentBookTest().getTestBook();
  }


  /**
   * Save an owner name to a text file.
   *
   * @throws IOException the io exception
   */
  @Test
  public void saveAnOwnerNameToATextFile() throws IOException {
    TextDumper textDumper = new TextDumper("test.txt");
    try {
      textDumper.dump(testBook);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedReader reader = new BufferedReader(new FileReader(new File("test.txt")));
    assertThat(reader.readLine(), equalTo(testBook.getOwnerName()));
  }

  /**
   * Saves an appointment book to file and its appointments.
   *
   * @throws IOException the io exception
   */
  @Test
  public void savesAnAppointmentBookToFileAndItsAppointments() throws IOException {
    TextDumper textDumper = new TextDumper("test.txt");
    String line;

    try {
      textDumper.dump(testBook);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(new File("test.txt")))) {
      String thisString = testBook.getOwnerName() + "\n" + textDumper.makeAppointmentsString(testBook);
      StringBuilder testString = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        testString.append(line).append("\n");
      }
      assertThat(testString.toString(), equalTo(thisString));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }


}