package BufferLoop;

import java.util.*;

// ==================== Train manager class ====================
class operation2 {
    Scanner sc = new Scanner(System.in);
    Train1[] T = new Train1[5];
    operations1 obj = new operations1();
    passNode[] heads = new passNode[5];
    seatMatrix[] seats = {
            new seatMatrix(), new seatMatrix(),
            new seatMatrix(), new seatMatrix(),
            new seatMatrix()
    };

    operation2() {
        for (int i = 0; i < 5; i++)
            T[i] = new Train1();

        T[0].trainNo = 101;
        T[0].trainName = "Rajdhani Express";
        T[0].source = "New Delhi";
        T[0].destination = "Mumbai Central";
        T[0].startTime = "16:25";
        T[0].endTime = "08:15 (+1)";

        T[1].trainNo = 102;
        T[1].trainName = "Shatabdi Express";
        T[1].source = "New Delhi";
        T[1].destination = "Bhopal";
        T[1].startTime = "06:00";
        T[1].endTime = "14:00";

        T[2].trainNo = 103;
        T[2].trainName = "Duronto Express";
        T[2].source = "Howrah";
        T[2].destination = "Pune";
        T[2].startTime = "20:10";
        T[2].endTime = "05:30 (+1)";

        T[3].trainNo = 104;
        T[3].trainName = "Garib Rath";
        T[3].source = "Delhi Sarai Rohilla";
        T[3].destination = "Amritsar";
        T[3].startTime = "10:30";
        T[3].endTime = "18:45";

        T[4].trainNo = 105;
        T[4].trainName = "Humsafar Express";
        T[4].source = "Lucknow";
        T[4].destination = "Bengaluru";
        T[4].startTime = "23:00";
        T[4].endTime = "06:30 (+2)";
    }

    void bookTrain() {
        System.out.println("\nAvailable Trains:");
        System.out.println(String.format("%-4s %-25s %-10s %-22s %-22s %-8s %-8s",
                "No.", "Train Name", "Train No", "Source", "Destination", "Dep.", "Arr."));
        System.out.println("-".repeat(103));
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("%-4d %-25s %-10d %-22s %-22s %-8s %-8s",
                    (i + 1),
                    T[i].trainName,
                    T[i].trainNo,
                    T[i].source,
                    T[i].destination,
                    T[i].startTime,
                    T[i].endTime));
        }
        System.out.print("\nChoose train (1-5): ");
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            heads[choice] = obj.book_ticket(heads[choice], seats[choice], T[choice]);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    void displayAllPassengers() {
        for (int i = 0; i < 5; i++) {
            System.out.println("\nPassengers in " + T[i].trainName
                    + " [" + T[i].source + " -> " + T[i].destination
                    + " | Dep: " + T[i].startTime + " Arr: " + T[i].endTime + "]:");
            obj.display_passenger(heads[i]);
        }
    }

    void cancel() {
        System.out.println("\nChoose train (1-5) to cancel from:");
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("  %d. %-25s [%s -> %s | Dep: %s]",
                    (i + 1), T[i].trainName, T[i].source, T[i].destination, T[i].startTime));
        }
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            heads[choice] = obj.cancelBooking(heads[choice], seats[choice]);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // ---------------- Search passenger ----------------
    void searchPassenger() {
        System.out.println("\nChoose train (1-5) to search in:");
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("  %d. %-25s [%s -> %s | Dep: %s]",
                    (i + 1), T[i].trainName, T[i].source, T[i].destination, T[i].startTime));
        }
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            System.out.print("Enter Registration Number: ");
            int regNo = sc.nextInt();
            obj.searchByRegNo(heads[choice], regNo);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // ---------------- Seat availability ----------------
    void showAvailability() {
        System.out.println("\nChoose train (1-5):");
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("  %d. %-25s [%s -> %s | Dep: %s, Arr: %s]",
                    (i + 1), T[i].trainName, T[i].source, T[i].destination,
                    T[i].startTime, T[i].endTime));
        }
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            System.out.println("Train: " + T[choice].trainName
                    + " | " + T[choice].source + " -> " + T[choice].destination
                    + " | Dep: " + T[choice].startTime + " Arr: " + T[choice].endTime);
            seats[choice].showAvailabilitySummary();
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
