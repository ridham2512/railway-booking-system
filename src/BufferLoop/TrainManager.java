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
        for (int i = 0; i < 5; i++) T[i] = new Train1();
        T[0].trainNo = 101; T[0].trainName = "Rajdhani Express";
        T[1].trainNo = 102; T[1].trainName = "Shatabdi Express";
        T[2].trainNo = 103; T[2].trainName = "Duronto Express";
        T[3].trainNo = 104; T[3].trainName = "Garib Rath";
        T[4].trainNo = 105; T[4].trainName = "Humsafar Express";
    }

    void bookTrain() {
        System.out.println("Available Trains:");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + T[i].trainName + " (Train No: " + T[i].trainNo + ")");
        }
        System.out.print("Choose train (1-5): ");
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            heads[choice] = obj.book_ticket(heads[choice], seats[choice]);
        }
    }

    void displayAllPassengers() {
        for (int i = 0; i < 5; i++) {
            System.out.println("\nPassengers in " + T[i].trainName + ":");
            obj.display_passenger(heads[i]);
        }
    }

    void cancel() {
        System.out.println("Choose train (1-5) to cancel from:");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + T[i].trainName);
        }
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            heads[choice] = obj.cancelBooking(heads[choice], seats[choice]);
        }
    }

    // ---------------- Search passenger ----------------
    void searchPassenger() {
        System.out.println("Choose train (1-5) to search in:");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + T[i].trainName);
        }
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            System.out.print("Enter Registration Number: ");
            int regNo = sc.nextInt();
            obj.searchByRegNo(heads[choice], regNo);
        }
    }

    // ---------------- Seat availability ----------------
    void showAvailability() {
        System.out.println("Choose train (1-5):");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + T[i].trainName);
        }
        int choice = sc.nextInt() - 1;
        if (choice >= 0 && choice < 5) {
            seats[choice].showAvailabilitySummary();
        }
    }
}
