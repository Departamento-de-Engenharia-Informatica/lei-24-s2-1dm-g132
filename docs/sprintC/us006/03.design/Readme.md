# US006 - Register a new vehicle

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_


| Interaction ID | Question                                     | Answer                    | Justification                                              |
|:---------------|:---------------------------------------------|:--------------------------|:-----------------------------------------------------------|
| Step 1         | ... interacting with the actor?              | RegisterVehicleUI         | Pure Fabrication                                           |
|                | ... coordinating the US?                     | RegisterVehicleController | Controller                                                 |
|                | ... getting the Vehicle list?                | VehicleRepository         | Pure Fabrication                                           |
| Step 2         | ... saving the inputted data?                | RegisterVehicleUI         | Pure Fabrication                                           |
| Step 3         | ... knowing the Vehicle Name to show?        | RegisterVehicleUI         | IE      |
| Step 4         | ... saving the selected category?            | Vehicle                   | IE:  |
| Step 5         | ... validating all data (local validation)?  | Vehicle                   | IE                                                         |
|                | ... validating all data (global validation)? | VehicleRepository         | IE , Pure Fabrication                                      |
|                | ... saving the created Vehicle?              | VehicleRepository         | IE                                                         |
| Step 6         | ... informing operation success?             | RegisterVehicleUI         | Pure Fabrication                                           |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Vehicle

Other software classes (i.e. Pure Fabrication) identified: 

* RegisterVehicleUI
* RegisterVehicleController
* VehicleRepository



## 3.2. Sequence Diagram (SD)


### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full]!![us006-class-diagram-Register_a_new_vehicle.svg](svg%2Fus006-class-diagram-Register_a_new_vehicle.svg)

### Split Diagrams

The following diagram shows the same sequence of interactions between the classes involved in the realization of this user story, but it is split in partial diagrams to better illustrate the interactions between the classes.

It uses Interaction Occurrence (a.k.a. Interaction Use).

**Register Vehicle Partial SD**

![Sequence Diagram - Partial - Get Task Category List]![us006-sequence-diagram-partial-RegisterVehicle.svg](svg%2Fus006-sequence-diagram-partial-RegisterVehicle.svg)

**Store Vehicle**

![Sequence Diagram - Partial - Get Task Category Object]![us006-sequence-diagram-partial-StoreVehicle.svg](svg%2Fus006-sequence-diagram-partial-StoreVehicle.svg)

## 3.3. Class Diagram (CD)

![Class Diagram]![us006-class-diagram-Register_a_new_vehicle.svg](svg%2Fus006-class-diagram-Register_a_new_vehicle.svg)