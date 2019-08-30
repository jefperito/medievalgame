package br.com.jefperito.medievalgame.application.entrypoint.commandline;

import br.com.jefperito.medievalgame.core.entity.history.MissedCharacterActionException;
import br.com.jefperito.medievalgame.core.usecase.characterexists.CharacterExists;
import br.com.jefperito.medievalgame.core.usecase.getrandomenemy.GetRandomEnemy;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.CharacterAction;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.Consequence;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.DeathException;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.HistoryInteractor;
import br.com.jefperito.medievalgame.core.usecase.newbattle.NewBattle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameInteractorTest {

    @Mock
    private CharacterExists characterExists;

    @Mock
    private HistoryInteractor historyInteractor;

    @Mock
    private CommandLineInterface commandLineInterface;

    @Mock
    private NewBattle newBattle;

    @Mock
    private GetRandomEnemy getRandomEnemy;

    @Test(expected = DeathException.class)
    public void shouldThrowsDeathExceptionIfHistoryInteractorReturnsADeathConsequence() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsTrue();
        configureHistoryInteractorReturnsDeathConsequence();

        new GameInteractor(null, null, null, historyInteractor, characterExists, null).start();
    }

    private void configureHistoryInteractorReturnsDeathConsequence() throws MissedCharacterActionException {
        when(historyInteractor.interact(any())).thenReturn(Consequence.ofDeath());
    }

    @Test
    public void shouldStartANewBattleIfHistoryInteractorReturnsBattleConsequence() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsTrue();
        configureHistoryInteractorToReturnsBattleConsequence();

        new GameInteractor(getRandomEnemy, null, commandLineInterface, historyInteractor, characterExists, newBattle).start();

        verify(commandLineInterface).printText(anyString());
        verify(newBattle).start(any());
        verify(getRandomEnemy).getRandom();
    }

    @Test
    public void shouldStartANewBattleIfHistoryInteractorReturnsHistoryConsequence() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsTrue();
        configureHistoryInteractorToReturnsHistoryConsequenceWithStartAction();
        configureHistoryInteractorToReturnsHistoryConsequenceWithIntentAction();

        new GameInteractor(null, null, commandLineInterface, historyInteractor, characterExists, null).start();

        verify(commandLineInterface, times(2)).printText(anyString());
        verify(historyInteractor, times(2)).interact(any());
        verify(commandLineInterface).waitForInputString();
    }

    private void configureHistoryInteractorToReturnsHistoryConsequenceWithStartAction() throws MissedCharacterActionException {
        when(historyInteractor.interact(argThat(new StartArgumentMatcher()))).thenReturn(Consequence.ofHistory("Whatever"));
    }

    private void configureHistoryInteractorToReturnsHistoryConsequenceWithIntentAction() throws MissedCharacterActionException {
        when(historyInteractor.interact(argThat(new IntentArgumentMatcher()))).thenReturn(Consequence.ofEnd());
    }

    private void configureHistoryInteractorToReturnsBattleConsequence() throws MissedCharacterActionException {
        when(historyInteractor.interact(any())).thenReturn(Consequence.ofBattle("Whatever"));
    }

    private void configureCharacterExistsToReturnsTrue() {
        when(characterExists.exists()).thenReturn(true);
    }

    private class StartArgumentMatcher implements ArgumentMatcher<CharacterAction> {

        @Override
        public boolean matches(CharacterAction argument) {
            if (argument == null) {
                return false;
            }
            return argument.getType() == CharacterAction.Type.START;
        }
    }

    private class IntentArgumentMatcher implements ArgumentMatcher<CharacterAction> {

        @Override
        public boolean matches(CharacterAction argument) {
            if (argument == null) {
                return false;
            }
            return argument.getType() == CharacterAction.Type.INTENT;
        }
    }
}