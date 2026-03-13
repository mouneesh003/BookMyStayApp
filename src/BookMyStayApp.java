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
 * CLASS - RoomAllocationService
 * =========================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 */

class RoomAllocationService {

    /** Stores all allocated room IDs */
    private Set<String> allocatedRoomIds;

    /** Stores assigned room IDs by room type */
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {

        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();

        assignedRoomsByType.put("Single", new HashSet<>());
        assignedRoomsByType.put("Double", new HashSet<>());
        assignedRoomsByType.put("Suite", new HashSet<>());
    }

    /**
     * Confirms booking request and allocates room
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) > 0) {

            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);
            assignedRoomsByType.get(roomType).add(roomId);

            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            System.out.println(
                    "Booking confirmed for Guest: "
                            + reservation.getGuestName()
                            + ", Room ID: "
                            + roomId
            );

        } else {
            System.out.println("No rooms available for " + roomType);
        }
    }

    /**
     * Generate unique room ID
     */
    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType.get(roomType).size() + 1;
        return roomType + "-" + count;
    }
}

/**
 * =========================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * =========================================================
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Single");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        allocationService.allocateRoom(r1, inventory);
        allocationService.allocateRoom(r2, inventory);
        allocationService.allocateRoom(r3, inventory);
    }
}