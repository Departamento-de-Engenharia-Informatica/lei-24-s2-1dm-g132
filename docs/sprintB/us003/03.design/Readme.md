# US003 - Register a collaborator

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...                  | Answer                         | Justification (with patterns)        |
|:---------------|:-------------------------------------------------------------|:-------------------------------|:-------------------------------------|
| Step 1  		     | 	... interacting with the actor?                             | RegisterCollaboratorUI         | Pure Fabrication                     |
|                | ... coordinating the US?                                     | RegisterCollaboratorController | Pure Fabrication, Controller         |
| 			  		        | 	... obtaining the jobs list?                                | JobRepository                  | Pure Fabrication, Information Expert |
| Step 2  		     | ... displaying the jobs?						                               | RegisterCollaboratorUI         | Pure Fabrication                     |
| Step 3  		     | 	... temporarily keeping the selected job?                   | RegisterCollaboratorUI         | Pure Fabrication                     |
| Step 4  		     | 	... displaying the form for the actor to input data?        | RegisterCollaboratorUI         | Pure Fabrication                     |
| Step 5  		     | 	... temporarily keeping the input data?                     | RegisterCollaboratorUI         | Pure Fabrication                     |
| Step 6  		     | 	... displaying all the information before submitting?						 | RegisterCollaboratorUI         | Pure Fabrication                     |              
| Step 7  		     | 	... validating all data (local validation)?                 | Collaborator                   | Information Expert                   | 
| 			  		        | 	... validating all data (global validation)?                | CollaboratorRepository         | Pure Fabrication, Information Expert | 
| 			  		        | 	... saving the created collaborator?                        | CollaboratorRepository         | Pure Fabrication, Creator (R: 1, 2)  | 
| Step 8  		     | 	... informing operation success?                            | RegisterCollaboratorUI         | Pure Fabrication                     | 

####################
OBS: Perguntar sobre que tipo de validações podem ser feitas na UI.
####################


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Collaborator

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterCollaboratorUI  
* RegisterCollaboratorController
* CollaboratorRepository
* JobRepository


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us006-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us006-sequence-diagram-split.svg)

**Get Task Category List Partial SD**

![Sequence Diagram - Partial - Get Task Category List](svg/us006-sequence-diagram-partial-get-task-category-list.svg)

**Get Task Category Object**

![Sequence Diagram - Partial - Get Task Category Object](svg/us006-sequence-diagram-partial-get-task-category.svg)

**Get Employee**

![Sequence Diagram - Partial - Get Employee](svg/us006-sequence-diagram-partial-get-employee.svg)

**Create Task**

![Sequence Diagram - Partial - Create Task](svg/us006-sequence-diagram-partial-create-task.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us006-class-diagram.svg)