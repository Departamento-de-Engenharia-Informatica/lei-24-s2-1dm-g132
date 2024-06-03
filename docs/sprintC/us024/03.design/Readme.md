# US024 - postpone an entry in the agenda

## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for...                      | Answer                  | Justification (with patterns)             |
|:---------------|:-----------------------------------------------------------------|:------------------------|:------------------------------------------|
| Step 1         | ...interacting with the actor?                                   | PostponeEntryUI         | Pure Fabrication                          |
|                | ...coordinating the US?                                          | PostponeEntryController | Pure Fabrication, Controller              |
|                | ...obtaining the green spaces list?                              | GreenSpaceRepository    | Pure Fabrication, Information Expert      |
| Step 2         | ...displaying green spaces list?                                 | PostponeEntryUI         | Pure Fabrication                          |
| Step 3         | ...obtaining tasks by green space?                               | Agenda                  | Pure Fabrication, Information Expert      |
| Step 4         | ...displaying entries in the agenda of the selected green space? | PostponeEntryUI         | Pure Fabrication                          |
| Step 5         | ...saving the selected entry?                                    | PostponeEntryUI         | Pure Fabrication                          |              
| Step 6         | ...requesting the new date?                                      | PostponeEntryUI         | Pure Fabrication                          | 
| Step 7         | ...postponing requested entry?                                   | TaskRepository          | Pure Fabrication, Information Expert      | 
| Step 8         | ...informing operation success?                                  | PostponeEntryUI         | Pure Fabrication                          |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Task
* Green Space

Other software classes (i.e. Pure Fabrication) identified: 

* PostponeEntryUI  
* PostponeEntryController
* TaskRepository
* GreenSpaceRepository
* Agenda

## 3.2. Sequence Diagram (SD)

### Full Diagram

![US24 - Sequence Diagram - Full](svg/us024-sequence-diagram-full.svg)

### Split Diagrams

![US24 - Sequence Diagram - split](svg/us024-sequence-diagram-split.svg)

**Get Green Spaces**

![Sequence Diagram - Partial - Get Green Spaces](svg/us024-sequence-diagram-partial-get-green-spaces.svg)

**Get Tasks by Green Space**

![Sequence Diagram - Partial - Get Tasks by Green Space](svg/us024-sequence-diagram-partial-get-tasks-by-green-space.svg)


## 3.3. Class Diagram (CD)

![US24 - Class Diagram](svg/us024-class-diagram.svg)