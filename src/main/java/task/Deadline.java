package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.EkkoException;
public class Deadline extends Task {
    private LocalDateTime dateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter ALT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    public Deadline(String description, String dateTimeString) throws EkkoException {
        super(description, Category.DEADLINE);
        try {
            this.dateTime = LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e1) {
            try {
                this.dateTime = LocalDateTime.parse(dateTimeString, ALT_FORMATTER);
            } catch (DateTimeParseException e2) {
                throw new EkkoException("Invalid date format! Use: d/M/yyyy HHmm (e.g., 2/12/2019 1800) or MMM dd yyyy, h:mm a (e.g., Dec 02 2019, 6:00 PM)");
            }
        }
    }

    public String getFormattedDate() {
        return dateTime.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return this.description + " (by: " + getFormattedDate() + ")";
    }
}
