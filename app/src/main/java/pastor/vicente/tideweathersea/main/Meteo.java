package pastor.vicente.tideweathersea.main;

import java.util.ArrayList;

import pastor.vicente.tideweathersea.Astronomy;

public class Meteo {
    Astronomy astronomy;
    ArrayList <Tides> tides=new ArrayList<Tides>();

    public Meteo(Astronomy astronomy, ArrayList<Tides> tides) {
        this.astronomy = astronomy;
        this.tides = tides;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public ArrayList<Tides> getTides() {
        return tides;
    }

    public void setTides(ArrayList<Tides> tides) {
        this.tides = tides;
    }
}
