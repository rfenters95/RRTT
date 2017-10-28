package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class RadiusMenu extends ContextMenu {

  private TextField textField;

  public RadiusMenu(TextField textField) {
    this.textField = textField;
    getItems().add(new DriveStraightItem());
    getItems().add(new RotateClockwiseItem());
    getItems().add(new RotateCounterClockwiseItem());
  }

  private class DriveStraightItem extends MenuItem implements EventHandler<ActionEvent> {

    private DriveStraightItem() {
      super("Special: Drive Straight");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("32768");
    }
  }

  private class RotateClockwiseItem extends MenuItem implements EventHandler<ActionEvent> {

    private RotateClockwiseItem() {
      super("Special: Rotate Clockwise");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("-1");
    }
  }

  private class RotateCounterClockwiseItem extends MenuItem implements EventHandler<ActionEvent> {

    private RotateCounterClockwiseItem() {
      super("Special: Rotate Counter Clockwise");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("1");
    }
  }

}
