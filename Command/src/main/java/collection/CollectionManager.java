package collection;


import workWithFile.FileManager;
import workWithFile.XmlParser;

import java.io.PrintStream;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:41
 */

/**
 * Класс который управляет коллекцией
 */
public class CollectionManager {
    /**
     * Коллекция, над которой будет осуществляться работа
     */
    TreeMap<Integer,LabWork> treeMap;
    /**
     * Время инициализации коллекции
     */
    ZonedDateTime collectionInitialization;

    /**
     * Конструктор - создание нового объекта менеджера коллекции
     */
    public CollectionManager() {
        this.treeMap = new TreeMap<>();
        String i = Instant.now().toString();
        collectionInitialization = ZonedDateTime.parse(i);
    }
    /**
     * Метод, выводящий основную информацию по используемой коллекции
     */
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Коллекция ").append(treeMap.getClass().getSimpleName()).append("\n");
        sb.append("Тип элементов коллекции: ").append(LabWork.class.getSimpleName()).append("\n");

        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(pattern);
        sb.append("Время ининициализации коллекции: ").append(collectionInitialization.plusHours(3).format(europeanDateFormatter)).append("\n");
        sb.append("Количество элементов в коллекции: ").append(treeMap.size()).append("\n");
        return sb.toString();
    }
    /**
     * Метод, выводящий информацию по элементам коллекции
     */
    public String show() {
        StringBuilder sb= new StringBuilder();
        if (treeMap.size() == 0) {
            sb.append("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, LabWork> entry : treeMap.entrySet()) {
                sb.append(entry.getValue().toString()).append("\n"); // перебор элементов коллкции
            } //Получение значений по коллекции
        }
        return sb.toString();
    }

    /**
     * Метод, демонстрирующий все сложности элементов колекции
     */
    public String showDifficult(){
        StringBuilder sb = new StringBuilder();
        if (treeMap.size() == 0) {
            sb.append("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, LabWork> entry : treeMap.entrySet()) {
               sb.append(entry.getValue().getDifficulty()).append("\n"); // перебор элементов коллкции
            } //Получение значений по коллекции
        }
        return sb.toString();
    }

    /**
     * Метод, демонстрирующий поле Person в порядке уменьшения
     */
    public String showPerson(){
        StringBuilder sb= new StringBuilder();
        ArrayList<Person> personArrayList = new ArrayList<>();
        if (treeMap.size() == 0) {
            sb.append("Коллекция пуста.");
        } else {
            for (Map.Entry<Integer, LabWork> entry : treeMap.entrySet()) {
                personArrayList.add(entry.getValue().getPerson());
            }
            Collections.reverse(personArrayList);
            for(Person person : personArrayList){
               sb.append(person.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Метод, удаляющий элементы с меньшим значением ( ключа)
     * @param id уникальный идентификатор элемента коллекции(ключ)
     */
    public void removeLowerKey(Integer id){
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, LabWork> entry : treeMap.entrySet()) {
            if (entry.getKey() < id) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            treeMap.remove(key);
        }
    }

    /**
     * Группировка по полю difficult элементов коллекции
     */
    public String GroupCountingByDifficult(){
        int countVERY_EASY=0;
        int countEASY=0;
        int countVERY_HARD=0;
        int countINSANE=0;
        int countHOPELESS=0;
        for (Map.Entry<Integer, LabWork> entry : treeMap.entrySet()) {
            Difficulty difficulty=(entry.getValue().getDifficulty());
            if(difficulty.valueOf("VERY_EASY").equals(difficulty)) countVERY_EASY+=1;
            else if(difficulty.valueOf("EASY").equals(difficulty)) countEASY+=1;
            else if(difficulty.valueOf("VERY_HARD").equals(difficulty)) countVERY_HARD+=1;
            else if(difficulty.valueOf("INSANE").equals(difficulty)) countINSANE+=1;
            else if(difficulty.valueOf("HOPELESS").equals(difficulty)) countHOPELESS+=1;
        }
        return("Кол-во элементов co значением поля Difficult: \nVERY_EASY - "+countVERY_EASY+"\nEASY - "+countEASY+"\n" +
                "VERY_HARD - "+countVERY_HARD+"\nINSANE - "+countINSANE+"\nHOPELESS - "+countHOPELESS);
    }

    /**
     * Метод, добавляющий новый элемент в коллекцию c рандомным id
     * @param labWork элемент коллекции, требующий добавления
     */
    public void insert(LabWork labWork) {
            int id=getNewRandomId();
            labWork.setId(id);
            treeMap.put(id, labWork);
        }
    /**
     * Метод, добавляющий новый элемент в коллекцию c конкретным  id
     * @param labWork элемент коллекции, требующий добавления
     */
    public void insertWithId(Integer id, LabWork labWork, PrintStream printStream) {
        if (treeMap.get(id) == null) {
            treeMap.put(id, labWork);
        } else printStream.printf("Элемент с данным ключом уже существует");
    }

    /**
     * Метод, возращаюзий рандомное значение id ( в пределах 10.000)
     * @return радномный id
     */
    public int getNewRandomId(){
        int max=10000;
        int i =(int)(Math.random() * max);
        while(treeMap.containsKey(i)){
             i =(int)(Math.random() * max);
        }
        return i;
    }
    /**
     * Метод, изменяющий поле выбранного по идентификатору элемента коллекции
     *
     * @param id    уникальный идентификатор элемента коллекции (ключ)
     * @param field имя поля элемента коллекции, требующее изменения
     * @param value значение поля элемента коллекции
     * @throws NullPointerException     исключение, выбрасываемое когда требует инициализации, но не инициалировано
     * @throws ClassCastException       исключение, выбрасываемое при попытке преобразовать один тип в другой, который не может быть получен из исходного
     * @throws IllegalArgumentException исключение, выбрасываемое при попытке передать методу недопустимые атрибуты
     */
    public void update(Integer id, String field, String value,PrintStream printStream) {
        if(LabWorkFieldValidation.validate(field,value)) {
            switch (field) {
                case "name": {
                    if (value.equals("")) throw new NullPointerException();
                    treeMap.get(id).setName(value);
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "coordinate_x": {
                    if (value.equals("")) value = null;
                    treeMap.get(id).setCoordinateX(Long.valueOf(value));
                    System.out.println("Значение поля было изменено");
                    break;

                }
                case "coordinate_y": {
                    if (value.equals("")) value = null;
                    treeMap.get(id).setCoordinateY(Double.parseDouble(value));
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "minimalPoint": {
                    if (value.equals("")) {
                        treeMap.get(id).setMinimalPoint(null);
                    } else {
                        treeMap.get(id).setMinimalPoint(Integer.parseInt(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "maximumPoint": {
                    if (value.equals("")) {
                        treeMap.get(id).setMaximumPoint(null);
                    } else {
                        treeMap.get(id).setMaximumPoint(Double.parseDouble(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "personalQualitiesMaximum": {
                    if (value.equals("")) {
                        treeMap.get(id).setPersonalQualitiesMaximum(null);
                    } else {
                        treeMap.get(id).setPersonalQualitiesMaximum(Integer.parseInt(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "difficulty": {
                    treeMap.get(id).setDifficulty(Difficulty.valueOf(value.toUpperCase(Locale.ROOT)));
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "PersonName": {
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().setName(null);
                    } else {
                        treeMap.get(id).getPerson().setName(value);
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "PersonHeight": {
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().setHeight(0);
                    } else {
                        treeMap.get(id).getPerson().setHeight(Float.parseFloat(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "PersonPassportID": {
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().setPassportID(null);
                    } else {
                        treeMap.get(id).getPerson().setPassportID(value);
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "LocationX": {
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().getLocation().setX(0);
                    } else {
                        treeMap.get(id).getPerson().getLocation().setX(Integer.parseInt(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "LocationY": {
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().getLocation().setY(0);
                    } else {
                        treeMap.get(id).getPerson().getLocation().setY(Float.parseFloat(value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "LocationName": {
                    if (value.equals("")) {
                        treeMap.get(id).getPerson().getLocation().setName(null);
                    } else {
                        treeMap.get(id).getPerson().getLocation().setName((value));
                    }
                    System.out.println("Значение поля было изменено");
                    break;
                }
                case "stop": {
                    break;
                }
                default: {
                    System.out.println("Поле не распознано");
                    break;
                }
            }
        }else{
            System.err.println("Неверно указано название поля или значение не принадлежит допустимому.");
        }
    }
    /**
     * Метод, удаляющий выбранный по идентификатору элемент коллекции
     *
     * @param id уникальный идентификатор элемента коллекции (ключ)
     */
    public void removeKey(Integer id) {
        treeMap.remove(id);
    }
    /**
     * Метод, удаляющий все элементы коллекции
     */
    public void clear() {
        treeMap.clear();
    }
    /**
     * Метод, выводящий булевый результат истины, если в коллекции существует элемент с выбранным ключом, иначе ложь
     *
     * @param id уникальный идентификатор элемента коллекции (ключ)
     * @return true - в коллекции существует элемент с выбранным ключом, false - такого элемента не существует
     */
    public boolean containsKey(Integer id) {
        return treeMap.containsKey(id);
    }
    /**
     * Метод, сохраняющий элементы коллекции в формате XML
     *
     * @param filePath путь до файла, куда следует сохранить элементы коллекции
     */
    public void save(String filePath) {
        XmlParser xmlParser = new XmlParser();
        FileManager fileManager = new FileManager();

        LabWork[] labWorks = new LabWork[treeMap.size()];
        labWorks = treeMap.values().toArray(labWorks);
        String str = xmlParser.parseToXml(labWorks);
        fileManager.writeToFile(str, filePath);
    }

    /**
     *
     * @return строка, содержащая все поля коллекции. Отформатировано выводит все в столбец
     */
    public static String getFieldNames(){
        return "Список всех полей: \nname (String)\ncoordinate_x (Long)\ncoordinate_y (Double)\n" +
                "minimalPoint (Integer)\nmaximumPoint (Double)\npersonalQualitiesMaximum (Integer)\ndifficulty" +
                Arrays.toString(Difficulty.values())+"\nPersonName (String)\nPersonHeight (Float)" +
                "\nPersonPassportID (String)\nLocationX (Int)\nLocationY (Float)\nLocationName (String)\n";
    }

    /**
     * Метод удаляет элементы коллекции,с полем ( id) выше, чем у заданного поля (id)
     * @param id уникальный идентификатор элемента коллекции (ключ)
     */
    public void removeGreaterKey(Integer id){
        ArrayList<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, LabWork> entry : treeMap.entrySet()) {
            if (entry.getKey() > id) keys.add(entry.getKey());
        }
        for (Integer key : keys) {
            treeMap.remove(key);
        }
    }
}
