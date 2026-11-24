package task2;

import java.util.Scanner;

/**
 * Завдання 2, варіант 5.
 * Визначити множину на основі множини цілих чисел (HashSet).
 * Створити методи для знаходження перетину та об'єднання множин.
 */
public class Main {

    /** Зчитує множину з рядка цілих чисел, розділених пробілами або комами. */
    private static IntSet readSet(Scanner in, String name) {
        while (true) {
            System.out.print("Введіть елементи множини " + name + " через пробіл: ");
            String line = in.nextLine().trim();
            IntSet set = new IntSet();
            if (line.isEmpty()) {
                return set;                     // порожня множина
            }
            try {
                int duplicates = 0;
                for (String token : line.split("[\\s,;]+")) {
                    if (!set.add(Integer.parseInt(token))) {
                        duplicates++;
                    }
                }
                if (duplicates > 0) {
                    System.out.println("  Повторних значень відкинуто: " + duplicates);
                }
                return set;
            } catch (NumberFormatException e) {
                System.out.println("  Помилка: потрібно вводити лише цілі числа.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        run(in);
        in.close();
    }

    public static void run(Scanner in) {
        System.out.println("=== Завдання 2. Варіант 5: множини цілих чисел (HashSet) ===");

        IntSet a = readSet(in, "A");
        IntSet b = readSet(in, "B");

        System.out.println();
        System.out.println("A = " + a + "  (потужність " + a.size() + ")");
        System.out.println("B = " + b + "  (потужність " + b.size() + ")");
        System.out.println("A ∪ B = " + a.union(b));
        System.out.println("A ∩ B = " + a.intersection(b));

        // перевірка властивостей операцій
        System.out.println();
        System.out.println("Перевірка: A ∪ B = B ∪ A -> " + a.union(b).equals(b.union(a)));
        System.out.println("Перевірка: A ∩ B = B ∩ A -> " + a.intersection(b).equals(b.intersection(a)));
        System.out.println("Перевірка: |A ∪ B| = |A| + |B| - |A ∩ B| -> "
                + (a.union(b).size() == a.size() + b.size() - a.intersection(b).size()));
    }
}
