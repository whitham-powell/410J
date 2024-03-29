package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class extends <code>AbstractAppointmentBook</code>
 */
public class AppointmentBook extends AbstractAppointmentBook<AbstractAppointment> {
  private String ownerName = "< unspecified >";
  final private Collection<AbstractAppointment> appointments;

  public AppointmentBook() {
    appointments = new ArrayList<>();
  }

  /**
   * Creates an instance of an <code>AppointmentBook</code> stores a <code>Collection</code> of {@link Appointment} objects
   * @param ownerName
   *          The name of the owner of <code>AppointmentBook</code>. Of type <code>String</code>
   */
  public AppointmentBook(String ownerName) {
    this.ownerName = ownerName;
    appointments = new ArrayList<>();
  }

  @Override
  /**
   * Returns the the owner of the <code>AppointmentBook</code> as a <code>String</code>
   */
  public String getOwnerName() {
    return this.ownerName;
  }

  @Override
  /**
   * Returns the collection of <code>AbstractAppointments</code> if it is not empty otherwise
   * returns <code>null</code>
   */
  public Collection<AbstractAppointment> getAppointments() {
    if(appointments.isEmpty()) {
      return null;
    } else {
      return this.appointments;
    }
  }

  @Override
  /**
   * Adds an <code>AbstractAppointment</code> object to the collection of appointments.
   * @param appointment - an <code>AbstractAppointment</code> object to be added to the collection.
   */
  public void addAppointment(AbstractAppointment appointment) {
    appointments.add(appointment);
  }
}
