package person;

import exceptions.InvalidFieldException;

import java.time.LocalDateTime;

/**
 * Интерфейс создателя объекта Person
 */
public interface PersonBuilder {

    /**
     * Сгенерировать id
     */
    void setId();

    /**
     * Задать идентификатор
     * @param id идентификатор
     * @throws InvalidFieldException если значение некорректно
     */
    void setId(int id) throws InvalidFieldException;

    /**
     * Задать имя
     * @param name имя
     * @throws InvalidFieldException если значение некорректно
     */
    void setName(String name) throws InvalidFieldException;

    /**
     * Задать координату x
     * @param x координата x
     * @throws InvalidFieldException если значение некорректно
     */
    void setCoordinatesX(Double x) throws InvalidFieldException;

    /**
     * Задать координату y
     * @param y координата y
     * @throws InvalidFieldException если значение некорректно
     */
    void setCoordinatesY(long y) throws InvalidFieldException;

    /**
     * Задать дату создания
     * @param creationDate дата создания
     * @throws InvalidFieldException если значение некорректно
     */
    void setCreationDate(LocalDateTime creationDate) throws InvalidFieldException;

    /**
     * задать рост
     * @param height рост
     * @throws InvalidFieldException если значение некорректно
     */
    void setHeight(long height) throws InvalidFieldException;

    /**
     * задать дату рождения
     * @param birthday дата рождения
     * @throws InvalidFieldException если значение некорректно
     */
    void setBirthday(LocalDateTime birthday) throws InvalidFieldException;

    /**
     * задать цвет глаз
     * @param eyeColor цвет глаз
     * @throws InvalidFieldException если значение некорректно
     */
    void setEyeColor(EyeColor eyeColor) throws InvalidFieldException;

    /**
     * задать цвет волос
     * @param hairColor цвет волос
     * @throws InvalidFieldException если значение некорректно
     */
    void setHairColor(HairColor hairColor) throws InvalidFieldException;

    /**
     * задать координату x локации
     * @param x координата x локации
     */
    void setLocationX(float x);

    /**
     * задать координату y локации
     * @param y координата y локации
     */
    void setLocationY(long y);

    /**
     * задать название локации
     * @param name название локации
     * @throws InvalidFieldException если значение некорректно
     */
    void setLocationName(String name) throws InvalidFieldException;

    /**
     * @return объект Person
     */
    Person getPerson();
}
