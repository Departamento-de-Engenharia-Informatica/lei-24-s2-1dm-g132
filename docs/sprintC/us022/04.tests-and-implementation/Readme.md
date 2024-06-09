# US022 - Add a new entry in the Agenda

## 4. Tests 

**Test 1:**

	@Test
    void ensureGetToDoListEntriesWorks()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO1 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask1 = toDoList.addEntry(gsTaskDTO1, greenSpace1);

        GSTaskDTO gsTaskDTO2 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");

        Optional<GSTask> gsTask2 = toDoList.addEntry(gsTaskDTO2, greenSpace2);

        List<GSTask> expectedList = new ArrayList<>();
        gsTask2.ifPresent(expectedList::add);

        List<GSTask> result = toDoList.getToDoListEntries("gsm2@this.app");

        assertEquals(expectedList, result);
    }
	

**Test 2:**

	@Test
    void ensureGetSelectedTaskWorks1()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO1 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask1 = toDoList.addEntry(gsTaskDTO1, greenSpace1);

        GSTaskDTO gsTaskDTO2 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");

        Optional<GSTask> gsTask2 = toDoList.addEntry(gsTaskDTO2, greenSpace2);

        List<GSTask> result = toDoList.getToDoListEntries("gsm2@this.app");

        toDoList.getSelectedTask(0);
    }

**Test 3:**

	@Test
    void ensureGetSelectedTaskWorks2()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO1 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask1 = toDoList.addEntry(gsTaskDTO1, greenSpace1);

        GSTaskDTO gsTaskDTO2 = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");

        Optional<GSTask> gsTask2 = toDoList.addEntry(gsTaskDTO2, greenSpace2);

        List<GSTask> result = toDoList.getToDoListEntries("gsm2@this.app");

        assertThrows(IndexOutOfBoundsException.class,
                () -> toDoList.getSelectedTask(1));
    }

**Test 4:**

	@Test
    void ensureIsPendingWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertTrue(gsTask.isPending());
    }

**Test 5:**

	@Test
    void ensureHasUserEmailWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertTrue(gsTask.hasUserEmail("gsm1@this.app"));
    }

**Test 6:**

	@Test
    void ensurePlanWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/6/25");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.MONTH, 6 - 1);
        cal.set(Calendar.DAY_OF_MONTH, 25);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        assertEquals("Planned", gsTask.getStatus());
        assertTrue(cal.equals(gsTask.getStartingDate()));
    }

**Test 7:**

	@Test
    void setInvalidStartingDate1()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> gsTask.plan("2024/7"));
    }

**Test 8:**

	@Test
    void setInvalidStartingDate2()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> gsTask.plan("2024/7/12a"));
    }

**Test 9:**

	@Test
    void setInvalidStartingDate3()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> gsTask.plan("2023/7/12"));
    }

**Test 10:**

	@Test
    void ensureAddEntryWorks() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace);

        agenda.addEntry(gsTask, "2024/8/9");
    }

**Test 11:**

	@Test
    void ensureSetProcessedWorks()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.setProcessed();

        assertEquals("Processed", gsTask.getStatus());
    }

**Test 12:**

	@Test
    void ensureCloneWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        GSTask clone = gsTask.clone();
    }

## 5. Construction (Implementation)

### Class AddEntryAgendaController

```java
public List<GSTaskDTO> getTodoListEntries()
{
    String email = applicationSession.getCurrentSession().getUserEmail();
    List<GSTask> associatedToDoEntriesList = toDoList.getToDoListEntries(email);
    List<GSTaskDTO> associatedToDoEntriesListDTO = GSTaskMapper.toDTOToDo(associatedToDoEntriesList);
    return associatedToDoEntriesListDTO;
}
```

```java
public void getSelectedTask(int i)
{
selectedTaskClone = toDoList.getSelectedTask(i);
}
```

```java
public boolean addEntry(String startingDate) {
    Optional<GSTask> updatedTask = Optional.empty();

    updatedTask = agenda.addEntry(selectedTaskClone, startingDate);

    if (updatedTask.isPresent()) {
        if (!toDoListFile.save(toDoList)) {
            return false;
        }
        if (!agendaFile.save(agenda)) {
            return false;
        }
    }

    return true;
}
```

### Class ToDoList

```java
public List<GSTask> getToDoListEntries(String email)
{
    associatedToDoEntriesList = new ArrayList<>();
    for(GSTask gSTask : entriesList)
    {
        if(gSTask.isPending() && gSTask.hasUserEmail(email))
        {
            associatedToDoEntriesList.add(gSTask);
        }
    }
    return associatedToDoEntriesList;
}
```

```java
public GSTask getSelectedTask(int i)
{
GSTask selectedTask = associatedToDoEntriesList.get(i);
selectedTask.setProcessed();
return selectedTask.clone();
}
```

### Class GSTask

```java
public boolean isPending()
{
return this.status.equals(GSTaskStatus.Pending);
}
```

```java
public boolean hasUserEmail(String email)
{
return this.greenSpace.hasUserEmail(email);
}
```

```java
public void plan(String startingDate)
{
this.status = GSTaskStatus.Planned;
setStartingDate(startingDate);
}
```

```java
private void setStartingDate(String startingDate) {
    String[] parts = startingDate.trim().split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid starting date format. Use year/month/day.");
        }

        for (String part : parts) {
            if (!part.matches("\\d+")) {
                throw new IllegalArgumentException("Non-numeric value found in starting date.");
            }
        }

        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Calendar currentDate = Calendar.getInstance();

        if(cal.before(currentDate))
        {
            throw new IllegalArgumentException("Task starting date must be after the current date.");
        }

        this.startingDate = cal;
}
```    

```java
public void setProcessed()
{
this.status = GSTaskStatus.Processed;
}
``` 

### Class GSTaskMapper

```java
public static List<GSTaskDTO> toDTOToDo(List<GSTask> associatedToDoEntriesList)
{
List<GSTaskDTO> associatedToDoEntriesListDTO = new ArrayList<>();
for(GSTask gSTask : associatedToDoEntriesList)
{
associatedToDoEntriesListDTO.add(toDTOToDo(gSTask));
}
return associatedToDoEntriesListDTO;
}
```

```java
public static GSTaskDTO toDTOToDo(GSTask gSTask) {
return new GSTaskDTO(gSTask.getTitle(), gSTask.getDescription(), gSTask.getDegreeOfUrgency(), gSTask.getExpectedDuration(), new GreenSpaceDTO(gSTask.getGreenSpace().getName(), gSTask.getGreenSpace().getAddress(), gSTask.getGreenSpace().getArea(), gSTask.getGreenSpace().getType()));
}
```

### Class GSTaskDTO

```java
public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpaceDTO greenSpace) {
this.title = title;
this.description = description;
this.degreeOfUrgency = degreeOfUrgency;
this.expectedDuration = expectedDuration;
this.greenSpace = greenSpace;
}
```

### Class Agenda

```java
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

```java
private boolean addTask(GSTask task, String startingDate)
{
task.plan(startingDate);
return entriesList.add(task);
}
```

## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some green space tasks are bootstrapped while system starts.


## 7. Observations

n/a