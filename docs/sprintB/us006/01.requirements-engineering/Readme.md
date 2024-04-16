# US006 - Register a Vehicle


## 1. Requirements Engineering

### 1.1. User Story Description
As an VFM, I wish to register a vehicle including Brand, Model, Type, Tare,
Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency (in Kms)

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost, as well as a task category. 

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** For the application to work does the FM need to fill all the attributes of the vehicle?
>
> **Answer:** Ainda Sem Resposta

> **Question:** Quando estamos a registar um veiculo ao inserir a marca, deve-se verificar se essa marca existe? igualmente com o modelo e o tipo do veiculo?
Deve-se verificar que os kms introduzidos, tara e valores numéricos, não sejam negativos?
>
> **Answer:** Ainda Sem Resposta

> **Question** Should the application identify a registered vehicle by a serial number or other attribute?
-Should the application a group the vehicles by their brand, serial number or other attribute?
-If the Fm inserts the same vehicle by mistake, should it inform ther user of the mistake and give him the option to add another vehicle?
>
> **Answer:** - By plate id;
no requirements were set concerning groups of vehicles;
again, duplication of data is not a business rule is technical one, since by definition in a set you cant have duplicates.

>**Question** For the application to work does the FM need to fill all the attributes of the vehicle?
> 
> **Answer:** yes, besides the vehicle plate that by mistake doesn't appear on the text.

> **Question** 
> 
> **Answer:** Ainda Sem Resposta
 

### 1.3. Acceptance Criteria

*Doesn't exist yet.*
### 1.4. Found out Dependencies

*Doesn't exist yet.*

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Brand
    * Model
    * Type
    * Tare
    * Gross Weight
    * Current Km
    * Register Date
    * Acquisition Date
    * Maintenance/Checkup Frequency (in Kms)
    * Plate


**Output Data:**


* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)


### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.