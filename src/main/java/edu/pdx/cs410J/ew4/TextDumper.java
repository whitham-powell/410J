package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The <code>TextDumper</code> class regulates the loading and creation of a file.
 *
 * @author Elijah Whitham-Powell
 */


public class TextDumper implements AppointmentBookDumper {

  protected boolean didCreate;
  protected String fileName;
  protected File file;

  public TextDumper(String fileName) throws IOException {
    this.fileName = fileName;
    this.file = new File(fileName);
    try {
      this.didCreate = this.file.createNewFile();
    } catch (IOException e) {
      throw new IOException("Failed to create file" + e.getMessage());
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
      throw new IOException("failed to create buffered file writer" + this.fileName);
    }
  }

  /**
   * Collects all of the appointments of a book and builds a string formatted for dumping
   *
   * @param book <code>AppointmentBook</code> containing appointments to dump.
   * @return
   */
  public String makeAppointmentsString(AbstractAppointmentBook book) {
    Appointment asAppointment;
    StringBuffer sb = new StringBuffer();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    for (Object o : book.getAppointments()) {
      asAppointment = (Appointment) o;
      sb.append("\"").append(asAppointment.getDescription()).append("\"").append("\n");

      sb.append(df.format(asAppointment.getBeginTime())).append("\n");
      sb.append(df.format(asAppointment.getEndTime())).append("\n");
    }
    return sb.toString();
  }

  public boolean fileExists() {
    return this.file.exists();
  }
}
