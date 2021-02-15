package main;

import commands.CommandManager;
import exceptions.InvalidArgumentException;
import io.FileManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;

import static log.Log.logger;

/**
 * Класс, который управляет коллекцией людей
 */
public class PersonManager {
    private TreeSet<Person> personsTreeSet;
    private LocalDate initDate;
    FileManager fileManager;

    /**
     * Конструктор
     * @param fileManager
     */
    public PersonManager(FileManager fileManager) {
        personsTreeSet = new TreeSet<>();
        initDate = LocalDate.now();
        this.fileManager = fileManager;
    }

    /**
     * Метод, который возвращает тип коллекции
     * @return
     */
    public Class getType(){
        return personsTreeSet.getClass();
    }

    /**
     * Метод, который возвращает количество элементов коллекции коллекции
     * @return
     */
    public int getLength(){
        return personsTreeSet.size();
    }

    /**
     * Метод, который возвращает дату инициализации коллекции
     * @return
     */
    public LocalDate getInitDate(){
        return initDate;
    }

    /**
     * Метод, который удаляет человека из коллекции по id
     * @param id
     * @return
     */
    public boolean removePerson(int id){
        for (Person person: personsTreeSet) {
            if (person.getId() == id){
                personsTreeSet.remove(person);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который удаляет всех людей из коллекции
     */
    public void removeAll(){
        personsTreeSet.clear();
    }

    /**
     * Метод, который обращается к File Manager для чтения коллекции из файла
     * @throws InvalidArgumentException
     */
    public void readFromFile() throws InvalidArgumentException {
        fileManager.read(personsTreeSet);
    }

    /**
     * Метод, который обращается к File Manager для записи коллекции в файл
     */
    public void writeToFile(){
        fileManager.write(personsTreeSet);
    }

    /**
     * Метод, который выводит в стандартный поток вывода информацию о человеке
     * @param person
     */
    public void printPerson(Person person){
        System.out.printf("Person: id=%d, name=%s, coordinates: coordinates.x=%f, coordinates.y=%d, " +
                        "creationDate=%s, height=%d, birthday=%s, eyeColor=%s, hairColor=%s, location: location.x=%f, " +
                        "location.y=%d, location.name=%s\n", person.getId(), person.getName(), person.getCoordinates().getX(),
                person.getCoordinates().getY(), person.getCreationDate().toLocalDate().toString(),
                person.getHeight(), person.getBirthday().toLocalDate().toString(), person.getEyeColor().toString(),
                person.getHairColor().toString(), person.getLocation().getX(), person.getLocation().getY(),
                person.getLocation().getName());
    }

    /**
     * Метод, который выводит в стандартный поток вывода информацию о каждом человеке в коллекции
     */
    public void printPersonsTreeSet(){
        for (Person person: personsTreeSet) {
            printPerson(person);
        }
    }

    /**
     * Метод, который выводит в стандартный поток вывода информацию о каждом человеке в коллекции, отсортированной
     * с помощью компаратора
     * @param comparator
     */
    public void printSortedPersonsEyeColorField(Comparator<Person> comparator){
        personsTreeSet.stream().sorted(comparator).forEach(person -> System.out.println(person.getEyeColor()));
    }

    /**
     * Метод, который выводит в стандартный поток вывода информацию о каждом человеке в коллекции, имя которого содержит
     * заданную подстроку
     * @param name
     */
    public void printPersonsContainsName(String name){
        for (Person person: personsTreeSet) {
            if (person.getName().contains(name)) {
                System.out.printf("Person: id=%d, name=%s, coordinates: coordinates.x=%f, coordinates.y=%d, " +
                                "creationDate=%s, height=%d, birthday=%s, eyeColor=%s, hairColor=%s, location: location.x=%f, " +
                                "location.y=%d, location.name=%s\n", person.getId(), person.getName(), person.getCoordinates().getX(),
                        person.getCoordinates().getY(), person.getCreationDate().toLocalDate().toString(),
                        person.getHeight(), person.getBirthday().toLocalDate().toString(), person.getEyeColor().toString(),
                        person.getHairColor().toString(), person.getLocation().getX(), person.getLocation().getY(),
                        person.getLocation().getName());
            }
        }
    }

    /**
     * Метод, который добавляет человека в коллекцию
     * @param name
     * @param coordinatesX
     * @param coordinatesY
     * @param height
     * @param birthday
     * @param eyeColor
     * @param hairColor
     * @param locationX
     * @param locationY
     * @param locationName
     */
    public void addPerson(String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                          HairColor hairColor, float locationX, long locationY, String locationName) {
        try {
            personsTreeSet.add(new Person(name, new Coordinates(coordinatesX, coordinatesY), height, birthday, eyeColor,
                    hairColor, new Location(locationX, locationY, locationName)));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, который добавляет человека в коллекцию
     * @param name
     * @param height
     * @param birthday
     * @param commandManager
     */
    public void addPerson(String name, long height, LocalDateTime birthday, CommandManager commandManager){
        Person person;
        try {
            person = new Person(name, height, birthday);
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            return;
        }
        while (true){
            try {
                person.setCoordinatesX(commandManager.askCoordinatesX());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
        while (true){
            try {
                person.setCoordinatesY(commandManager.askCoordinatesY());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
        while (true){
            try{
                person.setEyeColor(EyeColor.valueOf(commandManager.askEyeColor()));
                break;
            } catch (IllegalArgumentException | InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("This eyeColor does not exist");
            }
        }
        while (true){
            try{
                person.setHairColor(HairColor.valueOf(commandManager.askHairColor()));
                break;
            } catch (IllegalArgumentException | InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("This hairColor does not exist");
            }
        }
        person.setLocationX(commandManager.askLocationX());
        person.setLocationY(commandManager.askLocationY());
        while (true){
            try {
                person.setLocationName(commandManager.askLocationName());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid argument: max length of location.name is 480");
            }
        }
        personsTreeSet.add(person);
    }

    /**
     * Метод, который обновляет значение человека, id которого равен заданному
     * @param id
     * @param name
     * @param coordinatesX
     * @param coordinatesY
     * @param height
     * @param birthday
     * @param eyeColor
     * @param hairColor
     * @param locationX
     * @param locationY
     * @param locationName
     * @return
     */
    public boolean updatePerson(int id, String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                             HairColor hairColor, float locationX, long locationY, String locationName){
        LocalDateTime creationDate;
        for (Person person: personsTreeSet) {
            if (person.getId() == id){
                creationDate = person.getCreationDate();
                personsTreeSet.remove(person);
                try {
                    personsTreeSet.add(new Person(id, name, new Coordinates(coordinatesX, coordinatesY), creationDate,
                            height, birthday, eyeColor, hairColor, new Location(locationX, locationY, locationName)));
                } catch (InvalidArgumentException e){
                    logger.log(Level.WARNING, e.toString(), e);
                    System.out.println(e.getMessage());
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который обновляет значение человека, id которого равен заданному
     * @param id
     * @param name
     * @param height
     * @param birthday
     * @param commandManager
     * @return
     */
    public boolean updatePerson(int id, String name, long height, LocalDateTime birthday, CommandManager commandManager){
        LocalDateTime creationDate;
        Person person;
        for (Person oldPerson: personsTreeSet) {
            if (oldPerson.getId() == id){
                creationDate = oldPerson.getCreationDate();
                personsTreeSet.remove(oldPerson);
                try {
                    person = new Person(id, name, height, birthday, creationDate);
                } catch (InvalidArgumentException e){
                    logger.log(Level.WARNING, e.toString(), e);
                    System.out.println(e.getMessage());
                    return true;
                }
                while (true){
                    try {
                        person.setCoordinatesX(commandManager.askCoordinatesX());
                        break;
                    } catch (InvalidArgumentException e){
                        logger.log(Level.WARNING, e.getMessage(), e);
                        System.out.println(e.getMessage());
                    }
                }
                while (true){
                    try {
                        person.setCoordinatesY(commandManager.askCoordinatesY());
                        break;
                    } catch (InvalidArgumentException e){
                        logger.log(Level.WARNING, e.getMessage(), e);
                        System.out.println(e.getMessage());
                    }
                }
                while (true){
                    try{
                        person.setEyeColor(EyeColor.valueOf(commandManager.askEyeColor()));
                        break;
                    } catch (IllegalArgumentException | InvalidArgumentException e){
                        logger.log(Level.WARNING, e.toString(), e);
                        System.out.println("This eyeColor does not exist");
                    }
                }
                while (true){
                    try{
                        person.setHairColor(HairColor.valueOf(commandManager.askHairColor()));
                        break;
                    } catch (IllegalArgumentException | InvalidArgumentException e){
                        logger.log(Level.WARNING, e.toString(), e);
                        System.out.println("This hairColor does not exist");
                    }
                }
                person.setLocationX(commandManager.askLocationX());
                person.setLocationY(commandManager.askLocationY());
                while (true){
                    try {
                        person.setLocationName(commandManager.askLocationName());
                        break;
                    } catch (InvalidArgumentException e){
                        logger.log(Level.WARNING, e.toString(), e);
                        System.out.println("Invalid argument: max length of location.name is 480");
                    }
                }
                personsTreeSet.add(person);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который добавляет человека в коллекцию, если его значение превышает значение наибольшего человека
     * этой коллекции
     * @param name
     * @param coordinatesX
     * @param coordinatesY
     * @param height
     * @param birthday
     * @param eyeColor
     * @param hairColor
     * @param locationX
     * @param locationY
     * @param locationName
     */
    public void addPersonIfMax(String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                          HairColor hairColor, float locationX, long locationY, String locationName) {
        Person newPerson;
        boolean max = true;
        try {
            newPerson = new Person(name, new Coordinates(coordinatesX, coordinatesY), height, birthday, eyeColor,
                    hairColor, new Location(locationX, locationY, locationName));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            return;
        }
        for (Person person: personsTreeSet) {
            if (person.compareTo(newPerson) > 0) {
                max = false;
            }
        }
        if (max){
            personsTreeSet.add(newPerson);
        }
    }

    /**
     * Метод, который добавляет человека в коллекцию, если его значение превышает значение наибольшего человека
     * этой коллекции
     * @param name
     * @param height
     * @param birthday
     */
    public void addPersonIfMax(String name, long height, LocalDateTime birthday, CommandManager commandManager){
        Person newPerson;
        boolean max = true;
        try {
            newPerson = new Person(name, height, birthday);
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            return;
        }
        while (true){
            try {
                newPerson.setCoordinatesX(commandManager.askCoordinatesX());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
        while (true){
            try {
                newPerson.setCoordinatesY(commandManager.askCoordinatesY());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
        while (true){
            try{
                newPerson.setEyeColor(EyeColor.valueOf(commandManager.askEyeColor()));
                break;
            } catch (IllegalArgumentException | InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("This eyeColor does not exist");
            }
        }
        while (true){
            try{
                newPerson.setHairColor(HairColor.valueOf(commandManager.askHairColor()));
                break;
            } catch (IllegalArgumentException | InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("This hairColor does not exist");
            }
        }
        newPerson.setLocationX(commandManager.askLocationX());
        newPerson.setLocationY(commandManager.askLocationY());
        while (true){
            try {
                newPerson.setLocationName(commandManager.askLocationName());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid argument: max length of location.name is 480");
            }
        }
        for (Person person: personsTreeSet) {
            if (person.compareTo(newPerson) > 0) {
                max = false;
            }
        }
        if (max){
            personsTreeSet.add(newPerson);
        }
    }

    /**
     * Метод, который добавляет человека в коллекцию, если если его значение меньше, чем у наименьшего
     * человека этой коллекции
     * @param name
     * @param coordinatesX
     * @param coordinatesY
     * @param height
     * @param birthday
     * @param eyeColor
     * @param hairColor
     * @param locationX
     * @param locationY
     * @param locationName
     */
    public void addPersonIfMin(String name, Double coordinatesX, long coordinatesY, long height, LocalDateTime birthday, EyeColor eyeColor,
                               HairColor hairColor, float locationX, long locationY, String locationName) {
        Person newPerson;
        boolean min = true;
        try {
            newPerson = new Person(name, new Coordinates(coordinatesX, coordinatesY), height, birthday, eyeColor,
                    hairColor, new Location(locationX, locationY, locationName));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            return;
        }
        for (Person person: personsTreeSet) {
            if (person.compareTo(newPerson) < 0) {
                min = false;
            }
        }
        if (min){
            personsTreeSet.add(newPerson);
        }
    }

    /**
     * Метод, который добавляет человека в коллекцию, если если его значение меньше, чем у наименьшего
     * человека этой коллекции
     * @param name
     * @param height
     * @param birthday
     */
    public void addPersonIfMin(String name, long height, LocalDateTime birthday, CommandManager commandManager){
        Person newPerson;
        boolean min = true;
        try {
            newPerson = new Person(name, height, birthday);
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            return;
        }
        while (true){
            try {
                newPerson.setCoordinatesX(commandManager.askCoordinatesX());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
        while (true){
            try {
                newPerson.setCoordinatesY(commandManager.askCoordinatesY());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.getMessage(), e);
                System.out.println(e.getMessage());
            }
        }
        while (true){
            try{
                newPerson.setEyeColor(EyeColor.valueOf(commandManager.askEyeColor()));
                break;
            } catch (IllegalArgumentException | InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("This eyeColor does not exist");
            }
        }
        while (true){
            try{
                newPerson.setHairColor(HairColor.valueOf(commandManager.askHairColor()));
                break;
            } catch (IllegalArgumentException | InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("This hairColor does not exist");
            }
        }
        newPerson.setLocationX(commandManager.askLocationX());
        newPerson.setLocationY(commandManager.askLocationY());
        while (true){
            try {
                newPerson.setLocationName(commandManager.askLocationName());
                break;
            } catch (InvalidArgumentException e){
                logger.log(Level.WARNING, e.toString(), e);
                System.out.println("Invalid argument: max length of location.name is 480");
            }
        }
        for (Person person: personsTreeSet) {
            if (person.compareTo(newPerson) < 0) {
                min = false;
            }
        }
        if (min){
            personsTreeSet.add(newPerson);
        }
    }

    /**
     * Метод, который возвращет самого большого человека в коллекцию, сравнивая их полученным компаратором
     * @param comparator
     * @return
     */
    public Person getMax(Comparator<Person> comparator) {
        Iterator<Person> iterator = personsTreeSet.iterator();
        Person max;
        Person temp;
        if (iterator.hasNext()) {
            max = iterator.next();
        } else {
            return null;
        }
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (comparator.compare(temp, max) > 0) {
                max = temp;
            }
        }
        return max;
    }

    /**
     * Метод, который возвращает компаратор, сравнивающий людей по цвету глаз
     * @return
     */
    public Comparator<Person> getComparatorByEyeColor(){
        return (Person a, Person b) -> a.getEyeColor().getHex() - b.getEyeColor().getHex();
    }

    /**
     * Метод, который возвращает компаратор, сравнивающий людей по цвету глаз в обратном порядке
     * @return
     */
    public Comparator<Person> getDescendingComparatorByEyeColor(){
        return (Person a, Person b) -> b.getEyeColor().getHex() - a.getEyeColor().getHex();
    }
}