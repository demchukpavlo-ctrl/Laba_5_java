package task1;

import java.util.Comparator;

/**
 * Компаратор для об'єктів абстрактного класу Vehicle:
 * за максимальною швидкістю (за спаданням), при рівності — за роком випуску
 * (новіші вище), далі — за назвою.
 */
public class VehicleComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle a, Vehicle b) {
        int r = Integer.compare(b.getMaxSpeed(), a.getMaxSpeed());
        if (r != 0) {
            return r;
        }
        r = Integer.compare(b.getYear(), a.getYear());
        if (r != 0) {
            return r;
        }
        return a.getName().compareToIgnoreCase(b.getName());
    }
}
