package br.com.jefperito.medievalgame.core.entity.equipament;

import br.com.jefperito.medievalgame.core.entity.Entity;

public class Helmet extends Entity {

    private int defensePoints;

    public static Helmet basic() {
        Helmet helmet = new Helmet();
        helmet.defensePoints = 8;
        return helmet;
    }

    public int getDefensePoints() {
        return defensePoints;
    }
}
