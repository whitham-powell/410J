//AppointmentBookTests
//        -TODO getAppointments()
//        -TODO addAppointments()

package edu.pdx.cs410J.ew4;


import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AppointmentBook} class.
 */
public class AppointmentBookTest {
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
  public void canAnAppointmentBeAddedToTheAppointmentCollection() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");

  }
}
