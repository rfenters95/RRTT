package core;

import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;

public class RoombaJSSCSingleton {
    private static RoombaJSSC roombaJSSC = new RoombaJSSCSerial();
    static {
    }
    private RoombaJSSCSingleton() {}
    public static RoombaJSSC getRoombaJSSC() {
        return roombaJSSC;
    }
}
