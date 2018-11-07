
import java.util.ArrayList;

public class Building {
    private String NAME;
    private ArrayList<Room> rooms = new ArrayList<>();

    private int COUNT = 0;

    public Building(String name) {
        this.NAME = name;
    }

    public void addRoom(String name, float square, int numberOfWindow) throws NullOfSquareException {
        if (square == 0) {
            throw new NullOfSquareException("Not enough square");
        }
        Room room[] = new Room[10];
        room[COUNT] = new Room(name, square, numberOfWindow);
        rooms.add(room[COUNT]);
        COUNT++;
    }

    public Room getRoom(String nameOfRoom) {
        for (int i = 0; i < rooms.size(); i++) {
            if (nameOfRoom == rooms.get(i).getNAME_OF_ROOM()) {
                return rooms.get(i);
            }
        }
        return rooms.get(1);
    }

    public void check() throws NotEnoughIlluminanceException, IlluminanceTooMuchException, TooMuchSquareException {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).checkLamp();
            rooms.get(i).checkSquare();
        }
        describe();
    }

    public void describe()  {
        System.out.println(this.NAME);
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("Название комнаты - " + rooms.get(i).getNAME_OF_ROOM());
            System.out.print("Освещенность = " + rooms.get(i).getILLUMINATION());
            if(rooms.get(i).getNUMBER_OF_WINDOW()==1) {
                System.out.print("(" + rooms.get(i).getNUMBER_OF_WINDOW() + " окно");
                System.out.print(",лампочки ");
                rooms.get(i).describeLamp();
            }
            if(rooms.get(i).getNUMBER_OF_WINDOW()>1 && rooms.get(i).getNUMBER_OF_WINDOW()<5) {
                System.out.print("(" + rooms.get(i).getNUMBER_OF_WINDOW() + " окна");
                System.out.print(",лампочки ");
                rooms.get(i).describeLamp();
            }
            if(rooms.get(i).getNUMBER_OF_WINDOW()>4) {
                System.out.print("(" + rooms.get(i).getNUMBER_OF_WINDOW() + " окон");
                System.out.print(",лампочки ");
                rooms.get(i).describeLamp();
            }
            System.out.println("\nПлощадь комнаты = " + rooms.get(i).getSQUARE_OF_ROOM());
            System.out.println("Площадь мебели = " + rooms.get(i).getSQUARE_LIMIT());
            System.out.println("Мебель: ");
            rooms.get(i).describeFurniture();
            System.out.println("=========================");
        }

    }

}
