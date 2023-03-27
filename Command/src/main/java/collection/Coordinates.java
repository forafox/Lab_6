package collection;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:42
 */

import java.io.Serializable;

/**
 * Класс - координаты объекта класса LabWork
 */
public class Coordinates implements Serializable {
    /**
     * Поле координаты x. Значение поля должно быть больше -985.
     */
    private Long x;
    /**
     * поле координаты y.
     */
    private double y;

    /**
     * Конструктор класса.
     * @param x значение координаты x
     * @param y значение координаты y
     */
    public Coordinates(long x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * метод, устанавливающий значение координаты x
     * @param x значение координаты x
     */
    public void setX(Long x) {
        this.x = x > -985 ? x : this.x;
    }

    /**
     * метод, устанавливающий значение координаты y
     * @param y значение координаты y
     */
    public void setY(Double y) {
        this.y = y ;
    }

    /**
     * метод, возвращающий значение координаты x
     * @return значение координаты x
     */
    public Long getX() {
        return x;
    }
    /**
     * метод, возвращающий значение координаты y
     * @return значение координаты y
     */
    public Double getY() {
        return y;
    }

    /**
     * Метод, возращающий значения полей класса
     * @return значения координат x и y
     */
    @Override
    public String toString() {
        return "coordinates (x, y) = (" + getX() + ", " + getY() + ")";
    }
}
