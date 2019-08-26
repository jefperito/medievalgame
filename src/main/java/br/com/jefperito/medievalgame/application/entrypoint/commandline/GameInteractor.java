package br.com.jefperito.medievalgame.application.entrypoint.commandline;

import br.com.jefperito.medievalgame.application.entrypoint.commandline.newcharacter.NewCharacter;
import br.com.jefperito.medievalgame.core.entity.history.MissedCharacterActionException;
import br.com.jefperito.medievalgame.core.usecase.characterexists.CharacterExists;
import br.com.jefperito.medievalgame.core.usecase.getrandomenemy.GetRandomEnemy;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.CharacterAction;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.Consequence;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.DeathException;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.HistoryInteractor;
import br.com.jefperito.medievalgame.core.usecase.newbattle.NewBattle;

public class GameInteractor {

    private final CharacterExists characterExists;
    private final CommandLineInterface commandLineInterface;
    private final HistoryInteractor historyInteractor;
    private final NewBattle newBattle;
    private final NewCharacter newCharacter;
    private final GetRandomEnemy getRandomEnemy;

    public GameInteractor(GetRandomEnemy getRandomEnemy, NewCharacter newCharacter, CommandLineInterface commandLineInterface,
                          HistoryInteractor historyInteractor, CharacterExists characterExists, NewBattle newBattle) {
        this.commandLineInterface = commandLineInterface;
        this.historyInteractor = historyInteractor;
        this.characterExists = characterExists;
        this.newBattle = newBattle;
        this.newCharacter = newCharacter;
        this.getRandomEnemy = getRandomEnemy;
    }

    public void start() throws DeathException {
        if (!characterExists.exists()) {
            newCharacter.createACharacter();
        }
        try {
            interactHistory(historyInteractor.interact(CharacterAction.ofStart()));
        } catch (MissedCharacterActionException e) {
            throw new IllegalStateException("Problem to process", e);
        }
    }

    private void interactHistory(Consequence consequence) throws DeathException {
        switch (consequence.getType()) {
            case DEATH:
                throw new DeathException();
            case BATTLE:
                commandLineInterface.printText(consequence.getInformation());
                newBattle.start(getRandomEnemy.getRandom());
                break;
            case HISTORY:
                commandLineInterface.printText(consequence.getInformation());
                CharacterAction characterAction = CharacterAction.ofHistoryInteraction(commandLineInterface.waitForInputString());
                try {
                    interactHistory(historyInteractor.interact(characterAction));
                } catch (MissedCharacterActionException e) {
                    // TODO Throw an exception
                }

                break;
            case END:
                commandLineInterface.printText("THE END");
        }
    }
}
