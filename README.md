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
│       └── Railway.java
├── .gitignore
└── README.md
```

## How to Run

### From terminal

```bash
cd src
javac BufferLoop/Railway.java
java BufferLoop.Railway
```

### From an IDE (IntelliJ / Eclipse / VS Code)

1. Open this folder as a project, with `src` marked as the source root.
2. Run `BufferLoop/Railway.java` (the `main` method is in the `Railway` class).

## Design Notes (for viva / walkthrough)

- **`passNode`** — a linked list node holding one passenger's details. Passengers per train are stored as a singly linked list (`head` pointer per train).
- **`seatMatrix`** — holds a 4x5 seat layout (`int[][] seat`), a `HashSet<Integer>` of currently available seat numbers, a `HashMap` mapping grid position to seat number, and a `Queue` for waiting passengers.
- **Booking** — removes the chosen seat number from the `HashSet` and grid; if no seats are free, the passenger is enqueued in `waiting`.
- **Cancellation** — removes the passenger node from the linked list, frees the seat, and calls `waitingToConfirm`, which either returns the seat to the pool or promotes the next waiting passenger.
- **Fare Calculation** — `FareCalculator.getFare(class)` is a simple lookup table (switch-case) returning a fixed base price per travel class.
- **Search by Reg. No.** — a linear search (`O(n)`) over the passenger linked list comparing `Reg_no`.
- **Seat Availability Summary** — reads directly off the existing `HashSet` size and `Queue` size, no extra bookkeeping needed.

## Sample Trains

| No. | Name              |
|-----|-------------------|
| 101 | Rajdhani Express  |
| 102 | Shatabdi Express  |
| 103 | Duronto Express   |
| 104 | Garib Rath        |
| 105 | Humsafar Express  |
