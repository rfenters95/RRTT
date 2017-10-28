package main.core.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PowerIntensityMenu extends ContextMenu {

  private TextField textField;

  public PowerIntensityMenu(TextField textField) {
    this.textField = textField;
    getItems().add(new NoIntensityItem());
    getItems().add(new HalfIntensityItem());
    getItems().add(new FullIntensityItem());
  }

  private class NoIntensityItem extends MenuItem implements EventHandler<ActionEvent> {

    private NoIntensityItem() {
      super("Special: Intensity None");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("0");
    }

  }

  private class HalfIntensityItem extends MenuItem implements EventHandler<ActionEvent> {

    private HalfIntensityItem() {
      super("Special: Intensity Half");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("127");
    }

  }

  private class FullIntensityItem extends MenuItem implements EventHandler<ActionEvent> {

    private FullIntensityItem() {
      super("Special: Intensity Full");
      setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
      textField.setText("255");
    }

  }

}
