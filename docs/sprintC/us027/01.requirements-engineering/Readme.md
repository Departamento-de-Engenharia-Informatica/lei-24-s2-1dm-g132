# US027 - As a GSM, I need to list all green spaces 


## 1. Requirements Engineering

### 1.1. User Story Description

- As aAs a GSM, I need to list all green spaces managed by me

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> "an employee has a main occupation (job) and a set of skills that enable him to perform/take on certain tasks/responsibilities"


**From the client clarifications:**

> **Question:** Hello, I have some questions about the US01: Which information can be introduced to create a new skill?
Which information is mandatory for creating a new skill?
Which are the skills accepted? Or should we enable the HRM to introduce anything as a skill?
> 
> **Answer:** The skill name;
The skill name;
All, it's up to HRM to decide. (special characters or algarisms should not be allowed in the skill name)




### 1.3. Acceptance Criteria

* **AC1:**  The list of green spaces must be sorted by size in descending order. The sorting algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available.

### 1.4. Found out Dependencies

* There are no dependencies
### 1.5 Input and Output Data

**Input Data:**

* Typed data:

  * Yes/No to confirm the operation

* Selected data:
  * Type of sorting algorithm


**Output Data:**

* Listed Operations sorted by size in a specific order
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One]

### 1.7 Other Relevant Remarks
