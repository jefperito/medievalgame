package br.com.jefperito.medievalgame.core.usecase.getmenu;

import java.util.Arrays;
import java.util.List;

public class GetMenu {

    public List<String> execute() {
        return Arrays.asList("0 - Começar jogo", "1 - Sair do jogo");
    }
}
