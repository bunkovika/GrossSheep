package bunkovik.view;

import bunkovik.controller.Controller;
import javafx.scene.Scene;
public abstract class View {

    protected Controller controller;

    protected Scene scene;

    public Scene getScene() {
        return scene;
    }

    abstract public void init();

    abstract public void render();
}
