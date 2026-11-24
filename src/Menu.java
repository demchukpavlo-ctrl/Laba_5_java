import java.util.Scanner;

/**
 * Головне меню лабораторної роботи №5.
 * Дозволяє обрати, яке завдання запустити.
 */
public class Menu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("   Лабораторна робота №5. Колекції.");
            System.out.println(" 1 - Завдання 1: колекції записів (класи з лабораторної №3)");
            System.out.println(" 2 - Завдання 2 (варіант 5): множини на основі HashSet");
            System.out.println(" 0 - Вихід");
            System.out.print("Ваш вибір: ");

            if (!in.hasNextLine()) {
                break;
            }
            String choice = in.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    task1.Main.run(in);
                    break;
                case "2":
                    task2.Main.run(in);
                    break;
                case "0":
                    System.out.println("До побачення!");
                    in.close();
                    return;
                default:
                    System.out.println("Невірний вибір, введіть 0, 1 або 2.");
                    continue;
            }

            System.out.print("\nНатисніть Enter, щоб повернутися в меню...");
            if (!in.hasNextLine()) {
                break;
            }
            in.nextLine();
        }
        in.close();
    }
}
