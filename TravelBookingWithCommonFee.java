import java.util.Scanner;

abstract class TravelBooking {
    private static final double BOOKING_FEE = 50.0;
    protected final double distanceKm;

    public TravelBooking(double distanceKm) { this.distanceKm = distanceKm; }
    protected abstract double calculateBaseFare();
    public final double calculateTotalFare() { return calculateBaseFare() + BOOKING_FEE; }
}

class BusBooking extends TravelBooking {
    public BusBooking(double distanceKm) { super(distanceKm); }
    @Override protected double calculateBaseFare() { return distanceKm * 2.0; }
}

class TrainBooking extends TravelBooking {
    public TrainBooking(double distanceKm) { super(distanceKm); }
    @Override protected double calculateBaseFare() { return distanceKm * 1.5; }
}

class FlightBooking extends TravelBooking {
    public FlightBooking(double distanceKm) { super(distanceKm); }
    @Override protected double calculateBaseFare() { return 2500.0 + distanceKm * 4.0; }
}

public class TravelBookingWithCommonFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String mode = p[0];
            double distance = Double.parseDouble(p[1]);

            TravelBooking booking;
            switch (mode) {
                case "BUS": booking = new BusBooking(distance); break;
                case "TRAIN": booking = new TrainBooking(distance); break;
                case "FLIGHT": booking = new FlightBooking(distance); break;
                default: throw new IllegalArgumentException("Invalid travel mode: " + mode);
            }

            System.out.printf("%s: %.2f%n", mode, booking.calculateTotalFare());
        }

        sc.close();
    }
}
