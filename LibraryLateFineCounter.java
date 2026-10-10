import java.util.Scanner;

abstract class LibraryItem {
    protected final String title;
    protected final int daysLate;
    public LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
    public abstract double calculateFine();
}

class BookItem extends LibraryItem {
    public BookItem(String title, int daysLate) { super(title, daysLate); }
    @Override public double calculateFine() { return Math.max(0, daysLate) * 2.0; }
}

class DVDItem extends LibraryItem {
    public DVDItem(String title, int daysLate) { super(title, daysLate); }
    @Override public double calculateFine() { return Math.min(50.0, Math.max(0, daysLate) * 5.0); }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title, int daysLate) { super(title, daysLate); }
    @Override public double calculateFine() { return Math.max(0, daysLate); }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double totalFines = 0.0;

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String type = p[0], title = p[1];
            int daysLate = Integer.parseInt(p[2]);

            LibraryItem item;
            switch (type) {
                case "BOOK": item = new BookItem(title, daysLate); break;
                case "DVD": item = new DVDItem(title, daysLate); break;
                case "MAGAZINE": item = new MagazineItem(title, daysLate); break;
                default: throw new IllegalArgumentException("Invalid item type: " + type);
            }

            double fine = item.calculateFine();
            System.out.printf("%s: %.2f%n", title, fine);
            totalFines += fine;
        }

        System.out.printf("Total Fines: %.2f%n", totalFines);
        sc.close();
    }
}
