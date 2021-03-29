package model;


import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


//Unit test for the Task class
public class TaskTest {
    private Task myTask;

    @BeforeEach
    public void setUp() {
        // using constructor
        myTask = new Task("test", LocalDate.now());
    }


    @Test
    public void testGetTitle() {
        assertEquals("test", myTask.getTitle());
    }

    @Test
    public void testGetDueDate() {
        assertEquals(LocalDate.now(), myTask.getDueDate());
    }

    @Test
    public void testIsComplete() {
        assertFalse(myTask.isComplete());
    }

    @Test
    public void testIsImportant() {
        assertFalse(myTask.isImportant());
    }

    @Test
    public void testSetTitle() {
        assertEquals("test", myTask.getTitle());
        myTask.setTitle("falsetest");
        assertEquals("falsetest", myTask.getTitle());
    }

    @Test
    public void testSetDueDate() {
        assertEquals(LocalDate.now(), myTask.getDueDate());
        LocalDate dueDate = LocalDate.parse("2021-02-14");
        myTask.setDueDate(dueDate);
        assertEquals(dueDate, myTask.getDueDate());
    }

    @Test
    public void testSetPriority() {
        assertFalse(myTask.isImportant());
        myTask.setImportant(true);
        assertTrue(myTask.isImportant());
        myTask.setImportant(false);
        assertFalse(myTask.isImportant());
    }

    @Test
    public void testSetComplete() {
        assertFalse(myTask.isComplete());
        myTask.setComplete(true);
        assertTrue(myTask.isComplete());
        myTask.setComplete(false);
        assertFalse(myTask.isComplete());
    }

    @Test
    public void testGetStatus() {
        assertFalse(myTask.isImportant());
        assertEquals("Not Important", myTask.getStatus());
        myTask.setImportant(true);
        assertTrue(myTask.isImportant());
        assertEquals("Important", myTask.getStatus());
    }

    /*@Test
    public void testToJson() {
        try {

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(myTask.toJson());
            JSONObject json = new JSONObject();
            json.put("todolist", jsonArray);

            JsonWriter writer = new JsonWriter("./data/testToJsonTask.json");
            writer.open();
            writer.saveToFile(json.toString(4));


            JsonReader reader = new JsonReader("./data/testToJsonTask.json");
            ToDoList taskList = reader.read();
            assertEquals(1, taskList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
*/


}
