package BufferLoop;

import java.util.*;

// ==================== Train class ====================
class Train1 {
    int trainNo;
    String trainName;
    String source, destination;
    String startTime, endTime;
    float price;
    int count;

    Train1() {
        count = 0;
    }
}

// ==================== Passenger Node class ====================
class passNode {
    int Reg_no;
    String fName, lName;
    int age;
    String gender;
    String train_class;
    String date;
    int seatNo;
    float fare;        
    passNode next;
}

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

// ==================== Fare calculator  ====================
class FareCalculator {
    static float getFare(String travelClass) {
        switch (travelClass) {
            case "Sleeper": return 500f;
            case "1st AC":  return 2000f;
            case "2nd AC":  return 1500f;
            case "3rd AC":  return 1000f;
            default:        return 300f;   
        }
    }
}

// ==================== Booking operations ====================
class operations1 {
    Scanner sc = new Scanner(System.in);

    passNode book_ticket(passNode head, seatMatrix getSeat) {
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
            case 2: newNode.train_class = "1st AC"; break;
            case 3: newNode.train_class = "2nd AC"; break;
            case 4: newNode.train_class = "3rd AC"; break;
            default: newNode.train_class = "General"; break;
        }

        // ----------------fare gets calculated right after class is chosen ----------------
        newNode.fare = FareCalculator.getFare(newNode.train_class);

        while (true) {
            System.out.print("Enter date (dd/mm/yyyy): ");
            newNode.date = sc.next();
            if (newNode.date.matches("^\\d{2}/\\d{2}/\\d{4}$")) break;
            System.out.println("Invalid format. Try again.");
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
        System.out.println("Name: " + newNode.fName + " " + newNode.lName);
        System.out.println("Age: " + newNode.age);
        System.out.println("Gender: " + newNode.gender);
        System.out.println("Reg. No.: " + newNode.Reg_no);
        System.out.println("Date: " + newNode.date);
        System.out.println("Class: " + newNode.train_class);
        System.out.println("Seat No.: " + newNode.seatNo);
        System.out.println("Fare: Rs. " + newNode.fare); 
        System.out.println("------------------");

        if (head == null) return newNode;
        passNode ptr = head;
        while (ptr.next != null) ptr = ptr.next;
        ptr.next = newNode;
        return head;
    }

    void display_passenger(passNode head) {
        if (head == null) {
            System.out.println("No passengers found.");
            return;
        }
        passNode ptr = head;
        while (ptr != null) {
            System.out.println("\nName: " + ptr.fName + " " + ptr.lName);
            System.out.println("Age: " + ptr.age + ", Gender: " + ptr.gender);
            System.out.println("Reg. No.: " + ptr.Reg_no + ", Date: " + ptr.date);
            System.out.println("Class: " + ptr.train_class + ", Seat: " + ptr.seatNo);
            System.out.println("Fare: Rs. " + ptr.fare);
            ptr = ptr.next;
        }
    }

    // ---------------- NEW FEATURE 2: Search passenger by registration number ----------------
    void searchByRegNo(passNode head, int regNo) {
        passNode ptr = head;
        while (ptr != null) {
            if (ptr.Reg_no == regNo) {
                System.out.println("\n----- Passenger Found -----");
                System.out.println("Name: " + ptr.fName + " " + ptr.lName);
                System.out.println("Age: " + ptr.age + ", Gender: " + ptr.gender);
                System.out.println("Date: " + ptr.date + ", Class: " + ptr.train_class);
                System.out.println("Seat No.: " + ptr.seatNo + ", Fare: Rs. " + ptr.fare);
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
