import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Building building = new Building("Здание");
            building.addRoom("Комната 1", 30, 3);
            building.getRoom("Комната 1").add(new Lamp(200));
            building.getRoom("Комната 1").add(new Lamp(50));
            building.getRoom("Комната 1").add(new Table("Стол ", 5));
            building.getRoom("Комната 1").add(new Chair("Кресло ", 3));
            building.addRoom("Комната 2", 40, 4);
            building.getRoom("Комната 2").add(new Lamp(100));
            building.getRoom("Комната 2").add(new Lamp(200));
            building.check();
        } catch (NullOfSquareException | NotEnoughIlluminanceException | IlluminanceTooMuchException | TooMuchSquareException nullOfSquare) {
            System.out.println(nullOfSquare.getMessage());
        }
    }
}
