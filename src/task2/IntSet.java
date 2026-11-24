package task2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Множина цілих чисел на основі HashSet&lt;Integer&gt;
 * з методами знаходження перетину та об'єднання множин.
 */
public class IntSet implements Iterable<Integer> {

    private final Set<Integer> elements = new HashSet<>();

    public IntSet() {
    }

    public IntSet(Collection<Integer> values) {
        elements.addAll(values);
    }

    public IntSet(int... values) {
        for (int v : values) {
            elements.add(v);
        }
    }

    /** Додає елемент; повертає false, якщо такий елемент вже є. */
    public boolean add(int value) {
        return elements.add(value);
    }

    public boolean remove(int value) {
        return elements.remove(value);
    }

    public boolean contains(int value) {
        return elements.contains(value);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /** Об'єднання A ∪ B: елементи, що належать хоча б одній з множин. */
    public IntSet union(IntSet other) {
        IntSet result = new IntSet(this.elements);
        result.elements.addAll(other.elements);
        return result;
    }

    /** Перетин A ∩ B: елементи, що належать обом множинам. */
    public IntSet intersection(IntSet other) {
        IntSet result = new IntSet();
        // перебираємо меншу множину і перевіряємо наявність у більшій
        IntSet small = this.size() <= other.size() ? this : other;
        IntSet big = small == this ? other : this;
        for (int v : small.elements) {
            if (big.elements.contains(v)) {
                result.elements.add(v);
            }
        }
        return result;
    }

    @Override
    public Iterator<Integer> iterator() {
        return elements.iterator();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof IntSet && elements.equals(((IntSet) o).elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    /** Елементи виводяться за зростанням, щоб результат було зручно читати. */
    @Override
    public String toString() {
        return new TreeSet<>(elements).toString().replace('[', '{').replace(']', '}');
    }
}
