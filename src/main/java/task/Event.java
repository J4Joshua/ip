package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.EkkoException;

/**
 * Represents an Event task that has a start date/time and an end date/time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter ALT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an {@code Event} with the given description, start time, and end time.
     *
     * @param description The event description.
     * @param fromString  The start date/time as a string.
     * @param toString    The end date/time as a string.
     * @throws EkkoException If the date format is invalid.
     */
    public Event(String description, String fromString, String toString) throws EkkoException {
        super(description, Category.EVENT);
        this.from = parseDateTime(fromString);
        this.to = parseDateTime(toString);
    }

    /**
     * Parses a date-time string into a {@link LocalDateTime} object.
     *
     * @param dateTimeString The date-time string to parse.
     * @return The parsed {@link LocalDateTime} object.
     * @throws EkkoException If the date format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws EkkoException {
        assert dateTimeString != null : "DateTime string should not be null";

        // Try parsing with the primary format
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMATTER);
        } catch (DateTimeParseException ignored) {
            // If it fails, try the alternative format
        }

        try {
            return LocalDateTime.parse(dateTimeString, ALT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new EkkoException("Invalid event date format! Use: d/M/yyyy HHmm (e.g., 2/12/2019 1800) or "
                    + "MMM dd yyyy, h:mm a (e.g., Dec 02 2019, 6:00 PM)");
        }
    }

    /**
     * Returns the formatted start date/time of the event.
     *
     * @return A string representing the start date/time.
     */
    public String getFormattedFrom() {
        return from.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns the formatted end date/time of the event.
     *
     * @return A string representing the end date/time.
     */
    public String getFormattedTo() {
        return to.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the event, including the description and date range.
     *
     * @return The formatted event details.
     */
    @Override
    public String toString() {
        return this.description + " (from: " + getFormattedFrom() + " to: " + getFormattedTo() + ")";
    }
}
