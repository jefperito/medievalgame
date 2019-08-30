package br.com.jefperito.medievalgame.core.entity.history;

import br.com.jefperito.medievalgame.core.usecase.historyinteract.CharacterAction;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.Consequence;

public class DefaultHistory extends History {

    @Override
    public Consequence createConsequence(CharacterAction characterAction) throws MissedCharacterActionException {
        if (characterAction.getType().equals(CharacterAction.Type.START)) {
            return Consequence.ofHistory("Voce estava andando e tropecou em  uma pedra, o que voce deseja fazer?");
        }
        return Consequence.ofBattle("Nao ha tempo de fazer o que deseja, apareceu um inimigo!");
    }
}
