# US002 - Register a new job

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_



| Interaction ID | Question                                     | Answer                | Justification                                              |
|:---------------|:---------------------------------------------|:----------------------|:-----------------------------------------------------------|
| Step 1         | ... interacting with the actor?              | RegisterJobUI         | Pure Fabrication                                           |
|                | ... coordinating the US?                     | RegisterJobController | Controller                                                 |
|                | ... getting the job list?                    | JobRepository         | Pure Fabrication                                           |
| Step 2         | ... saving the inputted data?                | RegisterJobUI         | Pure Fabrication                                           |
| Step 3         | ... knowing the jobName to show?             | RegisterJobUI         | IE      |
| Step 4         | ... saving the selected category?            | Job                   | IE:  |
| Step 5         | ... validating all data (local validation)?  | Job                   | IE                                                         |
|                | ... validating all data (global validation)? | JobRepository         | IE , Pure Fabrication                                      |
|                | ... saving the created job?                  | JobRepository         | IE                                                         |
| Step 6         | ... informing operation success?             | RegisterJobUI         | Pure Fabrication                                           |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Job

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterJobUI  
* RegisterJobController
* JobRepository


## 3.2. Sequence Diagram (SD)


### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full]!![us002-class-diagram-Register_a_job__that_a_collaborator_needs_to_have.svg](svg%2Fus002-class-diagram-Register_a_job__that_a_collaborator_needs_to_have.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

**Register Job Partial SD**

![Sequence Diagram - Partial - Get Task Category List]![us002-sequence-diagram-partial-RegisterJob.svg](svg%2Fus002-sequence-diagram-partial-RegisterJob.svg)

**Store Job SD**

![Sequence Diagram - Partial - Get Task Category Object]![us002-sequence-diagram-partial-StoreJob.svg](svg%2Fus002-sequence-diagram-partial-StoreJob.svg)


## 3.3. Class Diagram (CD)

![Class Diagram]![us002-class-diagram-Register_a_job__that_a_collaborator_needs_to_have.svg](svg%2Fus002-class-diagram-Register_a_job__that_a_collaborator_needs_to_have.svg)