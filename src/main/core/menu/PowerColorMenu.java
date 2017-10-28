package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PowerColorMenu extends ContextMenu {

  private TextField textField;

  public PowerColorMenu(TextField textField) {
    this.textField = textField;
    getItems().add(new ColorGreenItem());
    getItems().add(new ColorOrangeItem());
    getItems().add(new ColorRedItem());
  }

  private class ColorGreenItem extends MenuItem implements EventHandler<ActionEvent> {

    private ColorGreenItem() {
      super("Special: Color Green");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("0");
    }

  }

  private class ColorOrangeItem extends MenuItem implements EventHandler<ActionEvent> {

    private ColorOrangeItem() {
      super("Special: Color Orange");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("127");
    }

  }

  private class ColorRedItem extends MenuItem implements EventHandler<ActionEvent> {

    private ColorRedItem() {
      super("Special: Color Red");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("255");
    }

  }

}
