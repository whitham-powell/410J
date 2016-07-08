package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.IOException;

/**
 * Created by edub629 on 7/7/16.
 */


public class TextDumper implements AppointmentBookDumper {

  protected String fileName;
  protected File file;

  public TextDumper(String fileName) {
    this.fileName = fileName;
    this.file = new File(fileName);
  }

  /**
   * Dumps an appointment book to some destination.
   *
   * @param book The appointment book whose contents are to be dumped
   * @throws IOException Something went wrong while dumping the appointment book
   */
  @Override
  public void dump(AbstractAppointmentBook book) throws IOException {

  }
}
