package pastor.vicente.tideweathersea.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

import pastor.vicente.tideweathersea.main.Lugar;
import pastor.vicente.tideweathersea.main.LugarAdapter;
import pastor.vicente.tideweathersea.R;
import pastor.vicente.tideweathersea.data.LugarViewModel;
import pastor.vicente.tideweathersea.main.MainActivity;


public class Listadolugares extends Fragment implements  LugarAdapter.OnButtonClikedListener {

    AlertDialog.Builder builder;
    AlertDialog dialogo;
    List<Lugar> lugarList=new ArrayList<>();
    RecyclerView recyclerView;
    LugarViewModel viewModel;
    LugarAdapter adapter;
    CardView cardView;
    Context context;
    Enviodecardview callback;

    View view;


    //interface
    private Enviodecardview enviodecardview;

    public Listadolugares() {
        // Required empty public constructor
    }

    public interface Enviodecardview{
        public void enviodecardview (Lugar lugar);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_listadolugares, container, false);

       FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventana();

            }
        });


        recyclerView = view.findViewById(R.id.listadolugares);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter=new LugarAdapter(getContext(),lugarList,this);
        recyclerView.setAdapter(adapter);

        viewModel= ViewModelProviders.of(this).get(LugarViewModel.class);
        viewModel.getLugares().observe(this, new Observer<List<Lugar>>() {


            //@Override
            public void onChanged(List<Lugar> lugares) {
                adapter.addLugares(lugares);

            }
        });


        return view;

    }



    private void ventana() {
        builder = new AlertDialog.Builder(getContext());
         view = LayoutInflater.from(getContext()).inflate(R.layout.ventanalugar, null);

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
                   // Toast.makeText(context, "guardado", Toast.LENGTH_SHORT).show();
                    Log.d("informacion","nombrelugar : "+nombreLugar+" latitudLugar : "+latitudLugar+" longitudlugar : "+longitudLugar);
                }/*else{
                   Toast.makeText(context, "problema guardado", Toast.LENGTH_SHORT).show();

                }*/
            }
        });



    }

    private void saveLugar(String nombre, String latitud,String longitud) {
        Log.i("informacion","nombrelugar : "+nombre+" latitudLugar : "+latitud+" longitudlugar : "+longitud);

        Lugar lugar = new Lugar(nombre, latitud,longitud);
        Log.i("info","objecto "+lugar);
        viewModel.addLugar(lugar);

        dialogo.dismiss();
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (Enviodecardview) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ " Debería implementar el interfaz OnNameSent");
        }
    }
    




    @Override
    public void onButtonCliked(View v, Lugar lugar) {
        if (v.getId() == R.id.udpateicon) {
            Log.e("vicente","el lugar es "+ lugar);
            //Toast.makeText(context, "Editar Lugar ", Toast.LENGTH_SHORT).show();

            editLugar(lugar);

        }else if (v.getId() == R.id.deleteicon) {

            deleteLugar(lugar);
        }else if(v.getId() == R.id.cardViewLugar){



            ((MainActivity) v.getContext()).enviodecardview(lugar);


        }

    }

    private void Cargardetalles(final Lugar lugar) {
        Toast.makeText(getContext(), "lanzado desde fragment", Toast.LENGTH_SHORT).show();
        //((MainActivity) v.getContext()).confirmConnection(address);
        // datosmeteo(lugar);
       // enviodecardvi


    }

    private void editLugar(final Lugar lugar) {

        builder = new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.ventanalugar, null);

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

    private void deleteLugar(Lugar lugar) {
        viewModel.deleteLugar(lugar);
    }


    }



