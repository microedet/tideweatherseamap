package pastor.vicente.tideweathersea.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import pastor.vicente.tideweathersea.R;
import pastor.vicente.tideweathersea.main.Lugar;
import pastor.vicente.tideweathersea.main.Meteo;


public class DatosMeteoFragment extends Fragment    {
    TextView textView;
    TextView textView2;
    String date;
    private RequestQueue requestQueue;
    //View view;




    public DatosMeteoFragment() {
        // Required empty public constructor
    }

    public static DatosMeteoFragment newInstance(){
        DatosMeteoFragment datosMeteoFragment=new DatosMeteoFragment();
        Bundle args=new Bundle();
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
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_datosmeteo, container, false);
        requestQueue = Volley.newRequestQueue(view.getContext());
        textView = view.findViewById(R.id.textViewResult);
        //textView.setText(date);
        textView2 = view.findViewById(R.id.textViewResult2);

        //consulta(v);

        /**para recibir los datos del fragment donde se pincha el
         * cardview, desde el listadodelugares favoritos**/
        Bundle datosRecuperados = getArguments();
        String latitud = datosRecuperados.getString("latitud");
        String longitud= datosRecuperados.getString("longitud");
        Log.e("datosvicente","latitud "+latitud);
       // final Meteo meteo=new Meteo();

        jsonParse( latitud, longitud,view);

       return view;
    }




   private  void jsonParse(String latitud, String longitud, View view) {

        final   TextView  textView = view.findViewById(R.id.textViewResult);
        //final   TextView  textView1=view.findViewById(R.id.textViewResult2);

                String url = "https://api.worldweatheronline.com/premium/v1/marine.ashx?key=b453899f387040d3a31112938190912&format=json&tide=yes&q="+latitud+","+longitud+"";


        Lugar lugar=new Lugar();
         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

             //   StringRequest request = new StringRequest(Request.Method.GET, url,null, new Response.Listener<String>() {
               // StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                //        new Response.Listener<String>() {


                @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject data=response.getJSONObject("data" );
                    JSONArray weather=data.getJSONArray("weather");

                    for (int i=0;i<1/*weather.length()*/;i++) {

                        //datos para mareas
                        String tideTime=" ",tideHeight_mt=" ",tideDateTime=" ",tide_type=" ";

                        JSONObject datosweather = weather.getJSONObject(i);
                        String date = datosweather.getString("date");
                        //textView.setText(date);
                        Log.i("LLIRIA", date);

                        JSONArray astronomy = datosweather.getJSONArray("astronomy");
                        JSONObject datosastronomy = astronomy.getJSONObject(0);
                        String sunrise = datosastronomy.getString("sunrise");
                        String sunset = datosastronomy.getString("sunset");
                        String moonrise = datosastronomy.getString("moonrise");
                        String moonset = datosastronomy.getString("moonset");

                        String maxtempC = datosweather.getString("maxtempC");
                        String mintempC = datosweather.getString("mintempC");

                        JSONArray tides=datosweather.getJSONArray("tides");
                        JSONObject datostides=tides.getJSONObject(0);
                        JSONArray  tide_data=datostides.getJSONArray("tide_data");
                        for(int h=0;h<tide_data.length();h++){
                            JSONObject datostide_data = tide_data.getJSONObject(h);
                            tideTime = datostide_data.getString("tideTime");
                            tideHeight_mt = datostide_data.getString("tideHeight_mt");
                            tideDateTime = datostide_data.getString("tideDateTime");
                            tide_type = datostide_data.getString("tide_type");

                        }

                        textView.setText(date);
                        textView2.setText(sunrise);
                       // textView.setText(date);
                       // textView2.


                       /* lugar.setLatitud(date);
                       textView.append("fecha: " + date + "\n"
                                + "salida sol: " + sunrise + "\n"
                                + "puesta sol: " + sunset + "\n"
                                + "salida luna: " + moonrise + "\n"
                                + "puesta luna: " + moonset + "\n"
                                + "Temperatura max: " + maxtempC + "\n"
                                + "Temperatura min: " + mintempC + "\n"
                                + "Hora de Marea: "+ tideTime + "\n"
                                + "Altura Marea: "+ tideHeight_mt +"\n"
                                + "Hora de Marea: "+ tideDateTime +"\n"
                                + "tipo marea: "+ tide_type +"\n\n");
*/

                    }//del for



                }//del try



                catch (JSONException e) {
                    e.printStackTrace();
                }//del catch


            }




        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }


}
