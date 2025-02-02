package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.EkkoException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String description, String fromString, String toString) throws EkkoException {
        super(description, Category.EVENT);
        try {
            this.from = LocalDateTime.parse(fromString, INPUT_FORMATTER);
            this.to = LocalDateTime.parse(toString, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new EkkoException("Invalid event date format! Use: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
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