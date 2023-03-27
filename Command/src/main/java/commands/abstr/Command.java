package commands.abstr;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 21.02.2023 20:12
 */

import exceptions.CannotExecuteCommandException;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Uнтерфейс, реализация которого приведена в командах.
 */
public abstract class Command {
    /**
     * Имя команды
     */
    private String name;
    /**
     * Результат команды, необходимый серверу для выполнения серверной части команды
     */
    protected ArrayList<Object> result;
    /**
     * Конструктор класса
     * @param name имя команды
     */
    protected Command(String name){
        this.name=name;
    }
    /**
     * Конструктор класса
     */
    protected Command(){

    }
    /**
     * Метод, исполняющий команду.
     * @param arguments аргументы команды.
     * @param invocationEnum режим, с которым следует исполнить команду.
     * @param printStream поток вывода.
     * @throws CannotExecuteCommandException исключение, выбрасываемое методом, когда при исполнеии команды что-то идет не так.
     *
     * @see InvocationStatus
     */
    public abstract void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream)
            throws CannotExecuteCommandException; //name of the command + arguments

    /**
     * Метод, возвращающий имя команды.
     * @return String - имя команды.
     */
    public String getName() { //возвращает имя команды
        return name;
    }

    /**
     * Метод, возвращающий данные, необходимые команде.
     * @return Аргументы, необходимые команде для выполнения на сервере.
     */
    public ArrayList<Object> getResult() { //возвращает результат команды для клиента
        return result;
    }

    /**
     * Метод, добавляющий необходимые для выполнения на сервере.
     * @param result аргументы, необходимые команде на сервере.
     */
    public void setResult(ArrayList<Object> result) {
        this.result = result;
    }

    /**
     * Метод, возвращающий описание команды. Если данный метод не переопределен в командах наследниках, то выводит заглушку.
     * @return String - описание команды по умолчанию.
     */
    public String getDescription() {
        return "у данной команды пока нет описания.";
    }

    @Override
    public String toString() {
        return "Uмя :" + getName() + "\nСодержимое на исполнение серверу: " + result.stream().toString();
    }
}
