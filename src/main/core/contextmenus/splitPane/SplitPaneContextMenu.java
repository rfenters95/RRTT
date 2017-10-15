package main.core.contextmenus.splitPane;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.SplitPane;
import main.core.contextmenus.splitPane.items.HideMenuItem;
import main.core.contextmenus.splitPane.items.ShowMenuItem;

public class SplitPaneContextMenu extends ContextMenu {

  public SplitPaneContextMenu(SplitPane splitPane) {
    getItems().add(new ShowMenuItem(splitPane));
    getItems().add(new HideMenuItem(splitPane));
  }
}
