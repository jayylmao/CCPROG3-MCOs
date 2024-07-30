/**
 * RoomIsOccupiedException is thrown when attempting to book a room
 * that is occupied on the given dates.
 */
public class RoomIsOccupiedException extends Exception {
    public RoomIsOccupiedException() {
        super("This room is occupied on the given dates.");
    }
}
