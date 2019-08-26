package br.com.jefperito.medievalgame.core.usecase;

public interface QueryUseCase<T> {
    T execute() throws Exception;
}
