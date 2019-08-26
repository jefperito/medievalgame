package br.com.jefperito.medievalgame.core.engine.plugin;

import br.com.jefperito.medievalgame.core.engine.Event;

@FunctionalInterface
public interface Listener {

    void onEvent(Event event, Object... data);
}
