package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import exception.EkkoException;

class EventTest {

    @Test
    void testEventParsingValidDates() throws EkkoException {
        Event event = new Event("Team meeting", "2/12/2019 1400", "2/12/2019 1600");

        assertEquals("Dec 02 2019, 2:00 pm", event.getFormattedFrom());
        assertEquals("Dec 02 2019, 4:00 pm", event.getFormattedTo());
    }

    @Test
    void testEventParsingInvalidFromDate() {
        Exception exception = assertThrows(EkkoException.class, () -> {
            new Event("Hackathon", "invalid date", "2/12/2019 1800");
        });
        assertTrue(exception.getMessage().contains("Invalid event date format"));
    }

    @Test
    void testEventParsingInvalidToDate() {
        Exception exception = assertThrows(EkkoException.class, () -> {
            new Event("Conference", "2/12/2019 1400", "invalid date");
        });
        assertTrue(exception.getMessage().contains("Invalid event date format"));
    }
}
