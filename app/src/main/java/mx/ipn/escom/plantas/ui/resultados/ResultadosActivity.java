package mx.ipn.escom.plantas.ui.resultados;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.ipn.escom.plantas.Adapter.CargarBaseDatosResultados;
import mx.ipn.escom.plantas.Adapter.Plantas;
import mx.ipn.escom.plantas.Adapter.PlantasAdapter;
import mx.ipn.escom.plantas.R;

public class ResultadosActivity extends AppCompatActivity {

    RecyclerView recyclerPlantas;
    PlantasAdapter plantasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        String frase = getIntent().getStringExtra("Frase");
        getSupportActionBar().setTitle("Resultados: "+frase);
        inicializarElementos(frase);


    }
    private void inicializarElementos(String frase) {
        recyclerPlantas = findViewById(R.id.recyclerResId);
        recyclerPlantas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        /*List<Plantas> plantasList= new ArrayList<>();
        for(int i = 0; i<5; i++)
        {
                plantasList.add(new Plantas( i+1, frase+i, "axd","https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Labiata.jpg/722px-Labiata.jpg",false));
        }
        plantasList.add(new Plantas( 1, frase, "axd","https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Labiata.jpg/722px-Labiata.jpg",false));*/
        CargarBaseDatosResultados db = new CargarBaseDatosResultados(getApplicationContext(),recyclerPlantas,1,frase);
    }
    public static void cargar(List<Plantas> plantasList, Context context, RecyclerView recyclerPlantas){
        PlantasAdapter plantasAdapter = new PlantasAdapter(plantasList,context);
        recyclerPlantas.setAdapter(plantasAdapter);
    }

}