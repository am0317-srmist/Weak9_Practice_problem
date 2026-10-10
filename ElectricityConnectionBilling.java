import java.util.Scanner;

abstract class Connection {
    protected final double units;
    public Connection(double units) { this.units = units; }
    public abstract double calculateBill();
}

class HomeConnection extends Connection {
    public HomeConnection(double units) { super(units); }
    @Override public double calculateBill() {
        return Math.min(units, 100) * 5.0 + Math.max(0, units - 100) * 7.0;
    }
}

class ShopConnection extends Connection {
    public ShopConnection(double units) { super(units); }
    @Override public double calculateBill() { return units * 8.0 + 100.0; }
}

class FactoryConnection extends Connection {
    public FactoryConnection(double units) { super(units); }
    @Override public double calculateBill() { return Math.max(1000.0, units * 6.0); }
}

public class ElectricityConnectionBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String type = p[0];
            double units = Double.parseDouble(p[1]);

            Connection connection;
            switch (type) {
                case "HOME": connection = new HomeConnection(units); break;
                case "SHOP": connection = new ShopConnection(units); break;
                case "FACTORY": connection = new FactoryConnection(units); break;
                default: throw new IllegalArgumentException("Invalid connection type: " + type);
            }

            double bill = connection.calculateBill();
            System.out.printf("%s: %.2f%n", type, bill);
            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
