import java.util.Scanner;

abstract class Staff {
    protected final String name;
    public Staff(String name) { this.name = name; }
    public abstract double calculatePay();
}

class FullTimeStaff extends Staff {
    private final double weeklySalary;
    public FullTimeStaff(String name, double weeklySalary) { super(name); this.weeklySalary = weeklySalary; }
    @Override public double calculatePay() { return weeklySalary; }
}

class HourlyStaff extends Staff {
    private final double hours, rate;
    public HourlyStaff(String name, double hours, double rate) { super(name); this.hours = hours; this.rate = rate; }
    @Override public double calculatePay() {
        return Math.min(hours, 40) * rate + Math.max(0, hours - 40) * rate * 1.5;
    }
}

class InternStaff extends Staff {
    private final double stipend;
    public InternStaff(String name, double stipend) { super(name); this.stipend = stipend; }
    @Override public double calculatePay() { return stipend; }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double totalPayroll = 0.0;

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            Staff staff;
            switch (p[0]) {
                case "FULLTIME":
                    staff = new FullTimeStaff(p[1], Double.parseDouble(p[2]));
                    break;
                case "HOURLY":
                    staff = new HourlyStaff(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
                    break;
                case "INTERN":
                    staff = new InternStaff(p[1], Double.parseDouble(p[2]));
                    break;
                default: throw new IllegalArgumentException("Invalid staff type: " + p[0]);
            }

            double pay = staff.calculatePay();
            System.out.printf("%s: %.2f%n", staff.name, pay);
            totalPayroll += pay;
        }

        System.out.printf("Total Payroll: %.2f%n", totalPayroll);
        sc.close();
    }
}
