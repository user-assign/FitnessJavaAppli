import java.util.*;

public class FitnessClub {
    private Map<String, List<Booking>> bookings; // mapping of customer names to their bookings
    
    public FitnessClub() {
        this.bookings = new HashMap<>();
    }
    
    public boolean makeBooking(String customerName, String className, String day) {
        if (!this.bookings.containsKey(customerName)) {
            this.bookings.put(customerName, new ArrayList<>());
        }
        List<Booking> customerBookings = this.bookings.get(customerName);
        
        // check if the customer has already booked a class at the same day and time
        for (Booking booking : customerBookings) {
            if (booking.getDay().equals(day) && booking.getClassName().equals(className)) {
                System.out.println("Sorry, you have already booked a " + className + " class on " + day);
                return false;
            }
        }
        
        // check if there is available space in the class
        List<Booking> classBookings = getClassBookings(className, day);
        if (classBookings.size() >= 5) {
            System.out.println("Sorry, the " + className + " class on " + day + " is full");
            return false;
        }
        
        // make the booking
        Booking booking = new Booking(customerName, className, day);
        customerBookings.add(booking);
        classBookings.add(booking);
        System.out.println("Booking successful! You have booked a " + className + " class on " + day);
        return true;
    }
    
    private List<Booking> getClassBookings(String className, String day) {
        List<Booking> classBookings = new ArrayList<>();
        for (List<Booking> bookings : this.bookings.values()) {
            for (Booking booking : bookings) {
                if (booking.getClassName().equals(className) && booking.getDay().equals(day)) {
                    classBookings.add(booking);
                }
            }
        }
        return classBookings;
    }
    
    public void printBookings(String customerName) {
        if (!this.bookings.containsKey(customerName)) {
            System.out.println("No bookings found for " + customerName);
            return;
        }
        List<Booking> customerBookings = this.bookings.get(customerName);
        if (customerBookings.isEmpty()) {
            System.out.println("No bookings found for " + customerName);
            return;
        }
        System.out.println("Bookings for " + customerName + ":");
        for (Booking booking : customerBookings) {
            System.out.println(booking);
        }
    }
    
    public static void main(String[] args) {
        FitnessClub club = new FitnessClub();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the fitness club booking system");
            System.out.println("Enter your name:");
            String customerName = scanner.nextLine();
            System.out.println("Enter 'book' to make a booking, 'view' to view your bookings, or 'exit' to exit:");
            String input = scanner.nextLine();
            if (input.equals("book")) {
                System.out.println("Enter the name of the class you want to book:");
                String className = scanner.nextLine();
                System.out.println("Enter the day you want to book the class (Saturday or Sunday):");
                String day = scanner.nextLine();
                club.makeBooking(customerName, className, day);
                System.out.println("How would you rate the class you took on a scale of 1-5?");
                int rating = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                // TODO
                System.out.println("Thanks for rating the class!");
                // TODO: Store the rating in the booking object or in a separate ratings map
            } else if (input.equals("view")) {
                club.printBookings(customerName);
            } else if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid input, please try again");
            }
        }
        scanner.close();
    }
}

class Booking {
private String customerName;
private String className;
private String day;
public Booking(String customerName, String className, String day) {
    this.customerName = customerName;
    this.className = className;
    this.day = day;
}

public String getCustomerName() {
    return customerName;
}

public String getClassName() {
    return className;
}

public String getDay() {
    return day;
}

@Override
public String toString() {
    return "Class: " + this.className + " | Day: " + this.day;
}
}

class ClassType {
private String name;
private int price;
public ClassType(String name, int price) {
    this.name = name;
    this.price = price;
}

public String getName() {
    return name;
}

public int getPrice() {
    return price;
}
}

class ClassSchedule {
private String day;
private List<ClassType> classes;
public ClassSchedule(String day) {
    this.day = day;
    this.classes = new ArrayList<>();
}

public String getDay() {
    return day;
}

public List<ClassType> getClasses() {
    return classes;
}

public void addClass(ClassType classType) {
    this.classes.add(classType);
}
}