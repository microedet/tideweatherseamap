package pastor.vicente.tideweathersea.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pastor.vicente.tideweathersea.R;
import pastor.vicente.tideweathersea.main.Lugar;


public class Datosmeteo extends Fragment {
    TextView textView;


    public Datosmeteo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_datosmeteo, container, false);
        textView = view.findViewById(R.id.textdatosmeteo);

        return view;
    }


    public void datosmeteo(Lugar lugar) {

        textView.setText(lugar.getLatitud());
    }


}
