package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;

/**
 * Created by edub629 on 7/8/16.
 */
public class TextParser implements AppointmentBookParser {
  private boolean wasLoaded = false;
  protected String fileName;
  protected File file;

  public TextParser(String fileName) {
    this.fileName = fileName;
    this.file = new File(fileName);
    if (this.file.exists()) {
      this.wasLoaded = true;
    }
  }

  /**
   * Parses the contents of a file or other input source and returns
   * an appointment book.
   */

  @Override
  public AbstractAppointmentBook parse() throws ParserException {
    try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
      String line;
      AppointmentBook theBook = new AppointmentBook(reader.readLine());
      Appointment appointment = null;
      int infoCount = 0;
      while ((line = reader.readLine()) != null) {
        if (infoCount == 0) {
          line = line.substring(1, line.length() - 1);
          appointment = new Appointment();
          appointment.setDescription(line);
        } else if (infoCount == 1) {
          appointment.setBeginTimeString(line);
        } else if (infoCount == 2) {
          appointment.setEndTimeString(line);
          theBook.addAppointment(appointment);
        }
        if (infoCount < 2) {
          ++infoCount;
        } else {
          infoCount = 0;
        }
      }
      return theBook;
    } catch (FileNotFoundException e) {
      return new AppointmentBook();
    } catch (IOException e) {
      throw new ParserException(e.getCause().getMessage());
    }
  }
}
