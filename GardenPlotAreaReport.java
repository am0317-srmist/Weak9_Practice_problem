import java.util.Scanner;

abstract class Plot {
    protected final String owner;

    public Plot(String owner) {
        this.owner = owner;
    }

    public abstract double calculateArea();
    public abstract String getShape();
}

class CirclePlot extends Plot {
    private final double radius;
    public CirclePlot(String owner, double radius) { super(owner); this.radius = radius; }
    @Override public double calculateArea() { return Math.PI * radius * radius; }
    @Override public String getShape() { return "CIRCLE"; }
}

class RectanglePlot extends Plot {
    private final double length, width;
    public RectanglePlot(String owner, double length, double width) {
        super(owner); this.length = length; this.width = width;
    }
    @Override public double calculateArea() { return length * width; }
    @Override public String getShape() { return "RECTANGLE"; }
}

class TrianglePlot extends Plot {
    private final double base, height;
    public TrianglePlot(String owner, double base, double height) {
        super(owner); this.base = base; this.height = height;
    }
    @Override public double calculateArea() { return 0.5 * base * height; }
    @Override public String getShape() { return "TRIANGLE"; }
}

public class GardenPlotAreaReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double totalArea = 0.0;

        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            Plot plot;
            switch (p[0]) {
                case "CIRCLE":
                    plot = new CirclePlot(p[1], Double.parseDouble(p[2]));
                    break;
                case "RECTANGLE":
                    plot = new RectanglePlot(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
                    break;
                case "TRIANGLE":
                    plot = new TrianglePlot(p[1], Double.parseDouble(p[2]), Double.parseDouble(p[3]));
                    break;
                default: throw new IllegalArgumentException("Invalid plot shape: " + p[0]);
            }

            double area = plot.calculateArea();
            System.out.printf("%s (%s): %.2f%n", plot.owner, plot.getShape(), area);
            totalArea += area;
        }

        System.out.printf("Total Area: %.2f%n", totalArea);
        sc.close();
    }
}
