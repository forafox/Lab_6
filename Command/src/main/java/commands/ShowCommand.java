package commands;

import collection.CollectionManager;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 22.02.2023 0:03
 */

/**
 * Команда, показывыющая содержимое всех элементов коллекции
 */
public class ShowCommand extends Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор класса без аргументов
     */
    public ShowCommand(){
        super("show");
    }
    /**
     * Конструктор класса.
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    /**
     * Метод, исполняющий команду. Показывает подробное содержание содержимого коллекции.
     * @param invocationEnum режим, с которым должна быть исполнена данная команда.
     * @param printStream поток вывода.
     * @param arguments аргументы команды.
     */
    @Override
    public void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationEnum.equals(InvocationStatus.CLIENT)) {
            if (arguments.length > 0) {
                throw new CannotExecuteCommandException("У данной команды нет аргументов.");
            }
        } else if (invocationEnum.equals(InvocationStatus.SERVER)) {
            printStream.println(collectionManager.show());
        }
    }
    /**
     * Метод, возращающий описание команды
     * @return Метод, возвращающий описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "показывает подробное содержимое всех элементов коллекции";
    }
}
