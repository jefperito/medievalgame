package br.com.jefperito.medievalgame.application.repository.inmemory;

import br.com.jefperito.medievalgame.core.entity.action.AllCharacterActions;
import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAllCharacterActions implements AllCharacterActions {

    private List<CharacterAction> characterActions = new ArrayList<>();

    @Override
    public CharacterAction load(CharacterAction.Type type, String inputData) {
        Optional<CharacterAction> characterActionOptional = getFromCache(type, inputData);
        if (characterActionOptional.isPresent()) {
            return characterActionOptional.get();
        }
        return new CharacterAction(type, inputData);

    }

    private Optional<CharacterAction> getFromCache(CharacterAction.Type type, String inputData) {
        return characterActions.stream()
                .filter(characterAction ->
                        characterAction.getType().equals(type)
                                && characterAction.getInputData().equals(inputData)
                ).findFirst();
    }
}
