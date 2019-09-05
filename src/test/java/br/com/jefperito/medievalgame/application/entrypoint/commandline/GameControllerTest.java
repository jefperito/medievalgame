package br.com.jefperito.medievalgame.application.entrypoint.commandline;

import br.com.jefperito.medievalgame.application.entrypoint.commandline.newcharacter.NewCharacter;
import br.com.jefperito.medievalgame.core.entity.action.CharacterAction;
import br.com.jefperito.medievalgame.core.entity.consequence.Consequence;
import br.com.jefperito.medievalgame.core.entity.history.MissedCharacterActionException;
import br.com.jefperito.medievalgame.core.usecase.characterexists.CharacterExists;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CharacterActionDTO;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacterAction;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacterStartAction;
import br.com.jefperito.medievalgame.core.usecase.getrandomenemy.GetRandomEnemy;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.ConsequenceDTO;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.DeathException;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.HistoryInteractor;
import br.com.jefperito.medievalgame.core.usecase.newbattle.NewBattle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

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

    @Mock
    private NewCharacter newCharacter;

    @Mock
    private CreateCharacterStartAction createCharacterStartAction;

    @Mock
    private CreateCharacterAction createCharacterAction;

    @Test(expected = DeathException.class)
    public void shouldThrowsDeathExceptionIfHistoryInteractorReturnsADeathConsequence() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsTrue();
        configureHistoryInteractorReturnsDeathConsequence();
        configureCharacterExistsToReturnsTrue();
        configureCreateCharacterStartAction();

        new GameController(null, null, null, historyInteractor, characterExists, null, null, createCharacterStartAction).start();
    }

    private void configureCreateCharacterStartAction() {
        when(createCharacterStartAction.createStartAction()).thenReturn(CharacterActionDTO.of(CharacterAction.ofStart()));
    }

    private void configureHistoryInteractorReturnsDeathConsequence() throws MissedCharacterActionException {
        when(historyInteractor.interact(any())).thenReturn(ConsequenceDTO.of(Consequence.ofDeath()));
    }

    @Test
    public void shouldStartANewBattleIfHistoryInteractorReturnsBattleConsequence() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsTrue();
        configureHistoryInteractorToReturnsBattleConsequence();
        configureCreateCharacterStartAction();

        new GameController(getRandomEnemy, null, commandLineInterface, historyInteractor, characterExists, newBattle, null, createCharacterStartAction).start();

        verify(commandLineInterface).printText(anyString());
        verify(newBattle).start(any());
        verify(getRandomEnemy).getRandom();
    }


    @Test
    public void shouldStartANewBattleIfHistoryInteractorReturnsHistoryConsequence() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsTrue();
        configureHistoryInteractorToReturnsHistoryConsequenceWithStartAction();
        configureHistoryInteractorToReturnsHistoryConsequenceWithIntentAction();
        configureCreateCharacterStartAction();
        configureCreateCharacterAction();

        new GameController(null, null, commandLineInterface,
                historyInteractor, characterExists, null, createCharacterAction,
                createCharacterStartAction
        ).start();

        verify(commandLineInterface, times(2)).printText(anyString());
        verify(historyInteractor, times(2)).interact(any());
        verify(commandLineInterface).waitForInputString();
    }

    private void configureCreateCharacterAction() {
        when(createCharacterAction.createAction(any())).thenReturn(CharacterActionDTO.of(CharacterAction.ofHistoryInteraction("whatever")));
    }

    @Test
    public void shouldCreateACharacterIfItDoesNotExistsAndCreateANewHistory() throws MissedCharacterActionException, DeathException {
        configureCharacterExistsToReturnsFalse();
        configureHistoryInteractorToReturnsHistoryConsequenceWithStartAction();
        configureHistoryInteractorToReturnsHistoryConsequenceWithIntentAction();
        configureCreateCharacterStartAction();
        configureCreateCharacterAction();

        new GameController(null, newCharacter, commandLineInterface, historyInteractor,
                characterExists, null, createCharacterAction, createCharacterStartAction
        ).start();

        verify(commandLineInterface, times(2)).printText(anyString());
        verify(historyInteractor, times(2)).interact(any());
        verify(newCharacter).createACharacter();
        verify(commandLineInterface).waitForInputString();
    }

    private void configureCharacterExistsToReturnsFalse() {
        when(characterExists.exists()).thenReturn(false);
    }

    //
    private void configureHistoryInteractorToReturnsHistoryConsequenceWithStartAction() throws MissedCharacterActionException {
        when(historyInteractor.interact(argThat(new StartArgumentMatcher()))).thenReturn(ConsequenceDTO.of(Consequence.ofHistory("Whatever")));
    }

    private void configureHistoryInteractorToReturnsHistoryConsequenceWithIntentAction() throws MissedCharacterActionException {
        when(historyInteractor.interact(argThat(new IntentArgumentMatcher()))).thenReturn(ConsequenceDTO.of(Consequence.ofEnd()));
    }

    private void configureHistoryInteractorToReturnsBattleConsequence() throws MissedCharacterActionException {
        when(historyInteractor.interact(any())).thenReturn(ConsequenceDTO.of(Consequence.ofBattle("Whatever")));
    }

    private void configureCharacterExistsToReturnsTrue() {
        when(characterExists.exists()).thenReturn(true);
    }

    private class StartArgumentMatcher implements ArgumentMatcher<CharacterActionDTO> {

        @Override
        public boolean matches(CharacterActionDTO argument) {
            if (argument == null) {
                return false;
            }
            return argument.getType() == CharacterAction.Type.START;
        }
    }

    private class IntentArgumentMatcher implements ArgumentMatcher<CharacterActionDTO> {

        @Override
        public boolean matches(CharacterActionDTO argument) {
            if (argument == null) {
                return false;
            }
            return argument.getType() == CharacterAction.Type.INTENT;
        }
    }
}