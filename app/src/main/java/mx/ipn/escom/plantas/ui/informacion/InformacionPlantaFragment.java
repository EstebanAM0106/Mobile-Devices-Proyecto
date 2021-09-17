package mx.ipn.escom.plantas.ui.informacion;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

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
        inicializarElementos(root,idPlanta,requireContext());


        return root;
    }
    public void inicializarElementos(View view, int idPlanta,Context context) {
        ImageView imgPlanta = (ImageView) view.findViewById(R.id.infImgId);
        TextView txtNombre = (TextView) view.findViewById(R.id.infNombre);
        TextView txtReino = (TextView) view.findViewById(R.id.infReino);
        TextView txtDivision = (TextView) view.findViewById(R.id.infDivision);
        TextView txtClase = (TextView) view.findViewById(R.id.infClase);
        TextView txtOrden = (TextView) view.findViewById(R.id.infOrden);
        TextView txtFamilia = (TextView) view.findViewById(R.id.infFamilia);
        TextView txtGenero = (TextView) view.findViewById(R.id.infGenero);
        TextView txtEspecie = (TextView) view.findViewById(R.id.infEspecie);
        TextView txtAltura = (TextView) view.findViewById(R.id.infAltura);
        TextView txtDiametro = (TextView) view.findViewById(R.id.infDiametro);
        TextView txtCicloRiego = (TextView) view.findViewById(R.id.infCicloRiego);
        ImageView imgDondePlantar = (ImageView) view.findViewById(R.id.infDondePlantar);
        ImageView imgLuminosidad = (ImageView) view.findViewById(R.id.infLuminosidad);
        TextView txtOtrasRecomendaciones = (TextView) view.findViewById(R.id.infOtrasRecomendaciones);
        TextView txtDescripcion = (TextView) view.findViewById(R.id.infDescripcion);
        TextView txtUltimoCambio = (TextView) view.findViewById(R.id.infUltimoCambio);
        RequestManager rm = Glide.with(context);

        Button btnActualizar = (Button) view.findViewById(R.id.infBtnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context," hola asinoes", Toast.LENGTH_SHORT).show();
                Glide.with(context).load(btnActualizar.getText().toString())
                        .placeholder(R.drawable.loading)
                        .centerCrop()
                        .into(imgPlanta);
            }
        });

        CargarBaseDatosInformacion bd =  new CargarBaseDatosInformacion(
                idPlanta,context,view,
                imgPlanta,
                txtNombre,
                txtReino,
                txtDivision,
                txtClase,
                txtOrden,
                txtFamilia,
                txtGenero,
                txtEspecie,
                txtAltura,
                txtDiametro,
                txtCicloRiego,
                imgDondePlantar,
                imgLuminosidad,
                txtOtrasRecomendaciones,
                txtDescripcion,
                txtUltimoCambio,
                btnActualizar,
                rm);

        //Toast.makeText(context,idPlanta+" yoh", Toast.LENGTH_SHORT).show();



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

    public static void cargar(PlantasInformacion plantasInformacion,
                              Context context,
                              View view,
                              ImageView imgPlanta,
                              TextView txtNombre,
                              TextView txtReino,
                              TextView txtDivision,
                              TextView txtClase,
                              TextView txtOrden,
                              TextView txtFamilia,
                              TextView txtGenero,
                              TextView txtEspecie,
                              TextView txtAltura,
                              TextView txtDiametro,
                              TextView txtCicloRiego,
                              ImageView imgDondePlantar,
                              ImageView imgLuminosidad,
                              TextView txtOtrasRecomendaciones,
                              TextView txtDescripcion,
                              TextView txtUltimoCambio,
                              Button btnActualizar,
                              RequestManager rm) {

        //Toast.makeText(context,"cargar "+plantaId, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,"encargar", Toast.LENGTH_SHORT).show();;
        txtNombre.setText(plantasInformacion.getNombre());
        txtReino.setText(plantasInformacion.getReino());
        txtDivision.setText(plantasInformacion.getDivision());
        txtClase.setText(plantasInformacion.getClase());
        txtOrden.setText(plantasInformacion.getOrden());
        txtFamilia.setText(plantasInformacion.getFamilia());
        txtGenero.setText(plantasInformacion.getGenero());
        txtEspecie.setText(plantasInformacion.getEspecie());
        txtAltura.setText(plantasInformacion.getAltura()+" "+plantasInformacion.getAlturaUnidad());
        txtDiametro.setText(plantasInformacion.getDiametro()+" "+plantasInformacion.getDiametroUnidad());
        txtCicloRiego.setText(plantasInformacion.getCicloRiego()+" "+plantasInformacion.getCicloRiegoUnidad());
        txtOtrasRecomendaciones.setText(plantasInformacion.getOtrasRecomendaciones());
        txtDescripcion.setText(plantasInformacion.getDescripcion());
        txtUltimoCambio.setText("Última edición: "+plantasInformacion.getUltimoUsuario());
        if(plantasInformacion.getEsMaceta()){
            imgDondePlantar.setImageResource(R.drawable.ic_maceta);
        }else{
            imgDondePlantar.setImageResource(R.drawable.ic_jardin);
        }
        if(plantasInformacion.getLuminosidad()==0){
            imgLuminosidad.setImageResource(R.drawable.ic_luna);
            imgLuminosidad.setColorFilter(ContextCompat.getColor(context,R.color.sombra_color));
        }else if(plantasInformacion.getLuminosidad()==1){
            imgLuminosidad.setImageResource(R.drawable.ic_medio_sol);
            imgLuminosidad.setColorFilter(ContextCompat.getColor(context,R.color.media_sombra_color));

        }else {
            imgLuminosidad.setImageResource(R.drawable.ic_sol);
            imgLuminosidad.setColorFilter(ContextCompat.getColor(context,R.color.sol_color));
        }
        btnActualizar.setText(plantasInformacion.getImagenURL());/*
        btnActualizar.performContextClick();
        btnActualizar.performClick();
        btnActualizar.callOnClick();*/
        //workerThread(context,imgPlanta, plantasInformacion.getImagenURL());




    }


}