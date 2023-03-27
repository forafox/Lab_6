package commands;



/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 22.02.2023 23:36
 */

import collection.CollectionManager;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;

/**
 * Команда, выводящая щначения поля difficult всех элементов коллекции в порядке возрастания
 */
public class PrintFieldAscendingDifficultyCommand extends Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    public PrintFieldAscendingDifficultyCommand(){
        super("print_field_ascending_difficulty");
    }
    /**
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public PrintFieldAscendingDifficultyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Метод, исполняющий команду. В случае выполнения вывсветятся значения поля difficulty всех элементов в порядке возрастания
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
            printStream.println(collectionManager.showDifficult());
        }
    }
    /**
     * Метод, выводящий значения поля difficulty всех элементов в порядке возрастания
     * @return  описание команды.
     * @see HelpCommand
     */
    @Override
    public String getDescription() {
        return "вывести значения поля difficulty всех элементов в порядке возрастания";
    }
}
