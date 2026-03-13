import java.util.*;

/**
 * =========================================================
 * CLASS - AddOnService
 * =========================================================
 *
 * Use Case 7: Add-On Service Selection
 */

class AddOnService {

    /** Name of the service */
    private String serviceName;

    /** Cost of the service */
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * =========================================================
 * CLASS - AddOnServiceManager
 * =========================================================
 */

class AddOnServiceManager {

    /**
     * Maps reservation ID to selected services
     * Key -> Reservation ID
     * Value -> List of services
     */
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    /** Attach service to reservation */
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    /** Calculate total add-on cost */
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddOnService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
}

/**
 * =========================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * =========================================================
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1000);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}