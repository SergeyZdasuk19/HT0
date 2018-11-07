import java.util.ArrayList;

public class Room {
    private String NAME_OF_ROOM;
    private float SQUARE_OF_ROOM;
    private int NUMBER_OF_WINDOW;
    private float SQUARE_LIMIT;
    private int ILLUMINATION;
    private final int MIN_ILLUMINATION = 300;
    private final int MAX_ILLUMINATION = 4000;
    private ArrayList<Lamp> lambs = new ArrayList<>();
    private ArrayList<Furniture> furnitures = new ArrayList<>();
    public Room(String nameOfRoom, float squareOfRoom, int numberOfWindow) {
        this.NAME_OF_ROOM = nameOfRoom;
        this.SQUARE_OF_ROOM = squareOfRoom;
        this.NUMBER_OF_WINDOW = numberOfWindow;
        this.ILLUMINATION += numberOfWindow * 700;
    }


    public void add(Lamp lamp) {
        lambs.add(lamp);
        this.ILLUMINATION += lamp.getIllumination();
    }

    public void add(Table table) {
        this.SQUARE_LIMIT += table.getSQUARE();
        furnitures.add(table);
    }
    public void add(Chair chair) {
        this.SQUARE_LIMIT += chair.getSQUARE();
        furnitures.add(chair);
    }
    public void checkLamp() throws NotEnoughIlluminanceException, IlluminanceTooMuchException {
        if (this.ILLUMINATION < MIN_ILLUMINATION) {
            throw new NotEnoughIlluminanceException("Not enough illuminance");
        }
        if (this.ILLUMINATION > MAX_ILLUMINATION) {
            throw new IlluminanceTooMuchException("Too much illuminance");
        }
    }

    public void describeLamp() {
        for (int i = 0; i < lambs.size(); i++) {
            if(i != lambs.size()-1)
            System.out.print(lambs.get(i).getIllumination() + ", ");
            if(lambs.size() - 1 == i){
                System.out.print(lambs.get(i).getIllumination() + ") ");
            }
        }
    }
    public void describeFurniture() {
        if(furnitures.size()==0){
            System.out.println("Мебели нет");
        }
        for (int i = 0; i < furnitures.size(); i++) {
            System.out.print(furnitures.get(i).getNAME() + " " + furnitures.get(i).getSQUARE() + "м^2"+ "\n");
        }
    }
    public void checkSquare() throws TooMuchSquareException {
        if (this.SQUARE_OF_ROOM * 0.7 < this.SQUARE_LIMIT) {
            throw new TooMuchSquareException("Too much square");
        }
    }

    public String getNAME_OF_ROOM() {
        return NAME_OF_ROOM;
    }

    public void setNAME_OF_ROOM(String NAME_OF_ROOM) {
        this.NAME_OF_ROOM = NAME_OF_ROOM;
    }

    public float getSQUARE_OF_ROOM() {
        return SQUARE_OF_ROOM;
    }

    public void setSQUARE_OF_ROOM(float SQUARE_OF_ROOM) {
        this.SQUARE_OF_ROOM = SQUARE_OF_ROOM;
    }

    public int getNUMBER_OF_WINDOW() {
        return NUMBER_OF_WINDOW;
    }

    public void setNUMBER_OF_WINDOW(int NUMBER_OF_WINDOW) {
        this.NUMBER_OF_WINDOW = NUMBER_OF_WINDOW;
    }

    public int getILLUMINATION() {
        return ILLUMINATION;
    }

    public float getSQUARE_LIMIT() {
        return SQUARE_LIMIT;
    }
}
