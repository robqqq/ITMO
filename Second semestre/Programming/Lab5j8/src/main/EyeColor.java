package main;

/**
 * Перечисление цветов глаз человека
 */
public enum EyeColor {
    BLACK(0x000000),
    ORANGE(0xFFA500),
    WHITE(0xFFFFFF);

    private int hex;

    /**
     * Конструктор
     * @param hex
     */
    EyeColor(int hex){
        this.hex = hex;
    }

    /**
     * Метод, который возвращает значение цвета в формате HEX
     * @return
     */
    public int getHex() {
        return hex;
    }
}
