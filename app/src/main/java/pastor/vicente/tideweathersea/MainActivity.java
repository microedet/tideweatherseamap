package pastor.vicente.tideweathersea;

import android.app.AlertDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pastor.vicente.tideweathersea.data.DataBaseRoom;
import pastor.vicente.tideweathersea.data.LugarViewModel;

public class MainActivity extends AppCompatActivity implements LugarAdapter.OnButtonClikedListener {

    AlertDialog.Builder builder;
    AlertDialog dialogo;
    List<Lugar> lugarList=new ArrayList<>();
    RecyclerView recyclerView;
    LugarAdapter adapter;
    LugarViewModel viewModel;
    private DataBaseRoom dbRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventana();

                  }
        });
        recyclerView = findViewById(R.id.listadolugares);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LugarAdapter(this, lugarList, this);
        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(this).get(LugarViewModel.class);
        viewModel.getLugares().observe(this, new Observer<List<Lugar>>() {


            //@Override
            public void onChanged(List<Lugar> lugares) {
                adapter.addLugares(lugares);

            }
        });


    }

    private void ventana() {
        builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.ventanalugar, null);

        builder.setView(view);

        dialogo = builder.create();
        dialogo.show();

        final EditText nombreLugar = view.findViewById(R.id.ventanaNombreLugar);
        final EditText latitudLugar = view.findViewById(R.id.ventanaLatitudLugar);
        final EditText longitudLugar=view.findViewById(R.id.ventanaLongitudLugar);
        Button saveButton = view.findViewById(R.id.saveLugarButton);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(nombreLugar.getText()) && !TextUtils.isEmpty(latitudLugar.getText()) && !TextUtils.isEmpty(longitudLugar.getText())) {
                    saveLugar(nombreLugar.getText().toString(), latitudLugar.getText().toString(),longitudLugar.getText().toString());
                    Toast.makeText(MainActivity.this, "guardado", Toast.LENGTH_SHORT).show();
                    Log.d("informacion","nombrelugar : "+nombreLugar+" latitudLugar : "+latitudLugar+" longitudlugar : "+longitudLugar);
                }
            }
        });

    }

    private void saveLugar(String nombre, String latitud,String longitud) {
        Lugar lugar = new Lugar(nombre, latitud,longitud);
        viewModel.addLugar(lugar);
        dialogo.dismiss();
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
    public void onButtonCliked(View v, Lugar lugar) {
        if (v.getId() == R.id.udpateicon) {
            Toast.makeText(this, "Editar Producto ", Toast.LENGTH_SHORT).show();

            editProduct(lugar);

        }else if (v.getId() == R.id.deleteicon) {

            deleteProduct(lugar);
        }

    }

    private void editProduct(final Lugar lugar) {
        //final Producto producto = productosList.get(posicion);

        builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.ventanalugar, null);

        builder.setView(view);

        dialogo = builder.create();
        dialogo.show();


        final EditText nombreLugar = view.findViewById(R.id.ventanaNombreLugar);
        final EditText latitud = view.findViewById(R.id.ventanaLatitudLugar);
        final EditText longitud = view.findViewById(R.id.ventanaLongitudLugar);
        Button saveButton = view.findViewById(R.id.saveLugarButton);

        nombreLugar.setText(lugar.getNombreLugar());
        latitud.setText(lugar.getLatitud());
        longitud.setText(lugar.getLongitud());

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(nombreLugar.getText()) && !TextUtils.isEmpty(latitud.getText()) && !TextUtils.isEmpty(longitud.getText())) {
                    lugar.setNombreLugar(nombreLugar.getText().toString());
                    lugar.setLatitud(latitud.getText().toString());
                    lugar.setLongitud(longitud.getText().toString());

                    viewModel.updateLugar(lugar);
                    Log.d("informacion","el producto es "+lugar);
                }
                dialogo.dismiss();
            }
        });
    }

    private void deleteProduct(Lugar lugar) {
        viewModel.deleteLugar(lugar);
    }

}
