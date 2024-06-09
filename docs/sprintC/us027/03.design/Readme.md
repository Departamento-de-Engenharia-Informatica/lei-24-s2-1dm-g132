# US001 - Register a skill

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...        | Answer                | Justification (with patterns)                                                                                                |
|:---------------|:---------------------------------------------------|:----------------------|:-----------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                   | ListGreenSpacesUI         | Pure Fabrication.                                                                                                            |
| 			  		        | 	... coordinating the US?                          | ListGreenSpacesController | Controller                                                                                                                   |
| 			  		        | 	... obtaining the green spaces list?              | GreenSpaceRepository          | Pure Fabrication, Information Expert.                                                                                        |
| 			  		        | ... ... converting the green spaces list into DTO? | GreenSpaceMapper           | Pure Fabrication, Information Expert, DTO.                                                                                   |
| Step 2  		     | 				... displaying the green spaces?			            |        ListGreenSpacesUI               | Pure Fabrication                                                                                                             |
| Step 3  		     | 	... identifying the selected green space?                     | GreenSpaceRepository                | Pure Fabrication, Information Expert.                                                                                        |
| Step 4  		     | 	... displaying the form for the actor to input data?           | Not applicable in this use case.                |                                                                                                                              |
| Step 5  		     | 	... converting DTO into a domain object?                 | Not applicable in this use case.                |                                                                                                                              |
| 		             | 	... validating all data (local validation)?						                                            |         Not applicable in this use case.              |                                                                                                                              |              
| Step 6  		     | 	... informing operation success?       | ListGreenSpacesUI                 | Pure Fabrication                                                                                                             | 
| Step 7			  		  | 	... sorting the green spaces list?      | ListGreenSpacesController          | Controller                                                                                                    | 
| Step 8		  		   | 	... displaying the sorted green spaces?                      | ListGreenSpacesUI         | Pure Fabrication                                                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* GreenSpace



Other software classes (i.e. Pure Fabrication) identified:

* ListGreenSpacesUI
* ListGreenSpacesController
* GreenSpaceRepository
* GreenSpaceMapper



## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us027-sequence-diagram-full.svg](svg%2Fus027-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![us027-class-diagram.svg](svg%2Fus027-class-diagram.svg)