package br.com.jefperito.medievalgame.application.entrypoint.commandline;

import br.com.jefperito.medievalgame.application.entrypoint.commandline.newcharacter.NewCharacter;
import br.com.jefperito.medievalgame.application.repository.inmemory.InMemoryAllCharacters;
import br.com.jefperito.medievalgame.core.engine.BattleEngine;
import br.com.jefperito.medievalgame.core.entity.action.Action;
import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.entity.history.HistoryFactory;
import br.com.jefperito.medievalgame.core.usecase.characterexists.CharacterExists;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacter;
import br.com.jefperito.medievalgame.core.usecase.getaction.ActionNotFound;
import br.com.jefperito.medievalgame.core.usecase.getaction.GetChosenAction;
import br.com.jefperito.medievalgame.core.usecase.getmenu.GetMenu;
import br.com.jefperito.medievalgame.core.usecase.getrandomenemy.GetRandomEnemy;
import br.com.jefperito.medievalgame.core.usecase.getwelcome.GetWelcome;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.DeathException;
import br.com.jefperito.medievalgame.core.usecase.historyinteract.HistoryInteractor;
import br.com.jefperito.medievalgame.core.usecase.newbattle.NewBattle;

public class Main {

    private static CommandLineInterface commandLineInterface = new CommandLineInterface();

    public static void main(String[] arguments) {
        commandLineInterface.printText(new GetWelcome().execute());
        commandLineInterface.printList(new GetMenu().execute());

        Action action = getAction();
        if (action.equals(Action.START_GAME)) {
            AllCharacters allCharacteres = new InMemoryAllCharacters();
            NewCharacter newCharacter = new NewCharacter(commandLineInterface, new CreateCharacter(allCharacteres));
            HistoryInteractor historyInteractor = new HistoryInteractor(allCharacteres, new HistoryFactory());
            CharacterExists characterExists = new CharacterExists();
            NewBattle newBattle = new NewBattle(BattleEngine.start(commandLineInterface), allCharacteres);
            GetRandomEnemy getRandomEnemy = new GetRandomEnemy();

            try {
                new GameInteractor(getRandomEnemy, newCharacter, commandLineInterface, historyInteractor, characterExists, newBattle).start();
            } catch (DeathException e) {
                commandLineInterface.printText("YOU ARE DEAD!");
            }
        }
    }

    private static Action getAction() {
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
