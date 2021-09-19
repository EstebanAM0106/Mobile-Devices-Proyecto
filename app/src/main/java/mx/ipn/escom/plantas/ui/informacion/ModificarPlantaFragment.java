package mx.ipn.escom.plantas.ui.informacion;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import mx.ipn.escom.plantas.Adapter.CargarBaseDatosModificar;
import mx.ipn.escom.plantas.Adapter.PlantasInformacion;
import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentModificarPlantaBinding;


public class ModificarPlantaFragment extends Fragment {
    FragmentModificarPlantaBinding binding;
    Button btnGuardar;
    Button btnActualizar;
    EditText edtURL;
    ImageView imgPlanta;

    FragmentTransaction transaction;
    Fragment fragmentInformacion,fragmentModificar;



    public ModificarPlantaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {// Inflate the layout for this fragment
        binding = FragmentModificarPlantaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Recibir valor
        int idPlanta = getArguments().getInt("idPlanta");
        Toast.makeText(getContext(),idPlanta+"", Toast.LENGTH_SHORT).show();

        //getActivity().getActionBar().setTitle("asies");
        if(idPlanta >= 0) {
            btnGuardar = (Button) root.findViewById(R.id.modBtnGuardar);
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentInformacion = new InformacionPlantaFragment();
                    transaction = getParentFragmentManager().beginTransaction();

                    //Enviar valor a otro Fragment
                    Bundle bundle = new Bundle();
                    bundle.putInt("idPlanta", idPlanta);
                    fragmentInformacion.setArguments(bundle);
                    transaction.replace(R.id.containerInformacionId, fragmentInformacion).commit();
                }
            });
        }else{
            btnGuardar = (Button) root.findViewById(R.id.modBtnGuardar);
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnActualizar.performClick();
                    getActivity().finish();
                }
            });

        }

        btnActualizar = (Button) root.findViewById(R.id.modBtnRecargar);
        edtURL = (EditText) root.findViewById(R.id.modUrlImg);
        edtURL.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnActualizar.performClick();
                    return true;
                }
                return false;
            }
        });
        imgPlanta = (ImageView) root.findViewById(R.id.modImgId);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getContext()).load(edtURL.getText().toString())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .centerCrop()
                        .into(imgPlanta);
            }
        });
        if(idPlanta>=0) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_modificar);
            inicializarElementos(root, idPlanta, getContext());
        }
        else {
            //Glide.with(getContext()).load("https://source.unsplash.com/collection/518317")
            Glide.with(getContext()).load("https://source.unsplash.com/user/mj3824")
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .into(imgPlanta);
            btnGuardar.setText(R.string.boton_agregar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.title_agregar);
        }

        return root;
    }

    public void inicializarElementos(View view, int idPlanta, Context context) {
        //ImageView imgPlanta = (ImageView) view.findViewById(R.id.infImgId);
        EditText edtNombre = (EditText) view.findViewById(R.id.modNombre);
        EditText edtNombreAlt = (EditText) view.findViewById(R.id.modNombreAlt);
        //EditText edtURL = (EditText) view.findViewById(R.id.modUrlImg);
        EditText edtReino = (EditText) view.findViewById(R.id.modReino);
        EditText edtDivision = (EditText) view.findViewById(R.id.modDivision);
        EditText edtClase = (EditText) view.findViewById(R.id.modClase);
        EditText edtOrden = (EditText) view.findViewById(R.id.modOrden);
        EditText edtFamilia = (EditText) view.findViewById(R.id.modFamilia);
        EditText edtGenero = (EditText) view.findViewById(R.id.modGenero);
        EditText edtEspecie = (EditText) view.findViewById(R.id.modEspecie);
        EditText edtAlturaVal = (EditText) view.findViewById(R.id.modAltVal);
        EditText edtAlturaUni = (EditText) view.findViewById(R.id.modAltUni);
        EditText edtDiametroVal = (EditText) view.findViewById(R.id.modDiaVal);
        EditText edtDiametroUni = (EditText) view.findViewById(R.id.modDiaUni);
        EditText edtCicloRiegoVal = (EditText) view.findViewById(R.id.modCicVal);
        EditText edtCicloRiegoUni = (EditText) view.findViewById(R.id.modCicUni);
        RadioButton rbtMaceta = (RadioButton) view.findViewById(R.id.modMaceta);
        RadioButton rbtJardin = (RadioButton) view.findViewById(R.id.modJardin);
        RadioButton rbtLuz = (RadioButton) view.findViewById(R.id.modLuz);
        RadioButton rbtMediaSombra = (RadioButton) view.findViewById(R.id.modMediaSombra);
        RadioButton rbtSombra = (RadioButton) view.findViewById(R.id.modSombra);
        EditText edtOtrasRecomendaciones = (EditText) view.findViewById(R.id.modOtrasRecomendaciones);
        EditText edtDescripcion = (EditText) view.findViewById(R.id.modDescripcion);


        CargarBaseDatosModificar bd =  new CargarBaseDatosModificar(
                idPlanta, context, view, getResources(),
                this.imgPlanta,
                edtNombre,
                edtNombreAlt,
                this.edtURL,
                edtReino,
                edtDivision,
                edtClase,
                edtOrden,
                edtFamilia,
                edtGenero,
                edtEspecie,
                edtAlturaVal,
                edtAlturaUni,
                edtDiametroVal,
                edtDiametroUni,
                edtCicloRiegoUni,
                edtCicloRiegoVal,
                rbtMaceta,
                rbtJardin,
                rbtLuz,
                rbtMediaSombra,
                rbtSombra,
                edtOtrasRecomendaciones,
                edtDescripcion);
        //Toast.makeText(context,idPlanta+" yoh", Toast.LENGTH_SHORT).show();

        btnGuardar = (Button) view.findViewById(R.id.modBtnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentInformacion = new InformacionPlantaFragment();
                transaction = getParentFragmentManager().beginTransaction();

                //Enviar valor a otro Fragment
                Bundle bundle = new Bundle();
                bundle.putInt("idPlanta", idPlanta);
                fragmentInformacion.setArguments(bundle);
                transaction.replace(R.id.containerInformacionId, fragmentInformacion).commit();
            }
        });



    }
    public static void cargar(PlantasInformacion plantasInformacion, Context context, View view, Resources resources,
                              ImageView imgPlanta,
                              EditText edtNombre,
                              EditText edtNombreAlt,
                              EditText edtURL,
                              EditText edtReino,
                              EditText edtDivision,
                              EditText edtClase,
                              EditText edtOrden,
                              EditText edtFamilia,
                              EditText edtGenero,
                              EditText edtEspecie,
                              EditText edtAlturaVal,
                              EditText edtAlturaUni,
                              EditText edtDiametroVal,
                              EditText edtDiametroUni,
                              EditText edtCicloRiegoVal,
                              EditText edtCicloRiegoUni,
                              RadioButton rbtMaceta,
                              RadioButton rbtJardin,
                              RadioButton rbtLuz,
                              RadioButton rbtMediaSombra,
                              RadioButton rbtSombra,
                              EditText edtOtrasRecomendaciones,
                              EditText edtDescripcion) {

        edtNombre.setText(plantasInformacion.getNombre());
        edtNombreAlt.setText(plantasInformacion.getNombreAlt());
        edtURL.setText(plantasInformacion.getImagenURL());
        edtReino.setText(plantasInformacion.getReino());
        edtDivision.setText(plantasInformacion.getDivision());
        edtClase.setText(plantasInformacion.getClase());
        edtOrden.setText(plantasInformacion.getOrden());
        edtFamilia.setText(plantasInformacion.getFamilia());
        edtGenero.setText(plantasInformacion.getGenero());
        edtEspecie.setText(plantasInformacion.getEspecie());
        edtAlturaVal.setText(plantasInformacion.getAltura()+"");
        edtAlturaUni.setText(plantasInformacion.getAlturaUnidad());
        edtDiametroVal.setText(plantasInformacion.getDiametro()+"");
        edtDiametroUni.setText(plantasInformacion.getDiametroUnidad());
        edtCicloRiegoVal.setText(plantasInformacion.getCicloRiego()+"");
        edtCicloRiegoUni.setText(plantasInformacion.getCicloRiegoUnidad());
        rbtMaceta.setChecked(plantasInformacion.getEsMaceta());
        rbtJardin.setChecked(!plantasInformacion.getEsMaceta());
        rbtLuz.setChecked((plantasInformacion.getLuminosidad()==2)?true:false);
        rbtMediaSombra.setChecked((plantasInformacion.getLuminosidad()==1)?true:false);
        rbtSombra.setChecked((plantasInformacion.getLuminosidad()==0)?true:false);
        edtOtrasRecomendaciones.setText(plantasInformacion.getOtrasRecomendaciones());
        edtDescripcion.setText(plantasInformacion.getDescripcion());

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
                        imgPlanta.setImageBitmap(cropImg);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        imgPlanta.setImageDrawable(placeholder);
                    }
                });
    }
}