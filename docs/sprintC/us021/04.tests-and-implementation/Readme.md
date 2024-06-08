# US021 - Add a new entry to the To-Do List

## 4. Tests 

**Test 1:**

	@Test
    void ensureAddEntryWorks()
    {
        ToDoList toDoList = new ToDoList();
        GSTaskDTO gsTaskDTO = new GSTaskDTO("Pruning Trees", "Prune all trees", "Medium", 4);
        GreenSpace greenSpace = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");

        Optional<GSTask> gsTask = toDoList.addEntry(gsTaskDTO, greenSpace);
    }
	

**Test 2:**

	@Test
    void ensureGSTaskIsCreatedSuccessfully()
    {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);
    }


## 5. Construction (Implementation)

### Class AddEntryToDoController 

```java
public List<GreenSpaceDTO> getGreenSpaces(){
    String email = applicationSession.getCurrentSession().getUserEmail();
    List<GreenSpace> associatedGreenSpacesList = greenSpaceRepository.getGreenSpaces(email);
    List<GreenSpaceDTO> associatedGreenSpacesListDTO = GreenSpaceMapper.toDTO(associatedGreenSpacesList);
    return associatedGreenSpacesListDTO;
}
```

```java
public void getSelectedGreenSpace(int i)
{
    selectedGreenSpace = greenSpaceRepository.getSelectedGreenSpace(i);
}
```

```java
public boolean addEntry(GSTaskDTO taskDto)
{
    Optional<GSTask> newTask = Optional.empty();

    newTask = toDoList.addEntry(taskDto, selectedGreenSpace);

    if(newTask.isPresent())
    {
        if(!toDoListFile.save(toDoList))
        {
            return false;
        }
    }

    return true;
}
```

### Class GreenSpaceRepository

```java
public List<GreenSpace> getGreenSpaces(String email) {
    associatedGreenSpacesList = new ArrayList<>();
    for(GreenSpace greenSpace : greenSpaces)
    {
        if(greenSpace.hasUserEmail(email))
        {
            associatedGreenSpacesList.add(greenSpace);
        }
    }
    return associatedGreenSpacesList;
}
```

```java
public GreenSpace getSelectedGreenSpace(int i)
{
return associatedGreenSpacesList.get(i);
}
```

### Class GreenSpace

```java
public boolean hasUserEmail(String email){
    return this.gsmEmail.equalsIgnoreCase(email);
}
```

### Class GreenSpaceMapper

```java
public static List<GreenSpaceDTO> toDTO(List<GreenSpace> associatedGreenSpacesList)
{
    List<GreenSpaceDTO> associatedGreenSpacesListDTO = new ArrayList<>();
    for(GreenSpace greenSpace : associatedGreenSpacesList)
    {
        associatedGreenSpacesListDTO.add(toDTO(greenSpace));
    }
    return associatedGreenSpacesListDTO;
}
```

```java
public static GreenSpaceDTO toDTO(GreenSpace greenSpace) {
    return new GreenSpaceDTO(greenSpace.getName(), greenSpace.getAddress(), greenSpace.getArea(), greenSpace.getType());
}
```

### Class GreenSpaceDTO

```java
public GreenSpaceDTO(String name, String address, int area, String type) {
this.name = name;
this.address = address;
this.area = area;
this.type = type;
}
```

### Class ToDoList

```java
public Optional<GSTask> addEntry(GSTaskDTO taskDTO, GreenSpace selectedGreenSpace)
{
Optional<GSTask> optionalValue = Optional.empty();

        GSTask task =  GSTaskMapper.toModel(taskDTO, selectedGreenSpace);

        if(addTask(task))
        {
            optionalValue = Optional.of(task);
        }

        return optionalValue;
    }
```

```java
private boolean addTask(GSTask task)
{
return entriesList.add(task);
}
```

### Class GSTaskMapper

```java
public static GSTask toModel(GSTaskDTO taskDTO, GreenSpace selectedGreenSpace)
{
return new GSTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDegreeOfUrgency(), taskDTO.getExpectedDuration(), selectedGreenSpace);
}
```

### Class GSTask

```java
public GSTask(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpace selectedGreenSpace)
{
setTitle(title);
this.description = description;
setDegreeOfUrgency(degreeOfUrgency);
setExpectedDuration(expectedDuration);
this.greenSpace = selectedGreenSpace;
this.status = GSTaskStatus.Pending;
}
```

## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some green space tasks are bootstrapped while system starts.


## 7. Observations

n/a