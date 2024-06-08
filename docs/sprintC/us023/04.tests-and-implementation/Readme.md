# US023 - Assign a Team to an entry in the Agenda

## 4. Tests 

**Test 1:** 

	@Test
    void ensureGetStartingDateWorks() {
        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);
        gsTask.plan("2024/7/12");

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.MONTH, 7 - 1);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        assertTrue(cal.equals(gsTask.getStartingDate()));
    }
	

**Test 2:** 

	@Test
    void ensureHasTeamWorks(){
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        gsTask.assignTeam(team);

        assertTrue(gsTask.hasTeam());
    }

**Test 3:**

	@Test
    void ensureBelongsToTeamWorks1()
    {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        gsTask.assignTeam(team);

        assertTrue(gsTask.belongsToTeam(team));
    }

**Test 4:**

	@Test
    void ensureBelongsToTeamWorks2()
    {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");
        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        assertFalse(gsTask.belongsToTeam(team));
    }

**Test 5:**

	@Test
    void ensureSendEmailWorks()
    {
        Job job = new Job("Jardineiro");
        Collaborator collaborator = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator);

        Team team = new Team(collaborators);

        GreenSpace greenSpace = new GreenSpace("Parque da águia", "Rua de Trás, 3666-389, Matosinhos", 5, "MediumSizedPark", "gsm1@this.app");

        GSTask gsTask = new GSTask("Pruning Trees", "Prune the trees in the frontyard.", "Low", 2, greenSpace);

        gsTask.plan("2024/10/15");

        gsTask.assignTeam(team);
    }


**Test 6:**

	@Test
    void ensureGetAgendaEntriesWorks() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        List<GSTask> expectedList = new ArrayList<>();
        gsTaskOptional2.ifPresent(expectedList::add);

        List<GSTask> result = agenda.getAgendaEntries("gsm2@this.app");

        assertEquals(expectedList, result);
    }


**Test 7:**

	@Test
    void ensureGetSelectedTaskWorks1() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        List<GSTask> result = agenda.getAgendaEntries("gsm2@this.app");

        agenda.getSelectedTask(0);
    }


**Test 8:**

	@Test
    void ensureGetSelectedTaskWorks2() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        List<GSTask> result = agenda.getAgendaEntries("gsm2@this.app");

        assertThrows(IndexOutOfBoundsException.class,
                () -> agenda.getSelectedTask(1));
    }


**Test 9:**

	@Test
    void ensureAssignTeamWorks() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        if (gsTaskOptional2.isPresent()) {
            GSTask gsTask2Retrieved = gsTaskOptional2.get();
            agenda.assignTeam(gsTask2Retrieved, team);
        }
    }


**Test 10:**

	@Test
    void ensureCheckTeamScheduleWorks1() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        if (gsTaskOptional2.isPresent()) {
            GSTask gsTask2Retrieved = gsTaskOptional2.get();
            agenda.assignTeam(gsTask2Retrieved, team);
        }

        if (gsTaskOptional1.isPresent()) {
            GSTask gsTask1Retrieved = gsTaskOptional1.get();
            assertTrue(agenda.checkTeamSchedule(team, gsTask1Retrieved));
        }
    }

**Test 11:**

	@Test
    void ensureCheckTeamScheduleWorks2() {
        Agenda agenda = new Agenda();

        GreenSpace greenSpace1 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm1@this.app");
        GSTask gsTask1 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 5, greenSpace1);

        Optional<GSTask> gsTaskOptional1 = agenda.addEntry(gsTask1, "2024/8/9");

        GreenSpace greenSpace2 = new GreenSpace("Jardim do morro", "Ponte D. Luís", 6, "garden", "gsm2@this.app");
        GSTask gsTask2 = new GSTask("Pruning Trees", "Prune all trees", "Medium", 4, greenSpace2);

        Optional<GSTask> gsTaskOptional2 = agenda.addEntry(gsTask2, "2024/8/9");

        Job job1 = new Job("Jardineiro");
        Job job2 = new Job("Eletricista");
        Collaborator collaborator1 = new Collaborator("André Gomes", "2000/1/1", "2020/2/20", "Rua Amanhã, 3366-089, Porto",
                919191919, "andreamanha3@gmail.com", 546882206, "BI", "20735924 7", job1);
        Collaborator collaborator2 = new Collaborator("Fábio Martins", "1990/5/25", "2015/2/10", "Rua da Areosa, 5696-089, Matosinhos",
                929894375, "fabioelect@gmail.com", 554765206, "BI", "20771024 7", job2);

        List<Collaborator> collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);

        Team team = new Team(collaborators);

        if (gsTaskOptional2.isPresent()) {
            GSTask gsTask2Retrieved = gsTaskOptional2.get();
            agenda.assignTeam(gsTask2Retrieved, team);
        }

        if (gsTaskOptional1.isPresent()) {
            GSTask gsTask1Retrieved = gsTaskOptional1.get();
            assertFalse(agenda.checkTeamSchedule(team, gsTask1Retrieved));
        }
    }

