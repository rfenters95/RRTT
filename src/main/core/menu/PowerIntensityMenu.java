package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PowerIntensityMenu extends ContextMenu {

  public PowerIntensityMenu(TextField textField) {
    getItems().add(new NoIntensityItem(textField));
    getItems().add(new HalfIntensityItem(textField));
    getItems().add(new FullIntensityItem(textField));
  }

  private class NoIntensityItem extends MenuItem implements EventHandler<ActionEvent> {

    private TextField textField;

    private NoIntensityItem(TextField textField) {
      super("Special: Intensity None");
      this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("0");
    }

  }

  private class HalfIntensityItem extends MenuItem implements EventHandler<ActionEvent> {

    private TextField textField;

    private HalfIntensityItem(TextField textField) {
      super("Special: Intensity Half");
      this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("127");
    }

  }

  private class FullIntensityItem extends MenuItem implements EventHandler<ActionEvent> {

    private TextField textField;

    private FullIntensityItem(TextField textField) {
      super("Special: Intensity Full");
      this.textField = textField;
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("255");
    }

  }

}
