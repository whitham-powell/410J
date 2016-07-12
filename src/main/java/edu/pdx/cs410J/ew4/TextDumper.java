package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**TODO Document Class
 * Created by edub629 on 7/7/16.
 */


public class TextDumper implements AppointmentBookDumper {

  protected boolean didCreate;
  protected String fileName;
  protected File file;

  public TextDumper(String fileName) {
    this.fileName = fileName;
    this.file = new File(fileName);
    try {
      this.didCreate = this.file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
      this.didCreate = false;
    }
  }

  /**
   * Dumps an appointment book to some destination.
   *
   * @param book The appointment book whose contents are to be dumped
   * @throws IOException Something went wrong while dumping the appointment book
   */
  @Override
  public void dump(AbstractAppointmentBook book) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
      writer.write(book.getOwnerName() + "\n");
      String outString = makeAppointmentsString(book);
      writer.write(outString);
      writer.close();
    } catch (IOException e) {
      throw new IOException("failed to create buffered file writer" + this.file.exists());
    }
  }

  /**
   * //TODO document makeAppointmentsString
   *
   * @param book
   * @return
   */
  public String makeAppointmentsString(AbstractAppointmentBook book) {
    Appointment asAppointment;
    StringBuilder sb = new StringBuilder();
    for (Object o : book.getAppointments()) {
      asAppointment = (Appointment) o;
      sb.append("\"").append(asAppointment.getDescription()).append("\"").append("\n");
      sb.append(asAppointment.getBeginTimeString()).append("\n");
      sb.append(asAppointment.getEndTimeString()).append("\n");
    }
    //TODO fix inconsitancy of date and time string format
    return sb.toString();
  }

  public boolean fileExists() {
    return this.file.exists();
  }
}
