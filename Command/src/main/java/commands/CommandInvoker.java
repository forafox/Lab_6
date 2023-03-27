package commands;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 21.02.2023 20:13
 */



import collection.CollectionManager;
import commands.abstr.Command;
import commands.abstr.CommandContainer;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;
import io.UserIO;
import workWithFile.LabWorkFieldsReader;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс, через который осуществляется исполнение команд. Хранит коллекции всех существующих команд.
 */
public class CommandInvoker {
    /**
     * Коллекция комманд для клиента
     */
    private HashMap<String, Command> clientCommands;
    /**
     * Коллекция команд для сервера.
     */
    private HashMap<String, Command> serverCommands;

    /**
     * Поле, хранящее ссылку на объект класса CollectionManager.
     * @see CollectionManager
     */
    private CollectionManager collectionManager;
    /**
     * Поле, хранящее ссылку на объект класса UserIO.
     * @see UserIO
     */
    private UserIO userIO;
    /**
     * Поле, хранящее строку, в которой записан адрес файла, куда следует сохранять полученную коллекцию (экземпляры коллекции).
     */
    /**
     * Контейнер с командой.
     */
    private CommandContainer lastCommandContainer;
    private String inputFile;
    /**
     * Поле, хранящее ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     * @see LabWorkFieldsReader
     */
    private LabWorkFieldsReader labWorkFieldsReader;
    /**
     * Поле, хранящее объект класса ExecuteScript.Script.
     * @see ExecuteScriptCommand
     */
    ExecuteScriptCommand.Script script;
    /**
     * Поле, хранящее список команд
     */
    ArrayList<String> commandsHistoryList = new ArrayList<>();

