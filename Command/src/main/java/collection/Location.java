package collection;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:42
 */

import java.io.Serializable;

/**
 * Класс- локакия объекта класса Person
 */
public class Location implements Serializable {
    /**
     * Поле координаты x
     */
    private int x;
    /**
     * Поле координаты y
     */
    private float y;
    /**
     * Поле имени. Длина строки не должна быть больше 669, Поле может быть null
     */
    private String name;


    /**
     * Конструктор класса с параметрами
     * @param x значение координаты x
     * @param y значение координаты y
     * @param name значение поля имя
     */
    public Location(Integer x,Float y, String name) {
        this.x=x;
        this.y=y;
        this.name=name;
    }

    /**
     * Конструктор класса без параметров
     */
    public Location() {
        this.x=0;
        this.y=0;
        this.name="Name";
    }

    /**
     * Метод возраюащий значение координаты x
     * @return x значение координаты x
     */
    public int getX() {
        return x;
    }
    /**
     * Метод устанавливающий значение координаты x
     * @param  x значение координаты x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Метод возращающий значение координаты y
     * @return y значение координаты y
     */
    public float getY() {
        return y;
    }
    /**
     * Метод устанавливающий значение координаты y
     * @param  y значение координаты y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Метод устанавливающий значение поля name
     * @return name значение поля name
     */
    public String getName() {
        return name;
    }
    /**
     * Метод возращающий значение поля name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возращает все поля объекта
     * @return поля объекта класса (x,y,name)
     */
    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
