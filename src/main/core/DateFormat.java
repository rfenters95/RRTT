package main.core;

import com.sun.javafx.binding.StringFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* DateFormat contains static methods for
* returning specially formatted date and time
* information.
* */
public class DateFormat {

  // Date formatter for console log
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
      StringFormatter.format("%12s", "hh:mm:ss:SSS").getValue());

  // Date formatter for file names
  private static final SimpleDateFormat fileNameDateFormat = new SimpleDateFormat(
      StringFormatter.format("%s", "hh.mm.ss").getValue());

  /*
  * Gets date for console log
  * @return String containing formatted date
  * */
  public static String logDate() {
    return simpleDateFormat.format(new Date());
  }

  /*
  * Gets date for file names
  * @return String containing formatted date
  * */
  public static String logDateForFileName() {
    return fileNameDateFormat.format(new Date());
  }

}
