package karl.task;

import karl.exception.KarlException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void add_and_size_success() {
        TaskList list = new TaskList();
        assertEquals(0, list.size());
        list.add(new Todo("Read book"));
        assertEquals(1, list.size());
    }

    @Test
    public void delete_validIndex_success() throws KarlException {
        TaskList list = new TaskList();
        Todo t = new Todo("sample");
        list.add(t);
        Task removed = list.delete(0);
        assertEquals(t, removed);
        assertEquals(0, list.size());
    }

    @Test
    public void delete_invalidIndex_throwsException() {
        TaskList list = new TaskList();
        assertThrows(KarlException.class, () -> list.delete(0));
    }

    @Test
    public void mark_and_unmark_changesTaskStatus() throws KarlException {
        TaskList list = new TaskList();
        list.add(new Todo("Task"));
        assertFalse(list.get(0).isDone());
        list.mark(0);
        assertTrue(list.get(0).isDone());
        list.unmark(0);
        assertFalse(list.get(0).isDone());
    }

    @Test
    public void mark_invalidIndex_throwsException() {
        TaskList list = new TaskList();
        assertThrows(KarlException.class, () -> list.mark(1));
    }
}
