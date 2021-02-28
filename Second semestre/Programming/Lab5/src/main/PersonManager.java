package main;

import cleint.ClientManagerInterface;
import exceptions.InvalidArgumentException;
import exceptions.NoIdLeftException;
import io.FileManagerInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Level;
import static log.Log.logger;

/**
 * Класс, который управляет коллекцией людей
 */
public class PersonManager implements ObjectManager{
    private TreeSet<Person> personsTreeSet;
    private LocalDate initDate;
    FileManagerInterface fileManager;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Конструктор
     * @param fileManager
     */
    public PersonManager(FileManagerInterface fileManager) {
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
    public boolean removeElement(int id){
        for (Person person: personsTreeSet) {
            if (person.getId() == id){
                personsTreeSet.remove(person);
                new PersonBuilder().removeId(id);
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
        new PersonBuilder().removeAllId();
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
     * @param object
     */
    public void printElement(Object object){
        Person person = (Person) object;
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
    public void printCollection(){
        for (Person person: personsTreeSet) {
            printElement(person);
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
     * @param clientManager
     */
    public void addPerson(ClientManagerInterface clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        generateId(personBuilder);
        inputFields(clientManager, personBuilder);
        try {
            personsTreeSet.add(personBuilder.getPerson());
        } catch (InvalidArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, который обновляет значение человека, id которого равен заданному
     * @param id, clientManager
     * @return
     */
    public boolean updatePerson(int id, ClientManagerInterface clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        for (Person oldPerson: personsTreeSet) {
            if (oldPerson.getId() == id){
                try {
                    personBuilder.removeId(id);
                    personBuilder.setId(id);
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                }
                personBuilder.setCreationDate(oldPerson.getCreationDate().toLocalDate());
                inputFields(clientManager, personBuilder);
                try {
                    personsTreeSet.add(personBuilder.getPerson());
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                personsTreeSet.remove(oldPerson);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который добавляет человека в коллекцию, если его значение превышает значение наибольшего человека
     * этой коллекции
     * @param clientManager
     */
    public void addPersonIfMax(ClientManagerInterface clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        boolean max = true;
        generateId(personBuilder);
        inputFields(clientManager, personBuilder);
        Person newPerson = null;
        try{
            newPerson = personBuilder.getPerson();
        }catch (InvalidArgumentException e){
            System.out.println(e.getMessage());
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
     * @param clientManager
     */
    public void addPersonIfMin(ClientManagerInterface clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        boolean min = true;
        generateId(personBuilder);
        inputFields(clientManager, personBuilder);
        Person newPerson = null;
        try{
            newPerson = personBuilder.getPerson();
        }catch (InvalidArgumentException e){
            System.out.println(e.getMessage());
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

    private void inputName(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try{
            personBuilder.setName(clientManager.askValue("Name"));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputName(clientManager, personBuilder);
        }
    }

    private void inputCoordinatesX(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try {
            personBuilder.setCoordinatesX(Double.parseDouble(clientManager.askValue("Coordinates.x")));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputCoordinatesX(clientManager, personBuilder);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Coordinates.x must be double");
            inputCoordinatesX(clientManager, personBuilder);
        }
    }

    private void inputCoordinatesY(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try {
            personBuilder.setCoordinatesY(Long.parseLong(clientManager.askValue("Coordinates.y")));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputCoordinatesY(clientManager, personBuilder);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Coordinates.y must be long");
            inputCoordinatesY(clientManager, personBuilder);
        }
    }

    private void inputHeight(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try{
            personBuilder.setHeight(Long.parseLong(clientManager.askValue("Height")));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputHeight(clientManager, personBuilder);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Height must be long");
            inputHeight(clientManager, personBuilder);
        }
    }

    private void inputBirthday(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try{
            personBuilder.setBirthday(LocalDate.parse(clientManager.askValue("Birthday")).atStartOfDay());
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputBirthday(clientManager, personBuilder);
        } catch (DateTimeParseException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Date must be in format yyyy-MM-dd");
            inputBirthday(clientManager, personBuilder);
        }
    }

    private void inputEyeColor(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try{
            personBuilder.setEyeColor(EyeColor.valueOf(clientManager.askValue("EyeColor " +
                    "(BLACK, ORANGE, WHITE)").toUpperCase()));
        } catch (IllegalArgumentException | InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("This eyeColor does not exist");
            inputEyeColor(clientManager, personBuilder);
        }
    }

    private void inputHairColor(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try{
            personBuilder.setHairColor(HairColor.valueOf(clientManager.askValue("HairColor " +
                    "(GREEN, BLACK, BLUE, YELLOW, WHITE)").toUpperCase()));
        } catch (IllegalArgumentException | InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("This hairColor does not exist");
            inputHairColor(clientManager, personBuilder);
        }
    }

    private void inputLocationX(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try {
            personBuilder.setLocationX(Float.parseFloat(clientManager.askValue("Location.x")));
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Location.x must be float");
            inputLocationX(clientManager, personBuilder);
        }
    }

    private void inputLocationY(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try {
            personBuilder.setLocationY(Long.parseLong(clientManager.askValue("Location.y")));
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Location.y must be long");
            inputLocationY(clientManager, personBuilder);
        }
    }

    private void inputLocationName(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        try {
            personBuilder.setLocationName(clientManager.askValue("Location.name"));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputLocationName(clientManager, personBuilder);
        }
    }

    private void generateId(PersonBuilderInterface personBuilder){
        try {
            personBuilder.setId();
        } catch (NoIdLeftException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
        }
    }

    private void inputFields(ClientManagerInterface clientManager, PersonBuilderInterface personBuilder){
        inputName(clientManager, personBuilder);
        inputCoordinatesX(clientManager, personBuilder);
        inputCoordinatesY(clientManager, personBuilder);
        inputHeight(clientManager, personBuilder);
        inputBirthday(clientManager, personBuilder);
        inputEyeColor(clientManager, personBuilder);
        inputHairColor(clientManager, personBuilder);
        inputLocationX(clientManager, personBuilder);
        inputLocationY(clientManager, personBuilder);
        inputLocationName(clientManager, personBuilder);
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