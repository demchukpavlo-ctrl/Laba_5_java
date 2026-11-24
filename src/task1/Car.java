package task1;

/**
 * Підклас "Автомобіль".
 * Природний порядок (Comparable): за маркою, потім за назвою моделі.
 */
public class Car extends Vehicle implements Comparable<Car> {
    private final String brand;      // марка
    private final String fuelType;   // тип пального
    private final int doors;         // кількість дверей

    public Car(String name, int maxSpeed, int capacity, int year,
               String brand, String fuelType, int doors) {
        super(name, maxSpeed, capacity, year);
        this.brand = brand;
        this.fuelType = fuelType;
        this.doors = doors;
    }

    /** Рядок файлу: назва;швидкість;місткість;рік;марка;пальне;двері */
    public static Car parse(String line) {
        String[] p = split(line, 7);
        return new Car(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]),
                p[4], p[5], Integer.parseInt(p[6]));
    }

    public String getBrand() { return brand; }
    public String getFuelType() { return fuelType; }
    public int getDoors() { return doors; }

    @Override
    public String getType() {
        return "Автомобіль";
    }

    @Override
    public int compareTo(Car other) {
        int r = brand.compareToIgnoreCase(other.brand);
        return r != 0 ? r : getName().compareToIgnoreCase(other.getName());
    }

    @Override
    public String toLine() {
        return super.toLine() + SEP + brand + SEP + fuelType + SEP + doors;
    }

    @Override
    public String toString() {
        return super.toString() + "  марка: " + brand + ", пальне: " + fuelType + ", дверей: " + doors;
    }
}
