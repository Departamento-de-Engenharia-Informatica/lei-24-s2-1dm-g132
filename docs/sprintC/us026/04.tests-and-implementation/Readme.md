# US026 -  Assign  vehicles to the Agenda

## 4. Tests 

**Test 1:** Check that a vehicle can be assigned to an Agenda entry.

	@Test
    public void ensureVehicleCanBeAssignedToAgendaEntry() {
    Agenda agenda = new Agenda();
    Vehicle vehicle = new Vehicle("ABC-123", "Sedan", "Toyota", "Camry");
    agenda.assignVehicle(vehicle);

    assertTrue(agenda.getVehicles().contains(vehicle));
    }



**Test 2:** Check that multiple vehicles can be assigned to an

    @Test
    public void ensureMultipleVehiclesCanBeAssignedToAgendaEntry() {
    Agenda agenda = new Agenda();
    Vehicle vehicle1 = new Vehicle("ABC-123", "Sedan", "Toyota", "Camry");
    Vehicle vehicle2 = new Vehicle("XYZ-789", "SUV", "Ford", "Explorer");

    agenda.assignVehicle(vehicle1);
    agenda.assignVehicle(vehicle2);
    
    assertTrue(agenda.getVehicles().contains(vehicle1));
    assertTrue(agenda.getVehicles().contains(vehicle2));
    assertEquals(2, agenda.getVehicles().size());
    }

**Test 3:** Check that assigning a null vehicle throws an exception.
   
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullVehicleCannotBeAssignedToAgendaEntry() {
    Agenda agenda = new Agenda();
    agenda.assignVehicle(null);
    }
**Test 4:** Check that the same vehicle cannot be assigned multiple times.

    @Test(expected = IllegalArgumentException.class)    
    public void ensureSameVehicleCannotBeAssignedMultipleTimes() {
    Agenda agenda = new Agenda();
    Vehicle vehicle = new Vehicle("ABC-123", "Sedan", "Toyota", "Camry");

    agenda.assignVehicle(vehicle);
    agenda.assignVehicle(vehicle); // This should throw an exception
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

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a