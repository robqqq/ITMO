package commands;

import exceptions.InvalidArgumentTypeException;
import exceptions.NoArgException;

/**
 * Класс-визитор для запуска команд и передачи им необходимых аргументов
 */
public interface CommandInvoker{

    /**
     * Метод, который запускает команду
     * @param command команда
     */
    void invokeCommand(Command command);

    /**
     * Метод, который передает команде целочисленный аргумент
     * @param command команда
     * @throws InvalidArgumentTypeException если аргумент не целочисленный
     * @throws NoArgException если аргумент отсутствует
     */
    void setIntegerArg(RequiringArg<Integer> command) throws InvalidArgumentTypeException, NoArgException;

    /**
     * Метод, который передает команде аргумент-строку
     * @param command команда
     * @throws NoArgException если аргумент отсутствует
     */
    void setStringArg(RequiringArg<String> command) throws NoArgException;

    /**
     * Метод, который задает необходимый аргумент
     * @param arg аргумент
     */
    void setArg(String arg);
}
