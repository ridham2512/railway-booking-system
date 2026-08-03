package BufferLoop;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// ==================== Booking operations ====================
class operations1 {
    Scanner sc = new Scanner(System.in);

    passNode book_ticket(passNode head, seatMatrix getSeat, Train1 train) {
        passNode newNode = new passNode();
        newNode.next = null;

        while (true) {
            System.out.print("Enter first name: ");
            newNode.fName = sc.next();
            if (newNode.fName.matches("^[a-zA-Z]*$")) break;
            System.out.println("Name should contain only alphabets.");
        }

        while (true) {
            System.out.print("Enter last name: ");
            newNode.lName = sc.next();
            if (newNode.lName.matches("^[a-zA-Z]*$")) break;
            System.out.println("Name should contain only alphabets.");
        }

        System.out.print("Age: ");
        newNode.age = sc.nextInt();

        do {
            System.out.print("Enter gender (F/M): ");
            newNode.gender = sc.next();
        } while (!(newNode.gender.equalsIgnoreCase("F") || newNode.gender.equalsIgnoreCase("M")));

        newNode.Reg_no = (int) (Math.random() * 9999);

        System.out.println("Select train class:");
        System.out.println("1. Sleeper");
        System.out.println("2. 1st AC");
        System.out.println("3. 2nd AC");
        System.out.println("4. 3rd AC");

        switch (sc.nextInt()) {
            case 1: newNode.train_class = "Sleeper"; break;
            case 2: newNode.train_class = "1st AC";  break;
            case 3: newNode.train_class = "2nd AC";  break;
            case 4: newNode.train_class = "3rd AC";  break;
            default: newNode.train_class = "General"; break;
        }

        newNode.fare = FareCalculator.getFare(newNode.train_class);

        newNode.trainName     = train.trainName;
        newNode.source        = train.source;
        newNode.destination   = train.destination;
        newNode.departureTime = train.startTime;
        newNode.arrivalTime   = train.endTime;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Enter date (dd/mm/yyyy): ");
            String input = sc.next();

            if (!input.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                System.out.println("Invalid format. Use dd/mm/yyyy (e.g. 15/08/2025).");
                continue;
            }

            try {
                LocalDate parsed = LocalDate.parse(input, dtf);

                if (parsed.isBefore(LocalDate.now())) {
                    System.out.println("Date cannot be in the past. Enter a future date.");
                    continue;
                }

                newNode.date = input;
                break;

            } catch (DateTimeParseException e) {
                String[] parts = input.split("/");
                int d = Integer.parseInt(parts[0]);
                int m = Integer.parseInt(parts[1]);

                if (m < 1 || m > 12) {
                    System.out.println("Invalid month '" + m + "'. Month must be between 01 and 12.");
                } else if (d < 1 || d > 31) {
                    System.out.println("Invalid day '" + d + "'. Day must be between 01 and 31.");
                } else {
                    System.out.println("Invalid date '" + input + "' (e.g. day does not exist in that month / leap-year issue).");
                }
            }
        }

