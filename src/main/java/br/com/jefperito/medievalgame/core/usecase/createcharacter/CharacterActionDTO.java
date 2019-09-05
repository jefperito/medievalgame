package br.com.jefperito.medievalgame.core.usecase.createcharacter;

import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;

public class CharacterActionDTO {
    private final String inputData;
    private final CharacterAction.Type type;

    private CharacterActionDTO(String inputData, CharacterAction.Type type) {
        this.inputData = inputData;
        this.type = type;
    }

    public static CharacterActionDTO of(CharacterAction characterAction) {
        return new CharacterActionDTO(characterAction.getInputData(), characterAction.getType());
    }

    public CharacterAction.Type getType() {
        return type;
    }

    public String getInputData() {
        return inputData;
    }
}