## 5. Construction (Implementation)

### Class AssignTeamController

```java
public List<GSTaskDTO> getAgendaEntries() {
    String email = applicationSession.getCurrentSession().getUserEmail();
    List<GSTask> freeAgendaEntriesList = agenda.getAgendaEntries(email);
    List<GSTaskDTO> freeAgendaEntriesListDTO = GSTaskMapper.toDTOAgenda(freeAgendaEntriesList);
    return freeAgendaEntriesListDTO;
}
```

```java
public List<TeamDTO> getTeams() {
List<Team> teamsList = teamRepository.getTeams();
List<TeamDTO> teamsListDTO = TeamMapper.toDTO(teamsList);
return teamsListDTO;
}
```

```java
public void getSelectedTask(int i)
    {
        selectedTask = agenda.getSelectedTask(i);
    }
```

```java
public boolean assignTeam(int i)
{
Optional<GSTask> updatedTask = Optional.empty();

        Team selectedTeam = teamRepository.getSelectedTeam(i);

        if(agenda.checkTeamSchedule(selectedTeam, selectedTask))
        {
            updatedTask = agenda.assignTeam(selectedTask, selectedTeam);

            if(updatedTask.isPresent())
            {
                if(!agendaFile.save(agenda))
                {
                    return false;
                }
            }
        }
        else
        {
            throw new UnsupportedOperationException("Team has scheduling conflicts.");
        }

        return true;
    }
```

### Class Agenda

```java
public List<GSTask> getAgendaEntries(String email)
{
    freeAgendaEntriesList = new ArrayList<>();
    for(GSTask gSTask : entriesList)
    {
        if(!gSTask.hasTeam() && gSTask.hasUserEmail(email))
        {
            freeAgendaEntriesList.add(gSTask);
        }
    }
    return freeAgendaEntriesList;
}
```

```java
public GSTask getSelectedTask(int i)
{
return freeAgendaEntriesList.get(i);
}
```

```java
public boolean checkTeamSchedule(Team selectedTeam, GSTask selectedTask)
{
List<GSTask> teamSchedule = new ArrayList<>();
for(GSTask gSTask : entriesList)
{
if(gSTask.belongsToTeam(selectedTeam))
{
teamSchedule.add(gSTask);
}
}
//Simplificação: se for uma task de mais de um dia, só pode começar num dia livre

        int workDayHours = 8;
        int availableDayHours = 8;
        int minDays = selectedTask.getExpectedDuration() / workDayHours;
        int totalOfRequestedHours = selectedTask.getExpectedDuration();

        for(GSTask gSTask : teamSchedule)
        {
            if(minDays == 0)                                                                                                    //Quando a task selecionada pode ser feita dentro de um dia
            {
                if(gSTask.getStartingDate().equals(selectedTask.getStartingDate()))                                             //Tirar o tempo das tasks que também começam no mesmo dia
                {
                    availableDayHours -= gSTask.getExpectedDuration();
                }
                else if(gSTask.getStartingDate().before(selectedTask.getStartingDate()) && gSTask.getExpectedDuration() > 8)    //Caso a task inicie antes mas tenha mais que um dia
                {
                    Calendar endingDate = (Calendar) gSTask.getStartingDate().clone();
                    endingDate.add(Calendar.DAY_OF_MONTH, gSTask.getExpectedDuration() / workDayHours);
                    if(endingDate.equals(selectedTask.getStartingDate()))                                                       //Task termina no mesmo dia que a selecionada começa
                    {
                        availableDayHours -= gSTask.getExpectedDuration() % workDayHours;
                    }
                    else if (endingDate.after(selectedTask.getStartingDate()))                                                  //Task termina só depois da selecionada começar (não pode)
                        return false;
                }
            }
            else                                                                                                                //Quando a task selecionada necessita de mais de um dia
            {
                if(gSTask.getStartingDate().equals(selectedTask.getStartingDate()))                                             //Simplificação da task só poder começar num dia sem tasks anteriormente
                {
                    return false;
                }
                else if(gSTask.getStartingDate().before(selectedTask.getStartingDate()) && gSTask.getExpectedDuration() > workDayHours)     //Quando a task começa antes mas tem mais que um dia
                {
                    Calendar endingDate = (Calendar) gSTask.getStartingDate().clone();
                    endingDate.add(Calendar.DAY_OF_MONTH, gSTask.getExpectedDuration() / workDayHours);
                    if(endingDate.equals(selectedTask.getStartingDate()) || endingDate.after(selectedTask.getStartingDate()))               //O dia não pode já ter parte de outra task ou a outra task não pode terminar depois do começo da selecionada
                    {
                        return false;
                    }
                }
                else if(gSTask.getStartingDate().after(selectedTask.getStartingDate()))                                                     //Caso a task seja depois da selecionada
                {
                    Calendar endingDateSelectedTask = (Calendar) selectedTask.getStartingDate().clone();
                    endingDateSelectedTask.add(Calendar.DAY_OF_MONTH, selectedTask.getExpectedDuration() / workDayHours);
                    if(endingDateSelectedTask.after(gSTask.getStartingDate()))                                                              //Se a task selecionada só terminar depois do começo desta
                    {
                        return false;
                    }
                    else if(endingDateSelectedTask.equals(gSTask.getStartingDate()))                                                        //Se a selecionada terminar no mesmo dia que esta começa
                    {
                        if(gSTask.getExpectedDuration() >= workDayHours)                                                                    //Se esta for maior que um dia de trabalho
                            return false;
                        else                                                                                                                //Se der para terminar a selecionada dias e terminar esta
                            availableDayHours -= gSTask.getExpectedDuration() % 8;
                    }
                }
            }
        }

        if(availableDayHours < totalOfRequestedHours % 8)
            return false;
        else
            return true;
    }
```

