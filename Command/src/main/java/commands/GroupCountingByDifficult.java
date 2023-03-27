package commands;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 23.02.2023 0:02
 */

import collection.CollectionManager;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;

/**
 * Команда, выводящая элементы коллекции с группировкой по полю difficult
 */
public class GroupCountingByDifficult extends Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор без аргументов
     */
    public GroupCountingByDifficult(){
        super("group_counting_by_difficulty");
    }

    /**
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public GroupCountingByDifficult(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод, исполняющий команду. В случае выполнения выведится кол-во элементов с группировкой по полю difficulty
     * В случае неудачи высветится прудепреждение
     * @param arguments аргументы команды
     * @param invocationEnum режим работы команды
     * @param printStream  поток, куда следует выводить результаты команды
     * @throws CannotExecuteCommandException
     */
    @Override
    public void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationEnum.equals(InvocationStatus.CLIENT)) {
            if (arguments.length > 0) {
                throw new CannotExecuteCommandException("У данной команды нет аргументов.");
            }
        } else if (invocationEnum.equals(InvocationStatus.SERVER)) {
            collectionManager.clear();
            printStream.println(collectionManager.GroupCountingByDifficult());
        }
    }
    /**
     * Метод, выводящий количество всех элементов с групировкой по полю difficulty
     * @return Метод, возвращающий описание команды.
     * @see HelpCommand
     */
    @Override
    public String getDescription() {
        return "сгруппировать элементы коллекции по значению поля difficulty, вывести количество элементов в каждой группе";
    }
}
