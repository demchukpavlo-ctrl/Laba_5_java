package task1;

/**
 * Підклас "Поїзд".
 * Природний порядок (Comparable): за кількістю вагонів (за зростанням), потім за маршрутом.
 */
public class Train extends Vehicle implements Comparable<Train> {
    private final int wagons;        // кількість вагонів
    private final String route;      // маршрут

    public Train(String name, int maxSpeed, int capacity, int year,
                 int wagons, String route) {
        super(name, maxSpeed, capacity, year);
        this.wagons = wagons;
        this.route = route;
    }

    /** Рядок файлу: назва;швидкість;місткість;рік;вагони;маршрут */
    public static Train parse(String line) {
        String[] p = split(line, 6);
        return new Train(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]),
                Integer.parseInt(p[4]), p[5]);
    }

    public int getWagons() { return wagons; }
    public String getRoute() { return route; }

    @Override
    public String getType() {
        return "Поїзд";
    }

    @Override
    public int compareTo(Train other) {
        int r = Integer.compare(wagons, other.wagons);
        return r != 0 ? r : route.compareToIgnoreCase(other.route);
    }

    @Override
    public String toLine() {
        return super.toLine() + SEP + wagons + SEP + route;
    }

    @Override
    public String toString() {
        return super.toString() + "  вагонів: " + wagons + ", маршрут: " + route;
    }
}
