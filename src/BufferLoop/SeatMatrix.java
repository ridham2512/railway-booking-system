package BufferLoop;

import java.util.*;

// ==================== Seat matrix and booking system ====================
class seatMatrix {
    int[][] seat = new int[4][5];
    HashSet<Integer> set = new HashSet<>();
    HashMap<String, Integer> map = new HashMap<>();
    Queue<passNode> waiting = new LinkedList<>();

    seatMatrix() {
        int k = 11;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                seat[i][j] = k++;
            }
        }
        for (int i = 11; i < 31; i++) {
            set.add(i);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                map.put(i + "," + j, seat[i][j]);
            }
        }
    }

    void display() {
        System.out.println("U\tM\tL\tL\tU");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(seat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // ---------------- 3: Seat Availability Summary ----------------
    void showAvailabilitySummary() {
        int totalSeats = 20;
        int available = set.size();
        int booked = totalSeats - available;
        int waitingCount = waiting.size();

        System.out.println("\n----- Seat Availability -----");
        System.out.println("Total seats   : " + totalSeats);
        System.out.println("Booked seats  : " + booked);
        System.out.println("Available     : " + available);
        System.out.println("Waiting list  : " + waitingCount);
        System.out.println("------------------------------");
    }
}
