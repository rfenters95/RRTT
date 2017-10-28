package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

public class SplitPaneMenu extends ContextMenu {

  private SplitPane splitPane;

  public SplitPaneMenu(SplitPane splitPane) {
    this.splitPane = splitPane;
    getItems().add(new ShowMenuItem());
    getItems().add(new HideMenuItem());
  }

  private class ShowMenuItem extends MenuItem implements EventHandler<ActionEvent> {

    private double initialDividerPosition;

    private ShowMenuItem() {
      super("Show/Reset Console");
      this.initialDividerPosition = splitPane.getDividerPositions()[0];
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      splitPane.setDividerPosition(0, initialDividerPosition);
    }
  }

  private class HideMenuItem extends MenuItem implements EventHandler<ActionEvent> {

    private HideMenuItem() {
      super("Hide Console");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      splitPane.setDividerPosition(0, 1);
    }
  }

}
