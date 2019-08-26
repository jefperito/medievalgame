package br.com.jefperito.medievalgame.core.entity.history;

import br.com.jefperito.medievalgame.core.entity.creature.Character;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.CharacterAction;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.Consequence;

import java.util.ArrayList;
import java.util.List;

public abstract class History {
    private List<HistoryLine> historyLines;

    public History() {
        historyLines = new ArrayList<>();
    }

    public void addHistoryLine(HistoryLine historyLine) {
        historyLines.add(historyLine);
    }

    public abstract Consequence createHistoryLine(Character character, CharacterAction characterAction) throws MissedCharacterActionException;
}