    /**
     * Конструктор класса. Внутри вызывается метод putCommands, добавляющий команды в коллекции команд, создается новый объект класса ExecuteScript.Script.
     *
     * @param userIO читает данные из указанного потока.
     */
    public CommandInvoker(UserIO userIO) { //для клиента
        this.clientCommands = new HashMap<>();
        this.userIO = userIO;
        this.labWorkFieldsReader = new LabWorkFieldsReader(userIO);

        this.script = new ExecuteScriptCommand.Script();
        this.putClientCommands();
        System.out.println("Элементы коллекции для клиента были загружены.");
    }
    /**
     * Конструктор класса, предназначенный для исполнения скрипта на клиенте.
     * @param userIO читает данные из указанного потока.
     * @param labWorkFieldsReader осуществляет чтение полей, валидацию и преобразование в объект класса Dragon.
     * @param script скрипт, хранит пути до файлов, из которых считывать команды.
     */
    public CommandInvoker(UserIO userIO, LabWorkFieldsReader labWorkFieldsReader, ExecuteScriptCommand.Script script) {
        this.clientCommands = new HashMap<>();

        this.userIO = userIO;
        this.labWorkFieldsReader = labWorkFieldsReader;
        this.script = script;

        this.putClientCommands();
    }
    /**
     * Конструктор класса, предназначенный для сервера.
     *
     * @param collectionManager менеджер коллекции.
     * @param inputFile файл, куда следует сохранять коллекцию по завершении работы сервера.
     */
    public CommandInvoker(CollectionManager collectionManager, String inputFile) {
        this.serverCommands = new HashMap<>();

        this.collectionManager = collectionManager;

        this.inputFile = inputFile;

        script = new ExecuteScriptCommand.Script();

        this.putServerCommands();
        System.out.println("Элементы коллекции для сервера были загружены");
    }
    /**
     * Конструктор класса, предназначенный для сервера.
     * @param collectionManager менеджер коллекции.
     */
    public CommandInvoker(CollectionManager collectionManager) {
        this.serverCommands = new HashMap<>();

        this.collectionManager = collectionManager;

        this.putServerCommands();
    }
    /**
     * Метод, добавляющий клиентские команды в соответствующую коллекции.
     */
    private void putClientCommands() {
        clientCommands.put("info", new InfoCommand());
        clientCommands.put("show", new ShowCommand());
        clientCommands.put("clear", new ClearCommand());
        clientCommands.put("exit", new ExitCommand());
        clientCommands.put("help", new HelpCommand(clientCommands));

        clientCommands.put("insert", new InsertElementCommand(labWorkFieldsReader));
        clientCommands.put("update", new UpdateElementCommand(userIO));
        clientCommands.put("remove_key", new RemoveKeyElementCommand());
        clientCommands.put("execute_script", new ExecuteScriptCommand(userIO, labWorkFieldsReader, script));
        clientCommands.put("remove_lower_key", new RemoveLowerKeyCommand());
        clientCommands.put("remove_greater_key",new RemoveGreaterKeyCommand());
        clientCommands.put("print_field_descending_author",new PrintFieldDescendingAuthor());
        clientCommands.put("print_field_ascending_difficulty",new PrintFieldAscendingDifficultyCommand());
        clientCommands.put("group_counting_by_difficulty",new GroupCountingByDifficult());
        clientCommands.put("history",new HistoryCommand());
    }
    /**
     * Метод, добавляющий серверные команды в соответствующую коллекцию.
     */
    private void putServerCommands() {
        serverCommands.put("info", new InfoCommand(collectionManager));//y
        serverCommands.put("show", new ShowCommand(collectionManager));//y
        serverCommands.put("clear", new ClearCommand(collectionManager));//y
        serverCommands.put("save", new SaveCommand(collectionManager, inputFile));//y
        serverCommands.put("help", new HelpCommand(serverCommands));//y
//
        serverCommands.put("insert", new InsertElementCommand(collectionManager));//y
        serverCommands.put("update", new UpdateElementCommand(collectionManager));//y
        serverCommands.put("remove_key", new RemoveKeyElementCommand(collectionManager));//y
        serverCommands.put("remove_greater_key",new RemoveGreaterKeyCommand(collectionManager));
        serverCommands.put("remove_lower_key", new RemoveLowerKeyCommand(collectionManager));
        serverCommands.put("print_field_descending_author",new PrintFieldDescendingAuthor(collectionManager));
        serverCommands.put("print_field_ascending_difficulty",new PrintFieldAscendingDifficultyCommand(collectionManager));
        serverCommands.put("group_counting_by_difficulty",new GroupCountingByDifficult(collectionManager));
        serverCommands.put("history", new HistoryCommand(commandsHistoryList));
        serverCommands.put("execute_script", new ExecuteScriptCommand(collectionManager));
    }
    /**
     * Метод, который определяет из полученной строки команду со стороны клиента, исполняет ее и передает ей необходимые аргументы.
     * Если команда не распознана, то в заданный поток вывода выводится соответствующее сообщение.
     *
     * @param firstCommandLine Первая строка команды, где хранится само ее название и переданные на этой же строке аргументы.
     * @param printStream поток вывода, куда следует записывать информацию  при исполнеии команды.
     *
     * @return boolean: true - команда исполнена, false - команда не исполнена.
     */
    public boolean executeClient(String firstCommandLine, PrintStream printStream) {

        String[] words = firstCommandLine.trim().split("\\s+");
        String[] arguments = Arrays.copyOfRange(words, 1, words.length);

        try {
            if (clientCommands.containsKey(words[0].toLowerCase(Locale.ROOT))) {
                Command command;
                command = clientCommands.get(words[0].toLowerCase(Locale.ROOT));

                command.execute(arguments, InvocationStatus.CLIENT, printStream);
                lastCommandContainer = new CommandContainer(command.getName(), command.getResult());
                return true;
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (CannotExecuteCommandException ex) {
            printStream.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Метод, который определяет из полученной строки команду со стороны сервера, исполняет ее и передает ей необходимые аргументы.
     *
     * @param firstCommandLine имя команды, аргументы.
     * @param result данные, необходимые для исполнения серверной части команды, полученные от клиента.
     * @param printStream поток вывода, куда следует записывать информацию  при исполнеии команды.
     *
     * @return boolean: true - команда исполнена, false - команда не исполнена.
     */
    public boolean executeServer(String firstCommandLine, ArrayList<Object> result, PrintStream printStream) {

        String[] words = firstCommandLine.trim().split("\\s+");
        String[] arguments = Arrays.copyOfRange(words, 1, words.length);
        try {
            if (serverCommands.containsKey(words[0].toLowerCase(Locale.ROOT))) {
                Command command;
                command = serverCommands.get(words[0].toLowerCase(Locale.ROOT));

                command.setResult(result);
                command.execute(arguments, InvocationStatus.SERVER, printStream); //throws CannotExecuteCommandException
                return true;
            }
        } catch (NullPointerException ex) {
            System.out.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
            ex.printStackTrace();
        } catch (CannotExecuteCommandException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Метод, возвращающий созданный контейнер с командой.
     * @return CommandContainer - контейнер с командой.
     */
    public CommandContainer getLastCommandContainer() {
        return lastCommandContainer;
    }
    public void addToCommandsHistory(String string){
        if(commandsHistoryList.size()==6) {
            commandsHistoryList.remove(0);
            commandsHistoryList.add(string);
        }else{
            commandsHistoryList.add(string);
        }
    }
}

