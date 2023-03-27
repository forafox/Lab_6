package commands;



/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 22.02.2023 1:46
 */

import collection.CollectionManager;
import collection.LabWorkFieldValidation;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Команда, удаляющая элементы коллекции, превыщающие заданный ключ
 */
public class RemoveGreaterKeyCommand extends Command {
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     */
    public RemoveGreaterKeyCommand(){
        super("remove_greater_key");
    }
    /**
     * Конструктор класса с аргументом
     * @param collectionManager Хранит ссылку на созданный в объекте Application объект CollectionManager.
     */
    public RemoveGreaterKeyCommand(CollectionManager collectionManager) {
        this.collectionManager=collectionManager;
    }

    /**
     * Метод, исполняющий команду. В случае успешного выполнения удалятся все элементы коллекции, значения идентификатора которых меньше переданного в качестве аргументов значения, иначе предупреждение.
     * @param invocationEnum режим, с которым должна быть исполнена данная команда.
     * @param printStream поток вывода.
     * @param arguments аргументы команды.
     */
    @Override
    public void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationEnum.equals(InvocationStatus.CLIENT)) {
            result = new ArrayList<>();
            if (arguments.length != 1) {
                throw new CannotExecuteCommandException("Количество аргументов у данной команды равно 1.");
            }
            if (LabWorkFieldValidation.validate("id", arguments[0])) {
                result.add(Integer.parseInt(arguments[0])); //сохраняем id, меньше которых следует удалять.
            } else {
                throw new CannotExecuteCommandException("Введены невалидные аргументы: id = " + arguments[0]);
            }
        } else if (invocationEnum.equals(InvocationStatus.SERVER)) {
            Integer id = (Integer) this.getResult().get(0);
            collectionManager.removeGreaterKey(id);
            printStream.println("Элементы с id > " + id + " были удалены");
        }
    }

    /**
     * Метод, возвращающий описание команды.
     *
     * @return Возращает описание команды.
     * @see Command
     */
    @Override
    public String getDescription() {
        return "удаляет все элементы коллекции, значение id которых меньше указанного в качестве атрибута команды";
    }
}
