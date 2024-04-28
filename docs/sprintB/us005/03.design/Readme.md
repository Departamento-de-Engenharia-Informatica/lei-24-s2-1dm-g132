# US005 - Generate a team proposal

## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID | Question: Which class is responsible for...                 | Answer                 | Justification (with patterns)        |
|:---------------|:------------------------------------------------------------|:-----------------------|:-------------------------------------|
| Step 1  		     | 	... interacting with the actor?                            | GenerateTeamUI         | Pure Fabrication                     |
|                | ... coordinating the US?                                    | GenerateTeamController | Pure Fabrication, Controller         |
| Step 2  		     | 	... displaying the form for the actor to input data?						 | GenerateTeamUI         | Pure Fabrication                     |
| Step 3  		     | 	... temporarily keeping the input data?                    | GenerateTeamUI         | Pure Fabrication                     |
|                | ... obtaining the skills list?                              | SkillRepository        | Pure Fabrication, Information Expert |
| Step 4  		     | 	... displaying the skills?                                 | GenerateTeamUI         | Pure Fabrication                     |
|                | ... displaying the text field for skill input               | GenerateTeamUI         | Pure Fabrication                     |
| Step 5  		     | 	... temporarily keeping the skills introduced?             | GenerateTeamUI         | Pure Fabrication                     |
| Step 6  		     | 	... asking the actor to stop the loop?						               | GenerateTeamUI         | Pure Fabrication                     |              
| Step 7  		     | 	... continuing/breaking the loop?                          | GenerateTeamUI         | Pure Fabrication                     |
|                | ... removing collaborators that are already on teams?       | Team                   |                                      |
|                | ... selecting the most suitable collaborators?              | Collaborator           | Information Expert                   |
| Step 8         | ... showing the proposed team?                              | GenerateTeamUI         | Pure Fabrication                     |
| Step 9		       | 	... receiving the acceptance or refusal of the team?       | GenerateTeamUI         | Pure Fabrication                     | 
|                | ... creating a team object?                                 | TeamRepository         | Pure Fabrication, Creator (R: 1, 2)  |
| Step 10        | ... informing operation result?                             | GenerateTeamUI         | Pure Fabrication                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Team
* Collaborator

Other software classes (i.e. Pure Fabrication) identified: 

* GenerateTeamUI
* GenerateTeamController
* TeamRepository
* SkillRepository
* CollaboratorRepository


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