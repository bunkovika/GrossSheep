package bunkovik.model.component;

import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Sheep;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

public class Inventory extends Observable {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private static Inventory instance;
    private int capacity;

    private ArrayList<Item> items = new ArrayList<>();

    public Inventory(int capacity) {
        this.capacity = capacity;
    }

    public boolean addItem(Item item) {
        if (!isFull()) {
                items.add(item);
                setChanged();
                notifyObservers();

                log.info("Item \"" + item.getName() + "\" was added to inventory!");
                return true;
        } else {
            log.info("Inventory is full!");
            return false;
        }
    }

    public boolean removeItem(Item item) {
        if (items.remove(item)) {
            setChanged();
            notifyObservers();
            log.info("Item \"" + item.getName() + "\" was deleted from inventory!");
            return true;
        };

        return false;
    }

    public boolean isInInventory(Item item) {
        return items.contains(item);
    }


    public boolean isFull() {
        return capacity == items.size();
    }

    public int getQuantity() {
        return items.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void resetInventory() {
        items = new ArrayList<>();
        setChanged();
        notifyObservers();
    }
//    public static Inventory getInventory() {
//        if (instance == null) {
//            instance = new Inventory(8);
//        }
//        return instance;
//    }
}
