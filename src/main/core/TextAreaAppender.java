package main.core;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaAppender extends WriterAppender {

  public static TextArea textArea;

  @Override
  public void append(LoggingEvent event) {

    /*
    * Version 1.0
    *
    * Status: Working
    * Reason for update: When implementing threads for SensorTabController
    * discovered that calling appendText without enclosing in
    * Platform.runLater may result in GUI thread flooding.
    *
    * See https://github.com/rfenters95/RRTT/commit/5d6e7604ddcfc2441b24944e4bfb68be2ca9b936
    * for more info. (Semi solution for sensor threads)
    *
    * See http://www.rshingleton.com/javafx-log4j-textarea-log-appender/
    * for more info.
    *
    * Version 1.0 Code:
    * textArea.appendText(this.layout.format(event));
    *
    * */

    final String message = this.layout.format(event);

    // Append formatted message to text area using the Thread.
    try {
      Platform.runLater(() -> {
        try {
          if (textArea != null) {
            textArea.appendText(message);
          }
        } catch (final Throwable t) {
          System.out.println("Unable to append log to text area: "
              + t.getMessage());
        }
      });
    } catch (final IllegalStateException e) {
      // ignore case when the platform hasn't yet been initialized
    }
  }

}
