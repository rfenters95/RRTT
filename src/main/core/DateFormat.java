package main.core;

import com.sun.javafx.binding.StringFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
      StringFormatter.format("%12s", "hh:mm:ss:SSS").getValue());

  private static final SimpleDateFormat fileSafeDateFormat = new SimpleDateFormat(
      StringFormatter.format("%s", "hh.mm.ss").getValue());

  public static String logDate() {
    return simpleDateFormat.format(new Date());
  }

  public static String logDateForFileName() {
    return fileSafeDateFormat.format(new Date());
  }

}