        if (getSeat.set.isEmpty()) {
            System.out.println("No seats available. Added to waiting list.");
            newNode.seatNo = 0;
            getSeat.waiting.add(newNode);
        } else {
            getSeat.display();
            System.out.println("Note: 0 = booked seat");
            int seat1;
            while (true) {
                System.out.print("Enter seat number to book: ");
                seat1 = sc.nextInt();
                if (getSeat.set.contains(seat1)) break;
                System.out.println("Invalid or already booked.");
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    if (getSeat.seat[i][j] == seat1) {
                        getSeat.seat[i][j] = 0;
                        break;
                    }
                }
            }
            getSeat.set.remove(seat1);
            newNode.seatNo = seat1;
        }

        System.out.println("\n----- Ticket -----");
        System.out.println("Train     : " + newNode.trainName);
        System.out.println("Route     : " + newNode.source + " -> " + newNode.destination);
        System.out.println("Departure : " + newNode.departureTime + "  Arrival: " + newNode.arrivalTime);
        System.out.println("Name      : " + newNode.fName + " " + newNode.lName);
        System.out.println("Age       : " + newNode.age);
        System.out.println("Gender    : " + newNode.gender);
        System.out.println("Reg. No.  : " + newNode.Reg_no);
        System.out.println("Date      : " + newNode.date);
        System.out.println("Class     : " + newNode.train_class);
        System.out.println("Seat No.  : " + newNode.seatNo);
        System.out.println("Fare      : Rs. " + newNode.fare);
        System.out.println("------------------");

        if (head == null) return newNode;
        passNode ptr = head;
        while (ptr.next != null) ptr = ptr.next;
        ptr.next = newNode;
        return head;
    }

    void display_passenger(passNode head) {
        if (head == null) {
            System.out.println("  No passengers found.");
            return;
        }
        passNode ptr = head;
        while (ptr != null) {
            System.out.println("  Name      : " + ptr.fName + " " + ptr.lName);
            System.out.println("  Age       : " + ptr.age + "  Gender: " + ptr.gender);
            System.out.println("  Route     : " + ptr.source + " -> " + ptr.destination);
            System.out.println("  Departure : " + ptr.departureTime + "  Arrival: " + ptr.arrivalTime);
            System.out.println("  Reg. No.  : " + ptr.Reg_no + "  Date: " + ptr.date);
            System.out.println("  Class     : " + ptr.train_class + "  Seat: " + ptr.seatNo);
            System.out.println("  Fare      : Rs. " + ptr.fare);
            System.out.println("  ----------");
            ptr = ptr.next;
        }
    }

    // --------------- Search passenger by registration number ----------------
    void searchByRegNo(passNode head, int regNo) {
        passNode ptr = head;
        while (ptr != null) {
            if (ptr.Reg_no == regNo) {
                System.out.println("\n----- Passenger Found -----");
                System.out.println("Train     : " + ptr.trainName);
                System.out.println("Route     : " + ptr.source + " -> " + ptr.destination);
                System.out.println("Departure : " + ptr.departureTime + "  Arrival: " + ptr.arrivalTime);
                System.out.println("Name      : " + ptr.fName + " " + ptr.lName);
                System.out.println("Age       : " + ptr.age + "  Gender: " + ptr.gender);
                System.out.println("Date      : " + ptr.date + "  Class: " + ptr.train_class);
                System.out.println("Seat No.  : " + ptr.seatNo + "  Fare: Rs. " + ptr.fare);
                System.out.println("----------------------------");
                return;
            }
            ptr = ptr.next;
        }
        System.out.println("No passenger found with Registration No: " + regNo);
    }

    passNode cancelBooking(passNode head, seatMatrix q) {
        if (head == null) {
            System.out.println("No bookings to cancel.");
            return null;
        }
        System.out.print("Enter seat number to cancel: ");
        int seatNum = sc.nextInt();
        passNode temp = head, prev = null;

        if (temp != null && temp.seatNo == seatNum) {
            head = temp.next;
            System.out.println("Booking cancelled.");
            waitingToConfirm(seatNum, q, head);
            return head;
        }

        while (temp != null && temp.seatNo != seatNum) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Seat not found.");
            return head;
        }

        prev.next = temp.next;
        System.out.println("Booking cancelled.");
        waitingToConfirm(seatNum, q, head);
        return head;
    }

    void waitingToConfirm(int seatNumber, seatMatrix q, passNode head) {
        if (q.waiting.isEmpty()) {
            for (Map.Entry<String, Integer> entry : q.map.entrySet()) {
                if (entry.getValue() == seatNumber) {
                    String[] idx = entry.getKey().split(",");
                    int i = Integer.parseInt(idx[0]);
                    int j = Integer.parseInt(idx[1]);
                    q.seat[i][j] = seatNumber;
                    break;
                }
            }
            q.set.add(seatNumber);
        } else {
            passNode wait = q.waiting.poll();
            wait.seatNo = seatNumber;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 5; j++) {
                    if (q.seat[i][j] == seatNumber) {
                        q.seat[i][j] = 0;
                        break;
                    }
                }
            }
            if (head == null) {
                head = wait;
            } else {
                passNode ptr = head;
                while (ptr.next != null) ptr = ptr.next;
                ptr.next = wait;
            }
            System.out.println("Waiting passenger assigned to seat: " + seatNumber);
        }
    }
}
