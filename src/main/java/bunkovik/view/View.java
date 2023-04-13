package bunkovik.view;

import bunkovik.controller.Controller;
import javafx.scene.Scene;


// An abstract view class.

public abstract class View {
    protected Controller controller;

    protected Scene scene;
    public Scene getScene() {
        return scene;
    }

    abstract public void init();
    abstract public void render();
}

