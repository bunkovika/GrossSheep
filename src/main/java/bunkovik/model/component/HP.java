package bunkovik.model.component;

import java.util.Observable;
public class HP extends Observable {
    private double maxHealth;
    private double currentHealth;

    public HP(double health) {
        this.maxHealth = health;
        this.currentHealth = health;
    }

    public void addHealth(double health) {
        currentHealth = Math.min(currentHealth + health, maxHealth);
        setChanged();
        notifyObservers(Math.round(currentHealth));
    }

    public void reduceHealth(double health) {
        currentHealth = Math.max(currentHealth - health, 0);
        setChanged();
        notifyObservers(Math.round(currentHealth));
    }

    public void setMaxHealth(double health) {
        this.maxHealth = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }


    public double getHealth() {
        return Math.round(currentHealth);
    }
}
