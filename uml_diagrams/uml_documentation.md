[this is placeholder stuff mostly, so I can remember the formatting]
# Cardiovascular Health Monitoring System UML Diagrams

## Alert Generation System

### Class Diagrams
#### Alert Generation System
This UML class diagram illustrates the architecture of the Alert Generation System. It includes several key classes such as Alert, AlertCondition, AlertAction, and AlertGenerator. The Alert class represents the alert itself, containing information about the alert type and status. The AlertCondition class defines the conditions under which an alert is triggered, while the AlertAction class specifies the actions to be taken when an alert condition is met. The AlertGenerator class coordinates the evaluation of alert conditions and triggers the corresponding actions. Relationships among these classes, including associations and dependencies, are also depicted, showcasing how they interact to implement the alert generation functionality.

#### Data Access Layer
The Data Access Layer (DAL) class diagram provides a detailed view of how data is accessed and managed within the system. Key classes include FileDataReader, DataStorage, and DataAccessObject. The FileDataReader class is responsible for reading data from files, converting it into a format usable by the system. The DataStorage class manages the storage of this data, ensuring it is organized and accessible. The DataAccessObject class acts as an intermediary, providing an interface for other system components to interact with the data storage. This diagram highlights the interactions between these classes, emphasizing how data is read, stored, and retrieved efficiently.

#### Data Storage Layer
The Data Storage Layer class diagram focuses on the storage mechanisms used in the system. It includes classes such as PatientRecord, DataStorage, and FileHandler. The PatientRecord class represents the data related to a patient, including personal and medical information. The DataStorage class oversees the overall storage structure, while the FileHandler class handles the reading and writing of data files. The relationships between these classes demonstrate how patient data is systematically stored and managed. Associations and dependencies are illustrated to show how data is maintained and accessed, ensuring data integrity and accessibility.

#### Patient Identification System
This class diagram details the Patient Identification System, showcasing classes like Patient, PatientIdentifier, and IdentificationService. The Patient class stores individual patient information, while the PatientIdentifier class handles the generation and management of unique patient IDs. The IdentificationService class provides functionalities for identifying and verifying patients based on their IDs. The diagram outlines how these classes interact, highlighting the process of patient identification and verification within the system. It shows associations and dependencies, illustrating the flow of data and the coordination among components to ensure accurate patient identification.


### Sequence Diagram
Description of the Alert Generation System sequence diagram.
- **AlertGenerator**: Monitors incoming patient data and generates alerts.
    - Methods: `evaluateData(PatientData data)`, `triggerAlert(Alert alert)`
- **Alert**: Represents an alert with patient ID, condition, and timestamp.
    - Attributes: `patientId`, `condition`, `timestamp`

#### Alert Generator System
The sequence diagram for the Alert Generator System provides a dynamic view of the interactions between 
objects over time. It includes the HealthDataSimulator, AlertGenerator, AlertCondition, and AlertAction 
objects. The diagram starts with the HealthDataSimulator generating health data, which is then passed 
to the AlertGenerator. The AlertGenerator evaluates this data against various AlertCondition objects 
to determine if any conditions are met. If a condition is satisfied, the AlertAction object is triggered 
to execute the corresponding actions. This diagram captures the flow of messages and the order of 
interactions, highlighting how alerts are generated in response to simulated health data.

### State Diagram
#### Alert Generator System
The state diagram for the Alert Generation System depicts the different states an alert can be in and 
the transitions between these states. Key states include Inactive, Active, Triggered, and Acknowledged. 
The diagram shows that an alert starts in the Inactive state, and when a condition is met, it transitions 
to the Active state. If the alert is triggered, it moves to the Triggered state, and once it is 
acknowledged, it transitions to the Acknowledged state. This diagram illustrates the lifecycle of
an alert, the events causing state changes, and the conditions under which these transitions occur, 
providing a clear understanding of alert management within the system.
