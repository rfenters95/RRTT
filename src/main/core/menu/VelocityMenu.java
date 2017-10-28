package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class VelocityMenu extends ContextMenu {

  private TextField textField;

  public VelocityMenu(TextField textField) {
    this.textField = textField;
    getItems().add(new MaxVelocityItem());
    getItems().add(new NoVelocityItem());
    getItems().add(new MinVelocityItem());
  }

  private class MaxVelocityItem extends MenuItem implements EventHandler<ActionEvent> {

    private MaxVelocityItem() {
      super("Special: Max Forward");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("500");
    }
  }

  private class NoVelocityItem extends MenuItem implements EventHandler<ActionEvent> {

    private NoVelocityItem() {
      super("Special: Stop");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("0");
    }
  }

  private class MinVelocityItem extends MenuItem implements EventHandler<ActionEvent> {

    private MinVelocityItem() {
      super("Special: Max Reverse");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("-500");
    }
  }

}
