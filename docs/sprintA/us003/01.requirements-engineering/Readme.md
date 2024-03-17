# US003 - Register a collaborator


## 1. Requirements Engineering

### 1.1. User Story Description

As an HRM, I want to register a collaborator with a job and fundamental characteristics.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each collaborator has a main job (exclusively one).

>   Each collaborator is characterized by a set of characteristics.

**From the client clarifications:**

> **Question:** Which information is mandatory to insert a collaborator in the program (fundamental characteristics)?
>
> **Answer:** name, birthdate, admission date, address, contact info (mobile and email), taxpayer number, ID doc type and respective number.

> **Question:** When creating a collaborator with an existing name ... What does the system do?
> 
> **Answer:** It's not common and most improbable to have different individual with same name in the same context, however it’s ID documentation number should be unique for sure.

> **Question:** Does the HRM select the job from a list that we display?
>
> **Answer:** Displaying or not, It's a matter of UX, the dev team should decide about it, but the valid jobs are the ones created within the US02.

### 1.3. Acceptance Criteria

* **AC1:** job, name, birthdate, admission date, address, contact info (mobile and email), taxpayer number, ID doc type(CC, BI, passport) and respective number should be provided by the HRM.
* **AC2:** When registering a collaborator with an existing ID documentation number, the system must reject such operation.

### 1.4. Found out Dependencies

* There is a dependency on "US002 - Register a job" as there must be at least one job to be attributed to the collaborator being registered.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a name
    * a birthdate
    * an admission date
    * an address
    * a mobile phone number
    * an email
    * a taxpayer number
    * an identification document type and its number
	
* Selected data:
    * a job

**Output Data:**

* List of existing jobs
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram](svg/us003-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks

* The way the job selection is done can be decided by the development team (displaying the valid jobs or confirming if the job entered is valid).