package br.com.jefperito.medievalgame.core.usecase.getmenu;

import br.com.jefperito.medievalgame.core.usecase.QueryUseCase;

import java.util.Arrays;
import java.util.List;

public class GetMenu implements QueryUseCase<List<String>> {

    @Override
    public List<String> execute() {
        return Arrays.asList("0 - Começar jogo", "1 - Sair do jogo");
    }
}
