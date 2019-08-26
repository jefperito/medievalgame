package br.com.jefperito.medievalgame.core.usecase.getcharacter;

import br.com.jefperito.medievalgame.core.entity.creature.Character;

public class CharacterDTO {

    public static CharacterDTO of(Character character) {
        return new CharacterDTO();
    }

    public Long getId() {
        return 0L;
    }
}
