package edu.pdx.cs410J.ew4;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;
//import org.apache.commons.cli.Options;
//import org.apache.commons.cli.Option;


/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {
// TODO can i make this a method?
  public static final String README = "usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n" +
          "\targs are (in this order): \n" +
          "\t\towner The person whose owns the appt book\n" +
          "\t\tdescription A description of the appointment\n" +
          "\t\tbeginTime When the appt begins (24-hour time)\n" +
          "\t\tendTime When the appt ends (24-hour time)\n" +
          "\toptions are (options may appear in any order):\n" +
          "\t\t-print Prints a description of the new appointment\n" +
          "\t\t-README Prints a README for this project and exits\n" +
          "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n";

//  public static final String[] options
//  private Options options = new Options();
//  Option readME = new Option("-README", "Prints this help message");
//  Option print = new Option("-print", "Prints the newly added appointment");
//  options.addOption();

  public static void main(String[] args) {

    //TODO WHAT IS THIS .CLASS BUSINESS?
    Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    if (args.length == 0) {
      System.err.println("Missing command line arguments: None Provided");
      System.out.print(README);
      System.exit(1);
    }
    if (args.length < 6) {
      if (args[0].equalsIgnoreCase("-README")) {
        System.out.print(README);
        System.exit(0);
      } else {
        System.err.println("Missing command line arguments: " + args.length + " provided \n");
        for (String arg : args) {
          System.out.println(arg);
        }
        System.out.print(README);
        System.exit(1);
      }
    } else {
      if(args[0].equalsIgnoreCase("-print")) {
        String appointmentOwner = args[1];
        String[] appointmentInfo = new String[ args.length - 2 ];
        System.arraycopy(args,2,appointmentInfo,0,(args.length - 2));
        AbstractAppointmentBook<AbstractAppointment> appointmentBook = new AppointmentBook(appointmentOwner);
        AbstractAppointment appointment = new Appointment(appointmentInfo);
        //TODO add the appointment to the book
        System.out.println(appointmentBook.getOwnerName());
        System.out.println(appointment);
      }
      System.exit(0);
    }
  }
}

