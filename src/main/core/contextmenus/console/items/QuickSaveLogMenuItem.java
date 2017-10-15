package main.core.contextmenus.console.items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import main.core.RoombaJSSCSingleton;

public class QuickSaveLogMenuItem extends MenuItem implements EventHandler<ActionEvent> {

  private Pane root;
  private TextArea console;

  public QuickSaveLogMenuItem(Pane root, TextArea console) {
    super("Log: Quick Save");
    this.root = root;
    this.console = console;
    setOnAction(this);
  }

  @Override
  public void handle(ActionEvent event) {
    try {
      String desktopPath = System.getProperty("user.home") + "/Desktop/log-"
          + RoombaJSSCSingleton.logDateForFileName() + ".txt";
      System.out.println(desktopPath);
      File logFile = new File(desktopPath);
      PrintWriter printWriter = new PrintWriter(logFile);
      printWriter.print(console.getText());
      printWriter.close();
    } catch (FileNotFoundException x) {
      // alert
      x.getMessage();
    }
  }

}
