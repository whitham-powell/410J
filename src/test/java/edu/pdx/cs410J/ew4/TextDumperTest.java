package edu.pdx.cs410J.ew4;

import org.junit.Before;

/**
 * Created by edub629 on 7/7/16.
 */
public class TextDumperTest {
  @Before
  public void setUp() throws Exception {
    AppointmentBook testBook = new AppointmentBook("Evan");
    Appointment testAppointment = new Appointment("Bang his own mother", "06/09/1993", "06/12/1993");
  }
}