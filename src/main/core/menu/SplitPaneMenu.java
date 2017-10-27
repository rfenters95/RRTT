package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

public class SplitPaneMenu extends ContextMenu {

  public SplitPaneMenu(SplitPane splitPane) {
    getItems().add(new ShowMenuItem(splitPane));
    getItems().add(new HideMenuItem(splitPane));
  }

  private class ShowMenuItem extends MenuItem implements EventHandler<ActionEvent> {

    private SplitPane splitPane;
    private double initialDividerPosition;

    private ShowMenuItem(SplitPane splitPane) {
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

  private class HideMenuItem extends MenuItem implements EventHandler<ActionEvent> {

    private SplitPane splitPane;

    private HideMenuItem(SplitPane splitPane) {
      super("Hide Console");
      this.splitPane = splitPane;
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      splitPane.setDividerPosition(0, 1);
    }
  }

}
