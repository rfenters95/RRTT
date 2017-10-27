package main.ui.modules;

import main.core.Injectable;
import main.ui.root.RootController;

public class ModuleController implements Injectable {

  public RootController rootController;

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }
}
