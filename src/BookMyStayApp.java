import java.util.*;

/**
 * =========================================================
 * CLASS - RoomInventory
 * =========================================================
 */

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * =========================================================
 * CLASS - CancellationService
 * =========================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 */

class CancellationService {

    /** Stack that stores recently released room IDs */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type */
    private Map<String, String> reservationToRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationToRoomTypeMap = new HashMap<>();
    }

    /** Register confirmed booking */
    public void registerBooking(String reservationId, String roomType) {
        reservationToRoomTypeMap.put(reservationId, roomType);
    }

    /** Cancel booking and restore inventory */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationToRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID.");
            return;
        }

        String roomType = reservationToRoomTypeMap.get(reservationId);

        releasedRoomIds.push(reservationId);

        Map<String, Integer> availability = inventory.getRoomAvailability();

        inventory.updateAvailability(roomType, availability.get(roomType) + 1);

        System.out.println(
                "Booking cancelled successfully. Inventory restored for room type: "
                        + roomType
        );
    }

    /** Display rollback history */
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        for (String id : releasedRoomIds) {
            System.out.println("Released Reservation ID: " + id);
        }
    }
}

/**
 * =========================================================
 * MAIN CLASS - UseCase10BookingCancellation
 * =========================================================
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        String reservationId = "Single-1";
        String roomType = "Single";

        cancellationService.registerBooking(reservationId, roomType);

        cancellationService.cancelBooking(reservationId, inventory);

        cancellationService.showRollbackHistory();

        System.out.println(
                "\nUpdated Single Room Availability: "
                        + inventory.getRoomAvailability().get("Single")
        );
    }
}