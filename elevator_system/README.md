# Elevator System - Low Level Design (LLD)


## Design Overview (UML Diagram)

```mermaid
classDiagram
    class Building {
        - static Building instance$
        - List~Floor~ floors
        - List~Elevator~ elevators
        - ElevatorController controller
        + getInstance(floors, lifts)$ Building
    }

    class Elevator {
        - String id
        - int currentFloor
        - Direction currentDirection
        - ElevatorStatus status
        - Door door
        - InternalPanel internalPanel
        - int maxCapacity
        - TreeSet~Integer~ upRequests
        - TreeSet~Integer~ downRequests
        + addInternalRequest(request)
        + addExternalRequest(floor, direction)
        + move()
        + stopAtFloor()
        + triggerAlarm(msg)
    }

    class Floor {
        - int floorNumber
        - ExternalPanel externalPanel
    }

    class ElevatorController {
        - List~Elevator~ elevators
        - ElevatorAssignmentStrategy strategy
        + addExternalRequest(request)
    }

    class ElevatorAssignmentStrategy {
        <<interface>>
        + findBestElevator(elevators, request) Elevator
    }

    class NearestIdleStrategy {
        + findBestElevator() Elevator
    }

    class Request {
        <<abstract>>
        - int floor
        - boolean isServed
    }

    class ExternalRequest {
        - Direction requestedDirection
    }

    class InternalRequest { }

    class ExternalPanel {
        - int floorNumber
        - ElevatorController controller
        + pressUp()
        + pressDown()
    }

    class InternalPanel {
        - Elevator elevator
        + selectFloor(floor)
    }

    class Door {
        - DoorStatus status
        - boolean obstructionDetected
        + open()
        + close()
    }

    Building *-- Floor
    Building *-- Elevator
    Building *-- ElevatorController
    Elevator *-- Door
    Floor *-- ExternalPanel
    Elevator *-- InternalPanel
    ElevatorController o-- ElevatorAssignmentStrategy
    ElevatorAssignmentStrategy <|.. NearestIdleStrategy
    Request <|-- ExternalRequest
    Request <|-- InternalRequest
    ExternalPanel ..> ElevatorController : notifies
```


## Design Patterns Used

1. **Singleton Pattern**: The `Building` class ensures there's only one instance of the entire system structure.
2. **Strategy Pattern**: Used for `ElevatorAssignmentStrategy` to find the best lift for a call dynamically.

