package br.com.jefperito.medievalgame.application.entrypoint.commandline.newcharacter;

import br.com.jefperito.medievalgame.application.entrypoint.commandline.CommandLineInterface;
import br.com.jefperito.medievalgame.core.usecase.createcharacter.CreateCharacter;

public class NewCharacter {

    private final CreateCharacter createCharacter;
    private final CommandLineInterface commandLineInterface;

    public NewCharacter(CommandLineInterface commandLineInterface, CreateCharacter createCharacter) {
        this.commandLineInterface = commandLineInterface;
        this.createCharacter = createCharacter;
    }

    public void createACharacter() {
        commandLineInterface.printText("Vejo que você não possui um personagem, qual seria o seu nome?");
        String rawName = commandLineInterface.waitForInputString();
        commandLineInterface.printText("Qual a sua classe de batalha?");
        String rawClass = commandLineInterface.waitForInputString();
        createCharacter
                .withName(rawName)
                .withClass(rawClass)
                .execute();
    }
}
