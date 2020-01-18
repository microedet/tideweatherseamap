package pastor.vicente.tideweathersea.main;

import android.util.Log;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pastor.vicente.tideweathersea.Astronomy;

public final class QueryUtils {
    //constructor
    private QueryUtils() {
    }

    public static Meteo jsonParse(JSONObject response) {
       Meteo meteo= new Meteo();
        try {
            JSONObject data = response.getJSONObject("data");
            JSONArray weather = data.getJSONArray("weather");
            for (int i = 0; i < 1; i++) {
                JSONObject datosweather = weather.getJSONObject(i);
                String date = datosweather.getString("date");
                JSONArray astronomy = datosweather.getJSONArray("astronomy");
                JSONObject datosastronomy = astronomy.getJSONObject(0);
                String sunrise = datosastronomy.getString("sunrise");
                String sunset = datosastronomy.getString("sunset");
                String moonrise = datosastronomy.getString("moonrise");
                String moonset = datosastronomy.getString("moonset");
                String maxtempC = datosweather.getString("maxtempC");
                String mintempC = datosweather.getString("mintempC");
                JSONArray tides = datosweather.getJSONArray("tides");
                JSONObject datostides = tides.getJSONObject(0);
                ArrayList<Tides> mareasdeldia = new ArrayList<>();

                if (datostides.has("tide_data")){
                    JSONArray tide_data = datostides.getJSONArray("tide_data");
                    for (int h = 0; h < tide_data.length(); h++) {
                        JSONObject datostide_data = tide_data.getJSONObject(h);
                        String tideTime = datostide_data.getString("tideTime");
                        String tideHeight_mt = datostide_data.getString("tideHeight_mt");
                        Tides mareadia = new Tides(tideTime, tideHeight_mt);
                        mareasdeldia.add(mareadia);
                    }
                }

                Astronomy astronomyObject = new Astronomy(date,sunset,sunrise,moonset,moonrise,maxtempC,mintempC);
                meteo=new Meteo(astronomyObject, mareasdeldia);
            }//del for
        }//del try
        catch (JSONException e) {
            e.printStackTrace();
        }//del catch

        return meteo;
    }
}