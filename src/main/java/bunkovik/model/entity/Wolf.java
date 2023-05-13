package bunkovik.model.entity;

import bunkovik.core.sprite.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.logging.Logger;

public class Wolf extends Sprite {
    // Logger
    private static Logger log = Logger.getLogger(Sheep.class.getName());
    private Sheep aim;

    // Characteristics
    protected final int id;
    private final String name;
    private final double damage;
    private final double damageRadius;
    private final double viewingRadius;
    private double health;
    private double attackSpeed = 300;

    private boolean inCombat = false;
    private boolean isDead;
    private double lastAttack;

    private String[] imagesAtFixedPosition; // stores the four images
    private int currentImageIndex; // keeps track of the current image index
    private long lastImageChangeTime;

    public Wolf(int id, String name, double health, double damage, double damageRadius, double viewingRadius, double attackSpeed, String[] images) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.damageRadius = damageRadius;
        this.viewingRadius = viewingRadius;
        this.attackSpeed = attackSpeed;

        this.imagesAtFixedPosition = images;
        this.currentImageIndex = 0;
        this.lastImageChangeTime = System.currentTimeMillis();

//         for attack
        currentDirection = Direction.BOTTOM;

    }
    public void attack(Sheep sheep) {
        if ((System.currentTimeMillis() - lastAttack) < attackSpeed) {
            return;
        }

        lastAttack = System.currentTimeMillis(); // cooldown

        if (!sheep.isDead()) {
            sheep.inAttack(this);
        }
    }

    public void inAttack(Sheep sheep) {
        double incomingDamage = sheep.getDamage();
        log.info("Monster \"" + getName() + "\" was attacked by player." + " Incoming damage is " + incomingDamage + ".");
        health = Math.max(health - incomingDamage, 0);

        if (health == 0) {
            isDead = true;
            log.info("Monster \"" + name + "\" is dead!");
        }

        // Counterattack
        attack(sheep);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public double getDamageRadius() {
        return damageRadius;
    }


    public double getViewingRadius() {
        return viewingRadius;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean intersectsRadiusViewBox(Sprite s) {
        return s.getCollisionBox().intersects(this.getRadiusViewCollisionBox());
    }

    public boolean intersectsDamageBox(Sprite s) {
        return s.getCollisionBox().intersects(this.getDamageRadiusBox());
    }

    public Rectangle2D getDamageRadiusBox() {
        double damageRadius = getDamageRadius();

        return new Rectangle2D(
                positionX - damageRadius,
                positionY - damageRadius,
                width + (2 * damageRadius),
                height + (2 * damageRadius));
    }

    public Rectangle2D getRadiusViewCollisionBox() {
        double viewingRadius = getViewingRadius();

        return new Rectangle2D(
                positionX - viewingRadius,
                positionY - viewingRadius,
                width + (2 * viewingRadius),
                height + (2 * viewingRadius));
    }


    public void setAim(Sheep sheep) {
        if (aim == null) {
            aim = sheep;
        }
        inCombat = true;
    }

    private void moveToAim() {
        if (intersectsDamageBox(aim)) {
            attack(aim);
            return;
        }

        double aimX = aim.getX();
        double aimY = aim.getY();

        if (Math.abs(aimX - positionX) > 10) {
            if (aimX > positionX) {
                moveRight(1.0);
            } else {
                moveLeft(1.0);
            }
        }

        if (Math.abs(aimY - positionY) > 10) {
            if (aimY > positionY) {
                moveDown(1.0);
            } else {
                moveUp(1.0);
            }
        }
    }
    public void setImageAttack (Image image){
        // Setting Up Direction Images
        images.put(Direction.TOP, new Image("wolf/1/Attack/wolf_top.png"));
        images.put(Direction.RIGHT, new Image("wolf/1/Attack/wolf_right.png"));
        images.put(Direction.BOTTOM, new Image("wolf/1/Attack/wolf_bottom.png"));
        images.put(Direction.LEFT, new Image("wolf/1/Attack/wolf_left.png"));

        setImage(images.get(currentDirection));

    }
    @Override
    public void moveUp(double path) {
        if (currentDirection != Direction.TOP) {
            currentDirection = Direction.TOP;
            setImageAttack(images.get(currentDirection));
        }
        positionY -= path;
    }
    @Override
    public void moveRight(double path) {
        if (currentDirection != Direction.RIGHT) {
            currentDirection = Direction.RIGHT;
            setImageAttack(images.get(currentDirection));
        }
        positionX += path;
    }

    @Override
    public void moveDown(double path) {
        if (currentDirection != Direction.BOTTOM) {
            currentDirection = Direction.BOTTOM;
            setImageAttack(images.get(currentDirection));
        }
        positionY += path;
    }


    @Override
    public void moveLeft(double path) {
        if (currentDirection != Direction.LEFT) {
            currentDirection = Direction.LEFT;
            setImageAttack(images.get(currentDirection));
        }
        positionX -= path;
    }
    private void stayingAtFixedPosition() {
        setPosition(positionX, positionY);
        updateImage();

    }
    public void updateImage() {
        if (images == null) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastImageChangeTime > 150) {
            currentImageIndex = (currentImageIndex + 1) % imagesAtFixedPosition.length;
            setImage(imagesAtFixedPosition[currentImageIndex]);
            if(currentImageIndex == 0){
                lastImageChangeTime = currentTime+ 1000;
            } else if(currentImageIndex == (imagesAtFixedPosition.length % 2)+2){
                lastImageChangeTime = currentTime+ 3500;
            }else{ lastImageChangeTime = currentTime;}
        }
    }

    public void offCombat() {
        inCombat = false;
    }
    @Override
    public void update(double delta) {
        if (!inCombat) {
            stayingAtFixedPosition();
        } else {
            moveToAim();
        }
    }
}
