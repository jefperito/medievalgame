package br.com.jefperito.medievalgame.core.usecase.createdefaulthistory;

import br.com.jefperito.medievalgame.core.entity.history.DefaultHistory;
import br.com.jefperito.medievalgame.core.entity.history.History;

public class CreateDefaultHistory {

    public History create() {
        return new DefaultHistory();
    }
}
