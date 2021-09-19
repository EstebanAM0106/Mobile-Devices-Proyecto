package mx.ipn.escom.plantas.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.ui.informacion.InformacionActivity;

public class PlantasAdapter extends RecyclerView.Adapter<PlantasAdapter.ViewHolder>{
    private List<Plantas> plantasList;
    private Context context;

    public PlantasAdapter(List<Plantas> plantasList, Context context) {
        this.plantasList = plantasList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_plantas,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombreTemp = plantasList.get(position).getNombre();
        String nombreAltTemp = plantasList.get(position).getNombreAlt();
        if(nombreAltTemp == "" || nombreAltTemp == " "|| nombreAltTemp == null) {
            holder.txtNombre.setText(nombreTemp);
        }else {

            holder.txtNombre.setText(nombreTemp + " (" + nombreAltTemp + ")");
        }
        holder.txtNombre.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.txtNombre.setSingleLine();
        holder.txtNombre.setSelected(true);
        Glide.with(context).load(plantasList.get(position).getImagenURL())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .centerCrop()
                .into(holder.imgPlanta);

        if(plantasList.get(position).getEsFavorito() == true){
            holder.btnStar.setImageResource(R.drawable.ic_star);
        }else{
            holder.btnStar.setImageResource(R.drawable.ic_empty_star);
        }


        holder.btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(plantasList.get(position).getEsFavorito()==false){
                    holder.btnStar.setImageResource(R.drawable.ic_star);
                    plantasList.get(position).setEsFavorito(true);

                }else{
                    holder.btnStar.setImageResource(R.drawable.ic_empty_star);
                    plantasList.get(position).setEsFavorito(false);
                }

            }
        });

        holder.imgPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent informacion = new Intent(context, InformacionActivity.class);
                //Enviar valor a otro Activity
                informacion.putExtra("idPlantaA", plantasList.get(position).getId());
                view.getContext().startActivity(informacion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPlanta;
        private TextView txtNombre;
        private ImageButton btnStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPlanta =itemView.findViewById((R.id.imgId));
            txtNombre = itemView.findViewById((R.id.nombreId));
            btnStar = itemView.findViewById((R.id.starId));
        }
    }
}
