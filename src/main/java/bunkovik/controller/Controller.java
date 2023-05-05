package bunkovik.controller;

import bunkovik.view.View;

public abstract class Controller {

    protected View view;

    protected boolean isOpened;

    public View getView() {
        return view;
    }

    public void reset() {
        isOpened = false;
    }

    public abstract void init();

    public abstract void tick(double delta);
}