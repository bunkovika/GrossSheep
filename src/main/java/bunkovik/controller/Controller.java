package bunkovik.controller;

import bunkovik.view.View;

public abstract class Controller {
    protected View view;
    protected boolean isOpened;
    public View getView() {
        return view;
    }
    public abstract void init();
    public void reset() {
        isOpened = false;
    }
}
