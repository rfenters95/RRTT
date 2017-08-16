package core;

import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RoombaJSSCSingleton {
    private static final RoombaJSSC roombaJSSC = new RoombaJSSCSerial();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    private RoombaJSSCSingleton() {}
    public static RoombaJSSC getRoombaJSSC() {
        return roombaJSSC;
    }
    public static String logDate() {
        return simpleDateFormat.format(new Date());
    }
}
