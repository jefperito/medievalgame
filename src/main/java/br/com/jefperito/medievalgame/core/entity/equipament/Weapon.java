package br.com.jefperito.medievalgame.core.entity.equipament;

import br.com.jefperito.medievalgame.core.entity.Entity;

public class Weapon extends Entity {

    private int attackPoints;

    public static Weapon basicSword() {
        Weapon weapon = new Weapon();
        weapon.attackPoints = 8;
        return weapon;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
