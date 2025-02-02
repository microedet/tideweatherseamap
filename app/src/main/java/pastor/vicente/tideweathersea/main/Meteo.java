package pastor.vicente.tideweathersea.main;

import java.util.ArrayList;

public class Meteo {
    Astronomy astronomy;
    ArrayList <Tides> tides=new ArrayList<Tides>();

    public Meteo() {
    }

    public Meteo(Astronomy astronomy, ArrayList<Tides> tides) {
        this.astronomy = astronomy;
        this.tides = tides;
    }

    public Meteo(Astronomy astronomy) {
        this.astronomy = astronomy;

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
