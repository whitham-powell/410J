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
      String ownerName = reader.readLine();
      List<String> lineListing = reader.lines().collect(Collectors.toList());
      ListIterator<String> iterator = lineListing.listIterator();

      //TODO check mismatch of supplied owner vs. found in file.
      AppointmentBook theBook = new AppointmentBook(ownerName);
      InfoValidator validator;

      // If the line count is not divisible by 3 throw ParserException
      if (lineListing.size() % 3 != 0) {
        throw new ParserException("Bad Formatting: Line count indicates missing data");
      }


      String description;
      String beginTimeString;
      String endTimeString;
      int lineNumber = 1;

      // Iterator through the files contents
      while (iterator.hasNext()) {
        if (iterator.hasNext()) {
          description = iterator.next();
          ++lineNumber;
          if (!description.startsWith("\"") || !description.endsWith("\"")) {
            throw new ParserException("Bad Formatting - Missing \"\" around description");
          }
          description = description.substring(1, description.length() - 1);
        } else {
          throw new ParserException("Hit end of file listing too soon - Expected an Appointment Description\n" +
                  "Last read : " + iterator.previous());
        }
        if (iterator.hasNext()) {
          beginTimeString = iterator.next();
          ++lineNumber;
          validator = new InfoValidator().validate(beginTimeString);
          if (validator.hasFailed()) {
            throw new ParserException(validator.getErrMsg() + " On line : " + lineNumber +
                    "\n" + beginTimeString);
          }
        } else {
          throw new ParserException("Hit end of file listing too soon - Expected an Appointment Begin Time\n" +
                  "Last read : " + iterator.previous());
        }
        if (iterator.hasNext()) {
          endTimeString = iterator.next();
          ++lineNumber;
          validator = new InfoValidator().validate(endTimeString);
          if (validator.hasFailed()) {
            throw new ParserException(validator.getErrMsg() + " On line : " + lineNumber +
                    "\n" + endTimeString);
          }
        } else {
          throw new ParserException("Hit end of file listing too soon - Expected an Appointment End Time\n" +
                  "Last read : " + iterator.previous());
        }
        theBook.addAppointment(new Appointment(description, beginTimeString, endTimeString));
      }
      return theBook;
    } catch (FileNotFoundException e) {
      return new AppointmentBook();
    } catch (IOException e) {
      throw new ParserException(e.getCause().getMessage());
    }
  }
}
