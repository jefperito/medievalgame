package br.com.jefperito.medievalgame.core.usecase.getaction;

import br.com.jefperito.medievalgame.core.entity.action.Action;
import br.com.jefperito.medievalgame.core.usecase.QueryUseCase;

import static br.com.jefperito.medievalgame.core.entity.action.Action.START_GAME;

public class GetChosenAction implements QueryUseCase<Action> {

    private String action;

    @Override
    public Action execute() throws ActionNotFound {
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
