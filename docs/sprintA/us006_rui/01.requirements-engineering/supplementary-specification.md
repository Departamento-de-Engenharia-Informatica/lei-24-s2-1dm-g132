# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

- Business rules validation must be respected when registering or updating data.
- The application should use object serialization to ensure data persistence between two runs of the application.
- All those who wish to use the application must be authenticated with a password of seven alphanumeric characters, including three capital letters and two digits.

## Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

- The application must support the English language. All project artifacts must also be produced in English.

## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

(fill in here )

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

(fill in here )

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

- The class structure must be conceived to allow its easy maintenance and addition of new functionalities, considering the best practices of OO.
- The development team must implement unit tests for all methods, except for methods that implement Input/Output operations.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- TDD (Test-Driven Development) must be adopted.
- Adopt best practices for identifying requirements, and for OO software analysis and design.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- Application development in Java language using the IntelliJ IDE or NetBeans.
- Graphical interface must be developed in JavaFX 11.
- Adopt recognized coding standards (e.g., CamelCase).
- Use Javadoc to generate useful documentation for Java code.
- The unit tests should be implemented using the JUnit 5 framework. The JaCoCo plugin should be used to generate the coverage report.
- All the images/figures produced during the software development process should be recorded in SVG format.


### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )