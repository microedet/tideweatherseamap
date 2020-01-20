package pastor.vicente.tideweathersea.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;
import pastor.vicente.tideweathersea.R;


public class TidesAdapter  extends ArrayAdapter<Tides> {
    ArrayList<Tides> mareas;
    Context context;

    public TidesAdapter(Context context, ArrayList<Tides> mareas) {
        super(context,0,mareas);
        this.context=context;
        this.mareas=mareas;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Tides tides = getItem(position);
        View view=convertView;
        view = LayoutInflater.from(getContext()).inflate(R.layout.itemtide,parent,false); // Sirve para inflar el list
        TextView horamarea = (TextView) view.findViewById(R.id.horamarea);
        TextView altura=(TextView)view.findViewById(R.id.altura);
        horamarea.setText(tides.getTideTime());
        altura.setText(tides.getTideHeight_mt());

        return view;
    }

    public void addall(List<Tides> marea){
        this.mareas.clear();
        this.mareas.addAll(marea);
        notifyDataSetChanged();

    }

}
