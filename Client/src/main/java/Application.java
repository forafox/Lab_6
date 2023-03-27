/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 21:55
 */


import commands.CommandInvoker;
import io.UserIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.NoSuchElementException;


/**
 * Класс, через который производится запуск данного приложения
 */
public class Application {
    private static final Logger rootLogger =  LogManager.getRootLogger();
    /**
     * Хранит ссылку на объект, производящий чтение и вывод команд
     */
    UserIO userIO;
    CommandInvoker commandInvoker;

    private final int port;
    public Application(Integer port){
        this.port=port;
        userIO=new UserIO();
        commandInvoker=new CommandInvoker(userIO);
        rootLogger.info("Конструктор класса Application был загружен");
    }
    /**
     * Метод, выполняющий запуск программы. Через него происходит работа всей программы
     */
    public void start() {

        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", port);//создание IP-сокета(IP+Номер порта)

            ClientConnection clientConnection = new ClientConnection();//Создаем экзепляр нашего класса
            clientConnection.connect(inetSocketAddress);//Явное подключение

            ResponseSender responseSender = new ResponseSender(clientConnection.getClientChannel());
            RequestReader requestReader = new RequestReader(clientConnection.getClientChannel());

            CommandProcessor commandProcessor = new CommandProcessor(commandInvoker);

            rootLogger.info("Клиент готов к чтению команд.");
            boolean isConnected = true;
            boolean isNeedInput = true;
            boolean isCommandAcceptable = false;

            String line = "";

            while (isConnected) {
                if (isNeedInput) {
                    System.out.println("Введите название команды:");
                    userIO.printPreamble();
                    line = userIO.readLine();
                    isCommandAcceptable =  commandProcessor.executeCommand(line);//Можно ли выполнить команду
                }
                try {
                    if (isCommandAcceptable) {

                        responseSender.sendContainer(commandInvoker.getLastCommandContainer(), inetSocketAddress);

                        rootLogger.info("Данные были отправлены.");
                        ByteBuffer byteBuffer = requestReader.receiveBuffer();
                        byteBuffer.flip();
                        rootLogger.info("Данные были получены.");
                        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8).trim() + "\n");

                        isNeedInput = true;
                    }
                } catch (PortUnreachableException | SocketTimeoutException ex) {
                    if (ex instanceof PortUnreachableException) {
                        rootLogger.warn("Порт " + port + " не доступен. Повторить отправку команды? y/n");
                    } else {
                        rootLogger.warn("Сервер не отвечает. Повторить отправку команды? y/n");
                    }
                    String result = userIO.readLine().trim().toLowerCase(Locale.ROOT).split("\\s+")[0];
                    if (result.equals("n")) {
                        rootLogger.info("Завершение работы клиента");
                        isConnected = false;
                    } else {
                        isNeedInput = false;
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            rootLogger.error("\nАварийное завершение работы.");
        } catch (SocketException ex) {
            rootLogger.error("Ошибка подключения сокета к порту, или сокет не может быть открыт."
                    + ex.getMessage() + "/n" + "localhost" + " ; " + port);
        } catch (IllegalArgumentException ex) {
            rootLogger.error("Порт не принадлежит ОДЗ: " + port);
        }catch (IOException  ex){
            rootLogger.error("Ошибка IOE");
        }catch (InterruptedException ex){
            rootLogger.error("Ошибка InterruptedException");
        }
    }
}