```java
public Optional<GSTask> assignTeam(GSTask selectedTask, Team selectedTeam)
{
Optional<GSTask> optionalValue = Optional.empty();

        optionalValue = Optional.of(selectedTask.assignTeam(selectedTeam));

        return optionalValue;
    }
```
    
### Class GSTask

```java
public boolean hasTeam()
{
return this.assignedTeam != null;
}
```

```java
public boolean hasUserEmail(String email)
{
return this.greenSpace.hasUserEmail(email);
}
```

```java
public boolean belongsToTeam(Team selectedTeam)
{
if(this.assignedTeam == null)
return false;
else
return this.assignedTeam.equals(selectedTeam);
}
```

```java
public GSTask assignTeam(Team selectedTeam) {
this.assignedTeam = selectedTeam;

        InputStream inputStream;

        try{
            Properties props = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file not found in the classpath");
            }

            String className = props.getProperty("EmailGenerator.Class");

            Class<?> oClass = Class.forName(className);

            EmailGenerator eg = (EmailGenerator) oClass.newInstance();

            if(!eg.sendEmail(this.title, this.startingDate, this.assignedTeam))
            {
                System.out.println("Error sending email to collaborators.");
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return this;
    }
```

### Class GSTaskMapper

```java
public static List<GSTaskDTO> toDTOAgenda(List<GSTask> freeAgendaEntriesList)
{
List<GSTaskDTO> freeAgendaEntriesListDTO = new ArrayList<>();
for(GSTask gSTask : freeAgendaEntriesList)
{
freeAgendaEntriesListDTO.add(toDTOAgenda(gSTask));
}
return freeAgendaEntriesListDTO;
}
```

```java
public static GSTaskDTO toDTOAgenda(GSTask gSTask) {
return new GSTaskDTO(gSTask.getTitle(), gSTask.getDescription(), gSTask.getDegreeOfUrgency(), gSTask.getExpectedDuration(), new GreenSpaceDTO(gSTask.getGreenSpace().getName(), gSTask.getGreenSpace().getAddress(), gSTask.getGreenSpace().getArea(), gSTask.getGreenSpace().getType()), gSTask.getStartingDate());
}
```

### Class GSTaskDTO

```java
public GSTaskDTO(String title, String description, String degreeOfUrgency, int expectedDuration, GreenSpaceDTO greenSpace, Calendar startingDate) {
this.title = title;
this.description = description;
this.degreeOfUrgency = degreeOfUrgency;
this.expectedDuration = expectedDuration;
this.greenSpace = greenSpace;
this.startingDate = startingDate;
}
```

### Class TeamRepository

```java
public List<Team> getTeams() {
return List.copyOf(teams);
}
```

```java
public Team getSelectedTeam(int i)
{
return teams.get(i);
}
```

### Class TeamMapper

```java
public static List<TeamDTO> toDTO(List<Team> teamsList)
{
List<TeamDTO> teamsListDTO = new ArrayList<>();
for(Team team : teamsList)
{
teamsListDTO.add(toDTO(team));
}
return teamsListDTO;
}
```

```java
public static TeamDTO toDTO(Team team) {
return new TeamDTO(team.getCollaborators());
}
```

### Class TeamDTO

```java
public TeamDTO(List<Collaborator> collaborators) {
this.collaborators = collaborators;
}
```

## 6. Integration and Demo 

* A new option on the GSM menu options was added.

* For demo purposes some tasks and teams are bootstrapped while system starts.


## 7. Observations

n/a