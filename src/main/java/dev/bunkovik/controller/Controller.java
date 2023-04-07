package dev.bunkovik.controller;

import dev.bunkovik.view.View;

public abstract class Controller {
    protected View view;
    protected boolean wasInitialized;
    public View getView() {
        return view;
    }
    public abstract void init();
    public void reset() {
        wasInitialized = false;
    }
}
