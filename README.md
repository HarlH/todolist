# A to-do list application
An application that helps keeping track of things to do, as well as deadlines of assignments and projects.

This to-do list application was designed with the purpose of helping everyone, especially students to keep track of their
progress in working and studying.

 Personally, I often forgot and missed the deadlines of various assignments and projects, so I decided to make 
 a to-do list to help to remind me of things to do.

## Basic features:

Support the ability to
- *add* a task to the to-do list
- *view* the todo list
- *mark* a task as **completed**
- *mark* a task as **important**
- *remove* (delete) tasks




A task is modeled with a title, a due date, a completion status, and a priority status.


## User Stories
- As a user, I want to be able to add multiple tasks to my to-do list
- As a user, I want to be able to view the list of tasks on my to-do list
- As a user, I want to be able to mark a task as complete on my to-do list
- As a user, I want to be able to mark a task as important on my to-do list
- As a user, I want to be able to delete a task from my to-do-list
- As a user, I want to be able to save my to-do list to file
- As a user, I want to be able to load my to-do list from file 


## Phase 4: Task 2

Type hierarchy: 

 TextField: The interface TextField has AddDateField, AddTitleField and
 MarkCompletedField as its subclasses.
 
They all override the addListener method to perform different actions 
 and check different requirements for each field (for example, AddDateField
 will make sure that the date inserted into the field must be in the form YYYY-MM-DD, otherwise 
 the add button won't be enabled) 

Buttons:
Similarly, I also have AddButton, MarkCompletedButton, LoadButton
and SaveButton implements the Buttons interface. Each of the button perform different
actions when clicked by overriding the addListener method. 



