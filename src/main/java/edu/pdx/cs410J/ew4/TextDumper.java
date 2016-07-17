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
  private BufferedWriter bw;

  /**
   * Instantiates a new Text dumper.
   *
   * @param fileName the file name
   * @throws IOException the io exception
   */
  public TextDumper(String fileName) throws IOException {
    this(new File(fileName));
  }

  /**
   * Instantiates a new Text dumper.
   *
   * @param file the file
   * @throws IOException the io exception
   */
  public TextDumper(File file) throws IOException {
    this(new FileWriter(file));
  }

  /**
   * Instantiates a new Text dumper.
   *
   * @param fileWriter the file writer
   */
  public TextDumper(FileWriter fileWriter) {
    this(new BufferedWriter(fileWriter));
  }

  /**
   * Instantiates a new Text dumper.
   *
   * @param bufferedWriter the buffered writer
   */
  public TextDumper(BufferedWriter bufferedWriter) {
    this.bw = bufferedWriter;
  }

  /**
   * Dumps an appointment book to some destination.
   *
   * @param book The appointment book whose contents are to be dumped
   * @throws IOException Something went wrong while dumping the appointment book
   */
  @Override
  public void dump(AbstractAppointmentBook book) throws IOException {
    this.bw.write(book.getOwnerName() + "\n");
    String outString = makeAppointmentsString(book);
    this.bw.write(outString);
    this.bw.close();
  }

  /**
   * Collects all of the appointments of a book and builds a string formatted for dumping
   *
   * @param book <code>AppointmentBook</code> containing appointments to dump.
   * @return string
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
}
