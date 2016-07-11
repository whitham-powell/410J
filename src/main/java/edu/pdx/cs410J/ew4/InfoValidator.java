package edu.pdx.cs410J.ew4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * TODO document info validator class
 * Created by edub629 on 7/10/16.
 */
public class InfoValidator {
  private int errCode = 0;
  private String errMsg = "";
  private boolean hasFailed = false;
  private String[] info;

  public String[] cleanse() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    List<String> aL = Arrays.asList(info);
    String[] split;
    split = df.format(getBeginDate()).split(" ");
    aL.set(1, df.format(split[0]));
    aL.set(2, df.format(split[1]));
    split = df.format(getEndDate()).split(" ");
    aL.set(3, df.format(split[0]));
    aL.set(4, df.format(split[1]));
    return aL.toArray(new String[info.length]);
  }

  public InfoValidator(String[] info) {
    this.info = info;
    StringBuilder errReason = new StringBuilder();

    // Check date formatting of beginTimeString
    if (!info[1].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Date - beginTime\n");
      this.errCode = 1;
    }

    // Check time formatting of beginTimeString
    if (!info[2].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Time - beginTime\n");
      this.errCode = 1;
    }

    // Check date formatting of endTimeString
    if (!info[3].matches("\\d{1,2}\\/\\d{1,2}\\/\\d{4}")) {
      errReason.append(" Date - endTime\n");
      this.errCode = 1;
    }

    // Check time formatting of endTimeString
    if (!info[4].matches("\\d{1,2}\\:\\d{2}")) {
      errReason.append(" Time - endTime\n");
      this.errCode = 1;
    }
    if (this.errCode == 1) {
      this.hasFailed = true;
      this.errMsg = ("Bad formatting: \n" + errReason.toString());
    }
  }

  private Date getBeginDate() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      Date date;
      date = df.parse(info[1] + " " + info[2]);
      return date;
    } catch (NullPointerException e) {
      return null;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Date getEndDate() {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    try {
      Date date;
      date = df.parse(info[3] + " " + info[4]);
      return date;
    } catch (NullPointerException e) {
      return null;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public int getErrCode() {
    return errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public boolean hasFailed() {
    return hasFailed;
  }
}
