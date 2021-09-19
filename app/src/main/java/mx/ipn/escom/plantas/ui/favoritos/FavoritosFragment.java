package mx.ipn.escom.plantas.ui.favoritos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.ipn.escom.plantas.Database.CargarBaseDatosFavoritos;
import mx.ipn.escom.plantas.Adapter.Plantas;
import mx.ipn.escom.plantas.Adapter.PlantasAdapter;
import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentFavoritosBinding;

public class FavoritosFragment extends Fragment {
    RecyclerView recyclerPlantas;
    PlantasAdapter plantasAdapter;

    //private FavoritosViewModel favoritosViewModel;
    private FragmentFavoritosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*favoritosViewModel =
                new ViewModelProvider(this).get(FavoritosViewModel.class);*/

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inicializarElementos(root);
        //final TextView textView = binding.textDashboard;
        /*favoritosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/
        return root;
    }
    private void inicializarElementos(View vista) {
        recyclerPlantas = vista.findViewById(R.id.recyclerFavId);
        recyclerPlantas.setLayoutManager(new LinearLayoutManager(getContext()));/*
        List<Plantas> plantasList= new ArrayList<>();
        for(int i = 0; i<10; i++)
        {
            plantasList.add(new Plantas(i, "Favorito"+i, "axd","https://upload.wikimedia.org/wikipedia/commons/a/a5/Nightbloomingcactus.jpg",true));
        }
        plantasAdapter = new PlantasAdapter(plantasList,getContext());
        recyclerPlantas.setAdapter(plantasAdapter);*/
        CargarBaseDatosFavoritos db = new CargarBaseDatosFavoritos(getContext(),recyclerPlantas,1);
    }
    public static void cargar(List<Plantas> plantasList, Context context,RecyclerView recyclerPlantas){
        PlantasAdapter plantasAdapter = new PlantasAdapter(plantasList,context);
        recyclerPlantas.setAdapter(plantasAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}