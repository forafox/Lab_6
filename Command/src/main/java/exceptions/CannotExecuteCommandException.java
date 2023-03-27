package exceptions;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 22:09
 */
/**
 * Uсключение, выбрасываемое в случае, если команда не может быть исполнена в поданными ей аргументами.
 */
public class CannotExecuteCommandException extends Exception {

    public CannotExecuteCommandException(String mes) {
        super(mes);
    }
}