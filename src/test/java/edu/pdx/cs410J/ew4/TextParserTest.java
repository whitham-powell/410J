package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**TODO Document Test
 * Created by edub629 on 7/8/16.
 */
public class TextParserTest {
  private AppointmentBook testBook;

  @Before
  public void setUp() {
    testBook = new AppointmentBookTest().getTestBook();
  }

  @Test
  public void canLoadAnAppointmentBookFromAFile() {
    TextParser textParser = new TextParser("test.txt");
    AbstractAppointmentBook appointmentBook = null;
    String thisString = null;
    try {
      appointmentBook = textParser.parse();
    } catch (ParserException e) {
      System.out.print(e.getCause() + e.getMessage());
    }
    assert appointmentBook != null;
    assertThat(appointmentBook.getOwnerName(), equalTo("Evan"));
    assert !appointmentBook.getAppointments().isEmpty();
    assertThat(appointmentBook.getAppointments().toString(), equalTo(testBook.getAppointments().toString()));
  }
}