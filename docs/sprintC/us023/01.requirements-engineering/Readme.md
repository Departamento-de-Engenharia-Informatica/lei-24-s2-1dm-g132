# US023 - Assign a Team to an entry in the Agenda


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign a Team to an entry in the Agenda.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>  The Agenda is made
up of entries that relate to a task (which was previously in the To-Do List),
the team that will carry out the task, the vehicles/equipment assigned to
the task, expected duration, and the status (Planned, Postponed, Canceled,
Done).

**From the client clarifications:**

### 1.3. Acceptance Criteria

* **AC1:** A message must be sent to all team members informing them about the assignment.
* **AC2:** Different email services can send the message. These services must be defined through a configuration file to allow the use of different platforms (e.g. Gmail, DEI’s email service, etc.).
* **AC3:** The Team and the entry in the agenda must be selected by the GSM.

### 1.4. Found out Dependencies

* There is a dependency on "US022 - Add a new entry in the Agenda." as there must be at least one entry in the agenda.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
  * a team
  * an entry from the Agenda

**Output Data:**

* List of existing entries in the Agenda
* List of existing teams
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram](svg/us003-system-sequence-diagram.svg)


### 1.7 Other Relevant Remarks