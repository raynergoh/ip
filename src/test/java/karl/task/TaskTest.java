package karl.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void testMarkingDoneAndNotDone() {
        Task task = new Todo("test task");
        assertFalse(task.isDone());

        task.markAsDone();
        assertTrue(task.isDone());

        task.markAsNotDone();
        assertFalse(task.isDone());
    }

    @Test
    public void testGetDescription() {
        Task task = new Todo("description");
        assertEquals("description", task.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Todo("desc");
        assertEquals(" ", task.getStatusIcon()); // not done

        task.markAsDone();
        assertEquals("X", task.getStatusIcon()); // done
    }

    @Test
    public void testToString() {
        Task task = new Todo("desc");
        assertEquals("[T][ ] desc", task.toString());

        task.markAsDone();
        assertEquals("[T][X] desc", task.toString());
    }
}
