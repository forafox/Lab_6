package workWithFile;

import collection.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * @author Karabanov Andrey
 * @version 1.0
 * @date 20.02.2023 23:40
 */
/**
 * Класс, осуществляющий парсинг данных, переданных в качестве строки, в коллекцию или из коллекции доставать данные, которые будут записаны в строку в формате XML.
 */
public class XmlParser {
    /**
     * Метод, осуществляющий парсинг данных, представленных в качестве строки, в коллекцию
     *
     * @param text данные, которые следует запарсить
     * @return массив объектов типа LabWork
     * @throws ParserConfigurationException указывает на серьезную ошибку, возникшую в ходе выполнения метода
     * @throws SAXException                 Encapsulate a general SAX error or warning
     * @throws IOException                  Signals that an I/O exception of some sort has occurred
     */
    public LabWork[] parseToCollection(InputSource text) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlHandler handler = new XmlHandler();
        try {
            parser.parse(text, handler);
        } catch (SAXException ignored) {
        }
        LabWork[] labWorkArr = new LabWork[handler.labWorks.size()];
        return handler.labWorks.toArray(labWorkArr);
    }


    private static class XmlHandler extends DefaultHandler {
        /**
         * Массив объектов типа LabWork
         */
        private ArrayList<LabWork> labWorks = new ArrayList<>();
        /**
         * Хранит поле name объекта класса LabWork
         */
        private String name;
        /**
         * Хранит поле x объекта класса Coordinates
         */
        private Long x = null;
        /**
         * Хранит поле y объекта класса Coordinates
         */
        private Double y = null;
        /**
         * Хранит поле creationDate объекта класса LabWork
         */
        private ZonedDateTime creationDate = null;
        /**
         * Хранит поле minimalPoint объекта класса LabWork
         */
        private Integer minimalPoint = null;
        /**
         * Хранит поле maximumPoint объекта класса LabWork
         */
        private Double maximumPoint = null;
        /**
         * Хранит поле personalQualitiesMaximum объекта класса LabWork
         */
        private Integer personalQualitiesMaximum = null;
        /**
         * Хранит поле difficulty объекта класса LabWork
         */
        private Difficulty difficulty = null;
        /**
         * Хранит поле personName объекта класса Person
         */
        private String personName;
        /**
         * Хранит поле personHeight объекта класса Person
         */
        private Float personHeight;
        /**
         * Хранит поле personPassportID объекта класса Person
         */
        private String personPassportID;
        /**
         * Хранит поле locationX объекта класса Location
         */
        private Integer locationX;
        /**
         * Хранит поле locationY объекта класса Location
         */
        private Float locationY;
        /**
         * Хранит поле locationName объекта класса Location
         */
        private String locationName;
        /**
         * Хранит название тега последнего встретившегося элемента, представленного в формате XML
         */
        private String lastElementName;

        /**
         * Метод, который присваивает полю lastElementName значение тега, который встретился в начале элемента.
         *
         * @param uri        The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
         * @param localName  The local name (without prefix), or the empty string if Namespace processing is not being performed.
         * @param qName      The qualified name (with prefix), or the empty string if qualified names are not available.
         * @param attributes The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
         */
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName = qName;
        }

        /**
         * Метод, присваивающий полю, который хранится в данном объекте класса, значение, соответствующее значению между тегами. Проверка осуществляется по последнему открытому тегу.
         *
         * @param ch     The characters.
         * @param start  The start position in the character array.
         * @param length The number of characters to use from the character array.
         * @throws ClassCastException бросается, когда мы пытаемся привести значение между тегами к недопустимому типу в поле, имя которого совпадает в названием тега
         */
        @Override
        public void characters(char[] ch, int start, int length) throws ClassCastException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();
            try {
                if (!information.isEmpty()) {
                    switch (lastElementName) {
                        case "name":
                            name = information;
                            break;
                        case "coordinate_x":
                            x = Long.parseLong(information);
                            break;
                        case "coordinate_y":
                            y = Double.parseDouble(information);
                            break;
                        case "creation_date":
                            creationDate = ZonedDateTime.parse(information);
                            break;
                        case "minimalPoint":
                            minimalPoint = Integer.parseInt(information);
                            break;
                        case "maximumPoint":
                            maximumPoint = Double.parseDouble(information);
                            break;
                        case "personalQualitiesMaximum":
                            personalQualitiesMaximum = personalQualitiesMaximum.valueOf(information);
                            break;
                        case "difficulty":
                            difficulty = difficulty.valueOf(information);
                            break;
                        case "PersonName":
                            personName = (information);
                            break;
                        case "PersonHeight":
                            personHeight = Float.parseFloat(information);
                            break;
                        case "PersonPassportID":
                            personPassportID = (information);
                            break;
                        case "LocationX":
                            locationX = Integer.parseInt(information);
                            break;
                        case "LocationY":
                            locationY = Float.parseFloat(information);
                            break;
                        case "LocationName":
                            locationName = (information);
                            break;
                    }


                }
            } catch (IllegalArgumentException ex) {
                System.err.println("Указанной константы перечисляемого типа не существует, либо невозможно преобразование типов");
                System.err.println(name);
            }
        }

        /**
         * Метод, добавляющий объект LabWork, когда встречает в файле встречается закрывающийся тег LabWork.
         * Если поля не соответствуют ОДЗ, то их значение не записывается, объект коллекции не сохраняется. После выполнения метода значения полей обнуляются.
         *
         * @param uri       The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
         * @param localName The local name (without prefix), or the empty string if Namespace processing is not being performed.
         * @param qName     The qualified name (with prefix), or the empty string if qualified names are not available.
         */
        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("LabWork")) {
                if ((name != null && !name.isEmpty()) &&
                        (x != null && x>-985 ) &&
                        (minimalPoint == null || minimalPoint > 0) &&
                        (maximumPoint != null && maximumPoint > 0) &&
                        (personalQualitiesMaximum == null || personalQualitiesMaximum>0) &&
                        (personName != null && !personName.isEmpty()) &&
                        (personHeight >0) &&
                        (personPassportID != null && !personPassportID.isEmpty() && personPassportID.length()<=28) &&
                        (locationName == null || locationName.length()<=669)) {

                    Coordinates coordinates = new Coordinates(x,y);

                    if (creationDate == null) {
                        String i = Instant.now().toString();
                        creationDate = ZonedDateTime.parse(i);
                    }
                    Location location =new Location(locationX,locationY,locationName);
                    Person person = new Person(personName,personHeight,personPassportID,location);


                    LabWork labWork = new LabWork(name,coordinates,creationDate,minimalPoint,maximumPoint,personalQualitiesMaximum,difficulty,person);

                    labWorks.add(labWork);
                } else System.err.println("Указаны не все параметры, либо параметры не принадлежат допустимой ОДЗ");

                 name = null;
                  x = null;
                  y = null;
                  creationDate = null;
                  minimalPoint = null;
                  maximumPoint = null;
                  personalQualitiesMaximum = null;
                  difficulty = null;
                  personName = null;
                  personHeight = null;
                  personPassportID = null;
                  locationX = null;
                  locationY = null;
                  locationName = null;
            }
        }
    }

    /**
     * Метод, осуществляющий преобразование массива объектов в формат XML, представленный в виде строки
     *
     * @param  labWorks объектов класса LabWork
     * @return строка, хранящая данные, представленные в формате XML
     */
    public String parseToXml(LabWork[] labWorks) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version = \"1.0\"?>\n");
        sb.append("<treemap>\n");
        for (LabWork labWork : labWorks) {
            sb.append("\t<LabWork>\n");
            sb.append("\t\t<name>").append(labWork.getName()).append("</name>");
            sb.append("\n\t\t<coordinate_x>").append(labWork.getCoordinates().getX()).append("</coordinate_x>");
            try {
                Double y = labWork.getCoordinates().getY();
                sb.append("\n\t\t<coordinate_y>").append(y).append("</coordinate_y>");
            } catch (NullPointerException ignored) {
            }
            sb.append("\n\t\t<creation_date>").append(labWork.getCreationDate()).append("</creation_date>");
            sb.append("\n\t\t<minimalPoint>").append(labWork.getMinimalPoint()).append("</minimalPoint>");
            sb.append("\n\t\t<maximumPoint>").append(labWork.getMaximumPoint()).append("</maximumPoint>");
            sb.append("\n\t\t<personalQualitiesMaximum>").append(labWork.getPersonalQualitiesMaximum()).append("</personalQualitiesMaximum>");
            sb.append("\n\t\t<difficulty>").append(labWork.getDifficulty()).append("</difficulty>");
            sb.append("\n\t\t<PersonName>").append(labWork.getPerson().getName()).append("</PersonName>");
            sb.append("\n\t\t<PersonHeight>").append(labWork.getPerson().getHeight()).append("</PersonHeight>");
            sb.append("\n\t\t<PersonPassportID>").append(labWork.getPerson().getPassportID()).append("</PersonPassportID>");
            sb.append("\n\t\t<LocationX>").append(labWork.getPerson().getLocation().getX()).append("</LocationX>");
            sb.append("\n\t\t<LocationY>").append(labWork.getPerson().getLocation().getY()).append("</LocationY>");
            sb.append("\n\t\t<LocationName>").append(labWork.getPerson().getLocation().getName()).append("</LocationName>");

            sb.append("\n\t</LabWork>\n");
        }
        sb.append("</treemap>\n");
        return sb.toString();
    }
}
