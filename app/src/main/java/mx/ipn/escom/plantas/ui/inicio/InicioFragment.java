package mx.ipn.escom.plantas.ui.inicio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.ipn.escom.plantas.Database.CargarBaseDatosInicio;
import mx.ipn.escom.plantas.Adapter.Plantas;
import mx.ipn.escom.plantas.Adapter.PlantasAdapter;
import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentInicioBinding;
import mx.ipn.escom.plantas.ui.informacion.InformacionActivity;

public class InicioFragment extends Fragment {

    RecyclerView recyclerPlantas;
    PlantasAdapter plantasAdapter;

    ImageButton botonAgregarPlantaId;


    private FragmentInicioBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        inicializarElementos(root);

        botonAgregarPlantaId = (ImageButton) root.findViewById(R.id.botonAgregarPlantaId);
        botonAgregarPlantaId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent informacion = new Intent(getContext(), InformacionActivity.class);
                //Enviar valor a otro Activity
                informacion.putExtra("idPlantaA",-1);
                view.getContext().startActivity(informacion);
            }
        });

        return root;
    }

    public void inicializarElementos(View vista) {
        recyclerPlantas = vista.findViewById(R.id.recyclerId);
        recyclerPlantas.setLayoutManager(new LinearLayoutManager(getContext()));
        /*List<Plantas> plantasList= new ArrayList<>();
        for(int i = 0; i<10; i++)
        {
            plantasList.add(new Plantas(i, "Planta x #"+i, "Nombre Cientifico","https://s1.eestatic.com/2021/04/29/actualidad/577453913_183658433_1706x960.jpg", false));
        }*/
        CargarBaseDatosInicio db = new CargarBaseDatosInicio(getContext(),recyclerPlantas,1);

    }
    public static void cargar(List<Plantas> plantasList, Context context, RecyclerView recyclerPlantas){
        PlantasAdapter plantasAdapter = new PlantasAdapter(plantasList,context);
        recyclerPlantas.setAdapter(plantasAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}