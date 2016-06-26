// AppointmentBook
// TODO Document getOwnerName
// TODO Document addAppointments
// TODO Document getAppointments


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
  public String getOwnerName() {
    return this.ownerName;
  }

  @Override
  public Collection<AbstractAppointment> getAppointments() {
    if(appointments.isEmpty()) {
      return null;
    } else {
      return this.appointments;
    }
  }

  @Override
  public void addAppointment(AbstractAppointment appointment) {
    appointments.add(appointment);
  }
}
