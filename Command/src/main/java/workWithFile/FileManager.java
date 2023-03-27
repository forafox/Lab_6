package workWithFile;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:38
 */

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, отвечающий за чтение/запись данных из файла/в файл.
 */
public class FileManager {
    /**
     * Метод, производящий чтение данных из указанного файла. В случае критических ошибок программа завершает свою работу.
     *
     * @param filePath файл, из которого следует читать данные
     * @return строка, которая хранит все содержимое данного файла
     */
    public String readFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;

        StringBuilder sb = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);

            while (inputStreamReader.ready()) {
                sb.append((char) inputStreamReader.read());
            }
        } catch (IOException ex) {
            System.err.println("Произошла ошибка при добавлении файла во входящий поток " + ex);
            System.exit(-1);
        } catch (NullPointerException ex) {
            System.err.println("Не указан файл, из которого следует читать данные " + ex);
            System.exit(-1);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException ex) {
                System.err.println("Произошла ошибка при закрытии " + ex);
            }
        }
        return sb.toString();
    }

    /**
     * Метод, производящий запись данных в указанный файл.
     *
     * @param filePath файл, куда следует записывать данные
     * @param str      строка, которую следует записать в файл
     */
    public void writeToFile(String str, String filePath) {
        FileWriter fileWriter = null;

        try {

            fileWriter = new FileWriter(filePath);
            fileWriter.write(str);

        } catch (IOException ex) {
            System.err.println("Произошла ошибка при добавлении файла в исходнящий поток\n" + ex);
        } catch (NullPointerException ex) {
            System.err.println("Не указан файл, куда следует записывать данные " + ex);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
                System.err.println("Произошла ошибка при закрытии " + ex);
            }
        }
    }
}

