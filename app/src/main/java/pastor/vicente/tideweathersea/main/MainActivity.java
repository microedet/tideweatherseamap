package pastor.vicente.tideweathersea.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pastor.vicente.tideweathersea.R;
import pastor.vicente.tideweathersea.fragment.Datosmeteo;
import pastor.vicente.tideweathersea.fragment.Listadolugares;
import pastor.vicente.tideweathersea.data.DataBaseRoom;

public class MainActivity extends AppCompatActivity  implements  Listadolugares.Enviodecardview{


    private DataBaseRoom dbRoom;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor,new Listadolugares())
                .commit();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected  void onDestroy(){

        dbRoom.close();
        super.onDestroy();
    }



    @Override
    public void enviodecardview(Lugar lugar) {
        Datosmeteo datosmeteo=(Datosmeteo) getSupportFragmentManager().findFragmentById(R.id.datosmeteo);
        if(datosmeteo!=null){
            datosmeteo.datosmeteo(lugar);
        }else{

           /* Intent intent=new Intent(this, DetallesEmailActivity.class);
            intent.putExtra("correo", email);
            startActivity(intent);*/
        }


    }
}
