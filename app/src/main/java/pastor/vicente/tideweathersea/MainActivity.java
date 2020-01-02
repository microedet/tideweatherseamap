package pastor.vicente.tideweathersea;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import pastor.vicente.tideweathersea.data.DataBaseRoom;
import pastor.vicente.tideweathersea.data.LugarViewModel;

public class MainActivity extends AppCompatActivity  {

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

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor,new Listadolugares())
                .commit();

/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventana();

                  }
        });
        recyclerView = findViewById(R.id.Listadolugares);
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
        });*/


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


}
