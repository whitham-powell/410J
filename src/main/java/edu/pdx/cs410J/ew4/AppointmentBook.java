


package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.Collection;


/**
 * This class extends <code>AbstractAppointmentBook</code>
 */
public class AppointmentBook extends AbstractAppointmentBook {
  private String ownerName = "< unspecified >";
  private Collection<AbstractAppointment> appointments;

  public AppointmentBook() {
    super();
  }

  /**
   * Creates an instance of an <code>AppointmentBook</code> stores a <code>Collection</code> of {@link Appointment} objects
   * @param ownerName
   *          The name of the owner of <code>AppointmentBook</code>. Of type <code>String</code>
   */
  public AppointmentBook(String ownerName) {
    super();
    this.ownerName = ownerName;
  }

  @Override
  public String getOwnerName() {
    return this.ownerName;
  }

  @Override
  public Collection getAppointments() {
    return this.appointments;
  }

  @Override
  public void addAppointment(AbstractAppointment appointment) {
    appointments.add(appointment);
  }
}
