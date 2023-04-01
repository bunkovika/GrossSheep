package dev.bunkovik.view;

import javafx.scene.Scene;

/**
 * An abstract view class.
 */
public abstract class AView {

    protected Scene scene;
    public Scene getScene() {
        return scene;
    }
    abstract public void init();
}

