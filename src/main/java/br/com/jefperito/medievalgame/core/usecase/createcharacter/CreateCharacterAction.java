package br.com.jefperito.medievalgame.core.usecase.createcharacter;

import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;

public class CreateCharacterAction {

    public CharacterActionDTO createAction(String inputData) {
        return CharacterActionDTO.of(CharacterAction.ofHistoryInteraction(inputData));
    }
}
