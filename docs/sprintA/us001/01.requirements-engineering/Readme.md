# US001 - register skills that a collaborator may have


## 1. Requirements Engineering

### 1.1. User Story Description

- As a Human Resources Manager (HRM), I want to register skills that a
  collaborator may have

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**


**From the client clarifications:**

> **Question:** Hello, I have some questions about the US01:
Question:** Which information can be introduced to create a new skill?
Question:** Which information is mandatory for creating a new skill?
Question:** Which are the skills accepted? Or should we enable the HRM to introduce anything as a skill?

> **Answer:** The skill name;
The skill name;
All, it's up to HRM to decide. (special characters or algarisms should not be allowed in the skill name)

> **Question:** Good Morning, client
I wanted to ask a few question:
Do I need to add skills  by writing them or can I just give a file with all of the skills?
Does the HRM need to see the confirmation of the sucess of the operation and the skills added or just the sucess of the operation?
Is there any other possible outcome like if the HMR does not put the requested data, do I need to ask the user to to register a diferent competence?

> **Answer:** Hi,
Both are acceptable since the business the same the crucial difference resides in the UX.
It will depend if you opt to insert one skil or a set of skils in a batch. Maybe some adaptation needed.
This US is quite simple, atm a skill is simply a name.

> **Question:** Que criterios sao necessarios para registar uma skill?
Que tipo de informação uma skill tem?
Quando é criado uma skill que ja existe o que sistema devera fazer ?

> **Answer:** o nome da skill, por exemplo:
podador,
condutor de veiculos pesados,
aplicador de fito-farmacos.
Por definição não é possível ter valores duplicados num conjunto. A verificação de duplicados não é uma regra de negócio é do nível tecnológico.




### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.

### 1.4. Found out Dependencies

* There are no dependencies
### 1.5 Input and Output Data

**Input Data:**

* Typed data:


* Selected data:


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us001-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us001-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks
