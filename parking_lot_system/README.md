# Parking Lot System - Low Level Design (LLD)

```mermaid
classDiagram
    class ParkingLot {
        - ParkingLot instance$
        - List~Floor~ floors
        - List~EntryGate~ entryGates
        - List~ExitGate~ exitGates
        - DisplayBoard displayBoard
        + getInstance()$ ParkingLot
        + init(floors, slotsPerFloor, etc)
        + getAvailableSlots(type) int
        + displayStatus()
    }

    class Floor {
        - int floorNumber
        - List~Slot~ slots
        + getSlots() List
    }

    class Slot {
        <<abstract>>
        - String slotId
        - SlotType slotType
        - double price
        - boolean isAvailable
        - Vehicle parkedVehicle
        - Map~String, Integer~ distanceFromEntryGates
        + assignVehicle(vehicle)
        + removeVehicle()
        + isAvailable() boolean
    }
    class SmallSlot { }
    class MediumSlot { }
    class LargeSlot { }

    Slot <|-- SmallSlot
    Slot <|-- MediumSlot
    Slot <|-- LargeSlot

    class Vehicle {
        <<abstract>>
        - String vehicleNumber
        - VehicleType type
    }
    class TwoWheeler { }
    class Car { }
    class HeavyVehicle { }

    Vehicle <|-- TwoWheeler
    Vehicle <|-- Car
    Vehicle <|-- HeavyVehicle

    class EntryGate {
        - String gateId
        + generateTicket(vehicle, strategy)
    }

    class ExitGate {
        - String gateId
        + processExit(ticket, strategy) Bill
    }

    class ParkingTicket {
        - String ticketId
        - Vehicle vehicle
        - Slot assignedSlot
        - LocalDateTime entryTime
        - String entryGateId
    }

    class Bill {
        - String billId
        - ParkingTicket ticket
        - LocalDateTime exitTime
        - double totalAmount
    }

    class SlotAssignmentStrategy {
        <<interface>>
        + assignSlot(vehicleType, gateId, parkingLot) Slot
    }

    class NearestSlotAssignmentStrategy {
        + assignSlot() Slot
    }
    SlotAssignmentStrategy <|-- NearestSlotAssignmentStrategy

    class BillingStrategy {
        <<interface>>
        + generateBill(ticket, exitTime) Bill
    }

    class TypeAndDurationBillingStrategy {
        + generateBill() Bill
    }
    BillingStrategy <|-- TypeAndDurationBillingStrategy

    class DisplayBoard {
        + showStatus(parkingLot)
    }

    ParkingLot *-- Floor
    Floor *-- Slot
    EntryGate ..> ParkingTicket : creates
    ExitGate ..> Bill : creates
    NearestSlotAssignmentStrategy ..> Slot : finds
```

**Design Patterns**:
- **Singleton**: The `ParkingLot` class holds unique instance-wide data.
- **Factory Method**: Vehicle and Slot object instantiation logic handles creation gracefully without bloating system configs.
- **Strategy Pattern**: Employed heavily for `SlotAssignmentStrategy` (calculating the nearest slot dynamically) and `BillingStrategy` (varying price factors depending on vehicle/duration).
- **Observer Pattern** (Hinted): `DisplayBoard` essentially acts as an observing module querying internal status across slots.
