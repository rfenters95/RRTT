package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PowerColorMenu extends ContextMenu {

  public PowerColorMenu(TextField textField) {
    getItems().add(new ColorGreenItem(textField));
    getItems().add(new ColorOrangeItem(textField));
    getItems().add(new ColorRedItem(textField));
  }

  private class ColorGreenItem extends MenuItem implements EventHandler<ActionEvent> {

    private TextField textField;

    private ColorGreenItem(TextField textField) {
      super("Special: Color Green");
      this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("0");
    }

  }

  private class ColorOrangeItem extends MenuItem implements EventHandler<ActionEvent> {

    private TextField textField;

    private ColorOrangeItem(TextField textField) {
      super("Special: Color Orange");
      this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("127");
    }

  }

  private class ColorRedItem extends MenuItem implements EventHandler<ActionEvent> {

    private TextField textField;

    private ColorRedItem(TextField textField) {
      super("Special: Color Red");
      this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("255");
    }

  }

}
