package bunkovik.view;

import bunkovik.controller.InventoryController;
import bunkovik.core.Config;
import bunkovik.model.GameModel;
import bunkovik.model.component.Equipment;
import bunkovik.model.entity.Item.Item;
import bunkovik.model.entity.Item.equipment.Weapon;
import bunkovik.model.entity.Item.food.Food;
import bunkovik.model.entity.Sheep;
import bunkovik.view.component.inventory.ItemView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Observable;
import java.util.Observer;

import static javafx.geometry.Pos.TOP_CENTER;


public class Inventory extends View implements Observer {
    private VBox itemInfoView;
    private Pane itemInfoContainer;
    private VBox itemsView;
    private VBox imageCont;
    private Label capacity;
    private FlowPane items;
    private FlowPane equippedItems;
    private bunkovik.model.component.Inventory inventory;
    private Sheep sheep;


    public Inventory(InventoryController controller) {
        this.controller = controller;
    }

    public void init() {
        GameModel gameModel = GameModel.getInstance();
        sheep = gameModel.getPlayer();
        inventory = sheep.getInventory();

        // Main Pane
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-background-color: #553A5F; ");
        hbox.setPadding(new Insets(70));

        //        main VBOX which has all items
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #956E8D; "+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        vbox.setPrefWidth(1100);
        vbox.setPrefHeight(700);
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(20);

//        VBOX Up
        HBox vboxUp = new HBox();
        vboxUp.setAlignment(TOP_CENTER);
        vboxUp.setStyle("-fx-background-color: #ECD2ED;"+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        vboxUp.setPrefWidth(900);
        vboxUp.setPrefHeight(350);
        vboxUp.setPadding(new Insets(35, 0, 0, 0));
        vboxUp.setSpacing(30);

//        Image container
        imageCont = new VBox();
        imageCont.setAlignment(Pos.CENTER_LEFT);
        imageCont.setStyle("-fx-background-color: #956E8D;"+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        imageCont.setPrefWidth(270);
        imageCont.setMaxHeight(270);

//        Item description
        itemInfoView = new VBox();
        itemInfoView.setStyle("-fx-background-color: #956E8D;"+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        itemInfoView.setPrefWidth(390);
        itemInfoView.setMaxHeight(270);
        itemInfoView.setPadding(new Insets(20));
        // Title
        Label itemInfoTitle = new Label("Item characteristics");
        itemInfoTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        itemInfoTitle.setTextFill(Color.web("#ECD2ED"));
        itemInfoTitle.setWrapText(true);

        itemInfoContainer = new Pane();

        itemInfoView.getChildren().add(itemInfoTitle);
        itemInfoView.getChildren().add(itemInfoContainer);

//        Equiped element
        VBox equipCont = new VBox();
        equipCont.setStyle("-fx-background-color: #956E8D;"+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        equipCont.setPrefWidth(130);
        equipCont.setMaxHeight(270);
        equipCont.setPadding(new Insets(20,0,20,10));

        // Equipped Items Title
        Label equippedItemsTitle = new Label("Equipped Items");
        equippedItemsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        equippedItemsTitle.setTextFill(Color.web("#ECD2ED"));
        equippedItemsTitle.setWrapText(true);

        // Equipped Items
        equippedItems = new FlowPane();
        equippedItems.setHgap(24);
        equippedItems.setVgap(24);
        equippedItems.setPrefWidth(64);
        equippedItems.setPrefHeight(64);
        equippedItems.setPadding(new Insets(30,30,20,15));

        equipCont.getChildren().add(equippedItemsTitle);
        equipCont.getChildren().add(equippedItems);

//        VBOX Up adding children
        vboxUp.getChildren().add(imageCont);
        vboxUp.getChildren().add(itemInfoView);
        vboxUp.getChildren().add(equipCont);

        //        VBOX Down
        itemsView = new VBox();
        itemsView.setAlignment(Pos.CENTER);
        itemsView.setStyle("-fx-background-color: #ECD2ED;"+
                "-fx-border-radius: 5; "+
                "-fx-background-radius: 5; " );
        itemsView.setPrefWidth(900);
        itemsView.setPrefHeight(150);

        // Capacity
        capacity = new Label("Capacity " + inventory.getQuantity() + " / " + inventory.getCapacity());
        capacity.setTextFill(Color.web("#553A5F"));
        capacity.setAlignment(Pos.TOP_LEFT);
        capacity.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        VBox.setMargin(capacity, new Insets(0, 0, 0, 0));

        // Items
        items = new FlowPane();
        items.setHgap(24);
        items.setVgap(24);
        items.setPrefWidth(64);
        items.setPrefHeight(64);
        items.setAlignment(Pos.CENTER);
        items.setPadding(new Insets(10));

        renderItems();
        renderEquippedItems();

        itemsView.getChildren().add(capacity);
        itemsView.getChildren().add(items);

//        keyInfo
        Label keyInfo = new Label("E - equip(weapon)       C - consume(food)");
        keyInfo.setTextFill(Color.web("#ECD2ED"));
        keyInfo.setFont(Font.font("Arial", FontWeight.BOLD, 25));

//        vbox adding child
        vbox.getChildren().add(vboxUp);
        vbox.getChildren().add(itemsView);
        vbox.getChildren().add(keyInfo);

        hbox.getChildren().add(vbox);

        // Setting Scene
        scene = new Scene(hbox, Config.getWindowWidth(), Config.getWindowHeight(), Color.BLACK);

        // Attaching Event Listeners
        scene.setOnKeyPressed(((InventoryController) controller)::keyPressedHandler);

        // Add observer
        inventory.addObserver(this);
        sheep.getEquipment().addObserver(this);
    }

    private void renderEquippedItems() {
        equippedItems.getChildren().clear();

        ItemView weaponView = new ItemView(this);
        equippedItems.getChildren().add(weaponView);

        Item currentWeapon = sheep.getCurrentWeapon();
        if (currentWeapon != null) {
            weaponView.setItem(currentWeapon);
        }
    }

    private void renderItems() {
        items.getChildren().clear();

        for (int i = 0; i < inventory.getCapacity(); i++) {
            items.getChildren().add(new ItemView(this));
        }

        int count = 0;
        for (Item item : inventory.getItems()) {
            ((ItemView) items.getChildren().get(count)).setItem(item);
            count++;
        }
    }

    public void setImageOfItem(Item item){
        imageCont.getChildren().clear();
        if(item == null){
            return;
        }
        ImageView clickedImageView = new ImageView(item.getImage());

        clickedImageView.setFitHeight(200);
        clickedImageView.setFitWidth(200);
        VBox.setMargin(clickedImageView, new Insets(0, 0, 0, (imageCont.getWidth() - clickedImageView.getBoundsInLocal().getWidth()) / 2));
        imageCont.getChildren().add(clickedImageView);
    }

    public void setItemInfo(Item item) {
        itemInfoContainer.getChildren().clear();
        if (item == null) return;

        VBox itemInfo = new VBox();
        itemInfo.setSpacing(10);

        // Name
        Text nameTitle = new Text("Name:");
        nameTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        nameTitle.setFill(Color.web("#ECD2ED"));

        Text name = new Text(item.getName());
        name.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        name.setFill(Color.web("#ECD2ED"));

        itemInfo.getChildren().add(nameTitle);
        itemInfo.getChildren().add(name);

        // Weapon
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon) item;

            // Damage
            Text damageTitle = new Text("Damage:");
            damageTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            damageTitle.setFill(Color.web("#ECD2ED"));

            Text damage = new Text(String.valueOf(weapon.getDamage()));
            damage.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
            damage.setFill(Color.web("#ECD2ED"));

            // Damage Radius
            Text damageRadiusTitle = new Text("Damage Radius:");
            damageRadiusTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            damageRadiusTitle.setFill(Color.web("#ECD2ED"));

            Text damageRadius = new Text(String.valueOf(weapon.getRadius()));
            damageRadius.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
            damageRadius.setFill(Color.web("#ECD2ED"));

            itemInfo.getChildren().add(damageTitle);
            itemInfo.getChildren().add(damage);
            itemInfo.getChildren().add(damageRadiusTitle);
            itemInfo.getChildren().add(damageRadius);
        }

        // Food
        if (item instanceof Food) {
            Food food = (Food) item;

            //Health
            Text foodTitle = new Text("Health:");
            foodTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            foodTitle.setFill(Color.web("#ECD2ED"));

            Text health = new Text("+" + food.getHealth());
            health.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
            health.setFill(Color.web("#ECD2ED"));

            itemInfo.getChildren().add(foodTitle);
            itemInfo.getChildren().add(health);
        }

        itemInfoContainer.getChildren().add(itemInfo);
    }

    @Override
    public void render() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof bunkovik.model.component.Inventory) {
            capacity.setText("Capacity " + inventory.getQuantity() + " / " + inventory.getCapacity());
            renderItems();
        }

        if (o instanceof Equipment) {
            renderEquippedItems();
        }
    }
}