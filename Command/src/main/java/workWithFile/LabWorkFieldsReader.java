package workWithFile;

import collection.*;
import exceptions.ValidValuesRangeException;
import io.UserIO;


import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 21.02.2023 19:30
 */

/**
 * Класс, использующийся для заполнения полей нового объекта класса LabWork
 */
public class LabWorkFieldsReader {
    private UserIO userIO;
    /**
     * Конструктор класса, который присваивает в поле userIO значение, переданное в конструкторе в качестве параметра
     *
     * @param userIO хранит ссылку на объект типа UserIO
     */
    public LabWorkFieldsReader(UserIO userIO) {
        this.userIO = userIO;
    }

    /**
     * Метод, выводящий производящий чтение данных из консоли. Запрашивает ввод полей в строго определенном порядке.
     *
     * @param id уникальный идентификатор объекта класса LabWork, который должен быть записан в качестве ключа в коллекцию
     * @return возращает объект типа LabWork
     */
    public LabWork read(Integer id) {
        String i = Instant.now().toString();
        return new LabWork(id, readName(), readCoordinates(),ZonedDateTime.parse(i).plusHours(3), readMinimalPoint(), readMaximumPoint(),
                readPersonalQualitiesMaximum(), readDifficulty(), readPerson());
    }
    /**
     * Метод, выводящий производящий чтение данных из консоли. Запрашивает ввод полей в строго определенном порядке.
     *
     * @return возращает объект типа LabWork
     */
    public LabWork read( ) {
        String i = Instant.now().toString();
        return new LabWork(readName(), readCoordinates(),ZonedDateTime.parse(i).plusHours(3), readMinimalPoint(), readMaximumPoint(),
                readPersonalQualitiesMaximum(), readDifficulty(), readPerson());
    }
    /**
     * Метод, производящий чтение поля name типа String объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поля заново.
     *
     * @return значение поля name, уже проверенное на недопустимую ОДЗ.
     */
    public String readName(){
        String str;
        while(true){
            userIO.printCommandText("name (not null): ");
            str= userIO.readLine().trim();
            if(str.equals("") || str==null) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой\n");
            else return str;
        }
    }

