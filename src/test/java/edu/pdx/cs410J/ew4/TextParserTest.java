package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by edub629 on 7/8/16.
 */
public class TextParserTest {
  private AppointmentBook testBook;
  private Appointment testAppointment;
  private Appointment testAppointment2;
  private Appointment testAppointment3;

  @Before
  public void setUp() throws Exception {
    testBook = new AppointmentBook("Evan");
    testAppointment = new Appointment("Bang his own mother", "06/09/1993 12:22", "06/12/1993 13:22");
    testAppointment2 = new Appointment("Lunch with his mother", "08/09/1993 12:22", "08/12/1993 13:22");
    testAppointment3 = new Appointment("Dinner with his father", "7/20/2010 16:50", "07/20/2010 17:30");
    testBook.addAppointment(testAppointment);
    testBook.addAppointment(testAppointment2);
    testBook.addAppointment(testAppointment3);
  }

  @Test
  public void canLoadAnAppointmentBookFromAFile() {
    TextParser textParser = new TextParser("test.txt");
    AbstractAppointmentBook appointmentBook = null;
    String thisString = null;
    try {
      appointmentBook = textParser.parse();
    } catch (ParserException e) {
      System.out.print(e.getCause() + e.getMessage());
    }
    assert appointmentBook != null;
    assertThat(appointmentBook.getOwnerName(), equalTo("Evan"));
    assert !appointmentBook.getAppointments().isEmpty();
    assertThat(appointmentBook.getAppointments().toString(), equalTo(testBook.getAppointments().toString()));
  }
}