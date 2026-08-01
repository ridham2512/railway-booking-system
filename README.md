# Railway Registration System

A console-based Railway ticket booking system built in Java. Demonstrates core data structures: **Linked List** (passenger records), **HashSet** (available seats), **HashMap** (seat-index mapping), and **Queue** (waiting list).

## Features

1. Book a ticket (choose train, class, seat)
2. Display all passengers across all trains
3. Cancel a booking (auto-promotes a waiting passenger if any)
4. Search passenger by Registration Number
5. Show seat availability summary (booked / available / waiting)
6. Exit

## Project Structure

```
railway-booking-system/
├── src/
│   └── BufferLoop/
│       ├── Railway.java            ← main() entry point
│       ├── Train.java              ← Train1 model
│       ├── PassengerNode.java      ← passNode linked-list node
│       ├── SeatMatrix.java         ← seat grid, availability, waiting queue
│       ├── FareCalculator.java     ← static fare lookup by travel class
│       ├── BookingOperations.java  ← book, cancel, search, display logic
│       └── TrainManager.java       ← train list + menu delegation
├── .gitignore
└── README.md
```

## How to Run

### From terminal

```bash
# Compile all files in the package at once
javac src/BufferLoop/*.java

# Run
java -cp src BufferLoop.Railway
```

### From an IDE (IntelliJ / Eclipse / VS Code)

1. Open this folder as a project with `src` marked as the source root.
2. Run `Railway.java` — the `main` method is in the `Railway` class.

## Date Validation

Travel dates are strictly validated during booking:

| Input        | Error                                              |
|--------------|----------------------------------------------------|
| `30/13/2025` | Invalid month — must be 01–12                      |
| `12/32/2005` | Invalid day — does not exist in that month         |
| `29/02/2025` | Leap-year violation — Feb 29 invalid in 2025       |
| `01-08-2025` | Wrong format — must be `dd/mm/yyyy`                |
| `15/08/2024` | Past date — only future dates accepted             |
| `15/08/2026` | ✅ Accepted                                         |

## Fare Chart

| Class   | Fare (₹) |
|---------|----------|
| Sleeper | 500      |
| 3rd AC  | 1000     |
| 2nd AC  | 1500     |
| 1st AC  | 2000     |
| General | 300      |

## Sample Trains

| No. | Name              |
|-----|-------------------|
| 101 | Rajdhani Express  |
| 102 | Shatabdi Express  |
| 103 | Duronto Express   |
| 104 | Garib Rath        |
| 105 | Humsafar Express  |

## Design Notes (for viva / walkthrough)

- **`passNode`** — a linked list node holding one passenger's details. Passengers per train are stored as a singly linked list (`head` pointer per train).
- **`seatMatrix`** — holds a 4×5 seat layout (`int[][] seat`), a `HashSet<Integer>` of currently available seat numbers, a `HashMap` mapping grid position to seat number, and a `Queue` for waiting passengers.
- **Booking** — removes the chosen seat number from the `HashSet` and grid; if no seats are free, the passenger is enqueued in `waiting`.
- **Cancellation** — removes the passenger node from the linked list, frees the seat, and calls `waitingToConfirm`, which either returns the seat to the pool or promotes the next waiting passenger.
- **Fare Calculation** — `FareCalculator.getFare(class)` is a simple lookup table (switch-case) returning a fixed base price per travel class.
- **Search by Reg. No.** — a linear search (`O(n)`) over the passenger linked list comparing `Reg_no`.
- **Seat Availability Summary** — reads directly off the existing `HashSet` size and `Queue` size; no extra bookkeeping needed.
- **Date Validation** — uses `LocalDate.parse()` with `DateTimeFormatter` to enforce format, valid calendar dates, and future-only travel dates.
