package main.core.contextmenus.console;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import main.core.contextmenus.console.items.QuickSaveLogMenuItem;
import main.core.contextmenus.console.items.SaveLogMenuItem;

public class ConsoleContextMenu extends ContextMenu {

  public ConsoleContextMenu(Pane root, TextArea console) {
    getItems().add(new SaveLogMenuItem(root, console));
    getItems().add(new QuickSaveLogMenuItem(root, console));
  }

}
