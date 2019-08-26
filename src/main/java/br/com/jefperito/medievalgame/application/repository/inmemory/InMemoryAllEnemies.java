package br.com.jefperito.medievalgame.application.repository.inmemory;

import br.com.jefperito.medievalgame.core.engine.EnemyGenerator;
import br.com.jefperito.medievalgame.core.entity.creature.AllEnemies;
import br.com.jefperito.medievalgame.core.entity.creature.Creature;

public class InMemoryAllEnemies implements AllEnemies {

    @Override
    public Creature withId(Long enemyId) {
        // TODO
        return EnemyGenerator.build();
    }
}
