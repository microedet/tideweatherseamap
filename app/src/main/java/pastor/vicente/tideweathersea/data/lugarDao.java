package pastor.vicente.tideweathersea.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pastor.vicente.tideweathersea.Lugar;

@Dao
public interface lugarDao {

    @Insert
    public long insertLugar(Lugar lugar);

    @Update
    public int updateLugar(Lugar lugar);

    @Delete
    public int deleteLugar(Lugar lugar);

    @Query("SELECT * FROM lugares where id=:id")
    public Lugar getLugar(long id);

    @Query("Select * from lugares")
    public LiveData<List<Lugar>> getLugares();


}