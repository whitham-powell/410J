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
  private AppointmentBook testBook;
  private Appointment testAppointment;
  private Appointment testAppointment2;
  private Appointment testAppointment3;

  /**
   * Instantiates a new Appointment book test.
   */
  public AppointmentBookTest() {
    testBook = new AppointmentBook("Evan");
    testAppointment = new Appointment("Bang his own mother", "06/09/1993 12:22", "06/12/1993 13:50");
    testAppointment2 = new Appointment("Lunch with his mother", "08/09/1993 12:22", "08/09/1993 17:50");
    testAppointment3 = new Appointment("Dinner with his father", "7/20/2010 16:50", "07/20/2010 17:30");
    testBook.addAppointment(testAppointment);
    testBook.addAppointment(testAppointment2);
    testBook.addAppointment(testAppointment3);
  }

  /**
   * Gets test book.
   *
   * @return the test book
   */
  public AppointmentBook getTestBook() {
    return testBook;
  }

  /**
   * Gets owner name returns same owner if unspecified.
   */
  @Test
  public void getOwnerNameReturnsSameOwnerIfUnspecified() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook();
    assertThat(appointmentBook.getOwnerName(), is("< unspecified >"));
  }

  /**
   * Verify that owner of appointment book created via constructor can be retrieved.
   */
  @Test
  public void verifyThatOwnerOfAppointmentBookCreatedViaConstructorCanBeRetrieved() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");
    assertThat(appointmentBook.getOwnerName(), is("Test Owner"));
  }

  /**
   * Gets appointments from empty appointment book returns null.
   */
  @Test
  public void getAppointmentsFromEmptyAppointmentBookReturnsNull() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");
    assertThat(appointmentBook.getAppointments(), is(nullValue()));
  }

  /**
   * Can an appointment be added to the appointment collection.
   */
  @Test
  public void canAnAppointmentBeAddedToTheAppointmentCollection() {
    AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook("Test Owner");
    AbstractAppointment appointment = new Appointment("Test appointment description", "10:30", "14:40");
    appointmentBook.addAppointment(appointment);
    assertThat(appointmentBook.getAppointments(), is(notNullValue()));
    assertThat(appointmentBook.getAppointments().toString(), containsString("Test appointment description"));
  }

  /**
   * Can add multiple appoints to the appointment book and recall the correct number of appointments.
   */
  @Test
  public void canAddMultipleAppointsToTheAppointmentBookAndRecallTheCorrectNumberOfAppointments() {
    assertThat(testBook.size(), is(3));
    testBook.addAppointment(testAppointment);
    testBook.addAppointment(testAppointment2);
    assertThat(testBook.size(), is(5));
  }

  /**
   * Appointment book can return a sorted set of appointments.
   */
  @Test
  public void appointmentBookCanReturnASortedSetOfAppointments() {
    AppointmentBook aBook = new AppointmentBook("Evee");
    aBook.addAppointment(testAppointment3);
    aBook.addAppointment(testAppointment);
    aBook.addAppointment(testAppointment2);

    assertThat(aBook.getSortedSet(), not(aBook.getAppointments()));
    assertThat(aBook.getSortedSet().toString(), is(equalTo(testBook.getAppointments().toString())));
    assertThat(aBook.getAppointments().toString(), not(equalTo(testBook.getAppointments().toString())));

    System.out.print(aBook.getSortedSet().toString());
  }
}
