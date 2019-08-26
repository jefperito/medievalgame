package br.com.jefperito.medievalgame.core.entity.equipament;

import br.com.jefperito.medievalgame.core.entity.Entity;

public class Armor extends Entity {

    private int defensePoints;

    public static Armor basic() {
        Armor armor = new Armor();
        armor.defensePoints = 7;
        return armor;
    }

    public int getDefensePoints() {
        return defensePoints;
    }
}
