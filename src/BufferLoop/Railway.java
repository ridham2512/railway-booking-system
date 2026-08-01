package BufferLoop;

import java.util.*;

// ==================== Main class ====================
public class Railway {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        operation2 op = new operation2();

        while (true) {
            System.out.println("\n--- Railway Registration System ---");
            System.out.println("1. Book Train");
            System.out.println("2. Display All Passengers");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Search Passenger by Reg. No.");
            System.out.println("5. Show Seat Availability");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            switch (sc.nextInt()) {
                case 1: op.bookTrain(); break;
                case 2: op.displayAllPassengers(); break;
                case 3: op.cancel(); break;
                case 4: op.searchPassenger(); break;
                case 5: op.showAvailability(); break;
                case 6: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
