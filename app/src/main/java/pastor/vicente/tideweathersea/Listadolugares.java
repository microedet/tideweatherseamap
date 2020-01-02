package pastor.vicente.tideweathersea;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import pastor.vicente.tideweathersea.data.DataBaseRoom;
import pastor.vicente.tideweathersea.data.LugarViewModel;



public class Listadolugares extends Fragment implements  LugarAdapter.OnButtonClikedListener {

    AlertDialog.Builder builder;
    AlertDialog dialogo;
    List<Lugar> lugarList=new ArrayList<>();
    RecyclerView recyclerView;
    LugarViewModel viewModel;
    LugarAdapter adapter;
    private DataBaseRoom dbRoom;
    Context context;

    private OnFragmentInteractionListener mListener;

    public Listadolugares() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_listadolugares, container, false);
        //viewModel = ViewModelProviders.of(this).get(LugarViewModel.class);

       FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventana();

            }
        });

        /*FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        recyclerView = (RecyclerView)view.findViewById(R.id.listadolugares);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // adapter = new LugarAdapter(context, lugarList, this);
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ventanalugar, null);

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
                }else{
                    Toast.makeText(context, "problema guardado", Toast.LENGTH_SHORT).show();
                }
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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    */
    
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onButtonCliked(View v, Lugar lugar) {
        if (v.getId() == R.id.udpateicon) {
            Log.e("vicente","el lugar es "+ lugar);
            //Toast.makeText(context, "Editar Lugar ", Toast.LENGTH_SHORT).show();

            editLugar(lugar);

        }else if (v.getId() == R.id.deleteicon) {

            deleteProduct(lugar);
        }

    }

    private void editLugar(final Lugar lugar) {

        builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ventanalugar, null);

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
