package edu.pdx.cs410J.ew4;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by edub629 on 7/7/16.
 */
public class TextDumperTest {
  @Before
  public void setUp() {
    AppointmentBook testBook = new AppointmentBook("Evan");
    Appointment testAppointment = new Appointment("Bang his own mother", "06/09/1993", "06/12/1993");
    testBook.addAppointment(testAppointment);
  }

  @Test
  public void canOpenAFileViaConstructor() {
    TextDumper textDumper = new TextDumper("test.txt");
    assertThat(textDumper.fileName, equalTo("test.txt"));
    assertThat(textDumper.file.exists(), is(true));
  }

  @Test
  public void saveAnOwnerNameToATextFile() {

  }


}