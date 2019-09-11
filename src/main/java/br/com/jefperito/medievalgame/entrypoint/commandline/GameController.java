package br.com.jefperito.medievalgame.entrypoint.commandline;

import br.com.jefperito.medievalgame.core.usecase.characterexists.CharacterExists;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CharacterActionDTO;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacterAction;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacterStartAction;
import br.com.jefperito.medievalgame.core.usecase.getrandomenemy.GetRandomEnemy;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.ConsequenceDTO;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.DeathException;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.HistoryInteractor;
import br.com.jefperito.medievalgame.core.usecase.newbattle.NewBattle;
import br.com.jefperito.medievalgame.entrypoint.commandline.newcharacter.NewCharacter;

import java.util.List;

public class GameController {

    private final CharacterExists characterExists;
    private final CommandLineInterface commandLineInterface;
    private final HistoryInteractor historyInteractor;
    private final NewBattle newBattle;
    private final NewCharacter newCharacter;
    private final GetRandomEnemy getRandomEnemy;
    private final CreateCharacterAction createCharacterAction;
    private final CreateCharacterStartAction createCharacterStartAction;

    public GameController(GetRandomEnemy getRandomEnemy, NewCharacter newCharacter, CommandLineInterface commandLineInterface,
                          HistoryInteractor historyInteractor, CharacterExists characterExists, NewBattle newBattle,
                          CreateCharacterAction createCharacterAction, CreateCharacterStartAction createCharacterStartAction) {
        this.commandLineInterface = commandLineInterface;
        this.historyInteractor = historyInteractor;
        this.characterExists = characterExists;
        this.newBattle = newBattle;
        this.newCharacter = newCharacter;
        this.getRandomEnemy = getRandomEnemy;
        this.createCharacterAction = createCharacterAction;
        this.createCharacterStartAction = createCharacterStartAction;
    }

    public void start() throws DeathException {
        if (!characterExists.exists()) {
            newCharacter.createACharacter();
        }
        interactHistory(historyInteractor.interact(createCharacterStartAction.createStartAction()));
    }

    private void interactHistory(ConsequenceDTO consequence) throws DeathException {
        switch (consequence.getType()) {
            case DEATH:
                throw new DeathException();
            case BATTLE:
                commandLineInterface.printText(consequence.getInformation());
                List<String> battleMessages = newBattle.start(getRandomEnemy.getRandom());
                battleMessages.forEach(commandLineInterface::printText);
                break;
            case HISTORY:
                commandLineInterface.printText(consequence.getInformation());
                CharacterActionDTO characterActionDTO = createCharacterAction.createAction(commandLineInterface.waitForInputString());
                interactHistory(historyInteractor.interact(characterActionDTO));

                break;
            case END:
                commandLineInterface.printText("THE END");
        }
    }
}
