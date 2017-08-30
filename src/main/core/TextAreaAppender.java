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
        * Version 1.0 Works
        *
        * textArea.appendText(this.layout.format(event));
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
