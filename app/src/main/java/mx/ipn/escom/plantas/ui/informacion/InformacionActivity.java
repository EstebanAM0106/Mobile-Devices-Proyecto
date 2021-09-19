package mx.ipn.escom.plantas.ui.informacion;

import android.content.Intent;
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
    int idPlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        fragmentInformacion = new InformacionPlantaFragment();
        fragmentModificar = new ModificarPlantaFragment();

        //Recibe valor de otro Activity
        idPlanta = (int) this.getIntent().getExtras().getInt("idPlantaA");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(idPlanta >= 0){
            getSupportFragmentManager().beginTransaction().add(R.id.containerInformacionId,fragmentInformacion,"F_INFORMACION").commit();
            //Envia valor a otro Fragment
            Bundle bundle = new Bundle();
            bundle.putInt("idPlanta", idPlanta);


            fragmentInformacion.setArguments(bundle);

        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.containerInformacionId,fragmentModificar,"F_MODIFICAR").commit();
            //Envia valor a otro Fragment
            Bundle bundle = new Bundle();
            bundle.putInt("idPlanta", idPlanta);
            fragmentModificar.setArguments(bundle);
        }
    }
    @Override
    public void onBackPressed()
    {
        ModificarPlantaFragment myFragment = (ModificarPlantaFragment) getSupportFragmentManager().findFragmentByTag("F_MODIFICAR");
        if (myFragment != null && myFragment.isVisible()) {
            if(idPlanta >=0)
            {
                Intent informacion = new Intent(getApplicationContext(), InformacionActivity.class);
                informacion.putExtra("idPlantaA", idPlanta);
                startActivity(informacion);
            }
        }
        InformacionPlantaFragment myFragment2 = (InformacionPlantaFragment) getSupportFragmentManager().findFragmentByTag("F_INFORMACION");
        if (myFragment2 != null && myFragment2.isVisible()) {
        }
        this.finish();
        super.onBackPressed();
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}