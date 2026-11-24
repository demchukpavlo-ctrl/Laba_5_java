package task1;

/**
 * Абстрактний суперклас "Транспортний засіб" (ієрархія з лабораторної роботи №3).
 */
public abstract class Vehicle {
    /** Роздільник полів у рядку файлу. */
    public static final String SEP = ";";

    private String name;       // назва / модель
    private int maxSpeed;      // максимальна швидкість, км/год
    private int capacity;      // кількість пасажирських місць
    private int year;          // рік випуску

    public static int count = 0; // кількість створених транспортних засобів

    protected Vehicle(String name, int maxSpeed, int capacity, int year) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
        this.year = year;
        count++;
    }

    public String getName() { return name; }
    public int getMaxSpeed() { return maxSpeed; }
    public int getCapacity() { return capacity; }
    public int getYear() { return year; }

    /** Тип об'єкта для виводу. */
    public abstract String getType();

    /** Представлення об'єкта рядком для запису у файл. */
    public String toLine() {
        return name + SEP + maxSpeed + SEP + capacity + SEP + year;
    }

    /** Виводить дані про об'єкт. */
    public void Show() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("%-12s %-16s %4d км/год %5d пас. %d р.",
                "[" + getType() + "]", name, maxSpeed, capacity, year);
    }

    /** Розбирає рядок файлу на поля та перевіряє їх кількість. */
    static String[] split(String line, int expected) {
        String[] p = line.split(SEP, -1);
        if (p.length != expected) {
            throw new IllegalArgumentException("очікується " + expected + " полів, а є " + p.length);
        }
        for (int i = 0; i < p.length; i++) {
            p[i] = p[i].trim();
        }
        return p;
    }
}
