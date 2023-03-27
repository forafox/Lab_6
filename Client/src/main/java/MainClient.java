import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 21:55
 */
public class MainClient {
    private static final Logger rootLogger = LogManager.getRootLogger();
    public static void main(String[] args){
        if (args.length != 0) {
            Application application = new Application(Integer.parseInt(args[0]));//запуск с портом
            try {
                application.start();
            } catch (NumberFormatException ex) {
                rootLogger.warn("Значение порта должно быть целочисленным.\n Введенное значение: " + args[0]);
            }
        }else{
            Application application = new Application(Integer.parseInt("5489"));//запуск с портом
            try {
                application.start();
            } catch (NumberFormatException ex) {
                rootLogger.warn("Значение порта должно быть целочисленным.\n Введенное значение: " + args[0]);
            }
        }
    }
}
