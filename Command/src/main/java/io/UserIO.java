package io;

import java.util.Scanner;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 18.03.2023 22:05
 */
public class UserIO {
    /**
     * хранит ссылку на Scanner, производящий чтение данных из указанного места
     */
    Scanner scanner;

    /**
     * Конструктор класса без параметров. При вызове в поле scanner присваивается Scanner, производящий чтение из стандартного потока ввода с кодировкой UTF-8
     */
    public UserIO() {
        scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     * Конструктор класса со сканером в качестве параметра. Необходим для корректного чтения-вывода данных на-из консоли.
     * @param scanner сканнер, необходимый для чтения и вывода.
     */
    public UserIO(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод, производящий чтение строки из места, на которое указывает объект, на который ссылается поле scanner данного объекта.
     *
     * @return возвращает прочитанную строку
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Метод, выводящий символ стрелки вправо перед запросом ввода команды.
     */
    public void printPreamble() {
        System.out.print(">");
    }
    public void printCommandText(String str){
        System.out.println(str);
    }

    public void printCommandError(String s) {
        System.err.println(s);
    }
}
