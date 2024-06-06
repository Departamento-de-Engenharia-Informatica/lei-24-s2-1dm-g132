# US029 - Record the completion of a task.

## 4. Tests 

**Test 1:** Check that it is possible to complete an entry in the Agenda.

	@Test
    public void ensureEntryCanBeCompleted() {
    Agenda agenda = new Agenda();
    Task task = new Task("Task 1", "Description", "Informal", "Technical", 10, 100.0, new TaskCategory(), new Employee());
    agenda.addTask(task);
    agenda.completeTask(task);
    assertTrue(task.isCompleted());
    }
	

**Test 2:** Check that the agenda updates appropriately when a task is completed.

	@Test
    public void ensureAgendaUpdatesOnCompletion() {
    Agenda agenda = new Agenda();
    Task task = new Task("Task 1", "Description", "Informal", "Technical", 10, 100.0, new TaskCategory(), new Employee());
    agenda.addTask(task);
    agenda.completeTask(task);
    assertTrue(task.isCompleted());
    }



## 5. Construction (Implementation)

### Class ChangeStatusController 

```java
public Task completeTask(String taskReference) {
    Employee employee = getEmployeeFromSession();
    Organization organization = getOrganizationRepository().getOrganizationByEmployee(employee);
    Agenda agenda = organization.getAgenda();

    Task task = agenda.getTaskByReference(taskReference)
            .orElseThrow(() -> new NoSuchElementException("Task with reference " + taskReference + " not found"));

    agenda.completeTask(task);

    return task;
}

private Employee getEmployeeFromSession() {
    // Method to get the current logged-in employee
    // Implement according to your authentication system
}

private OrganizationRepository getOrganizationRepository() {
    // Method to get the organization repository
    // Implement according to your system structure
}
```

### Class Agenda

```java
public class Agenda {

    private List<Task> tasks = new ArrayList<>();

    public Optional<Task> getTaskByReference(String reference) {
        return tasks.stream()
                .filter(task -> task.getReference().equals(reference))
                .findFirst();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(Task task) {
        if (task.isCompleted()) {
            throw new IllegalStateException("Task is already completed");
        }
        task.complete();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
```


## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


