package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * TODO document class
 * Created by edub629 on 7/8/16.
 */
public class TextParser implements AppointmentBookParser {
  private String bookOwner;
  private boolean wasLoaded = false;
  private String fileName;
  private File file;

  public TextParser(String fileName, String bookOwner) {
    this.fileName = fileName;
    this.file = new File(fileName);
    if (this.file.exists()) {
      this.wasLoaded = true;
    }
    this.bookOwner = bookOwner;
  }

  /**
   * Parses the contents of a file or other input source and returns
   * an appointment book.
   */

  @Override
  public AbstractAppointmentBook parse() throws ParserException {
    try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
      String line = reader.readLine();
      List<String> lineListing = reader.lines().collect(Collectors.toList());
      ListIterator<String> iterator = lineListing.listIterator();
      AppointmentBook theBook = new AppointmentBook(line);
//      Appointment appointment = null;
      int infoCount = 0;

      // If the line count is not divisible by 3 throw ParserException
      if (lineListing.size() % 3 != 0) {
        throw new ParserException("Bad Formatting: Line count indicates missing data");
      }


      //TODO bad time formatting throw exception


      String description;
      String beginTimeString;
      String endTimeString;
      while (iterator.hasNext()) {
        if (iterator.hasNext()) {
          //TODO description missing quotes exception
          description = iterator.next();
          if (!description.startsWith("\"") || !description.endsWith("\"")) {
            throw new ParserException("");
          }
          description = description.substring(1, description.length() - 1);
        } else {
          throw new ParserException("Hit end of file listing too soon - Expected an Appointment Description");
        }
        if (iterator.hasNext()) {
          beginTimeString = iterator.next();
        } else {
          throw new ParserException("Hit end of file listing too soon - Expected an Appointment Begin Time");
        }
        if (iterator.hasNext()) {
          endTimeString = iterator.next();
        } else {
          throw new ParserException("Hit end of file listing too soon - Expected an Appointment End Time");
        }

        theBook.addAppointment(new Appointment(description, beginTimeString, endTimeString));
      }
      while ((line = reader.readLine()) != null) {
//        if (infoCount == 0) {
//          line = line.substring(1, line.length() - 1);
//          appointment = new Appointment();
//          appointment.setDescription(line);
//        } else if (infoCount == 1) {
//          appointment.setBeginTimeString(line);
//        } else if (infoCount == 2) {
//          appointment.setEndTimeString(line);
//          theBook.addAppointment(appointment);
//        }
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
