package br.com.jefperito.medievalgame.core.entity.history;

import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;
import br.com.jefperito.medievalgame.core.entity.consequence.Consequence;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultHistoryTest {

    @Test
    public void shouldCreateHistorySequenceIfActionIsStart() {
        Consequence consequence = new DefaultHistory().createConsequence(CharacterAction.ofStart());

        assertThat(consequence.getType()).isEqualTo(Consequence.Type.HISTORY);
    }

    @Test
    public void shouldCreateBattleSequenceIfActionIsIntent() {
        Consequence consequence = new DefaultHistory().createConsequence(CharacterAction.ofHistoryInteraction("whatever"));

        assertThat(consequence.getType()).isEqualTo(Consequence.Type.BATTLE);
    }
}