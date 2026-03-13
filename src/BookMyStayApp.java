import java.util.*;

/**
 * =========================================================
 * CLASS - Reservation
 * =========================================================
 */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**
 * =========================================================
 * CLASS - BookingHistory
 * =========================================================
 *
 * Use Case 8: Booking History & Reporting
 */

class BookingHistory {

    /** List that stores confirmed reservations */
    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    /** Add confirmed reservation */
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    /** Return reservation list */
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/**
 * =========================================================
 * CLASS - BookingReportService
 * =========================================================
 */

class BookingReportService {

    /** Generate booking report */
    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            ", Room Type: " + r.getRoomType()
            );
        }
    }
}

/**
 * =========================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * =========================================================
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history);
    }
}