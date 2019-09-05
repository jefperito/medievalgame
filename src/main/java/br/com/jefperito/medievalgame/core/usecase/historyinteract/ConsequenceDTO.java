package br.com.jefperito.medievalgame.core.usecase.historyinteract;

import br.com.jefperito.medievalgame.core.entity.consequence.Consequence;

public class ConsequenceDTO {

    private final Type type;
    private final String information;

    private ConsequenceDTO(Consequence.Type type, String information) {
        this.type = Type.of(type);
        this.information = information;
    }

    public Type getType() {
        return type;
    }

    public String getInformation() {
        return information;
    }

    public static ConsequenceDTO of(Consequence consequence) {
        return new ConsequenceDTO(consequence.getType(), consequence.getInformation());
    }

    public enum Type {
        DEATH,
        BATTLE,
        HISTORY,
        END;

        static Type of(Consequence.Type type) {
            return Type.valueOf(type.name());
        }
    }
}
