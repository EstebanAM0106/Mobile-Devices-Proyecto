package mx.ipn.escom.plantas.ui.informacion;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import mx.ipn.escom.plantas.R;

public class InformacionActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentInformacion,fragmentModificar;
    Button btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        fragmentInformacion = new InformacionPlantaFragment();
        fragmentModificar = new ModificarPlantaFragment();

        //Recibe valor de otro Activity
        int idPlanta = (int) this.getIntent().getExtras().getInt("idPlantaA");
        //Toast.makeText(getApplicationContext(),valor+"",Toast.LENGTH_SHORT).show();

        if(idPlanta >= 0){
            getSupportFragmentManager().beginTransaction().add(R.id.containerInformacionId,fragmentInformacion).commit();
            //Envia valor a otro Fragment
            Bundle bundle = new Bundle();
            bundle.putInt("idPlanta", idPlanta);


            fragmentInformacion.setArguments(bundle);

        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.containerInformacionId,fragmentModificar).commit();
            //Envia valor a otro Fragment
            Bundle bundle = new Bundle();
            bundle.putInt("idPlanta", idPlanta);
            fragmentModificar.setArguments(bundle);
        }

        ///CAMBIAR TITULO BARRA DE ESTADO
    }
}