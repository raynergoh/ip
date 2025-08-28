package karl.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    @Test
    public void testConstructorAndGetters() {
        Event event = new Event("project meeting", "2025-11-10", "2025-11-12");
        assertEquals("project meeting", event.getDescription());
        assertEquals(LocalDate.parse("2025-11-10"), event.getFrom());
        assertEquals(LocalDate.parse("2025-11-12"), event.getTo());
    }

    @Test
    public void testToStringFormat() {
        Event event = new Event("project meeting", "2025-11-10", "2025-11-12");
        String expected = "[E][ ] project meeting (from: " + LocalDate.parse("2025-11-10").format(outputFormat) +
                " to: " + LocalDate.parse("2025-11-12").format(outputFormat) + ")";
        assertEquals(expected, event.toString());

        event.markAsDone();
        expected = "[E][X] project meeting (from: " + LocalDate.parse("2025-11-10").format(outputFormat) +
                " to: " + LocalDate.parse("2025-11-12").format(outputFormat) + ")";
        assertEquals(expected, event.toString());
    }
}
