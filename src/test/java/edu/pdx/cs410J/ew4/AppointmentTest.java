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

  @Test
  public void getBeginTimeStringNeedsReturnsNullWhenObjectIsCreatedWithNoArguments() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getBeginTimeString(), is(""));
  }

  @Test
  @Ignore("Ignored appointmentTimesCanBeModified")
  public void appointmentTimesCanBeModified() {
    Appointment appointment = makeTestAppointment();
    appointment.setBeginTimeString("11:00");
    assertThat(appointment.getBeginTimeString(), is("11:00"));
    appointment.setEndTimeString("13:00");
    assertThat(appointment.getEndTimeString(), is("13:00"));
  }

  @Test
  public void getEndTimeStringNeedsReturnsNullWhenObjectIsCreatedWithNoArguments() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getEndTimeString(), is(""));
  }

  @Test
  public void initiallyAllAppointmentsHaveTheSameDescription() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getDescription(), containsString("< empty >"));
  }

  @Test
  public void itIsOkayIfGetBeginTimeReturnsNullForEmptyAppointment() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  @Test
  public void itIsOkayIfGetEndTimeReturnsNullForEmptyAppointment() {
    AbstractAppointment appointment = new Appointment();
    assertThat(appointment.getEndTime(), is(nullValue()));
  }

  @Test
  public void appointmentsCanBeCreatedByConstructor() {
    AbstractAppointment appointment = makeTestAppointment();
    assertThat(appointment.getDescription(), is("Test description"));
  }

  @Test
  public void appointmentDescriptionCanBeModified() {
    Appointment appointment = makeTestAppointment();
    appointment.setDescription("After Modification");
    assertThat(appointment.getDescription(), is("After Modification"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void setAppointmentBeginTimeWithNullArgumentThrowsException() {
    Appointment appointment = makeTestAppointment();
    appointment.setBeginTimeString(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setAppointmentEndTimeWithNullArgumentThrowsException() {
    Appointment appointment = makeTestAppointment();
    appointment.setEndTimeString(null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void setAppointmentDescriptionTimeWithNullArgumentThrowsException() {
    Appointment appointment = makeTestAppointment();
    appointment.setDescription(null);
  }

  @Ignore
  @Test
  public void ConstructorCanTakeAndParseAnArrayOfStringsItSelf () {
    String [] arrayOfStrings = {"Test Description", "06/29/2016", "10:00", "06/29/2016", "14:00"};
    Appointment appointment = new Appointment(arrayOfStrings);
    assertThat(appointment.toString(), containsString("Test Description"));
    assertThat(appointment.getBeginTimeString(), is("06/29/2016 10:00"));
    assertThat(appointment.getEndTimeString(), is("06/29/2016 14:00"));
  }

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
