package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.EkkoException;

/**
 * Represents an Event task that has a start date/time and an end date/time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter ALT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    public Event(String description, String fromString, String toString) throws EkkoException {
        super(description, Category.EVENT);
        this.from = parseDateTime(fromString);
        this.to = parseDateTime(toString);
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws EkkoException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(dateTimeString, ALT_FORMATTER);
            } catch (DateTimeParseException e2) {
                throw new EkkoException("Invalid event date format! Use: d/M/yyyy HHmm (e.g., 2/12/2019 1800) or MMM dd yyyy, h:mm a (e.g., Dec 02 2019, 6:00 PM)");
            }
        }
    }

    public String getFormattedFrom() {
        return from.format(OUTPUT_FORMATTER);
    }

    public String getFormattedTo() {
        return to.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return this.description + " (from: " + getFormattedFrom() + " to: " + getFormattedTo() + ")";
    }
}