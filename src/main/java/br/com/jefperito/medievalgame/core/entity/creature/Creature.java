package br.com.jefperito.medievalgame.core.entity.creature;

import br.com.jefperito.medievalgame.core.entity.Entity;

public class Creature extends Entity {

    private int velocityPoints;

    private int strengthPoints;

    private int magicPoints;

    private int healthPoints;

    private int manaPoints;

    public static Creature build(String name) {
        Creature creature = new Creature();
        creature.setName(name);
        return creature;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getVelocityPoints() {
        return velocityPoints;
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }

    public void increaseHeathPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public void increaseManaPoints(int manaPoints) {
        this.manaPoints += manaPoints;
    }

    public void increaseStrengthPoints(int strengthPoints) {
        this.strengthPoints += strengthPoints;
    }

    protected void increaseVelocityPoints(int velocityPoints) {
        this.velocityPoints += velocityPoints;
    }

    public void increaseMagicPoints(int magicPoints) {
        this.magicPoints += magicPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public void setVelocityPoints(int velocityPoints) {
        this.velocityPoints = velocityPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public void setStrengthPoints(int strengthPoints) {
        this.strengthPoints = strengthPoints;
    }

    public boolean isAlive() {
        return getHealthPoints() > 0;
    }

    public double getPowerAttack() {
        return getStrengthPoints();
    }

    public double getPowerDefense() {
        return getStrengthPoints() * 0.25;
    }

    public void decreaseHealthPoints(int healthPoints) {
        this.healthPoints -= healthPoints;
    }

    public void decreaseManaPoints(int manaPoints) {
        this.manaPoints -= manaPoints;
    }
}
