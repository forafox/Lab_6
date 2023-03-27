package commands;


/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 22.02.2023 0:31
 */

import collection.CollectionManager;
import collection.LabWork;
import collection.LabWorkFieldValidation;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;
import io.UserIO;
import workWithFile.LabWorkFieldsReader;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Команда, добавляющая элемент в коллекцию
 */
public class InsertElementCommand extends Command{
    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     */
    private CollectionManager collectionManager;

    /**
     * Поле, хранящее ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    private LabWorkFieldsReader labWorkFieldsReader;

    /**
     * Конструктор класса, предназначенный для клиента.
     * @param labWorkFieldsReader Хранит ссылку на объект, осуществляющий чтение полей из указанного в UserIo потока ввода.
     */
    public InsertElementCommand(LabWorkFieldsReader labWorkFieldsReader){
        super("insert");
        this.labWorkFieldsReader=labWorkFieldsReader;
    }
    /**
     * Конструктор класса, предназначенный для серверной части команды
     * @param collectionManager менеджер коллекции
     */
    public InsertElementCommand(CollectionManager collectionManager) {
    this.collectionManager=collectionManager;
    }
    /**
     * Метод, исполняющий команду. При запуске команды запрашивает ввод указанных полей. При успешном выполнении команды на стороне сервера высветится уведомление о добавлении элемента в коллекцию. В случае критической ошибки выполнение команды прерывается.
     *
     * @param invocationStatus режим, с которым должна быть исполнена данная команда.
     * @param printStream поток вывода.
     * @param arguments аргументы команды.
     */
//    @Override
//    public void execute(String[] arguments, InvocationStatus invocationStatus, PrintStream printStream) throws CannotExecuteCommandException {
//        if (invocationStatus.equals(InvocationStatus.CLIENT)) {
//            result = new ArrayList<>();
//            if (arguments.length != 1) {
//                throw new CannotExecuteCommandException("Количество аргументов у данной команды должно равняться 1.");
//            }
//            if (LabWorkFieldValidation.validate("id", arguments[0])) {
//                printStream.println("Введите значения полей для элемента коллекции:\n");
//                LabWork dragon = labWorkFieldsReader.read(Integer.parseInt(arguments[0]));
//                super.result.add(Integer.parseInt(arguments[0])); //Integer id - result(0), dragon - result(1)
//                super.result.add(dragon);
//            } else
//                throw new CannotExecuteCommandException("Введены невалидные аргументы: id = " + arguments[0]);
//        } else if (invocationStatus.equals(InvocationStatus.SERVER)) {
//            LabWork labWork = (LabWork) this.getResult().get(1);
//            collectionManager.insertWithId((Integer) this.getResult().get(0), labWork, printStream);
//        }
//    }
    /**
     * Метод, исполняющий команду. При запуске команды запрашивает ввод указанных полей. При успешном выполнении команды на стороне сервера высветится уведомление о добавлении элемента в коллекцию. В случае критической ошибки выполнение команды прерывается.
     *
     * @param invocationStatus режим, с которым должна быть исполнена данная команда.
     * @param printStream поток вывода.
     * @param arguments аргументы команды.
     */
    @Override
    public void execute(String[] arguments, InvocationStatus invocationStatus, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationStatus.equals(InvocationStatus.CLIENT)) {
            result = new ArrayList<>();
            if (arguments.length > 1) {
                throw new CannotExecuteCommandException("Количество аргументов у данной команды должно быть не более 1.");
            }
            if (arguments.length == 1) {
                if (LabWorkFieldValidation.validate("id", arguments[0])) {
                    printStream.println("Введите значения полей для элемента коллекции:\n");
                    LabWork labWork = labWorkFieldsReader.read(Integer.parseInt(arguments[0]));
                    super.result.add(Integer.parseInt(arguments[0])); //Integer id - result(0), dragon - result(1)
                    super.result.add(labWork);
                } else
                    throw new CannotExecuteCommandException("Введены невалидные аргументы: id = " + arguments[0]);
            }else{
                    printStream.println("Введите значения полей для элемента коллекции:\n");
                    LabWork labWork = labWorkFieldsReader.read();
                    super.result.add(labWork);
            }
        } else if (invocationStatus.equals(InvocationStatus.SERVER)) {
            if(result.size()==2) {
                LabWork labWork = (LabWork) this.getResult().get(1);
                collectionManager.insertWithId((Integer) this.getResult().get(0), labWork, printStream);
            }else{
                LabWork labWork = (LabWork) this.getResult().get(0);
                collectionManager.insert(labWork);
            }
        }
    }
    /**
     * Метод, возвращающий описание данной команды.
     * @return Описание данной команды.
     *
     * @see HelpCommand
     */
    @Override
    public String getDescription() {
        return "добавляет элемент с указанным ключом в качестве атрибута";
    }
}
