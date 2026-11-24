package task1;

/**
 * Підклас "Експрес" (швидкісний поїзд) — нащадок класу Поїзд.
 * Порядок: спочатку за кількістю зупинок (менше зупинок — вище), потім як у Train.
 */
public class Express extends Train {
    private final int stops;             // кількість зупинок
    private final boolean hasWifi;       // наявність Wi-Fi

    public Express(String name, int maxSpeed, int capacity, int year,
                   int wagons, String route, int stops, boolean hasWifi) {
        super(name, maxSpeed, capacity, year, wagons, route);
        this.stops = stops;
        this.hasWifi = hasWifi;
    }

    /** Рядок файлу: назва;швидкість;місткість;рік;вагони;маршрут;зупинки;wifi(true/false) */
    public static Express parse(String line) {
        String[] p = split(line, 8);
        return new Express(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]),
                Integer.parseInt(p[4]), p[5], Integer.parseInt(p[6]), Boolean.parseBoolean(p[7]));
    }

    public int getStops() { return stops; }
    public boolean isHasWifi() { return hasWifi; }

    @Override
    public String getType() {
        return "Експрес";
    }

    /** Експреси порівнюються за кількістю зупинок; з іншими поїздами — за правилом Train. */
    @Override
    public int compareTo(Train other) {
        if (other instanceof Express) {
            int r = Integer.compare(stops, ((Express) other).stops);
            if (r != 0) {
                return r;
            }
        }
        return super.compareTo(other);
    }

    @Override
    public String toLine() {
        return super.toLine() + SEP + stops + SEP + hasWifi;
    }

    @Override
    public String toString() {
        return super.toString() + ", зупинок: " + stops + ", Wi-Fi: " + (hasWifi ? "так" : "ні");
    }
}
