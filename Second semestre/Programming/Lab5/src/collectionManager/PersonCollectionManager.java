package collectionManager;

import exceptions.*;
import fileManager.DataManager;
import messages.Messenger;
import person.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * Класс, который управляет коллекцией
 */
public class PersonCollectionManager implements CollectionManager {
    private final LocalDate initDate;
    private TreeSet<Person> personTreeSet;
    private DataManager dataManager;

    public PersonCollectionManager(DataManager dataManager) {
        initDate = LocalDate.now();
        personTreeSet = new TreeSet<>();
        this.dataManager = dataManager;
    }

    /**
     * @return тип коллекции
     */
    @Override
    public Class getType() {
        return personTreeSet.getClass();
    }

    /**
     * @return количество элементов коллекции
     */
    @Override
    public int getSize() {
        return personTreeSet.size();
    }

    /**
     * @return дата инициализации коллекции
     */
    @Override
    public LocalDate getInitDate() {
        return initDate;
    }

    @Override
    public void addElement(Person person) {
        personTreeSet.add(person);
    }

    /**
     * Удаление элемента из коллекции по его id
     * @param id значение поля id элемента
     * @return true, если элемент был найден и удален; false, если не был удален
     */
    @Override
    public boolean removeElement(int id){
        for (Person person: personTreeSet) {
            if (person.getId() == id){
                PersonIdManager.getInstance().removeId(id);
                personTreeSet.remove(person);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод, который очищает коллекцию
     */
    @Override
    public void clear(){
        PersonIdManager.getInstance().clearIdentifiers();
        personTreeSet.clear();
    }

    /**
     * Метод, который загружает объекты Person в коллекцию, используя DataManager
     * @throws NoEnvVarException если отсутсвует переменная окружения, указывающая имя файла
     * @throws InvalidFieldException если какое-то из полей имеет неверный формат или неверный тип данных
     * @throws NoDataException если отсутсвует хранилище данных
     * @throws BrokenDataException если хранилище данных сломано
     */
    @Override
    public void loadPersons() throws NoEnvVarException, InvalidFieldException, NoDataException, BrokenDataException{
        personTreeSet.addAll(dataManager.readElements());
    }

    @Override
    public void savePersons() {
        dataManager.writeElements(personTreeSet);
    }

    @Override
    public Stream<Person> getPersonStream() {
        return personTreeSet.stream();
    }

    /*
    *//**
     * Метод, который выводит в стандартный поток вывода информацию о каждом человеке в коллекции, отсортированной
     * с помощью компаратора
     * @param comparator
     *//*
    public void printSortedPersonsEyeColorField(Comparator<Person> comparator){
        personTreeSet.stream().sorted(comparator).forEach(person -> System.out.println(person.getEyeColor()));
    }

    *//**
     * Метод, который выводит в стандартный поток вывода информацию о каждом человеке в коллекции, имя которого содержит
     * заданную подстроку
     * @param name
     *//*
    public void printPersonsContainsName(String name){
        for (Person person: personTreeSet) {
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

    *//**
     * Метод, который добавляет человека в коллекцию
     * @param clientManager
     *//*
    public void addPerson(InputManager clientManager){
        PersonBuilder personBuilder = new PersonBuilderImpl();
        try {
            inputFields(clientManager, personBuilder);
            personTreeSet.add(personBuilder.getPerson());
        } catch (InvalidArgumentException e){
            System.out.println("Wrong field. Command add stopped");
            return;
        }
    }

    *//**
     * Метод, который обновляет значение человека, id которого равен заданному
     * @param id, clientManager
     * @return
     *//*
    public boolean updatePerson(int id, InputManager clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        for (Person oldPerson: personTreeSet) {
            if (oldPerson.getId() == id){
                try {
                    personBuilder.removeId(id);
                    personBuilder.setId(id);
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                }
                personBuilder.setCreationDate(oldPerson.getCreationDate().toLocalDate());
                try {
                    inputFields(clientManager, personBuilder);
                    personTreeSet.add(personBuilder.getPerson());
                } catch (InvalidArgumentException e) {
                    System.out.println("Wrong field. Command update stopped");
                    return false;
                }
                personTreeSet.remove(oldPerson);
                return true;
            }
        }
        return false;
    }

    *//**
     * Метод, который добавляет человека в коллекцию, если его значение превышает значение наибольшего человека
     * этой коллекции
     * @param clientManager
     *//*
    public void addPersonIfMax(InputManager clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        boolean max = true;
        generateId(personBuilder);
        Person newPerson = null;
        try{
            inputFields(clientManager, personBuilder);
            newPerson = personBuilder.getPerson();
        }catch (InvalidArgumentException e){
            System.out.println("Wrong field. Command add_person_if_max stopped");
            return;
        }
        for (Person person: personTreeSet) {
            if (person.compareTo(newPerson) > 0) {
                max = false;
            }
        }
        if (max){
            personTreeSet.add(newPerson);
        }
    }

    *//**
     * Метод, который добавляет человека в коллекцию, если если его значение меньше, чем у наименьшего
     * человека этой коллекции
     * @param clientManager
     *//*
    public void addPersonIfMin(InputManager clientManager){
        PersonBuilderInterface personBuilder = new PersonBuilder();
        boolean min = true;
        generateId(personBuilder);
        Person newPerson = null;
        try{
            inputFields(clientManager, personBuilder);
            newPerson = personBuilder.getPerson();
        }catch (InvalidArgumentException e){
            System.out.println("Wrong field. Command add_person_if_min stopped");
            return;
        }
        for (Person person: personTreeSet) {
            if (person.compareTo(newPerson) < 0) {
                min = false;
            }
        }
        if (min){
            personTreeSet.add(newPerson);
        }
    }

    *//**
     * Метод, который возвращет самого большого человека в коллекцию, сравнивая их полученным компаратором
     * @param comparator
     * @return
     *//*
    public Person getMax(Comparator<Person> comparator) {
        Iterator<Person> iterator = personTreeSet.iterator();
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

    private void inputName(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try{
            personBuilder.setName(clientManager.askValue("Name"));
        } catch (InvalidArgumentException e) {
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            if (!clientManager.isScript()) {
                inputName(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputCoordinatesX(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try {
            personBuilder.setCoordinatesX(Double.parseDouble(clientManager.askValue("Coordinates.x")));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputCoordinatesX(clientManager, personBuilder);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Coordinates.x must be double");
            if (!clientManager.isScript()) {
                inputCoordinatesX(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputCoordinatesY(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try {
            personBuilder.setCoordinatesY(Long.parseLong(clientManager.askValue("Coordinates.y")));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputCoordinatesY(clientManager, personBuilder);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Coordinates.y must be long");
            if (!clientManager.isScript()) {
                inputCoordinatesY(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputHeight(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try{
            personBuilder.setHeight(Long.parseLong(clientManager.askValue("Height")));
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputHeight(clientManager, personBuilder);
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Height must be long");
            if (!clientManager.isScript()) {
                inputHeight(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputBirthday(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try{
            personBuilder.setBirthday(LocalDate.parse(clientManager.askValue("Birthday")).atStartOfDay());
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            inputBirthday(clientManager, personBuilder);
        } catch (DateTimeParseException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Date must be in format yyyy-MM-dd");
            if (!clientManager.isScript()) {
                inputBirthday(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputEyeColor(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try{
            personBuilder.setEyeColor(EyeColor.valueOf(clientManager.askValue("EyeColor " +
                    "(BLACK, ORANGE, WHITE)").toUpperCase()));
        } catch (IllegalArgumentException | InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("This eyeColor does not exist");
            if (!clientManager.isScript()) {
                inputEyeColor(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputHairColor(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try{
            personBuilder.setHairColor(HairColor.valueOf(clientManager.askValue("HairColor " +
                    "(GREEN, BLACK, BLUE, YELLOW, WHITE)").toUpperCase()));
        } catch (IllegalArgumentException | InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("This hairColor does not exist");
            if (!clientManager.isScript()) {
                inputHairColor(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputLocationX(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try {
            personBuilder.setLocationX(Float.parseFloat(clientManager.askValue("Location.x")));
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Location.x must be float");
            if (!clientManager.isScript()) {
                inputLocationX(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputLocationY(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try {
            personBuilder.setLocationY(Long.parseLong(clientManager.askValue("Location.y")));
        } catch (NumberFormatException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println("Location.y must be long");
            if (!clientManager.isScript()) {
                inputLocationY(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
        }
    }

    private void inputLocationName(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
        try {
            String locationName = clientManager.askValue("Location.name");
            personBuilder.setLocationName(locationName == "" ? null : locationName);
        } catch (InvalidArgumentException e){
            logger.log(Level.WARNING, e.toString(), e);
            System.out.println(e.getMessage());
            if (!clientManager.isScript()) {
                inputLocationName(clientManager, personBuilder);
            } else {
                throw new InvalidArgumentException(e.getMessage());
            }
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

    private void inputFields(InputManager clientManager, PersonBuilderInterface personBuilder) throws InvalidArgumentException {
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

    *//**
     * Метод, который возвращает компаратор, сравнивающий людей по цвету глаз
     * @return
     *//*
    public Comparator<Person> getComparatorByEyeColor(){
        return (Person a, Person b) -> a.getEyeColor().getHex() - b.getEyeColor().getHex();
    }

    *//**
     * Метод, который возвращает компаратор, сравнивающий людей по цвету глаз в обратном порядке
     * @return
     *//*
    public Comparator<Person> getDescendingComparatorByEyeColor(){
        return (Person a, Person b) -> b.getEyeColor().getHex() - a.getEyeColor().getHex();
    }
    */
}