package pastor.vicente.tideweathersea;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Astronomy {
    String fecha,sunrise, sunset, moonrise, moonset,maxtempC,mintempC;

    public Astronomy(String fecha,String sunrise, String sunset, String moonrise, String moonset, String maxtempC, String mintempC) {

        this.fecha= fecha;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonrise = moonrise;
        this.moonset = moonset;
        this.maxtempC = maxtempC;
        this.mintempC = mintempC;
    }

    public Astronomy(){

    }

    public String getDate() {

        return fecha;
    }

        public void setDate(String date) {
        this.fecha = fecha;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public String getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(String maxtempC) {
        this.maxtempC = maxtempC;
    }

    public String getMintempC() {
        return mintempC;
    }

    public void setMintempC(String mintempC) {
        this.mintempC = mintempC;
    }


    public String formatdate(String fecha){

        Date date = new Date(fecha);
        SimpleDateFormat sd = new SimpleDateFormat("LLL dd,yyyy");
        return sd.format(date);

    }
}
