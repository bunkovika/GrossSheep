package bunkovik.config;

import javafx.animation.AnimationTimer;

class GameLoop extends AnimationTimer {
    private long lastNanoTime = System.nanoTime();

    @Override
    public void handle(long now) {
        double delta = (now - lastNanoTime) / 1000000000.0;

        // Frame rate cap
        if (delta > 1/1000.00) {
            lastNanoTime = now;
            StateManager.getCurrentState().tick(delta);
        }
    }
}

