package collection;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:42
 */

import java.io.Serializable;

/**
 * Класс - поле объекта класса LabWork
 */
public class Person implements Serializable {
    /**
     * Поле имя. Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     * Поле рост. Значение поля должно быть больше 0
     */
    private float height;
    /**
     * Поле id пасспорта. Строка не может быть пустой, Длина строки не должна быть больше 28, Поле не может быть null
     */
    private String passportID;
    /**
     * Поле с объектом класса Location. Поле может быть null
     */
    private Location location;

    /**
     * Конструктор класса без аргументов
     */

    public Person(){
        name="Name";
        height=0;
        passportID="";
        location = new Location();
    }

    /**
     * Конструктор класса с параметрами
     * @param name значение поля name
     * @param height значение поля height
     * @param passportID значение поля passportID
     * @param location значения поля location
     */
    public Person(String name,Float height, String passportID,Location location){
        this.name=name;
        this.height=height;
        this.passportID=passportID;
        this.location=location;
    }

    /**
     * Возращает поле name
     * @return значение поля name
     */
    public String getName() {
        return name;
    }

    /**
     * устанавливает значение поля name
     * @param name значение поля name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возращает значение поля height
     * @return height значение поля height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Устанавливает значение поля height
     * @param height значение поля height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Возращает значения поля passportID
     * @return passportID значение поля passportID
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Устанавливает значение поля PassportId
     * @param passportID значение поля passportID
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    /**
     * Метод, возращающий значение поля location
     * @return location значения поля location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Устаналивает значение поля location
     * @param location значение поля location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Метод, возраюащий все значения поля класса
     * @return значение полей класса
     */

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                ", location=" + location +
                '}';
    }
}
