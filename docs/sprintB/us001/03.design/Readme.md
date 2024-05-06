# US001 - Register a skill

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                | Justification (with patterns)                                                                                 |
|:-------------  |:----------------------------------------------|:----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		 | 	... interacting with the actor?              | CreateSkillUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		 | 	... coordinating the US?                     | CreateSkillController | Controller                                                                                                    |
| 			  		 | 	... instantiating a new Skill?               | Organization          | Creator (Rule 1): in the DM Organization has a Task.                                                          |
| 			  		 | ... knowing the user using the system?        | UserSession           | IE: cf. A&A component documentation.                                                                          |
| 			  		 | 							                                       | Organization          | IE: knows/has its own Employees                                                                               |
| 			  		 | 							                                       | Employee              | IE: knows its own data (skill name)                                                                           |
| Step 2  		 | 							                                       |                       |                                                                                                               |
| Step 3  		 | 	...saving the inputted data?                 | Skill                 | IE: object created in step 1 has its own data.                                                                |
| Step 4  		 | 	...knowing the task categories to show?      | System                | IE: Skill Categories are defined by the Administrators.                                                       |
| Step 5  		 | 	... saving the selected category?            | Skill                 | IE: object created in step 1 is classified in one Category.                                                   |
| Step 6  		 | 							                                       |                       |                                                                                                               |              
| Step 7  		 | 	... validating all data (local validation)?  | Skill                 | IE: owns its data.                                                                                            | 
| 			  		 | 	... validating all data (global validation)? | Organization          | IE: knows all its skills.                                                                                     | 
| 			  		 | 	... saving the created task?                 | Organization          | IE: owns all its skills.                                                                                      | 
| Step 9         | ...adding skills to repository               | SkillRepository         |                                 |
| Step 8  		 | 	... informing operation success?             | CreateSkillUI         | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Collaborator
* Skill


Other software classes (i.e. Pure Fabrication) identified:

* CreateSkillUI
* CreateSkillController
* SkillRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us001-sequence-diagram-full.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

![Sequence Diagram - split](svg/us001-sequence-diagram-split.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us001-class-diagram.svg)