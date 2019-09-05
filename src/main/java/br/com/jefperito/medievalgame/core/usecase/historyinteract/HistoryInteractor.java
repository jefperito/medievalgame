package br.com.jefperito.medievalgame.core.usecase.historyinteract;

import br.com.jefperito.medievalgame.core.entity.action.AllCharacterActions;
import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.entity.history.History;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CharacterActionDTO;
import br.com.jefperito.medievalgame.core.usecase.createdefaulthistory.CreateDefaultHistory;

public class HistoryInteractor {

    private final AllCharacters allCharacters;
    private final CreateDefaultHistory historyFactory;
    private final AllCharacterActions allCharacterActions;

    public HistoryInteractor(AllCharacters allCharacters, AllCharacterActions allCharacterActions, CreateDefaultHistory historyFactory) {
        this.allCharacters = allCharacters;
        this.historyFactory = historyFactory;
        this.allCharacterActions = allCharacterActions;
    }

    public ConsequenceDTO interact(CharacterActionDTO characterActionDTO) {
        History history = historyFactory.create();

        return ConsequenceDTO.of(history.createConsequence(allCharacterActions.load(characterActionDTO.getType(), characterActionDTO.getInputData())));
    }
}
