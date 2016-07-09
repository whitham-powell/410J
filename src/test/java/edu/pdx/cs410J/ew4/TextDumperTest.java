package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by edub629 on 7/7/16.
 */
public class TextDumperTest {

  private AppointmentBook testBook;
  private Appointment testAppointment;
  private Appointment testAppointment2;
  private Appointment testAppointment3;

  @Before
  public void setUp() {
    testBook = new AppointmentBook("Evan");
    testAppointment = new Appointment("Bang his own mother", "06/09/1993 12:22", "06/12/1993 13:22");
    testAppointment2 = new Appointment("Lunch with his mother", "06/09/1993 12:22", "06/12/1993 13:22");
    testAppointment3 = new Appointment("Dinner with his father", "7/20/2010 16:50", "07/20/2010 17:30");
    testBook.addAppointment(testAppointment);
    testBook.addAppointment(testAppointment2);
    testBook.addAppointment(testAppointment3);
  }

  @Test
  public void canOpenAFileViaConstructor() {
    TextDumper textDumper = new TextDumper("test.txt");
    assertThat(textDumper.fileName, equalTo("test.txt"));
    assertThat(textDumper.fileExists(), is(true));
  }

  @Test
  public void saveAnOwnerNameToATextFile() throws IOException {
    TextDumper textDumper = new TextDumper("test.txt");
    try {
      textDumper.dump(testBook);
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedReader reader = new BufferedReader(new FileReader(textDumper.file));
    assertThat(reader.readLine(), equalTo(testBook.getOwnerName()));
  }

  @Test
  public void savesAnAppointmentBookToFileAndItsAppointments() throws IOException {
    TextDumper textDumper = new TextDumper("test.txt");
    String line;

    try {
      textDumper.dump(testBook);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(textDumper.file))) {
      String thisString = testBook.getOwnerName() + "\n" +
              "\"" + testAppointment.getDescription() + "\"" + "\n" +
              testAppointment.getBeginTimeString() + "\n" +
              testAppointment.getEndTimeString() + "\n" +
              "\"" + testAppointment2.getDescription() + "\"" + "\n" +
              testAppointment2.getBeginTimeString() + "\n" +
              testAppointment2.getEndTimeString() + "\n" +
              "\"" + testAppointment3.getDescription() + "\"" + "\n" +
              testAppointment3.getBeginTimeString() + "\n" +
              testAppointment3.getEndTimeString() + "\n";
      StringBuilder testString = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        testString.append(line).append("\n");
        System.out.println(line);
      }
      assertThat(testString.toString(), equalTo(thisString));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }


}