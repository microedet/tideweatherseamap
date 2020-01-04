package pastor.vicente.tideweathersea.main;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lugares")

public class Lugar {


    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String nombreLugar;
    @NonNull
    private String latitud;
    @NonNull
    private String longitud;

    public Lugar() { }

    public Lugar(long id, @NonNull String nombreLugar, @NonNull String latitud, @NonNull String longitud) {
        this.id = id;
        this.nombreLugar = nombreLugar;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Lugar(@NonNull String nombreLugar, @NonNull String latitud, @NonNull String longitud) {
        this.nombreLugar = nombreLugar;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(@NonNull String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    @NonNull
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(@NonNull String latitud) {
        this.latitud = latitud;
    }

    @NonNull
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull String longitud) {
        this.longitud = longitud;
    }
}