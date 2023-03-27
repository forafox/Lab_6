import commands.CommandInvoker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 21:55
 */
public class CommandProcessor {
    private static final Logger rootLogger= LogManager.getRootLogger();
    private final CommandInvoker commandInvoker;
    public CommandProcessor(CommandInvoker commandInvoker){
        this.commandInvoker=commandInvoker;
    }
    public boolean executeCommand(String firstCommandLine) {

        if (!commandInvoker.executeClient(firstCommandLine, System.out)) {
            rootLogger.warn("Команда не была исполнена");
            return false;
        } else {
            return !commandInvoker.getLastCommandContainer().getName().equals("help");
        }
    }

}
