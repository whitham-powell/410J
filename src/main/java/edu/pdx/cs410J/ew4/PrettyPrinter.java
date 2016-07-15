package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * TODO document class
 * Created by edub629 on 7/14/16.
 */
public class PrettyPrinter implements AppointmentBookDumper {
  private BufferedWriter bw;

  /**
   * Instantiates a new Pretty printer.
   *
   * @param fileName the file name
   * @throws IOException the io exception
   */
  public PrettyPrinter(String fileName) throws IOException {
    this(new File(fileName));
  }

  /**
   * Instantiates a new Pretty printer.
   *
   * @param file the file
   * @throws IOException the io exception
   */
  public PrettyPrinter(File file) throws IOException {
    this(new FileWriter(file));
  }

  /**
   * Instantiates a new Pretty printer.
   *
   * @param fileWriter the file writer
   */
  public PrettyPrinter(FileWriter fileWriter) {
    this(new BufferedWriter(fileWriter));
  }

  /**
   * Instantiates a new Pretty printer.
   *
   * @param bufferedWriter the buffered writer
   */
  public PrettyPrinter(BufferedWriter bufferedWriter) {
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
    StringBuffer sb = new StringBuffer();
    sb.append("Owner: ").append(book.getOwnerName()).append("\n");

    SortedSet<Appointment> sortedBook = ((AppointmentBook) book).getSortedSet();
    Iterator<Appointment> sortedAppointments = sortedBook.iterator();

    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);

    while (sortedAppointments.hasNext()) {
      Appointment appointment = sortedAppointments.next();
      sb.append("=====================================================\n")
              .append("Description:\n").append(appointment.getDescription()).append("\n");

      // Get the begin and end times
      Date beginTime = appointment.getBeginTime();
      Date endTime = appointment.getEndTime();

      // If either is null duration is 0
//      Duration d = null;
      if (beginTime != null && endTime != null) {
//        d = Duration.between(beginTime.toInstant(), endTime.toInstant());
//        LocalDate begin = beginTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate end = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        Period p = Period.between(begin, end);
        long hours = ChronoUnit.HOURS.between(beginTime.toInstant(), endTime.toInstant());
        long minutes = ChronoUnit.MINUTES.between(beginTime.toInstant(), endTime.toInstant());
        sb.append("From: ").append(df.format(beginTime)).append("\n")
                .append("Until: ").append(df.format(endTime)).append("\n");
        sb.append("Duration: ").append(hours).append(" hours, ").append((int) minutes % 60).append(" minutes\n");
      }
    }
    bw.write(sb.toString());
    bw.close();
  }


}
