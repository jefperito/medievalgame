package br.com.jefperito.medievalgame.entrypoint.commandline;

import br.com.jefperito.medievalgame.core.engine.BattleEngine;
import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.usecase.characterexists.CharacterExists;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacter;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacterAction;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacterStartAction;
import br.com.jefperito.medievalgame.core.usecase.createdefaulthistory.CreateDefaultHistory;
import br.com.jefperito.medievalgame.core.usecase.getmenu.GetMenu;
import br.com.jefperito.medievalgame.core.usecase.getrandomenemy.GetRandomEnemy;
import br.com.jefperito.medievalgame.core.usecase.getwelcome.GetWelcome;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.DeathException;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.HistoryInteractor;
import br.com.jefperito.medievalgame.core.usecase.newbattle.NewBattle;
import br.com.jefperito.medievalgame.entrypoint.commandline.getaction.ActionNotFound;
import br.com.jefperito.medievalgame.entrypoint.commandline.getaction.GetChosenAction;
import br.com.jefperito.medievalgame.entrypoint.commandline.newcharacter.NewCharacter;
import br.com.jefperito.medievalgame.repository.inmemory.InMemoryAllCharacterActions;
import br.com.jefperito.medievalgame.repository.inmemory.InMemoryAllCharacters;

public class Main {

    private static CommandLineInterface commandLineInterface = new CommandLineInterface();

    public static void main(String[] arguments) {
        commandLineInterface.printText(new GetWelcome().execute());
        commandLineInterface.printList(new GetMenu().execute());

        InputAction action = getAction();
        if (action.equals(InputAction.START_GAME)) {
            AllCharacters allCharacteres = new InMemoryAllCharacters();
            NewCharacter newCharacter = new NewCharacter(commandLineInterface, new CreateCharacter(allCharacteres));
            HistoryInteractor historyInteractor = new HistoryInteractor(allCharacteres, new InMemoryAllCharacterActions(), new CreateDefaultHistory());
            CharacterExists characterExists = new CharacterExists();
            NewBattle newBattle = new NewBattle(BattleEngine.start(), allCharacteres);
            GetRandomEnemy getRandomEnemy = new GetRandomEnemy();
            CreateCharacterAction createCharacterAction = new CreateCharacterAction();
            CreateCharacterStartAction createCharacterStartAction = new CreateCharacterStartAction();

            try {
                new GameController(
                        getRandomEnemy, newCharacter, commandLineInterface, historyInteractor, characterExists,
                        newBattle, createCharacterAction, createCharacterStartAction
                ).start();
            } catch (DeathException e) {
                commandLineInterface.printText("YOU ARE DEAD!");
            }
        }
    }

    private static InputAction getAction() {
        String rawAction = commandLineInterface.waitForInputString();
        try {
            return new GetChosenAction().withAction(rawAction).execute();
        } catch (ActionNotFound actionNotFound) {
            commandLineInterface.printText("Opção não encontrada, selecione uma das opções:");
            getAction();
        }
        return null;
    }
}
