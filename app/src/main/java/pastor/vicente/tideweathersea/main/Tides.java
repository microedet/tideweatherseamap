package pastor.vicente.tideweathersea.main;

public class Tides {
    String tideTime,tideHeight_mt;

    public Tides(String tideTime, String tideHeight_mt) {
        this.tideTime = tideTime;
        this.tideHeight_mt = tideHeight_mt;
    }

    public Tides() {
    }

    public String getTideTime() {
        return tideTime;
    }

    public void setTideTime(String tideTime) {
        this.tideTime = tideTime;
    }

    public String getTideHeight_mt() {
        return tideHeight_mt;
    }

    public void setTideHeight_mt(String tideHeight_mt) {
        this.tideHeight_mt = tideHeight_mt;
    }
}
