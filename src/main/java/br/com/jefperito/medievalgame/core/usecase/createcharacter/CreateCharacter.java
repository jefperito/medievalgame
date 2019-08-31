package br.com.jefperito.medievalgame.core.usecase.createcharacter;

import br.com.jefperito.medievalgame.core.entity.creature.AllCharacters;
import br.com.jefperito.medievalgame.core.entity.creature.Character;

public class CreateCharacter {

    private final AllCharacters allCharacters;
    private String name;
    private String clazz;

    public CreateCharacter(AllCharacters allCharacters) {
        this.allCharacters = allCharacters;
    }

    public void execute() {
        allCharacters.save(Character.buildWarrior(name, 10, 8));
    }

    public CreateCharacter withName(String name) {
        this.name = name;
        return this;
    }

    public CreateCharacter withClass(String clazz) {
        this.clazz = clazz;
        return this;
    }
}
