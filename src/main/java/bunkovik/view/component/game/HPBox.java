package bunkovik.view.component.game;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class HPBox {
    private final Text health;
    public HPBox(double health) {
        this.health = new Text(50, 50, "HP: " + health);
        this.health.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        this.health.setFill(Color.WHITE);
    }
    public Text getText() {
        return health;
    }

    public void update(Object health) {

    }
}
