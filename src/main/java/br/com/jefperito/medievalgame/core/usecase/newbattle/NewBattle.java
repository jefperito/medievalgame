package br.com.jefperito.medievalgame.core.usecase.newbattle;

import br.com.jefperito.medievalgame.core.engine.BattleEngine;
import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.entity.creature.Creature;

import java.util.List;

public class NewBattle {

    private final BattleEngine battleEngine;
    private final AllCharacters allCharacters;

    public NewBattle(BattleEngine battleEngine, AllCharacters allCharacters) {
        this.battleEngine = battleEngine;
        this.allCharacters = allCharacters;
    }

    public List<String> start(Creature enemy) {
        return battleEngine.battle(allCharacters.getActive(), enemy);
    }
}
