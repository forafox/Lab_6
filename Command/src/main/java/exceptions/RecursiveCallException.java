package exceptions;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 21.02.2023 0:16
 */

/**
 * Uсключение, выбрасываемое когда script совершает рекурсивный вызов или вызов по циклу.
 */
public class RecursiveCallException extends RuntimeException{
    private final String filename;
    public RecursiveCallException(String filename){
            this.filename=filename;
    }
    public String getFilename(){
        return filename;
    }
}
