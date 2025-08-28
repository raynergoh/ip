package karl.storage;

import karl.task.*;
import karl.exception.KarlException;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final String TEST_FILE = "data/test-karl.txt";

    @AfterEach
    public void cleanup() {
        // Clean up test file after each test
        File f = new File(TEST_FILE);
        if (f.exists()) f.delete();
    }

    @Test
    public void saveAndLoad_tasks_integrity() throws KarlException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("t1"));
        tasks.add(new Deadline("d1", "2023-01-01"));
        tasks.add(new Event("e1", "2023-01-02", "2023-01-03"));
        Storage storage = new Storage(TEST_FILE);
        storage.saveTasks(tasks);

        Storage storage2 = new Storage(TEST_FILE);
        ArrayList<Task> loaded = storage2.loadTasks();
        assertEquals(3, loaded.size());
        assertTrue(loaded.get(0) instanceof Todo);
        assertTrue(loaded.get(1) instanceof Deadline);
        assertTrue(loaded.get(2) instanceof Event);
        assertEquals("t1", loaded.get(0).getDescription());
        assertEquals("d1", loaded.get(1).getDescription());
        assertEquals("e1", loaded.get(2).getDescription());
        assertEquals("2023-01-01", ((Deadline)loaded.get(1)).getBy().toString());
        assertEquals("2023-01-02", ((Event)loaded.get(2)).getFrom().toString());
    }

    @Test
    public void loadTasks_malformedLines_skippedGracefully() throws Exception {
        File f = new File(TEST_FILE);
        f.getParentFile().mkdirs();
        java.nio.file.Files.write(f.toPath(),
                ("bad line\nT | 0 | test\n").getBytes());
        Storage storage = new Storage(TEST_FILE);
        ArrayList<Task> loaded = storage.loadTasks();
        // Only one of the 2 lines is a valid task
        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof Todo);
    }
}
