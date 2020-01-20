package pastor.vicente.tideweathersea.data;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pastor.vicente.tideweathersea.main.Lugar;

public class LugarViewModel extends AndroidViewModel {
    private LiveData<List<Lugar>> lugaresList;
    public static DataBaseRoom dbRoom;


    public LugarViewModel(@NonNull Application application) {
        super(application);
        dbRoom = DataBaseRoom.getInstance(application);
        lugaresList = dbRoom.lugarDao().getLugares();
    }

    public LiveData<List<Lugar>> getLugares() {
        return lugaresList;

    }

    public void addLugar(Lugar lugar) {

        new AsyncAddLugarDb().execute(lugar);
    }

    public void updateLugar(Lugar lugar) {

        new AsyncEditLugarDB().execute(lugar);
    }

    public void deleteLugar(Lugar lugar) {

        new AsyncDeleteLugarDB().execute(lugar);
    }


    public class AsyncAddLugarDb extends AsyncTask<Lugar, Void, Long> {
        Lugar lugar;


        @Override
        protected Long doInBackground(Lugar... lugares) {
            long id = -1;

            if (lugares.length != 0) {
                lugar = lugares[0];
                id = dbRoom.lugarDao().insertLugar(lugares[0]);
                lugar.setId(id);
            }
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1) {
                Toast.makeText(getApplication(), "error añadiendo el lugar", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getApplication(), "lugar añadido", Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }


    public class AsyncEditLugarDB extends AsyncTask<Lugar, Void, Integer> {

        public AsyncEditLugarDB(){}


        @Override
        protected Integer doInBackground(Lugar... lugares) {
            int updatedRows = 0;
            if (lugares.length != 0) {
                updatedRows = dbRoom.lugarDao().updateLugar(lugares[0]);

            }

            return updatedRows;
        }

        @Override
        protected void onPostExecute(Integer updatedRows) {
            if (updatedRows == 0) {
                Toast.makeText(getApplication(), "error actualizando el lugar", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getApplication(), "lugar editado", Toast.LENGTH_LONG)
                        .show();
                }
        }


    }

    public class AsyncDeleteLugarDB extends AsyncTask<Lugar, Void, Integer> {


        public AsyncDeleteLugarDB() {

        }

        @Override
        protected Integer doInBackground(Lugar...lugares) {
            int deleteRows = 0;
            if (lugares.length != 0) {
                deleteRows = dbRoom.lugarDao().deleteLugar(lugares[0]);
            }

            return deleteRows;
        }

        @Override
        protected void onPostExecute(Integer deleteRows) {
            if (deleteRows == 0) {
                Toast.makeText(getApplication(), "error Borrando el lugar", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getApplication(),"lugar Borrado", Toast.LENGTH_LONG)
                        .show();

            }
        }
    }

}
