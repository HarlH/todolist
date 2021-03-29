package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



// Code taken and modified from JsonWriterTest.java package in JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            toDoList = reader.read();
            assertEquals(0, toDoList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ToDoList toDoList = new ToDoList();
            LocalDate dueDateTest1 = LocalDate.parse("2021-01-01");
            LocalDate dueDateTest2 = LocalDate.parse("2022-02-02");

            toDoList.addTask(new Task("Test1", dueDateTest1));
            toDoList.addTask(new Task ("Test2", dueDateTest2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralToDoList.json");
            toDoList = reader.read();
            List<Task> myToDoList = toDoList.getTaskList();
            assertEquals(2, myToDoList.size());

            checkTask("Test1", dueDateTest1, myToDoList.get(0));
            checkTask("Test2", dueDateTest2, myToDoList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}