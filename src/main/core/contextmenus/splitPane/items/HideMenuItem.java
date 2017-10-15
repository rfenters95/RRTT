package main.core.contextmenus.splitPane.items;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

public class HideMenuItem extends MenuItem implements EventHandler<ActionEvent> {

  private SplitPane splitPane;

  public HideMenuItem(SplitPane splitPane) {
    super("Hide Console");
    this.splitPane = splitPane;
    setOnAction(this);
  }

  @Override
  public void handle(ActionEvent event) {
    splitPane.setDividerPosition(0, 1);
  }
}
