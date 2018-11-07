public class Armchair extends Furniture {
    public Armchair(String name,int min,int max){
        this.NAME = name;
        this.MIN_SQUARE = min;
        this.MAX_SQUARE = max;

    }
    public Armchair(String name,int square){
        this.NAME = name;
        this.SQUARE = square;

    }
}
