package br.com.jefperito.medievalgame.core.entity.equipament;

import br.com.jefperito.medievalgame.core.entity.Entity;

public class Boot extends Entity {

    private int impetiusPoints;

    private int defensePoints;

    public static Boot basic() {
        Boot boot = new Boot();
        boot.defensePoints = 6;
        boot.impetiusPoints = 9;
        return boot;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public int getImpetiusPoints() {
        return impetiusPoints;
    }
}
