package br.com.jefperito.medievalgame.core.usecase.historyinteract;

public class Consequence {

    private final Type type;
    private String information;

    private Consequence(Type type) {
        this.type = type;
    }

    private Consequence(Type type, String information) {
        this.type = type;
        this.information = information;
    }

    public static Consequence ofBattle(String information) {
        return new Consequence(Type.BATTLE, information);
    }

    public static Consequence ofHistory(String information) {
        return new Consequence(Type.HISTORY, information);
    }

    public static Consequence ofDeath() {
        return new Consequence(Type.DEATH, "Voce esta morto");
    }

    public static Consequence ofEnd() {
        return new Consequence(Type.END);
    }

    public Type getType() {
        return type;
    }

    public String getInformation() {
        return information;
    }

    public static enum Type {
        DEATH,
        BATTLE,
        HISTORY,
        END
    }
}
