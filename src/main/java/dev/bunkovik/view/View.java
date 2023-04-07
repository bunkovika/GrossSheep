package dev.bunkovik.view;

import dev.bunkovik.controller.Controller;
import javafx.scene.Scene;

/**
 * An abstract view class.
 */
public abstract class View {
    protected Controller controller;

    protected Scene scene;
    public Scene getScene() {
        return scene;
    }
    abstract public void init();
}

