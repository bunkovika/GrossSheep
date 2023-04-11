package bunkovik.config;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class WindowConfig {
    private static double windowWidth = 1000;
    private static double windowHeight = 700;

    public static void setWindowWidth(double width) {
        windowWidth = width;
    }

    public static void setWindowHeight(double height) {
        windowHeight = height;
    }

    public static double getWindowWidth() {
        return windowWidth;
    }

    public static double getWindowHeight() {
        return windowHeight;
    }
    public static void setFullScreenSize() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        setWindowWidth(primaryScreenBounds.getWidth());
        setWindowHeight(primaryScreenBounds.getHeight());
    }
}
