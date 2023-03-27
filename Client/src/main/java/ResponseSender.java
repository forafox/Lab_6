import commands.abstr.CommandContainer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Base64;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 21:56
 */

public class ResponseSender {
    private final DatagramChannel clientChannel;
    public ResponseSender(DatagramChannel clientChannel){
        this.clientChannel=clientChannel;
    }
    public void sendContainer(CommandContainer commandContainer, InetSocketAddress inetSocketAddress) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);//Выделение нового байтового буфера
        byteBuffer.clear();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();//Конструктор создает буфер в памяти в 32 байта.
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(commandContainer);

        byteBuffer.put(Base64.getEncoder().withoutPadding().encode(baos.toByteArray()));//\,кладем значения

        byteBuffer.flip();//переключается из режима записи в режим чтения

        clientChannel.send(byteBuffer, inetSocketAddress);
    }
}
