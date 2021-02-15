package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

// Unit tests for the ToDoList class
public class ToDoListTest {
    private ToDoList myToDoList;

    @BeforeEach
    public void setUp() {

        myToDoList = new ToDoList();
    }


    @Test
    public void testGetSize() {
        assertEquals(0, myToDoList.getSize());
        Task a = new Task("testa", LocalDate.now());
        myToDoList.addTask(a);
        assertEquals(1, myToDoList.getSize());
    }

    @Test
    public void testGetTask() {
        assertEquals(0, myToDoList.getSize());
        Task b = new Task("testb", LocalDate.now());
        myToDoList.addTask(b);
        assertEquals(b, myToDoList.getTask(0));
    }

    @Test
    public void testGetList() {
        assertEquals(0, myToDoList.getSize());

        Task aTask = new Task("a", LocalDate.now());
        Task bTask = new Task("b", LocalDate.now());

        myToDoList.addTask(aTask);
        myToDoList.addTask(bTask);
        assertEquals(2, myToDoList.getSize());

        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(aTask);
        arrayList.add(bTask);
        assertEquals(arrayList, myToDoList.getList());
    }


    @Test
    public void testAddTask() {
        Task aTask = new Task("test", LocalDate.now());
        myToDoList.addTask(aTask);
        assertEquals(1, myToDoList.getSize());
        assertEquals(aTask, myToDoList.getTask(0));
    }

    @Test
    public void testRemoveTasks() {
        assertEquals(0, myToDoList.getSize());

        Task aTask = new Task("a", LocalDate.now());
        Task bTask = new Task("b", LocalDate.now());

        myToDoList.addTask(aTask);
        myToDoList.addTask(bTask);
        assertEquals(2, myToDoList.getSize());

        myToDoList.getTask(0).setComplete(true);
        myToDoList.removeTask("a");
        assertEquals(1, myToDoList.getSize());
        assertEquals(bTask, myToDoList.getTask(0));


        Task cTask = new Task("c", LocalDate.now());
        Task dTask = new Task("d", LocalDate.now());

        myToDoList.addTask(cTask);
        myToDoList.addTask(dTask);
        assertEquals(3, myToDoList.getSize());

        myToDoList.removeTask("c");
        assertEquals(2, myToDoList.getSize());
        assertEquals(bTask, myToDoList.getTask(0));
        assertEquals(dTask, myToDoList.getTask(1));
    }

    @Test
    public void testMarkedCompleted () {
        assertEquals(0, myToDoList.getSize());

        Task aTask = new Task("a", LocalDate.now());
        Task bTask = new Task("b", LocalDate.now());

        myToDoList.addTask(aTask);
        myToDoList.addTask(bTask);
        assertEquals(2, myToDoList.getSize());

        assertFalse(aTask.isComplete());
        assertFalse(bTask.isComplete());

        myToDoList.markTaskCompleted("b");
        assertFalse(aTask.isComplete());
        assertTrue(bTask.isComplete());

    }



}
