package br.com.jefperito.medievalgame.core.usecase.getwelcome;

import java.util.Arrays;
import java.util.Collection;

public class GetWelcome {

    public Collection<String> execute() {
        return Arrays.asList(
                "Bem vindo ao Medieval Game! Esse é um jogo de RPG baseado em turnos via terminal!",
                "Jogo desenvolvido por Jeferson V. Perito<jefperito@gmail.com>",
                "Para começar, selecione uma das opções:"
        );
    }
}
