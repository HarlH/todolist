package persistence;

import model.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(String name, LocalDate dueDate, Task task) {
        assertEquals(name, task.getTitle());
        assertEquals(dueDate, task.getDueDate());
    }
}