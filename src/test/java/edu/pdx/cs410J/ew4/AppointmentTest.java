package edu.pdx.cs410J.ew4;


import edu.pdx.cs410J.AbstractAppointment;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

  /**
   * Gets begin time string needs returns null when object is created with no arguments.
   */
  @Test
  public void getBeginTimeStringNeedsReturnsNullWhenObjectIsCreatedWithNoArguments() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getBeginTimeString(), is(""));
  }

  /**
   * Appointment times can be modified.
   */
  @Test
  @Ignore("Ignored appointmentTimesCanBeModified")
  public void appointmentTimesCanBeModified() {
    Appointment appointment = makeTestAppointment();
    appointment.setBeginTimeString("11:00");
    assertThat(appointment.getBeginTimeString(), is("11:00"));
    appointment.setEndTimeString("13:00");
    assertThat(appointment.getEndTimeString(), is("13:00"));
  }

  /**
   * Gets end time string needs returns null when object is created with no arguments.
   */
  @Test
  public void getEndTimeStringNeedsReturnsNullWhenObjectIsCreatedWithNoArguments() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getEndTimeString(), is(""));
  }

  /**
   * Initially all appointments have the same description.
   */
  @Test
  public void initiallyAllAppointmentsHaveTheSameDescription() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getDescription(), containsString("< empty >"));
  }

  /**
   * It is okay if get begin time returns null for empty appointment.
   */
  @Test
  public void itIsOkayIfGetBeginTimeReturnsNullForEmptyAppointment() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  /**
   * It is okay if get end time returns null for empty appointment.
   */
  @Test
  public void itIsOkayIfGetEndTimeReturnsNullForEmptyAppointment() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getEndTime(), is(nullValue()));
  }

  /**
   * Appointments can be created by constructor.
   */
  @Test
  public void appointmentsCanBeCreatedByConstructor() {
    AbstractAppointment appointment = makeTestAppointment();
    assertThat(appointment.getDescription(), is("Test description"));
  }

  /**
   * Appointment description can be modified.
   */
  @Test
  public void appointmentDescriptionCanBeModified() {
    Appointment appointment = makeTestAppointment();
    appointment.setDescription("After Modification");
    assertThat(appointment.getDescription(), is("After Modification"));
  }

  /**
   * Sets appointment begin time with null argument throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setAppointmentBeginTimeWithNullArgumentThrowsException() {
    Appointment appointment = makeTestAppointment();
    appointment.setBeginTimeString(null);
  }

  /**
   * Sets appointment end time with null argument throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setAppointmentEndTimeWithNullArgumentThrowsException() {
    Appointment appointment = makeTestAppointment();
    appointment.setEndTimeString(null);
  }


  /**
   * Sets appointment description time with null argument throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setAppointmentDescriptionTimeWithNullArgumentThrowsException() {
    Appointment appointment = makeTestAppointment();
    appointment.setDescription(null);
  }

  /**
   * Constructor can take and parse an array of strings it self.
   */
  @Ignore
  @Test
  public void ConstructorCanTakeAndParseAnArrayOfStringsItSelf() {
    String[] arrayOfStrings = {"Test Description", "06/29/2016", "10:00", "06/29/2016", "14:00"};
    Appointment appointment = new Appointment(arrayOfStrings);
    assertThat(appointment.toString(), containsString("Test Description"));
    assertThat(appointment.getBeginTimeString(), is("06/29/2016 10:00"));
    assertThat(appointment.getEndTimeString(), is("06/29/2016 14:00"));
  }

  /**
   * Sets appointment begin time with null argument throws exception with correct message.
   */
  @Test
  public void setAppointmentBeginTimeWithNullArgumentThrowsExceptionWithCorrectMessage() {
    Appointment appointment = makeTestAppointment();
    boolean thrown = false;
    String exception = "";
    try {
      appointment.setBeginTimeString(null);
    } catch (IllegalArgumentException e) {
      exception = e.getMessage();
      thrown = true;
    }
    assert (thrown);
    assertThat(exception, containsString("beginTimeString cannot be null"));
  }

  /**
   * Sets appointment end time with null argument throws exception with correct message.
   */
  @Test
  public void setAppointmentEndTimeWithNullArgumentThrowsExceptionWithCorrectMessage() {
    Appointment appointment = makeTestAppointment();
    boolean thrown = false;
    String exception = "";
    try {
      appointment.setEndTimeString(null);
    } catch (IllegalArgumentException e) {
      exception = e.getMessage();
      thrown = true;
    }
    assert (thrown);
    assertThat(exception, containsString("endTimeString cannot be null"));
  }

  /**
   * Sets appointment description time with null argument throws exception with correct message.
   */
  @Test
  public void setAppointmentDescriptionTimeWithNullArgumentThrowsExceptionWithCorrectMessage() {
    Appointment appointment = makeTestAppointment();
    boolean thrown = false;
    String exception = "";
    try {
      appointment.setDescription(null);
    } catch (IllegalArgumentException e) {
      exception = e.getMessage();
      thrown = true;
    }
    assert (thrown);
    assertThat(exception, containsString("description cannot be null"));
  }

  /**
   * Appointment compare to functions as expected.
   */
  @Test
  public void appointmentCompareToFunctionsAsExpected() {
    Appointment w = new Appointment("Bcd", "06/29/2016 10:00", "07/29/2016 14:00");
    Appointment x = new Appointment("Abc", "06/29/2016 10:00", "07/29/2016 14:00");
    Appointment y = new Appointment("Abc", "06/29/2016 10:00", "07/29/2016 14:00");
    Appointment z = new Appointment("Aab", "06/29/2016 10:00", "07/29/2016 14:00");
    if ((w.compareTo(y) > 0 && y.compareTo(z) > 0)) {
      assertThat(w.compareTo(z) > 0, is(true));
    }
    assertThat(x.compareTo(y), is(0));
    assertThat((1 * (x.compareTo(y))) == (-1 * (y.compareTo(x))), is(true));

    boolean signChange = (1 * (x.compareTo(w))) == (-1 * (w.compareTo(x)));
    assertThat(signChange, is(true));
    boolean compareToAndEquals = x.compareTo(y) == 0 && x.equals(y);
    assertThat(compareToAndEquals, is(true));
  }

  private Appointment makeTestAppointment() {
    return new Appointment("Test description", "10:00", "14:00");
  }
}
