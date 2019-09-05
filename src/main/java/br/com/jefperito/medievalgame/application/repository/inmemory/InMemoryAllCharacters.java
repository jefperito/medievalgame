package br.com.jefperito.medievalgame.application.repository.inmemory;

import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.entity.creature.Character;

public class InMemoryAllCharacters implements AllCharacters {

    private Character character;

    @Override
    public Character getActive() {
        return character;
    }

    @Override
    public void save(Character character) {
        this.character = character;
    }
}
