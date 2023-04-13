package bunkovik.model;
import bunkovik.model.entity.Sheep;
import java.util.logging.Logger;
public class GameModel {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private static GameModel instance;
    private Sheep sheep;
    public Sheep getSheep() {
        return sheep;
    }


}
