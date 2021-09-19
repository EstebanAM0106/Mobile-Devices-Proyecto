package mx.ipn.escom.plantas.ui.informacion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import mx.ipn.escom.plantas.Database.CargarBaseDatosInformacion;
import mx.ipn.escom.plantas.Adapter.PlantasInformacion;
import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentInformacionPlantaBinding;

public class InformacionPlantaFragment extends Fragment  implements View.OnClickListener {
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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.title_informacion);
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
        CargarBaseDatosInformacion bd =  new CargarBaseDatosInformacion(
                idPlanta, context, view, getResources(),
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
                txtUltimoCambio);




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
                transaction.replace(R.id.containerInformacionId,fragmentModificar,"F_MODIFICAR").commit();
            }
        });

    }

    public static void cargar(PlantasInformacion plantasInformacion, Context context, View view, Resources resources,
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
                              String ultimoUsuarioNombre) {
        if(plantasInformacion.getNombreAlt() == "" || plantasInformacion.getNombreAlt() == " " || plantasInformacion.getNombreAlt() == null)
        {
            txtNombre.setText(plantasInformacion.getNombre());
        }else{
            txtNombre.setText(plantasInformacion.getNombre() + " (" + plantasInformacion.getNombreAlt() + ")");
        }
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
        txtUltimoCambio.setText(resources.getText(R.string.ultima_edicion)+" "+ultimoUsuarioNombre);
        /*if(plantasInformacion.getEsMaceta()){
            imgDondePlantar.setImageResource(R.drawable.ic_maceta);
        }else{
            imgDondePlantar.setImageResource(R.drawable.ic_jardin);
        }*/
        imgDondePlantar.setImageResource(plantasInformacion.getEsMaceta()?R.drawable.ic_maceta:R.drawable.ic_jardin);
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
        imgPlanta.setImageDrawable(resources.getDrawable(R.drawable.error, context.getTheme()));
        Glide.with(context)
                .asBitmap()
                .load(plantasInformacion.getImagenURL())
                .placeholder(R.drawable.loading)
                .centerCrop()
                .into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int width  = resource.getWidth();
                int height = resource.getHeight();
                int newWidth = (height > width) ? width : height;
                int newHeight = (height > width)? width : height;
                int cropW = (width - height) / 2;
                cropW = (cropW < 0)? 0: cropW;
                int cropH = (height - width) / 2;
                cropH = (cropH < 0)? 0: cropH;
                Bitmap cropImg = Bitmap.createBitmap(resource, cropW, cropH, newWidth, newHeight);
                /*int cropH = (height - width) / 2 * (3 / 2);
                cropH = (cropH < 0)? 0: cropH;
                Bitmap cropImg = Bitmap.createBitmap(resource, cropW, cropH, newWidth, newHeight*2/3);*/
                imgPlanta.setImageBitmap(cropImg);
            }
            /*@Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                imgPlanta.setImageBitmap(resource);
            }*/
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                imgPlanta.setImageDrawable(placeholder);
            }
        });
        imgDondePlantar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plantasInformacion.getEsMaceta())
                    Toast.makeText(context, resources.getText(R.string.maceta), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, resources.getText(R.string.jardinera), Toast.LENGTH_SHORT).show();
            }
        });
        imgLuminosidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plantasInformacion.getLuminosidad() == 2)
                    Toast.makeText(context, resources.getText(R.string.luz), Toast.LENGTH_SHORT).show();
                else if (plantasInformacion.getLuminosidad() == 1)
                    Toast.makeText(context, resources.getText(R.string.media_sombra), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, resources.getText(R.string.sombra), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}