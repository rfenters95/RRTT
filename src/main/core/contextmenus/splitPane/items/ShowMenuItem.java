package main.core.contextmenus.splitPane.items;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

public class ShowMenuItem extends MenuItem implements EventHandler<ActionEvent> {

  private SplitPane splitPane;
  private double initialDividerPosition;

  public ShowMenuItem(SplitPane splitPane) {
    super("Show/Reset Console");
    this.splitPane = splitPane;
    this.initialDividerPosition = splitPane.getDividerPositions()[0];
    setOnAction(this);
  }

  @Override
  public void handle(ActionEvent event) {
    splitPane.setDividerPosition(0, initialDividerPosition);
  }
}
