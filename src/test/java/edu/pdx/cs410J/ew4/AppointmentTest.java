package edu.pdx.cs410J.ew4;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

  @Test
  public void getBeginTimeStringNeedsReturnsNullWhenObjectIsCreatedWithNoArguments() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTimeString(), is(nullValue()));
  }

  @Test
  public void appointmentTimesCanBeModified() {
    Appointment appointment = makeTestAppointment();
    appointment.setBeginTimeString("11:00");
    assertThat(appointment.getBeginTimeString(), is("11:00"));
    appointment.setEndTimeString("13:00");
    assertThat(appointment.getEndTimeString(), is("13:00"));
  }

  @Test
  public void getEndTimeStringNeedsReturnsNullWhenObjectIsCreatedWithNoArguments() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getEndTimeString(), is(nullValue()));
  }

  @Test
  public void initiallyAllAppointmentsHaveTheSameDescription() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getDescription(), containsString("< empty >"));
  }

  @Test
  public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  @Test
  public void appointmentsCanBeCreatedByConstructor() {
    Appointment appointment = makeTestAppointment();
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

  private Appointment makeTestAppointment() {
    return new Appointment("Test description", "10:00", "14:00");
  }
}
