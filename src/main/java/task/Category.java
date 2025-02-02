package task;

public enum Category {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String symbol;

    Category(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
