package person;

/**
 * Перечисление цветов глаз
 */
public enum EyeColor{
    BLACK(0x000000),
    ORANGE(0xFFA500),
    WHITE(0xFFFFFF);

    private final int hex;

    /**
     * @param hex значение цвета в формате HEX
     */
    EyeColor(int hex){
        this.hex = hex;
    }

    /**
     * @return значение цвета в формате HEX
     */
    public int getHex() {
        return hex;
    }
}
