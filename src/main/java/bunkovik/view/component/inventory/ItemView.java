package bunkovik.view.component.inventory;

import bunkovik.model.entity.Item.Item;
import bunkovik.view.InventoryView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ItemView extends Button {
    private Item itemRef;

    public ItemView(InventoryView inventory) {
        super();

        setPrefWidth(90);
        setPrefHeight(90);
        setDefaultStyle();

        // Focus Events
        focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                setFocusedStyle();
                inventory.setItemInfo(itemRef);
                inventory.setImageOfItem(itemRef);
            } else {
                setDefaultStyle();
            }
        });
    }

    private void setFocusedStyle() {
        setStyle(
                "-fx-background-color: rgba(85,58,95,0.76);"+
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-width: 3px;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: #ccb6d4;"
        );
    }

    private void setDefaultStyle() {
        setStyle(
                "-fx-background-color: #553a5f;"+
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-width: 1px;" +
                "-fx-border-style: solid;" +
                "-fx-border-color: #553a5f;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"
        );
    }

    public void setItem(Item item) {
        itemRef = item;
        setGraphic(new ImageView(item.getImage()));
    }

    public Item getItem() {
        return itemRef;
    }
}
