# US001 - Register a skill

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                       | Justification (with patterns)                               |
|:---------------|:----------------------------------------------|:-----------------------------|:------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?              | RegisterGreenSpaceUI         | Pure Fabrication                                            |
| 			  		        | 	... coordinating the US?                     | RegisterGreenSpaceController | Controller, Pure fabrication                                |
| 			  		        | 	... instantiating a new GreenSpace?          | GreenSpace                   | I.E.                                                        |
| 			  		        | ... knowing the user using the system?        | UserSession                  | IE                                                          |
| 			  		        | 							                                       | Organization                 | IE, Pure fabrication                                        |
| 			  		        | 							                                       | Employee                     | IE,DTO                                                      |
| Step 2 		      | 	...saving the inputted data?                 | GreenSpace                   | IE: object created in step 1 has its own data.              |
| Step 3  		     | 	...knowing the task categories to show?      | System                       | IE: Skill Categories are defined by the Administrators.     |
| Step 4  		     | 	... saving the selected category?            | GreenSpace                   | IE: object created in step 1 is classified in one Category. |
| Step 5  		     | 							                                       |                              |                                                             |              
| Step 6  		     | 	... validating all data (local validation)?  | GreenSpace                   | IE: owns its data.                                          | 
| 			  		        | 	... validating all data (global validation)? | GreenSpaceRepository         | IE: knows all its skills.                                   | 
| 			  		        | 	... saving the created GreenSpace?           | GreenSpaceRepository         | IE: owns all its skills.                                    | 
| Step 7         | ...adding GreenSpaces to repository           | GreenSpaceRepository         |                                                             |
| Step 8  		     | 	... informing operation success?             | RegisterGreenSpaceUI                | IE: is responsible for user interactions.                   | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* GreenSpace


Other software classes (i.e. Pure Fabrication) identified:

* GreenSpaceRepository
* RegisterGreenSpaceUI
* RegisterGreenSpaceController
* GreenSpaceMapper



## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us020-sequence-diagram-full.svg](svg%2Fus020-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![us020-class-diagram.svg](svg%2Fus020-class-diagram.svg)