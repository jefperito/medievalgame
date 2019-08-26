package br.com.jefperito.medievalgame.core.engine;

import br.com.jefperito.medievalgame.core.entity.creature.Creature;

public class EnemyGenerator {

    public static Creature build() {
        Creature creature = Creature.build("Very Weak");
        creature.setHealthPoints(190);
        creature.setManaPoints(0);
        creature.setMagicPoints(0);
        creature.setVelocityPoints(7);
        creature.setStrengthPoints(18);
        return creature;
    }
}
