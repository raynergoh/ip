package karl.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    @Test
    public void testConstructorAndGetters() {
        Deadline deadline = new Deadline("return book", "2025-12-25");
        assertEquals("return book", deadline.getDescription());
        assertEquals(LocalDate.parse("2025-12-25"), deadline.getBy());
    }

    @Test
    public void testToStringFormat() {
        Deadline deadline = new Deadline("return book", "2025-12-25");
        String expected = "[D][ ] return book (by: " + LocalDate.parse("2025-12-25").format(outputFormat) + ")";
        assertEquals(expected, deadline.toString());

        deadline.markAsDone();
        expected = "[D][X] return book (by: " + LocalDate.parse("2025-12-25").format(outputFormat) + ")";
        assertEquals(expected, deadline.toString());
    }
}
