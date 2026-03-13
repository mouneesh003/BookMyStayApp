import java.util.LinkedList;
import java.util.Queue;

/**
 * =========================================================
 * CLASS - Reservation
 * =========================================================
 *
 * Represents a booking request made by a guest.
 */

class Reservation {

    /** Name of the guest making the booking */
    private String guestName;

    /** Requested room type */
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
 * CLASS - BookingRequestQueue
 * =========================================================
 *
 * Manages booking requests using FIFO queue.
 */

class BookingRequestQueue {

    /** Queue that stores booking requests */
    private Queue<Reservation> requestQueue;

    /** Initializes empty queue */
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /** Add booking request */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    /** Retrieve next booking request */
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    /** Check if queue has pending requests */
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

/**
 * =========================================================
 * MAIN CLASS - UseCase5BookingRequestQueue
 * =========================================================
 *
 * Use Case 5: Booking Request (FIFO)
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Display header
        System.out.println("Booking Request Queue");

        // Initialize queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process requests FIFO
        while (bookingQueue.hasPendingRequests()) {

            Reservation r = bookingQueue.getNextRequest();

            System.out.println(
                    "Processing booking for Guest: "
                            + r.getGuestName()
                            + ", Room Type: "
                            + r.getRoomType()
            );
        }
    }
}