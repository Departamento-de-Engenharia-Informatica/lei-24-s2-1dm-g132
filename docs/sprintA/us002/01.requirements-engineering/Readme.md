# US002 - Register a job


## 1. Requirements Engineering

### 1.1. User Story Description

- As an HRM(Human resource Manager), I want to register a job.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost, as well as a task category. 

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** Bom dia, Para a US02, gostaria de esclarecer o seguinte:
É relevante associar uma área ou setor específico a cada Job? (Por exemplo, "Jardineiro" seria inserido no setor de "Manutenção")
Deve-se incluir informações como salário, tipo de contratação (full-time ou part-time), e modalidade de trabalho (presencial, remoto ou híbrido) no Job? Ou essas informações encaixam-se melhor no âmbito do colaborador, ou talvez nem sejam necessárias?
Que outras informações acha necessárias associar ao Job?
Agradeço a atenção dispensada.
>
> **Answer:** Bom dia,
não é necessário na medida que não existem US que sugiram que isso possa vir a ser necessário;
idem
para já nenhumas
De nada.

> **Question:** 1.What should the output of the automation be? (should it just store the team proposal or show it to the customer?)
> Will the team proposal be a document about all the instructions of each team member/worker?
>
> **Answer:** Ainda Sem Resposta

> **Question:** Que criterios sao necessarios para registar uma skill?
Que tipo de informação uma skill tem?
Quando é criado uma skill que ja existe o que sistema devera fazer ?
> 
> **Answer:** Ainda Sem Resposta

> **Question:** -When the user registers a job does he select a job from a list or does he type out a custom job name?
What data is introduced by the user when creating a job?
>
> **Answer:** Ainda Sem Resposta

> **Question:** Boa dia,
Quando se regista uma skill ou trabalho, que campos são preenchidos, por exemplo, designação, descrição mais concreta, número de identificação, ...
É desejado que todos os campos, no caso de haver mais que um, dependendo da resposta à questão anterior, sejam de preenchimento obrigatório ou facultativo, e quais?
No caso do número de identificação, o cliente pretende fornecer um, ou que um seja atribuído pelo sistema
Sempre que algo é registado com sucesso, é pretendido apenas observar uma mensagem de sucesso, ou também uma lista do que já foi registado anteriormente?
>
> **Answer:** Ainda Sem Resposta

> **Question**  Quais são os dados de entrada para a criação de uma profissão?
> 
> **Answer:** o Nome da profissão:
jardineiro
calceteiro
electricista
condutor
...

> **Question1** What are the acceptance criteria?
When are creating a job that already exit, what the system do?
>
> **Answer:** By definition a set can´t have duplicates. Assuring no duplicates is not a business rule is a tecnichal issue.
>
> **Question2** What are the criteries to accept a job category regist?
Any bussiness rule?
>
> **Answer2:** Ainda Sem Resposta

> **Question** Good afternoon client,
I would like to inquire about the functionalities that the system should have:
When the HRM registers a job, is it necessary to provide a job description and inquire about the collaborator's qualifications?
Should the system be able to display a list of all jobs currently registered?
Does the system need to provide the capability to edit a job?
I appreciate your attention.
Best regards, Samara Miranda
>
> **Answer:** Ainda Sem Resposta

> **Question** When creating a job:
Can a job be anything?
should numbers or special char be allowed?
Is there a minim or max number of char?
> 
> **Answer:** Ainda Sem Resposta





### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US003 - Create a task category" as there must be at least one task category to classify the task being created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference
    * a designation 
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost
	
* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.