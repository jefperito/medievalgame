package br.com.jefperito.medievalgame.application.entrypoint.commandline.getaction;

import br.com.jefperito.medievalgame.application.entrypoint.commandline.InputAction;

import static br.com.jefperito.medievalgame.application.entrypoint.commandline.InputAction.START_GAME;

public class GetChosenAction {

    private String action;

    public InputAction execute() throws ActionNotFound {
        if (action.equals("0")) {
            return START_GAME;
        }
        throw new ActionNotFound();
    }

    public GetChosenAction withAction(String action) {
        this.action = action;
        return this;
    }
}
