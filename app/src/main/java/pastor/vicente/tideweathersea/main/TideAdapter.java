package pastor.vicente.tideweathersea.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pastor.vicente.tideweathersea.R;

public class TideAdapter extends RecyclerView.Adapter<TideAdapter.ViewHolder> {
    private List<Tides> mareas;
    private Context context;

    public TideAdapter(List<Tides> mareas, Context context) {
        this.mareas = mareas;
        this.context = context;
    }


    @NonNull
    @Override
    public TideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.itemtide, viewGroup, false);
        TideAdapter.ViewHolder viewHolder = new TideAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Tides t=mareas.get(position);

        viewHolder.horamarea.setText(t.getTideTime());
        viewHolder.alturamarea.setText(t.getTideHeight_mt());
    }
    @Override
    public int getItemCount() {
        return  mareas.size();
    }

    public void addMareas(List<Tides> listadomareas){
        mareas =listadomareas;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView horamarea;
        TextView alturamarea;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            horamarea=itemView.findViewById(R.id.horamarea);
            alturamarea=itemView.findViewById(R.id.altura);

        }

    }
}
