package pastor.vicente.tideweathersea.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import pastor.vicente.tideweathersea.R;
import pastor.vicente.tideweathersea.fragment.DatosMeteoFragment;
import pastor.vicente.tideweathersea.fragment.Listadolugares;
import pastor.vicente.tideweathersea.mapsActivity.MapsActivity;

public class MainActivity extends AppCompatActivity  implements  Listadolugares.Enviodecardview {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //boton atras
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            //para que pinte si no hay fragment
            if (savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new Listadolugares())
                        .commit();
            }else{

            }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.Ver_mapas:

                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

   /* @Override
    protected void onDestroy() {

        dbRoom.close();
        super.onDestroy();
    }*/


    @Override
    public void enviodecardview(Lugar lugar) {

        //Cargardetalles(lugar);
        Bundle datosAEnviar = new Bundle();
        // Aquí pon todos los datos que quieras en formato clave, valor

        datosAEnviar.putString("nombrelugar",lugar.getNombreLugar());
        datosAEnviar.putString("latitud", lugar.getLatitud());
        datosAEnviar.putString("longitud", lugar.getLongitud());
        Fragment fragmento=new DatosMeteoFragment();
        fragmento.setArguments(datosAEnviar);

       /* Muestraemail_Fragment muestraemailFragment=(Muestraemail_Fragment) getSupportFragmentManager().findFragmentById(R.id.Muestraemail_Fragment);
        if(muestraemailFragment!=null){
            muestraemailFragment.detallesemail(email);
        }else{

            Intent intent=new Intent(this, DetallesEmailActivity.class);
            intent.putExtra("correo", email);
            startActivity(intent);
        }*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, fragmento)
                .addToBackStack(null)
                .commit();


        }


    }

