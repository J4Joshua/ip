package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.EkkoException;

/**
 * Represents a Deadline task that has a due date and time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter ALT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline} with the given description and due date/time.
     *
     * @param description    The description of the deadline task.
     * @param dateTimeString The due date/time as a string.
     * @throws EkkoException If the date format is invalid.
     */
    public Deadline(String description, String dateTimeString) throws EkkoException {
        super(description, Category.DEADLINE);
        try {
            this.dateTime = LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e1) {
            try {
                this.dateTime = LocalDateTime.parse(dateTimeString, ALT_FORMATTER);
            } catch (DateTimeParseException e2) {
                throw new EkkoException("Invalid date format! Use: d/M/yyyy HHmm (e.g., 2/12/2019 1800) or "
                        + "MMM dd yyyy, h:mm a (e.g., Dec 02 2019, 6:00 PM)");
            }
        }
    }

    /**
     * Returns the formatted due date of the deadline task.
     *
     * @return The formatted date as a string.
     */
    public String getFormattedDate() {
        return dateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The formatted string with description and due date.
     */
    @Override
    public String toString() {
        return this.description + " (by: " + getFormattedDate() + ")";
    }
}
