package commands.abstr;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 22:28
 */
public class CommandContainer implements Serializable {
    /**
     * Контейнер с командой для отправки на сервер.
     */
    private final String name;

    /**
     * Данные, необходимые серверу для выполнения его части команды.
     */
    private final ArrayList<Object> result;

    /**
     * Конструктор класса.
     * @param name имя команды.
     * @param result данные для исполнения серверной части команды.
     */
    public CommandContainer(String name, ArrayList<Object> result) {
        this.name = name;
        this.result = result;
    }

    /**
     * Метод для получения имени команды.
     * @return String - имя команды.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения данных команды.
     * @return Данные команды.
     */
    public ArrayList<Object> getResult() {
        return result;
    }
}
