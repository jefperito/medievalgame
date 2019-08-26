package br.com.jefperito.medievalgame.core.usecase.historyinteract;

import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.entity.creature.Character;
import br.com.jefperito.medievalgame.core.entity.history.History;
import br.com.jefperito.medievalgame.core.entity.history.HistoryFactory;
import br.com.jefperito.medievalgame.core.entity.history.MissedCharacterActionException;

public class HistoryInteractor {

    private final AllCharacters allCharacters;
    private final HistoryFactory historyFactory;

    public HistoryInteractor(AllCharacters allCharacters, HistoryFactory allHistories) {
        this.allCharacters = allCharacters;
        historyFactory = allHistories;
    }

    public Consequence interact(CharacterAction characterAction) throws MissedCharacterActionException {
        History history = historyFactory.createDefault();
        Character character = allCharacters.getActive();

        return history.createHistoryLine(character, characterAction);
    }
}
