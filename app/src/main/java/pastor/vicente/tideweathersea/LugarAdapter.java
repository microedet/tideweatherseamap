package pastor.vicente.tideweathersea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {

    private List<Lugar> lugares;
    private Context context;
    private OnButtonClikedListener listener;

    public LugarAdapter(Context context,List<Lugar> lugares, OnButtonClikedListener listener) {

        this.context = context;
        this.lugares = lugares;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.itemlugar, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Lugar l = lugares.get(position);


        viewHolder.nombreLugar.setText(l.getNombreLugar());
        viewHolder.latitud.setText(l.getLatitud());
        viewHolder.longitud.setText(l.getLongitud());

        viewHolder.editProductIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonCliked(view, lugares.get(position));

            }
        });

        viewHolder.deleteProductIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonCliked(view, lugares.get(position));
            }
        });

    }



    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public void addLugares(List<Lugar> listaProductos){
        lugares =listaProductos;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreLugar;
        TextView latitud;
        TextView longitud;
        ImageView editProductIcon;
        ImageView deleteProductIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreLugar = itemView.findViewById(R.id.Lugar);
            latitud = itemView.findViewById(R.id.Latitud);
            longitud=itemView.findViewById(R.id.Longitud);
            editProductIcon = itemView.findViewById(R.id.udpateicon);
            deleteProductIcon = itemView.findViewById(R.id.deleteicon);


        }

    }

    public interface OnButtonClikedListener {
        void onButtonCliked(View v, Lugar lugar);
    }
}
