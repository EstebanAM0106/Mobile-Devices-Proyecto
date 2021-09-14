package mx.ipn.escom.plantas.ui.informacion;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import mx.ipn.escom.plantas.Adapter.CargarBaseDatosInformacion;
import mx.ipn.escom.plantas.Adapter.PlantasInformacion;
import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentInformacionPlantaBinding;

public class InformacionPlantaFragment extends Fragment {
    FragmentInformacionPlantaBinding binding;
    Button btnModificar;


    FragmentTransaction transaction;
    Fragment fragmentInformacion,fragmentModificar;


    public InformacionPlantaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInformacionPlantaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //root=inflater.inflate(R.layout.fragment_informacion_planta, container, false);

        //Recibir valor
        int idPlanta = getArguments().getInt("idPlanta");
        inicializarElementos(root,idPlanta,getContext());


        return root;
    }
    public void inicializarElementos(View view, int idPlanta,Context context) {
        ImageView imgPlanta = (ImageView) view.findViewById(R.id.infImgId);
        TextView txtNombre = (TextView) view.findViewById(R.id.infNombre);
        CargarBaseDatosInformacion bd =  new CargarBaseDatosInformacion(idPlanta,context,view,imgPlanta,txtNombre);


        Toast.makeText(context,idPlanta+" yoh", Toast.LENGTH_SHORT).show();
        btnModificar = (Button) view.findViewById(R.id.infBtnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentModificar = new ModificarPlantaFragment();
                transaction= getParentFragmentManager().beginTransaction();

                //Enviar valor a otro Fragment
                Bundle bundle = new Bundle();
                bundle.putInt("idPlanta", idPlanta);
                fragmentModificar.setArguments(bundle);
                transaction.replace(R.id.containerInformacionId,fragmentModificar).commit();
            }
        });

    }

    public static void cargar(PlantasInformacion plantasInformacion, Context context, View view, ImageView imgPlanta, TextView txtNombre,Button btnModificar){

        //Toast.makeText(context,"cargar "+plantaId, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,"encargar", Toast.LENGTH_SHORT).show();
        /*Glide.with(context).load(plantasInformacion.getImagenURL())
                .placeholder(R.drawable.loading)
                .centerCrop()
                .into(imgPlanta);*/
        txtNombre.setText(plantasInformacion.getNombre());

    }
}