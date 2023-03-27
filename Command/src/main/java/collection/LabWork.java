package collection;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:42
 */

/**
 * Класс объектов(значений) коллекции
 */

public class LabWork implements Serializable {
    /**
     * @value уникальный идентификатор, присваиваемый объекту коллекции
     */
    private static int uniqueId=1;
    /**
     * уникальный идентификатор коллекции. Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля генерируется автоматически
     */
    private Integer id;
    /**
     * Имя объекта класса. Поле не может быть null, Строка не может быть пустой
     */
    private String name;
    /**
     *  Координаты объекта класса. Поле не может быть null
     */
    private Coordinates coordinates;
    /**
     * Время создания объекта класса. Поле не может быть null, Значение этого поля должно генерироваться автоматически
     */
    private ZonedDateTime creationDate;
    /**
     * Минимальныое значение баллов за работу. Поле может быть null, Значение поля должно быть больше 0
     */
    private Integer minimalPoint;
    /**
     * Максимальное значение баллов за работу. Поле не может быть null, Значение поля должно быть больше 0
     */
    private Double maximumPoint;
    /**
     * Максимальные баллы за личные качества. Поле может быть null, Значение поля должно быть больше 0
     */
    private Integer personalQualitiesMaximum;
    /**
     * Сложность выполненной работы. Поле может быть null, Значение поля должно быть больше 0
     */
    private Difficulty difficulty;
    /**
     * Автор работы Поле может быть null
     */
    private Person person;

    /**
     * Конструктор класса с параметрами (без id)
     * @param name название работы
     * @param coordinates координаты работы
     * @param creationDate дата создания
     * @param minimalPoint минимальное значение
     * @param maximumPoint максимальное значение
     * @param personalQualitiesMaximum максимальные баллы за работу
     * @param difficulty сложность работы
     * @param person автор работы
     */
    public LabWork(String name, Coordinates coordinates, ZonedDateTime creationDate,
                   Integer minimalPoint, Double maximumPoint, Integer personalQualitiesMaximum,
                   Difficulty difficulty, Person person) {
        this.id = uniqueId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
        this.personalQualitiesMaximum = personalQualitiesMaximum;
        this.difficulty = difficulty;
        this.person = person;
    }
    /**
     * Конструктор класса с параметрами (включая id)
     * @param name название работы
     * @param coordinates координаты работы
     * @param creationDate дата создания
     * @param minimalPoint минимальное значение
     * @param maximumPoint максимальное значение
     * @param personalQualitiesMaximum максимальные баллы за работу
     * @param difficulty сложность работы
     * @param person автор работы
     */
    public LabWork(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate,
                   Integer minimalPoint, Double maximumPoint, Integer personalQualitiesMaximum,
                   Difficulty difficulty, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
        this.personalQualitiesMaximum = personalQualitiesMaximum;
        this.difficulty = difficulty;
        this.person = person;
    }

    /**
     * Метод, возращающий уникальный id
     * @return uniqueId значение поля uniqueId
     */
    public static int getUniqueId() {
        return uniqueId;
    }

    /**
     * Метод, устанавливающий уникальный id
     * @param uniqueId значение поля uniqueId
     */
    public static void setUniqueId(int uniqueId) {
        LabWork.uniqueId = uniqueId;
    }
    /**
     * Метод, возращающий индендификатор
     * @return id значение поля id
     */
    public int getId() {
        return id;
    }
    /**
     * Метод, устанавливающий id объекта
     * @param id значение поля id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Метод, возращающий имя объекта
     * @return name значение поля name
     */
    public String getName() {
        return name;
    }
    /**
     * Метод, устанавливающий имя объекта
     * @param name значение поля name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Метод, возращающий координаты объекта
     * @return coordinates значение поля coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
    /**
     * Метод, устанавливающий координату x
     * @param x значение поля x
     */
    public void setCoordinateX(Long x) {
        this.getCoordinates().setX(x);
    }
    /**
     * Метод, устанавливающий координату y
     * @param y значение поля y
     */
    public void setCoordinateY(Double y) {
        this.getCoordinates().setY(y);
    }
    /**
     * Метод, возращающий дату создания  объекта
     * @return creationDate значение поля creationDate
     */
    public String getCreationDate(){
        return ""+creationDate;
    }
    /**
     * Метод, возращающий отформатированиую дату создания объекта
     * @return creationDate значение поля creationDate
     */
    public String getFormattedCreationDate() {
        //2017-12-03T10:15:30+01:00
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        return creationDate.plusHours(3).format(europeanDateFormatter);
    }
    /**
     * Метод, устанавливающий дату создания объекта
     * @param creationDate значение поля creationDate
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * Метод, возращающий минимальные баллы работы
     * @return minimalPoint значение поля minimalPoint
     */
    public Integer getMinimalPoint() {
        return minimalPoint;
    }
    /**
     * Метод, устанавливающий минимальные баллы за работу
     * @param minimalPoint значение поля minimalPoint
     */
    public void setMinimalPoint(Integer minimalPoint) {
        this.minimalPoint = minimalPoint;
    }
    /**
     * Метод, возращающий максимальные баллы за работу
     * @return maximumPoint значение поля maximumPoint
     */
    public Double getMaximumPoint() {
        return maximumPoint;
    }
    /**
     * Метод, устанавливающий максимальные баллы за работу
     * @param maximumPoint значение поля maximumPoint
     */
    public void setMaximumPoint(Double maximumPoint) {
        this.maximumPoint = maximumPoint;
    }
    /**
     * Метод, возращающий максимальные баллы за личные качества
     * @return personalQualitiesMaximum значение поля personalQualitiesMaximum
     */
    public Integer getPersonalQualitiesMaximum() {
        return personalQualitiesMaximum;
    }
    /**
     * Метод, устанавливающий максимальные баллы за личные качества
     * @param personalQualitiesMaximum значение поля personalQualitiesMaximum
     */
    public void setPersonalQualitiesMaximum(Integer personalQualitiesMaximum) {
        this.personalQualitiesMaximum = personalQualitiesMaximum;
    }
    /**
     * Метод, возращающий уровень сложности
     * @return difficulty значение поля difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }
    /**
     * Метод, устанавливающий сложность работы
     * @param difficulty значение поля difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    /**
     * Метод, возращающий автора работы
     * @return person значение поля v
     */
    public Person getPerson() {
        return person;
    }
    /**
     * Метод, устанавливающий автора работы
     * @param person значение поля person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Метод, возращающий отформатированный вывод полей класса
     * @return поля объекта класса
     */
    @Override
    public String
    toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", maximumPoint=" + maximumPoint +
                ", personalQualitiesMaximum=" + personalQualitiesMaximum +
                ", difficulty=" + difficulty +
                ", person=" + person +
                '}';
    }
}
