package karl.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testToStringFormat() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
