# US029 - Record the completion of a task.


## 1. Requirements Engineering

### 1.1. User Story Description

As a Collaborator, I want to record the completion of a task.

### 1.2. Customer Specifications and Clarifications

**From the client clarifications:**

> **Question:** Can an employee record more than one completed task at a time?
>
> **Answer:** It's a matter of UX/UI, each dev team can decide about it.

> **Question:** When a collaborator records a task, it should be asked for any observations regarding the completed task?
>
> **Answer:** Maybe if optional, not mandatory.

> **Question:** The collaborator can see what type of entries? Like what status can he filter ? Can he see canceled Entry's?
>
> **Answer:** The ones assigned to him.  He can filter by the different values the status of the status, like planned, executed, canceled ...

> **Question:** Can a collaborator mark a task as "done" only if it is in the "planned" status?
>
> **Answer:** It depends in the status set your team decide to have/use. But if you consider only status refered in the text and in the forum my answer would be, yes, just the "Planned" status can be changed to "Done".

> **Question:** This "record" refers to the act of only changing the status of the task, or do we want to save a list of the completed tasks within each Collaborator?
>
> **Answer:** It means to record that a task was completed (changing the status) with the finish time.


### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.

### 1.4. Found out Dependencies

* There is a dependency on "US022 - Add a new entry in the agenda" as there must be at least one entry to be able to postpone it.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:

* Selected data:
 * Skill
 
**Output Data:**

* List of skills
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us029-system-sequence-diagram.svg)


