package buildings;

public class Flat {
    private static final double FLAT_SQUARE = 50.0; // default room area
    private static final int ROOM_COUNT = 2;        // default number of rooms
    private double flatSquare;                      // room square
    private int roomCount;                          // number of rooms

    public Flat(){
        this.flatSquare = FLAT_SQUARE;
        this.roomCount = ROOM_COUNT;
    }

    public Flat(double flatSquare){
        if (flatSquare <= 0){
            throw new IllegalArgumentException("Square of flat has to be > 0");
        }
        this.flatSquare = flatSquare;
        this.roomCount = ROOM_COUNT;
    }

    public Flat(double flatSquare, int roomCount){
        if (flatSquare < 0){
            throw new IllegalArgumentException("Square of flat has to be >= 0");
        }
        if (roomCount < 1){
            throw new IllegalArgumentException("Rooms in flat has to be > 1");
        }
        this.flatSquare = flatSquare;
        this.roomCount = roomCount;
    }

    public double getFlatSquare() {
        return flatSquare;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setFlatSquare(double flatSquare) {
        this.flatSquare = flatSquare;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override                                                                   // delete if you don't use class java.util.Arrays
    public String toString(){
        return "Кв. площадью " + flatSquare + " с " + roomCount + " комн.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Double.compare(flatSquare, flat.flatSquare) == 0 && roomCount == flat.roomCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatSquare, roomCount);
    }
}


