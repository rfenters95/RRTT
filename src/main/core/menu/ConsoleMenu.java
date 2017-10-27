package main.core.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import main.core.RoombaJSSCSingleton;

public class ConsoleMenu extends ContextMenu {

  public ConsoleMenu(Pane root, TextArea console) {
    getItems().add(new SaveLogMenuItem(root, console));
    getItems().add(new QuickSaveLogMenuItem(root, console));
  }

  private class SaveLogMenuItem extends MenuItem implements EventHandler<ActionEvent> {

    private Pane root;
    private TextArea console;

    private SaveLogMenuItem(Pane root, TextArea console) {
      super("Log: Save");
      this.root = root;
      this.console = console;
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
      Stage stage = (Stage) (root.getScene().getWindow());
      File selectedFile = fileChooser.showSaveDialog(stage);
      if (selectedFile != null) {
        try {
          PrintWriter printWriter = new PrintWriter(selectedFile);
          printWriter.print(console.getText());
          printWriter.close();
        } catch (FileNotFoundException ex) {
          ex.printStackTrace();
        }
      }
    }

  }

  private class QuickSaveLogMenuItem extends MenuItem implements EventHandler<ActionEvent> {

    private Pane root;
    private TextArea console;

    private QuickSaveLogMenuItem(Pane root, TextArea console) {
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

}
