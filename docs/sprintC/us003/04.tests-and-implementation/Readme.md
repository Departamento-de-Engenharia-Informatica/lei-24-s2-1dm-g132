# US003 - Register a collaborator

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Task instance = new Task(null, null, null, null, null, null, null);
	}
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureReferenceMeetsAC2() {
		Category cat = new Category(10, "Category 10");
		
		Task instance = new Task("Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat);
	}

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class RegisterCollaboratorUI

```java
public void run() {
    boolean dadosInvalidos1 = true;
    boolean dadosInvalidos2 = true;

    System.out.println("\n\n--- Register Collaborator ------------------------");

    do{
        try {
            jobName = displayAndSelectJob();
            dadosInvalidos1 = false;

        } catch (InputMismatchException e){
            System.out.println("\nERROR: " + "Invalid input value.\n");
        }
        catch (RuntimeException e){
            System.out.println("\nERROR: " + e.getMessage());
            return;
        }
    }while (dadosInvalidos1);

    do {
        try {
            requestData();
            submitData();
            dadosInvalidos2 = false;
        }
        catch (IllegalArgumentException e){
            System.out.println("\nERROR: " + e.getMessage());
        }
        catch (InputMismatchException e){
            System.out.println("\nERROR: " + "Invalid input value.\n");
        }
    }while (dadosInvalidos2);
}
```

```java
private void submitData() {
        Optional<Collaborator> collaborator = getController().registerCollaborator(collaboratorName, collaboratorBirthdate, collaboratorAdmissionDate,
                collaboratorAddress, collaboratorPhoneNumber, collaboratorEmail, collaboratorTaxpayerNumber, collaboratorIdentificationDocumentType, collaboratorIdentificationDocumentNumber, jobName);

        if (collaborator.isPresent()) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered successfully!");
        }
    }
```

```java
private void requestData() {

    collaboratorName = requestCollaboratorName();

    collaboratorBirthdate = requestCollaboratorBirthdate();

    collaboratorAdmissionDate = requestCollaboratorAdmissionDate();

    collaboratorAddress = requestCollaboratorAddress();

    collaboratorPhoneNumber = requestCollaboratorPhoneNumber();

    collaboratorEmail = requestCollaboratorEmail();

    collaboratorTaxpayerNumber = requestCollaboratorTaxpayerNumber();

    collaboratorIdentificationDocumentType = requestCollaboratorIdentificationDocumentType();

    collaboratorIdentificationDocumentNumber = requestCollaboratorIdentificationDocumentNumber();
}
```

```java
private String requestCollaboratorName() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Name: ");
    return input.nextLine();
}
```

```java
private String requestCollaboratorBirthdate() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Birthdate (year/month/day): ");
    return input.nextLine();
}
```

```java
private String requestCollaboratorAdmissionDate() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Admission Date (year/month/day): ");
    return input.nextLine();
}
```

```java
private String requestCollaboratorAddress() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Address (street, zipcode, city): ");
    return input.nextLine();
}
```

```java
private int requestCollaboratorPhoneNumber() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Phone Number: ");
    return input.nextInt();
}
```

```java
private String requestCollaboratorEmail() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Email: ");
    return input.nextLine();
}
```

```java
private int requestCollaboratorTaxpayerNumber() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Taxpayer Number: ");
    return input.nextInt();
}
```

```java
private String requestCollaboratorIdentificationDocumentType() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Identification Document Type (CC, BI or Passport): ");
    return input.nextLine();
}
```

```java
    private String requestCollaboratorIdentificationDocumentNumber() {
    Scanner input = new Scanner(System.in);
    System.out.print("Collaborator Identification Document Number: ");
    return input.nextLine();
}
```

```java
    private String displayAndSelectJob() {
    List<Job> jobs = controller.getJobs();

    int listSize = jobs.size();

    if(listSize == 0)
        throw new RuntimeException("There are no jobs to display at the moment.");

    int answer = -1;

    Scanner input = new Scanner(System.in);

    while (answer < 1 || answer > listSize) {
        displayJobOptions(jobs);
        System.out.print("Select a job: ");
        answer = input.nextInt();
    }

    String name = jobs.get(answer - 1).getJobName();
    return name;
}
```

```java
private void displayJobOptions(List<Job> jobs) {
    int i = 1;
    for (Job job : jobs) {
        System.out.println("  " + i + " - " + job.getJobName());
        i++;
    }
}
```


### Class Organization

```java
public Optional<Task> createTask(String reference, String description, String informalDescription,
                                 String technicalDescription, Integer duration, Double cost, TaskCategory taskCategory,
                                 Employee employee) {
    
    Task task = new Task(reference, description, informalDescription, technicalDescription, duration, cost,
                         taskCategory, employee);

    addTask(task);
        
    return task;
}
```


## 6. Integration and Demo 

n/a


## 7. Observations

n/a