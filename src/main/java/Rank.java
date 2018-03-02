// TODO check Rank has ordering
public enum Rank {
    // TODO reverse order for easier comparison
    TWO(14, "2"),
    THREE(13, "3"),
    FOUR(12, "4"),
    FIVE(11, "5"),
    SIX(10, "6"),
    SEVEN(9, "7"),
    EIGHT(8, "8"),
    NINE(7, "9"),
    TEN(6, "10"),
    JACK(5, "J"),
    QUEEN(4, "Q"),
    KING(3, "K"),
    ACE(2, "A");

    // TODO Change name
    private final int levelCode;
    private final String value;

    Rank(Integer levelCode, String value) {
        this.levelCode = levelCode;
        this.value = value;
    }

    public Integer getLevelCode() {
        return this.levelCode;
    }

    public String value() {
        return this.value;
    }
}