    /**
     * Метод, производящий чтение координат x, y.
     *
     * @return возвращает объект типа Coordinates.
     */
    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    /**
     * Метод, производящий чтение поля x типа Double объекта Coordinates из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля x, уже проверенное на недопустимую ОДЗ.
     */
    public Long readCoordinateX() {
        Long x;
        while (true) {
            try {

                userIO.printCommandText("coordinate_x (Long & x > -985): ");
                String str=userIO.readLine().trim();
                if(str.equals("") || str==null) x= Long.valueOf(0);
                else {
                    x = Long.parseLong(str);
                    if (x <= -985) throw new ValidValuesRangeException();
                    else return x;
                }
            } catch (ValidValuesRangeException ex) {
                System.out.println("Координата x должна быть больше -985");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Long");
            }
        }
    }
    /**
     * Метод, производящий чтение поля y типа int объекта Coordinates из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля y, уже проверенное на недопустимую ОДЗ.
     */
    public Double readCoordinateY() {
        double y;
        while (true) {
            try {
                userIO.printCommandText("coordinate_y (Double): ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str == null) y = 0;
                else {
                    y = Double.parseDouble(str);
                }
                return y;
                }catch(NumberFormatException ex){
                    System.err.println("Число должно быть типа Double");
                }
            }
    }
    /**
     * Метод, производящий чтение поля minimalPoint типа int объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля minimalPoint, уже проверенное на недопустимую ОДЗ.
     */
    public int readMinimalPoint(){
        int minimalPoint;
        while (true){
        try {
            userIO.printCommandText("minimalPoint (Integer x > 0): ");
            String str=userIO.readLine().trim();
            if(str.equals("") || str==null) minimalPoint=0;
            else {
                minimalPoint = Integer.parseInt(str);
                if (minimalPoint <=0) throw new ValidValuesRangeException();
                else return minimalPoint;
            }
        } catch (ValidValuesRangeException ex) {
            System.out.println("minimalPoint должен быть больше 0");
        } catch (NumberFormatException ex) {
            System.err.println("Число должно быть типа Integer");
        }
    }
    }
    /**
     * Метод, производящий чтение поля maximumPoint типа Double объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля maximumPoint, уже проверенное на недопустимую ОДЗ.
     */
    public Double readMaximumPoint(){
        Double maximumPoint;
        while (true){
            try {
                userIO.printCommandText("maximumPoint (not null && Double x > 0): ");
                String str=userIO.readLine().trim();
                    maximumPoint = Double.parseDouble(str);
                    if (maximumPoint <0) throw new ValidValuesRangeException();
                    else return maximumPoint;
            } catch (ValidValuesRangeException ex) {
                System.out.println("maximumPoint должен быть больше 0");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Double и не null");
            }
        }
    }
    /**
     * Метод, производящий чтение поля personalQualitiesMaximum типа Int объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля personalQualitiesMaximum, уже проверенное на недопустимую ОДЗ.
     */
    public int readPersonalQualitiesMaximum(){
        int personalQualitiesMaximum;
        while (true){
            try {
                userIO.printCommandText("personalQualitiesMaximum (Integer x > 0): ");
                String str=userIO.readLine().trim();
                if(str.equals("")) personalQualitiesMaximum=0;
                else {
                    personalQualitiesMaximum = Integer.parseInt(str);
                    if (personalQualitiesMaximum <=0) throw new ValidValuesRangeException();
                    else return personalQualitiesMaximum;
                }
            } catch (ValidValuesRangeException ex) {
                System.out.println("personalQualitiesMaximum должен быть больше 0");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа Integer");
            }
        }
    }
    /**
     * Метод, производящий чтение поля difficulty типа Difficulty объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля difficulty, уже проверенное на недопустимую ОДЗ.
     */
    public Difficulty readDifficulty() {
        Difficulty difficulty;
        while (true) {
            try {
                userIO.printCommandText("Допустимые значения difficulty :\n");
                for (Difficulty val : Difficulty.values()) {
                    userIO.printCommandText(val.name() + "\n");
                }
                userIO.printCommandText("difficulty: ");
                difficulty = Difficulty.valueOf(userIO.readLine().toUpperCase().trim());
                return difficulty;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении difficulty");
            }
        }
    }
    /**
     * Метод, производящий чтение поля person типа Person объекта LabWork из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля person, уже проверенное на недопустимую ОДЗ.
     */

    public Person readPerson(){
        return new Person(readPersonName(),readPersonHeight(),readPassportId(),readLocation());
    }
    /**
     * Метод, производящий чтение поля name типа String объекта Person из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля name, уже проверенное на недопустимую ОДЗ.
     */

    public String readPersonName(){
        String str;
        while(true){
            userIO.printCommandText("PersonName (not null and not empty): ");
            str= userIO.readLine().trim();
            if(str.equals("") || str==null) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой\n");
            else return str;
        }
    }
    /**
     * Метод, производящий чтение поля personHeight типа Float объекта Person из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля personHeight, уже проверенное на недопустимую ОДЗ.
     */
    public Float readPersonHeight() {
        float height;
        while (true) {
            try {
                userIO.printCommandText("height (Float && >0): ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str==null) height = 0;
                else {
                    height = Float.parseFloat(str);
                    if (height <=0) throw new ValidValuesRangeException();
                }
                return height;
            }catch (ValidValuesRangeException ex) {
                System.out.println("height должен быть больше 0");
            }catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть Float");
            }
        }
    }
    /**
     * Метод, производящий чтение поля PassportId типа Float объекта Person из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля PassportId, уже проверенное на недопустимую ОДЗ.
     */
    public String readPassportId() {
        while (true) {
            try {
                userIO.printCommandText("PersonPassportID (String & not null and not empty & length <=28): ");
                String str=userIO.readLine().trim();
                if(str.equals("") || str==null || str.length()>28) throw new ValidValuesRangeException();
                else {
                     return str;
                }
            } catch (ValidValuesRangeException ex) {
                System.out.println("PersonPassportID должна быть меньше 28 символов  и не пустой");
            } catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть String");
            }
        }
    }
    /**
     * Метод, производящий чтение поля location типа Location объекта Person из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля location, уже проверенное на недопустимую ОДЗ.
     */
    public Location readLocation(){
        return new Location(readLocationCoordinateX(),readLocationCoordinateY(),readLocationName());
    }
    /**
     * Метод, производящий чтение поля locationCoordinateX типа Int объекта Location из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля locationCoordinateX, уже проверенное на недопустимую ОДЗ.
     */
    public int readLocationCoordinateX() {
        int x = 0;
        while (true) {
            try {
                userIO.printCommandText("Location coordinate_x (int) : ");
                String str = userIO.readLine().trim();
                if (str.equals("") || str==null) x = 0;
                else {
                    x = Integer.parseInt(str);
                    if (x <=0) throw new ValidValuesRangeException();
                }
                return x;
            }catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть int");
            }
        }
    }
    /**
     * Метод, производящий чтение поля locationCoordinateY типа Int объекта Location из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля locationCoordinateY, уже проверенное на недопустимую ОДЗ.
     */
    public Float readLocationCoordinateY() {
        float y = 0;
        while (true) {
            try {
                userIO.printCommandText("Location coordinate_y (Float) :");
                String str = userIO.readLine().trim();
                if (str.equals("") || str==null) y = 0;
                else {
                    y = Float.parseFloat(str);
                    if (y <=0) throw new ValidValuesRangeException();
                }
                return y;
            }catch (NumberFormatException ex) {
                System.err.println("Вводимое значение должно быть float");
            }
        }
    }
    /**
     * Метод, производящий чтение поля locationName типа String объекта Location из потока, указанного в поле userIO. При некорректном вводе просит ввести поле заново.
     *
     * @return значение поля locationName, уже проверенное на недопустимую ОДЗ.
     */
    public String readLocationName(){
        String str;
        while(true){
            userIO.printCommandText("LocationName (length<=669): ");
            str= userIO.readLine().trim();
            if(str==null) str="";
            if(str.length()>669) userIO.printCommandError("\nЗначение поля не может быть null или пустой строкой\n");
            else return str;
        }
    }


}
