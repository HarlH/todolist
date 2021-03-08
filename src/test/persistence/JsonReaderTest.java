package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



// Code taken and modified from JsonReaderTest.java package in JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList toDoList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals(0, toDoList.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");

        LocalDate dueDateTest1 = LocalDate.parse("3021-01-01");
        LocalDate dueDateTest2 = LocalDate.parse("3022-02-02");
        try {
            ToDoList toDoList = reader.read();
            List<Task> thingies = toDoList.getTaskList();
            assertEquals(2, thingies.size());
            checkTask("ThisIsTest1", dueDateTest1, thingies.get(0));
            checkTask("ThisIsTest2", dueDateTest2, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
