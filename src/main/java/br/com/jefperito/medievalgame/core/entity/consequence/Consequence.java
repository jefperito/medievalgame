package br.com.jefperito.medievalgame.core.entity.consequence;

public class Consequence {

    private final Consequence.Type type;
    private String information;

    private Consequence(Consequence.Type type) {
        this.type = type;
    }

    private Consequence(Consequence.Type type, String information) {
        this.type = type;
        this.information = information;
    }

    public static Consequence ofBattle(String information) {
        return new Consequence(Consequence.Type.BATTLE, information);
    }

    public static Consequence ofHistory(String information) {
        return new Consequence(Consequence.Type.HISTORY, information);
    }

    public static Consequence ofDeath() {
        return new Consequence(Consequence.Type.DEATH, "Voce esta morto");
    }

    public static Consequence ofEnd() {
        return new Consequence(Consequence.Type.END);
    }

    public Consequence.Type getType() {
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