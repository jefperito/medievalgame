package br.com.jefperito.medievalgame.core.entity.history;

import br.com.jefperito.medievalgame.core.usecase.historyinteract.CharacterAction;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.Consequence;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultHistoryTest {

    @Test
    public void shouldCreateHistorySequenceIfActionIsStart() throws MissedCharacterActionException {
        Consequence consequence = new DefaultHistory().createConsequence(CharacterAction.ofStart());

        assertThat(consequence.getType()).isEqualTo(Consequence.Type.HISTORY);
    }

    @Test
    public void shouldCreateBattleSequenceIfActionIsIntent() throws MissedCharacterActionException {
        Consequence consequence = new DefaultHistory().createConsequence(CharacterAction.ofHistoryInteraction("whatever"));

        assertThat(consequence.getType()).isEqualTo(Consequence.Type.BATTLE);
    }
}