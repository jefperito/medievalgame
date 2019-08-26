package br.com.jefperito.medievalgame.core.engine.plugin;

import br.com.jefperito.medievalgame.core.engine.Event;

public class Logger implements Listener {

    @Override
    public void onEvent(Event event, Object... data) {
        if (Event.PRE_COMBAT.equals(event)) {
            System.out.println("Pre combate");
        }
        if (Event.POST_COMBAT.equals(event)) {
            System.out.println("Pós combate");
        }
    }
}
