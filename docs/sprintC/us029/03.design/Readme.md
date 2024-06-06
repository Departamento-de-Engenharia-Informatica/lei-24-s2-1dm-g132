# US029 - Record the completion of a task.

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                 | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | ChangeStatusUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                     | ChangeStatusController | Controller                                                                                                    |
| 			  		        | 	... instantiating a new Skill?               | Collabborator          | Creator (Rule 1): in the DM Collaborator has a Task.                                                          |
| 			  		        | ... knowing the user using the system?        | UserSession            | IE: cf. A&A component documentation.                                                                          |
| 			  		        | 							                                       | Collabborator               | IE: knows its own data (skill name)                                                                           |
| Step 2  		     | 				… asking to choose a skill?				                                       |          ChangeStatusUI              |      	Information Expert: UI is responsible for requesting and displaying information to the user.                                                                                                         |
| Step 3  		     | 	… choosing skill from list?	                 | ChangeStatusUI                  | Information Expert: UI is responsible for requesting and displaying information to the user.                                                              |
| Step 4  		     | 			… asking for confirmation of task completion?					                                       |       ChangeStatusUI                 |             	Information Expert: UI is responsible for requesting and displaying information to the user.                                                                                                  |              
| Step 5  		     | 	... validating all data (local validation)?  | ChangeStatusController                  | r	Controller: Processes the user’s input and directs the flow of the system operation.                                                                                            | 
| Step 6  		     | 	... informing operation success?             | ChangeStatusUI          | 	Information Expert: UI is responsible for presenting the data to the user.                                                                | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Collaborator
* Task


Other software classes (i.e. Pure Fabrication) identified:

* ChangeStatusUI
* ChangeStatusController


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us029-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us029-sequence-diagram-split.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us029-class-diagram.svg)