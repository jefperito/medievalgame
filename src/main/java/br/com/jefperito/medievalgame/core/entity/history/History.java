package br.com.jefperito.medievalgame.core.entity.history;

import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;
import br.com.jefperito.medievalgame.core.entity.consequence.Consequence;

public abstract class History {
    public abstract Consequence createConsequence(CharacterAction characterAction);
}
