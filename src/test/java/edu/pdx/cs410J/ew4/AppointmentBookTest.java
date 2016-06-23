//AppointmentBookTests
//        -TODO getAppointments()
//        -TODO addAppointments()

package edu.pdx.cs410J.ew4;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AppointmentBook} class.
 */
public class AppointmentBookTest {
    @Test
    public void getOwnerNameReturnsSameOwnerIfUnspecified() {
        AppointmentBook appointmentBook = new AppointmentBook();
        assertThat(appointmentBook.getOwnerName(), is("< unspecified >"));
    }

    @Test
    public void verifyThatOwnerOfAppointmentBookCreatedViaConstructorCanBeRetrieved() {
        AppointmentBook appointmentBook = new AppointmentBook("Test Owner");
        assertThat(appointmentBook.getOwnerName(), is("Test Owner"));
    }
}
