package br.com.jefperito.medievalgame.entrypoint.commandline;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineInterface {

    private Scanner scanner = new Scanner(System.in);

    public void printText(String text) {
        System.out.println(text);
    }

    public void printText(Collection<String> text) {
        System.out.println(prettifyText(text));
    }

    public void printList(Collection<String> text) {
        System.out.println(prettifyList(text));
    }

    public String waitForInputString() {
        return scanner.nextLine();
    }

    private String prettifyList(Collection<String> text) {
        return prettify(text, "\n");
    }

    private String prettifyText(Collection<String> text) {
        return prettify(text, ",");
    }

    private String prettify(Collection<String> text, String token) {
        return text.stream().collect(Collectors.joining(token));
    }
}
