package commands;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 21.02.2023 23:03
 */

import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;
import java.util.HashMap;

/**
 * Класс команды, которая выводит описания всех команд, реализованных в программе.
 */
public class HelpCommand extends Command {
    /**
     * Коллекция, содержащая объекты всех доступных в программе команд без дополнительных аргументов.
     */
    private final HashMap<String, Command> commands;
    /**
     * Конструктор класса.
     *
     * @param commands Коллекция, содержащая объекты всех доступных в программе команд с дополнительными аргументами.(либо клиентские, либо сервера)
     */
    public HelpCommand(HashMap<String, Command> commands) {
        super("help");
        this.commands = commands;
    }
    /**
     * Метод, выводящий справку по всем доступным командам.
     *
     * @param invocationEnum режим, с которым должна быть исполнена данная команда.
     * @param printStream поток вывода.
     * @param arguments аргументы команды.
     */
    public void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationEnum.equals(InvocationStatus.CLIENT)) {
            if (arguments.length > 0) {
                throw new CannotExecuteCommandException("У данной команды нет аргументов.");
            } else {
                commands.forEach((key, value) -> System.out.println(key + ": " + value.getDescription()));
            }
        } else if (invocationEnum.equals(InvocationStatus.SERVER)) {
            commands.forEach((key, value) -> System.out.println(key + ": " + value.getDescription()));
        }
    }
    /**
     * @return Описание данной команды.
     * @see HelpCommand
     */
    @Override
    public String getDescription() {
        return "выводит справку по всем командам";
    }
}
