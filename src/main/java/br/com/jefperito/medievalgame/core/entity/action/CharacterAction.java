package br.com.jefperito.medievalgame.core.entity.action;

public class CharacterAction {

    private String inputData;
    private Type type;

    private CharacterAction(Type type) {
        this.type = type;
    }

    public CharacterAction(Type type, String inputData) {
        this.type = type;
        this.inputData = inputData;
    }

    public static CharacterAction ofStart() {
        return new CharacterAction(Type.START);
    }

    public static CharacterAction ofHistoryInteraction(String inputData) {
        return new CharacterAction(Type.INTENT, inputData);
    }

    public Type getType() {
        return type;
    }

    public String getInputData() {
        return inputData;
    }

    public enum Type {
        INTENT,
        START
    }
}
