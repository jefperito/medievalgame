package br.com.jefperito.medievalgame.application.entrypoint.commandline;

import java.util.Collection;
import java.util.stream.Collectors;

class Prettifier {

    String prettifyText(Collection<String> text) {
        return prettify(text, ",");
    }

    String prettifyList(Collection<String> text) {
        return prettify(text, "\n");
    }

    private String prettify(Collection<String> text, String token) {
        return text.stream().collect(Collectors.joining(token));
    }
}
