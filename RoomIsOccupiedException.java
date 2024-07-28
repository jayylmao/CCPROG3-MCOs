
public class RoomIsOccupiedException extends Exception {
    public RoomIsOccupiedException() {
        super("This room is occupied on the given dates.");
    }
}
