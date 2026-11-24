package task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Завдання 1. Керування записами на основі класів з лабораторної роботи №3
 * (Vehicle -> Car, Train -> Express).
 * Дані читаються з файлів (для кожного класу — окремий файл) у колекції ArrayList,
 * сортуються Collections.sort() (Comparable), доповнюються записом з клавіатури,
 * потім усі записи об'єднуються в ArrayList&lt;Vehicle&gt;, сортуються компаратором
 * VehicleComparator і записуються у файл.
 */
public class Main {

    private static final String CARS_FILE = "data/cars.txt";
    private static final String TRAINS_FILE = "data/trains.txt";
    private static final String EXPRESS_FILE = "data/expresses.txt";
    private static final String RESULT_FILE = "data/all_sorted.txt";

    // ---------- введення з клавіатури ----------

    private static int readInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Помилка: введіть ціле число.");
            }
        }
    }

    private static String readString(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            if (!s.isEmpty() && !s.contains(Vehicle.SEP)) {
                return s;
            }
            System.out.println("  Помилка: значення не може бути порожнім або містити '" + Vehicle.SEP + "'.");
        }
    }

    private static Car readCar(Scanner in) {
        System.out.println("--- Новий автомобіль ---");
        String name = readString(in, "Назва: ");
        int speed = readInt(in, "Макс. швидкість (км/год): ");
        int capacity = readInt(in, "Місткість (пасажирів): ");
        int year = readInt(in, "Рік випуску: ");
        String brand = readString(in, "Марка: ");
        String fuel = readString(in, "Тип пального: ");
        int doors = readInt(in, "Кількість дверей: ");
        return new Car(name, speed, capacity, year, brand, fuel, doors);
    }

    private static Train readTrain(Scanner in) {
        System.out.println("--- Новий поїзд ---");
        String name = readString(in, "Назва: ");
        int speed = readInt(in, "Макс. швидкість (км/год): ");
        int capacity = readInt(in, "Місткість (пасажирів): ");
        int year = readInt(in, "Рік випуску: ");
        int wagons = readInt(in, "Кількість вагонів: ");
        String route = readString(in, "Маршрут: ");
        return new Train(name, speed, capacity, year, wagons, route);
    }

    private static Express readExpress(Scanner in) {
        System.out.println("--- Новий експрес ---");
        String name = readString(in, "Назва: ");
        int speed = readInt(in, "Макс. швидкість (км/год): ");
        int capacity = readInt(in, "Місткість (пасажирів): ");
        int year = readInt(in, "Рік випуску: ");
        int wagons = readInt(in, "Кількість вагонів: ");
        String route = readString(in, "Маршрут: ");
        int stops = readInt(in, "Кількість зупинок: ");
        String wifi = readString(in, "Є Wi-Fi? (так/ні): ");
        boolean hasWifi = wifi.equalsIgnoreCase("так") || wifi.equalsIgnoreCase("y")
                || wifi.equalsIgnoreCase("yes") || wifi.equals("1");
        return new Express(name, speed, capacity, year, wagons, route, stops, hasWifi);
    }

    // ---------- робота з файлами ----------

    /** Читає записи з файлу; некоректні рядки пропускаються з повідомленням. */
    static <T> ArrayList<T> load(String fileName, Function<String, T> parser) throws IOException {
        ArrayList<T> list = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int number = 0;
            while ((line = reader.readLine()) != null) {
                number++;
                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }
                try {
                    list.add(parser.apply(line));
                } catch (RuntimeException e) {
                    System.out.println("  " + fileName + ", рядок " + number + " пропущено: " + e.getMessage());
                }
            }
        } catch (NoSuchFileException e) {
            throw new IOException("файл " + path.toAbsolutePath() + " не знайдено");
        }
        return list;
    }

    static void save(String fileName, List<? extends Vehicle> list) throws IOException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("# тип;назва;швидкість;місткість;рік;додаткові поля");
            writer.newLine();
            for (Vehicle v : list) {
                writer.write(v.getType() + Vehicle.SEP + v.toLine());
                writer.newLine();
            }
        }
    }

    private static void print(String title, List<? extends Vehicle> list) {
        System.out.println("\n" + title + " (" + list.size() + "):");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%3d. %s%n", i + 1, list.get(i));
        }
    }

    // ---------- запуск ----------

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        run(in);
        in.close();
    }

    /** Запуск завдання з уже відкритим Scanner (використовується з меню). */
    public static void run(Scanner in) {
        System.out.println("=== Завдання 1. Колекції записів (класи з лабораторної роботи №3) ===");
        try {
            ArrayList<Car> cars = load(CARS_FILE, Car::parse);
            ArrayList<Train> trains = load(TRAINS_FILE, Train::parse);
            ArrayList<Express> expresses = load(EXPRESS_FILE, Express::parse);

            System.out.println("\n##### Прочитані дані #####");
            print("Автомобілі (" + CARS_FILE + ")", cars);
            print("Поїзди (" + TRAINS_FILE + ")", trains);
            print("Експреси (" + EXPRESS_FILE + ")", expresses);

            Collections.sort(cars);
            Collections.sort(trains);
            Collections.sort(expresses);
            System.out.println("\n##### Після сортування (Comparable) #####");
            print("Автомобілі (за маркою, потім назвою)", cars);
            print("Поїзди (за кількістю вагонів, потім маршрутом)", trains);
            print("Експреси (за кількістю зупинок)", expresses);

            System.out.println("\n##### Додавання записів з клавіатури #####");
            cars.add(readCar(in));
            trains.add(readTrain(in));
            expresses.add(readExpress(in));

            Collections.sort(cars);
            Collections.sort(trains);
            Collections.sort(expresses);
            System.out.println("\n##### Після додавання і повторного сортування #####");
            print("Автомобілі", cars);
            print("Поїзди", trains);
            print("Експреси", expresses);

            // ArrayList на основі абстрактного класу
            ArrayList<Vehicle> all = new ArrayList<>();
            all.addAll(cars);
            all.addAll(trains);
            all.addAll(expresses);
            Collections.sort(all, new VehicleComparator());
            print("Усі записи, відсортовані VehicleComparator (швидкість ↓, рік ↓, назва)", all);

            save(RESULT_FILE, all);
            System.out.println("\nВідсортований список записано у файл " + Paths.get(RESULT_FILE).toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }
}
