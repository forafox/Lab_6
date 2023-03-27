package commands;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 22.02.2023 23:43
 */

import collection.CollectionManager;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;

/**
 * Команда, выводящая значения поля author всех элементов в порядке убывания
 */
public class PrintFieldDescendingAuthor extends Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;
    /**
     * Конструктор класса
     */
    public PrintFieldDescendingAuthor(){
        super("print_field_descending_author");
    }

    /**
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public PrintFieldDescendingAuthor(CollectionManager collectionManager)
    {
        this.collectionManager=collectionManager;
    }

    /**
     * Метод, исполняющий команду. В случае выполнения вывсветятся поля author элементов коллекции в порядке убывания
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
            printStream.println(collectionManager.showPerson());
        }
    }
    /**
     * Метод,выводящий значение поля author всех элементов в порядке убывания
     * @return Описание команды
     * @see HelpCommand
     */
    @Override
    public String getDescription() {
        return "вывести значения поля author всех элементов в порядке убывания";
    }
}
