package br.com.jefperito.medievalgame.core.entity.creature;

import br.com.jefperito.medievalgame.core.entity.equipament.Armor;
import br.com.jefperito.medievalgame.core.entity.equipament.Boot;
import br.com.jefperito.medievalgame.core.entity.equipament.Helmet;
import br.com.jefperito.medievalgame.core.entity.equipament.Weapon;

public class Character extends Creature {

    private Helmet helmet;

    private Armor armor;

    private Boot boot;

    private Weapon weapon;

    private Character() {
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Boot getBoot() {
        return boot;
    }

    public void setBoot(Boot boot) {
        this.boot = boot;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public static Character buildWarrior(String name, int strengthPoints, int velocityPoints) {
        Character character = new Character();
        character.setName(name);
        character.setStrengthPoints(strengthPoints);
        character.setVelocityPoints(velocityPoints);
        character.setHealthPoints(340);
        character.setManaPoints(30);
        character.setMagicPoints(5);
        character.setWeapon(Weapon.basicSword());
        character.setArmor(Armor.basic());
        character.setBoot(Boot.basic());
        character.setHelmet(Helmet.basic());
        return character;
    }

    @Override
    public double getPowerAttack() {
        return super.getPowerAttack() + (getWeapon().getAttackPoints() * 1.45);
    }

    @Override
    public double getPowerDefense() {
        int armorDefensePoints = getArmor().getDefensePoints();
        int helmetDefensePoints = getHelmet().getDefensePoints();
        int bootDefensePoints = getBoot().getDefensePoints();
        return super.getPowerDefense() + armorDefensePoints + helmetDefensePoints + bootDefensePoints;
    }
}
