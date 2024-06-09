# US025 - cancel an entry in the agenda

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values.

	@Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
    Task instance = new Task(null, null, null, null, null, null, null);
    }



**Test 2:** Check that it is possible to cancel an entry in the Agenda.

	@Test
    public void ensureEntryCanBeCancelled() {
    Agenda agenda = new Agenda();
    Task task = new Task("Task 1", "Description", "2024-06-06", "High", "Open", "John Doe", "Work");
    agenda.addTask(task);
    agenda.cancelTask(task);
    assertTrue(task.isCancelled());
    }

**Test 3:** Check that the cancellation of an entry updates the agenda appropriately.

    @Test
    public void ensureAgendaUpdatesOnCancellation() {
    Agenda agenda = new Agenda();
    Task task = new Task("Task 1", "Description", "2024-06-06", "High", "Open", "John Doe", "Work");
    agenda.addTask(task);
    agenda.cancelTask(task);
    assertFalse(agenda.getTasks().contains(task)); // Ensure the task is no longer in the agenda
    }


_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class CancelEntryController 

```java
    public Task cancelEntry(String taskReference) {
        Employee employee = getEmployeeFromSession();
        Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);
        Agenda agenda = organization.getAgenda();

        Task task = agenda.getTaskByReference(taskReference)
                .orElseThrow(() -> new NoSuchElementException("Task with reference " + taskReference + " not found"));

        agenda.cancelTask(task);

        return task;
    }
```

### Class Agenda

```java
 public Agenda() {
    entriesList = new ArrayList<>();
}

public Optional<GSTask> addEntry(GSTask selectedTaskClone, String startingDate)
{
    Optional<GSTask> optionalValue = Optional.empty();

    if(addTask(selectedTaskClone, startingDate))
    {
        optionalValue = Optional.of(selectedTaskClone);
    }

    return optionalValue;
}

```


## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


