package br.com.jefperito.medievalgame.core.usecase.getrandomenemy;

import br.com.jefperito.medievalgame.core.engine.EnemyGenerator;
import br.com.jefperito.medievalgame.core.entity.creature.Creature;

public class GetRandomEnemy {

    public Creature getRandom() {
        return EnemyGenerator.build();
    }
}
