# US022 - Add a new entry in the Agenda

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...                    | Answer                   | Justification (with patterns)             |
|:---------------|:---------------------------------------------------------------|:-------------------------|:------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                               | AddEntryAgendaUI         | Pure Fabrication                          |
|                | ... coordinating the US?                                       | AddEntryAgendaController | Pure Fabrication, Controller              |
|                | ... knowing the GSM using the system?                          | UserSession              | Information Expert                        |
|                | ... knowing to which GSM belongs the Entry?                    | GreenSpace               | Information Expert                        |
|                | ... knowing if Entry is pending?                               | Task                     | Information Expert                        |
| 			  		        | 	... obtaining the To-Do list entries?                         | ToDoList                 | Information Expert                        |
|                | ... converting the entries list into DTO?                      | TaskMapper               | Pure Fabrication, Information Expert, DTO |
| Step 2  		     | ... displaying the To-Do List entries?						                   | AddEntryAgendaUI         | Pure Fabrication                          |
| Step 3  		     | 	... identifying the selected Task?                            | ToDoList                 | Information Expert                        |
| Step 4  		     | 	... displaying the form for the actor to input starting date? | AddEntryAgendaUI         | Pure Fabrication                          |
| Step 5         | ... saving the created entry in the agenda?                    | Agenda                   | Creator (R: 1, 2)                         |
|                | ... validating starting date?                                  | Task                     | Information Expert                        |
| Step 6  		     | 	... informing operation success? 	                            | AddEntryAgendaUI         | Pure Fabrication                          |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Task
* Agenda
* ToDoList
* GreenSpace

Other software classes (i.e. Pure Fabrication) identified:

* AddEntryAgendaUI
* AddEntryAgendaController
* TaskMapper


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us022-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us022-class-diagram.svg)