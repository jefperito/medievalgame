package br.com.jefperito.medievalgame.core.usecase.getcharacter;

import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;

public class GetActiveCharacter {

    private final AllCharacters allCharacters;

    public GetActiveCharacter(AllCharacters allCharacters) {
        this.allCharacters = allCharacters;
    }

    public CharacterDTO get() {
        return CharacterDTO.of(allCharacters.getActive());
    }
}
