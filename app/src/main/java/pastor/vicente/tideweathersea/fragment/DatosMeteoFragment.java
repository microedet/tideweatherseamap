package pastor.vicente.tideweathersea.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pastor.vicente.tideweathersea.Astronomy;
import pastor.vicente.tideweathersea.R;
import pastor.vicente.tideweathersea.data.LugarViewModel;
import pastor.vicente.tideweathersea.main.Lugar;
import pastor.vicente.tideweathersea.main.Meteo;
import pastor.vicente.tideweathersea.main.QueryUtils;
import pastor.vicente.tideweathersea.main.Tides;
import pastor.vicente.tideweathersea.main.TidesAdapter;


public class DatosMeteoFragment extends Fragment {

    //String url
    private static final String REQUEST_URL = "https://api.worldweatheronline.com/premium/v1/marine.ashx?key=b453899f387040d3a31112938190912&format=json&tide=yes&q=";

    //variable para objeto
    //public Meteo meteo;


    TextView fecha, nombrelugar, salidasol, puestasol, salidaluna, puestaluna, nombretemperatura,
            maximatemperaruta, minimatemperatura;
    //RecyclerView listadomarea;

    private RequestQueue requestQueue;
    private TidesAdapter adapter;
    Tides mareas = new Tides();
    //View view;


    public DatosMeteoFragment() {
        // Required empty public constructor
    }

    public static DatosMeteoFragment newInstance() {
        DatosMeteoFragment datosMeteoFragment = new DatosMeteoFragment();
        Bundle args = new Bundle();
        datosMeteoFragment.setArguments(args);
        return datosMeteoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_datosmeteo, container, false);
        requestQueue = Volley.newRequestQueue(view.getContext());

        final ListView mareas = view.findViewById(R.id.listadomareas);
        adapter = new TidesAdapter(getContext(), new ArrayList<Tides>() );
        mareas.setAdapter(adapter);


        fecha = view.findViewById(R.id.textViewFecha);
        nombrelugar = view.findViewById(R.id.textViewNombreLugar);
        salidasol = view.findViewById(R.id.salidasol);
        puestasol = view.findViewById(R.id.puestasol);
        salidaluna = view.findViewById(R.id.salidaluna);
        puestaluna = view.findViewById(R.id.puestaluna);
        nombretemperatura = view.findViewById(R.id.nombretemperatura);
        maximatemperaruta = view.findViewById(R.id.maxtemperatura);
        minimatemperatura = view.findViewById(R.id.mintemperatura);

        /**para recibir los datos del fragment donde se pincha el
         * cardview, desde el listadodelugares favoritos**/

        Bundle datosRecuperados = getArguments();
        String nlugar = datosRecuperados.getString("nombrelugar");
        String latitud = datosRecuperados.getString("latitud");
        String longitud = datosRecuperados.getString("longitud");


        /**ponemos el nombre del lugar en la pantalla**/
        nombrelugar.setText(nlugar);


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://api.worldweatheronline.com/premium/v1/marine.ashx?key=b453899f387040d3a31112938190912&format=json&tide=yes&q=" + latitud + "," + longitud + "";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                      Meteo meteo= QueryUtils.jsonParse(response);

                      //con la respuesta de meteo
                       String fech=meteo.getAstronomy().getDate();
                       String salsol=meteo.getAstronomy().getSunset();
                       String ponsol=meteo.getAstronomy().getSunrise();
                       String salluna=meteo.getAstronomy().getMoonset();
                       String puesluna=meteo.getAstronomy().getMoonrise();
                       String maxtemp=meteo.getAstronomy().getMaxtempC();
                       String mintemp=meteo.getAstronomy().getMintempC();
                       salidasol.setText(salsol);
                       puestasol.setText(ponsol);
                       salidaluna.setText(salluna);
                       puestaluna.setText(puesluna);
                       maximatemperaruta.setText(maxtemp);
                       minimatemperatura.setText(mintemp);
                       fecha.setText(fech);
                       adapter.addall(meteo.getTides());
                   }

               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       error.printStackTrace();
                   }

               });
        requestQueue.add(request);
        return view;
    }

}





