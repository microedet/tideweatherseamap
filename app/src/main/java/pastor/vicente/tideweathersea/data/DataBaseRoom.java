package pastor.vicente.tideweathersea.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pastor.vicente.tideweathersea.Lugar;

@Database(entities ={Lugar.class}, version=2, exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {
    public abstract LugarDao lugarDao();

    private static DataBaseRoom INSTANCE = null;

    public static  DataBaseRoom getInstance(
            final Context context)

    {

        if (INSTANCE == null) {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), pastor.vicente.tideweathersea.data.DataBaseRoom.class, "lugares.db").fallbackToDestructiveMigration().build();

        }
        return INSTANCE;

    }
    public static void destroyInstance(){
        INSTANCE=null;
    }
}
