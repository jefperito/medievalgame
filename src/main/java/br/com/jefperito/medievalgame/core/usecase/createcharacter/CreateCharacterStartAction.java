package br.com.jefperito.medievalgame.core.usecase.createcharacter;

import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;

public class CreateCharacterStartAction {

    public CharacterActionDTO createStartAction() {
        return CharacterActionDTO.of(CharacterAction.ofStart());
    }
}
