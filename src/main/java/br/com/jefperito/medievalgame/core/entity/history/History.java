package br.com.jefperito.medievalgame.core.entity.history;

import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;
import br.com.jefperito.medievalgame.core.entity.consequence.Consequence;

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

    public abstract Consequence createConsequence(CharacterAction characterAction);
}
