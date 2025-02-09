package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exception.EkkoException;

class DeadlineTest {

    @Test
    void testDeadlineParsingValidDate() throws EkkoException {
        Deadline deadline = new Deadline("Submit assignment", "2/12/2019 1800");
        assertEquals("Dec 02 2019, 6:00 pm", deadline.getFormattedDate());
    }

    @Test
    void testDeadlineParsingInvalidDate() {
        Exception exception = assertThrows(EkkoException.class, () -> {
            new Deadline("Submit report", "invalid date format");
        });
        assertTrue(exception.getMessage().contains("Invalid date format"));
    }
}
