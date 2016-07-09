//AppointmentBookTests

package edu.pdx.cs410J.ew4;


import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AppointmentBook} class.
 */
public class AppointmentBookTest {
  private AppointmentBook testBook;
  private Appointment testAppointment;
  private Appointment testAppointment2;

  @Before
  public void setUp() {
    testBook = new AppointmentBook("Evan");
    testAppointment = new Appointment("Lunch with his mother", "06/09/1993 12:22", "06/12/1993 13:22");
    testAppointment2 = new Appointment("Dinner with his father", "7/20/2010 16:50", "07/20/2010 17:30");
  }
  @Test
  public void getOwnerNameReturnsSameOwnerIfUnspecified() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook();
    assertThat(appointmentBook.getOwnerName(), is("< unspecified >"));
  }

  @Test
  public void verifyThatOwnerOfAppointmentBookCreatedViaConstructorCanBeRetrieved() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");
    assertThat(appointmentBook.getOwnerName(), is("Test Owner"));
  }

  @Test
  public void getAppointmentsFromEmptyAppointmentBookReturnsNull() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");
    assertThat(appointmentBook.getAppointments(), is(nullValue()));
  }

  @Test
  public void canAnAppointmentBeAddedToTheAppointmentCollection() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");
    AbstractAppointment appointment = new Appointment("Test appointment description", "10:30", "14:40");
    appointmentBook.addAppointment(appointment);
    assertThat(appointmentBook.getAppointments(), is(notNullValue()));
    assertThat(appointmentBook.getAppointments().toString(), containsString("Test appointment description"));
  }

  @Test
  public void canAddMultipleAppointsToTheAppointmentBookAndRecallTheCorrectNumberOfAppointments() {
    testBook.addAppointment(testAppointment);
    testBook.addAppointment(testAppointment2);
    assertThat(testBook.size(), is(2));
  }
}
