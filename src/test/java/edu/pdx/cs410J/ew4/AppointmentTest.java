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

    private Appointment makeTestAppointment() {
    return new Appointment("Test description", "10:00", "14:00");
  }
}
