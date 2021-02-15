package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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




}
