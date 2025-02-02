package task;

public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description, Category.DEADLINE);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return this.description + " (by: " + date + ")";
    }
}
