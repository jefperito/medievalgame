package br.com.jefperito.medievalgame.core.usecase.createconsequence;

import br.com.jefperito.medievalgame.core.entity.action.AllCharacterActions;
import br.com.jefperito.medievalgame.core.entity.history.AllHistories;
import br.com.jefperito.medievalgame.core.entity.history.History;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CharacterActionDTO;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.ConsequenceDTO;

@Deprecated
public class CreateSequence {

    private final AllHistories allHistories;
    private final AllCharacterActions allCharacterActions;

    public CreateSequence(AllHistories allHistories, AllCharacterActions allCharacterActions) {
        this.allHistories = allHistories;
        this.allCharacterActions = allCharacterActions;
    }

    public ConsequenceDTO create(CharacterActionDTO characterActionDTO) {
        History activeHistory = allHistories.ofActive();
        return ConsequenceDTO.of(activeHistory.createConsequence(allCharacterActions.load(characterActionDTO.getType(), characterActionDTO.getInputData())));
    }
}
