package main.core;

import main.ui.root.RootController;

/*
* Nested controllers of Root Controller implement Injectable.
* Keeps injection process consistence and easy to understand || debug.
* */
public interface Injectable {

  void inject(RootController rootController);

}
