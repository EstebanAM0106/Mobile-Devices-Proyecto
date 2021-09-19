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

import mx.ipn.escom.plantas.Adapter.PlantasInformacion;
import mx.ipn.escom.plantas.Database.CargarBaseDatosModificar;
import mx.ipn.escom.plantas.Database.EnviarBaseDatosModificar;
import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentModificarPlantaBinding;


public class ModificarPlantaFragment extends Fragment implements View.OnClickListener{
    FragmentModificarPlantaBinding binding;
    Button btnGuardar;
    Button btnActualizar;

    ImageView imgPlanta;
    EditText edtNombre;
    EditText edtNombreAlt;
    EditText edtURL;
    EditText edtReino;
    EditText edtDivision;
    EditText edtClase;
    EditText edtOrden;
    EditText edtFamilia;
    EditText edtGenero;
    EditText edtEspecie;
    EditText edtAlturaVal;
    EditText edtAlturaUni;
    EditText edtDiametroVal;
    EditText edtDiametroUni;
    EditText edtCicloRiegoVal;
    EditText edtCicloRiegoUni;
    RadioButton rbtMaceta;
    RadioButton rbtJardin;
    RadioButton rbtLuz;
    RadioButton rbtMediaSombra;
    RadioButton rbtSombra;
    EditText edtOtrasRecomendaciones;
    EditText edtDescripcion;


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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modMaceta:
                Toast.makeText(getContext(),getResources().getText(R.string.maceta), Toast.LENGTH_SHORT).show();
                break;
            case R.id.modJardin:

                Toast.makeText(getContext(),getResources().getText(R.string.jardinera), Toast.LENGTH_SHORT).show();
                break;
            case R.id.modLuz:
                Toast.makeText(getContext(),getResources().getText(R.string.luz), Toast.LENGTH_SHORT).show();
                break;
            case R.id.modMediaSombra:
                Toast.makeText(getContext(),getResources().getText(R.string.media_sombra), Toast.LENGTH_SHORT).show();
                break;
            case R.id.modSombra:
                Toast.makeText(getContext(),getResources().getText(R.string.sombra), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {// Inflate the layout for this fragment
        binding = FragmentModificarPlantaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Recibir valor
        int idPlanta = getArguments().getInt("idPlanta");

        //ImageView imgPlanta = (ImageView) root.findViewById(R.id.infImgId);
        EditText edtNombre = (EditText) root.findViewById(R.id.modNombre);
        EditText edtNombreAlt = (EditText) root.findViewById(R.id.modNombreAlt);
        //EditText edtURL = (EditText) root.findViewById(R.id.modUrlImg);
        EditText edtReino = (EditText) root.findViewById(R.id.modReino);
        EditText edtDivision = (EditText) root.findViewById(R.id.modDivision);
        EditText edtClase = (EditText) root.findViewById(R.id.modClase);
        EditText edtOrden = (EditText) root.findViewById(R.id.modOrden);
        EditText edtFamilia = (EditText) root.findViewById(R.id.modFamilia);
        EditText edtGenero = (EditText) root.findViewById(R.id.modGenero);
        EditText edtEspecie = (EditText) root.findViewById(R.id.modEspecie);
        EditText edtAlturaVal = (EditText) root.findViewById(R.id.modAltVal);
        EditText edtAlturaUni = (EditText) root.findViewById(R.id.modAltUni);
        EditText edtDiametroVal = (EditText) root.findViewById(R.id.modDiaVal);
        EditText edtDiametroUni = (EditText) root.findViewById(R.id.modDiaUni);
        EditText edtCicloRiegoVal = (EditText) root.findViewById(R.id.modCicVal);
        EditText edtCicloRiegoUni = (EditText) root.findViewById(R.id.modCicUni);
        RadioButton rbtMaceta = (RadioButton) root.findViewById(R.id.modMaceta);
        RadioButton rbtJardin = (RadioButton) root.findViewById(R.id.modJardin);
        RadioButton rbtLuz = (RadioButton) root.findViewById(R.id.modLuz);
        RadioButton rbtMediaSombra = (RadioButton) root.findViewById(R.id.modMediaSombra);
        RadioButton rbtSombra = (RadioButton) root.findViewById(R.id.modSombra);
        EditText edtOtrasRecomendaciones = (EditText) root.findViewById(R.id.modOtrasRecomendaciones);
        EditText edtDescripcion = (EditText) root.findViewById(R.id.modDescripcion);

        rbtMaceta.setOnClickListener(this);
        rbtJardin.setOnClickListener(this);
        rbtLuz.setOnClickListener(this);
        rbtMediaSombra.setOnClickListener(this);
        rbtSombra.setOnClickListener(this);
        btnGuardar = (Button) root.findViewById(R.id.modBtnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnActualizar.performClick();
                String errors = "";
                String nombre = (String) edtNombre.getText().toString()+"";
                String nombreAlt = (String) edtNombreAlt.getText().toString()+"";
                String imagenURL = (String) edtURL.getText().toString()+"";
                String reino = (String) edtReino.getText().toString()+"";
                String division = (String) edtDivision.getText().toString()+"";
                String clase = (String) edtClase.getText().toString()+"";
                String orden = (String) edtOrden.getText().toString()+"";
                String familia = (String) edtFamilia.getText().toString()+"";
                String genero = (String) edtGenero.getText().toString()+"";
                String especie = (String) edtEspecie.getText().toString()+"";
                int altura = Integer.parseInt("0"+(String) edtAlturaVal.getText().toString());
                String alturaUnidad = (String) edtAlturaUni.getText().toString()+"";
                int diametro = Integer.parseInt("0"+(String) edtDiametroVal.getText().toString());
                String diametroUnidad = (String) edtDiametroUni.getText().toString()+"";
                int cicloRiego = Integer.parseInt("0"+(String) edtCicloRiegoVal.getText().toString());
                String cicloRiegoUnidad = (String) edtCicloRiegoUni.getText().toString()+"";
                boolean maceta = (Boolean) rbtMaceta.isChecked();
                int luminosidad = (rbtLuz.isChecked())?2:(rbtMediaSombra.isChecked())?1:0;
                String otrasRecomendaciones = (String) edtOtrasRecomendaciones.getText().toString()+"";
                String descripcion = (String) edtDescripcion.getText().toString()+"";
                int ultimoUsuario = 1;

                if(nombre==""||nombre==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.nombre)+"\n";
                if(imagenURL=="" ||imagenURL==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.url_imagen)+"\n";
                if(reino=="" ||reino==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.reino)+"\n";
                if(division=="" ||division==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.division)+"\n";
                if(clase=="" ||clase==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.clase)+"\n";
                if(orden=="" ||orden==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.orden)+"\n";
                if(familia=="" ||familia==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.familia)+"\n";
                if(genero=="" ||genero==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.genero)+"\n";
                if(especie=="" ||especie==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.nombre_alternativo)+"\n";
                if(altura<=0)
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.altura)+"\n";
                if(alturaUnidad=="" ||alturaUnidad==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.altura)+" "+getResources().getText(R.string.unidad)+"\n";
                if(diametro<=0)
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.diametro)+"\n";
                if(diametroUnidad=="" ||diametroUnidad==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.diametro)+" "+getResources().getText(R.string.unidad)+"\n";
                if(cicloRiego<=0)
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.ciclo_riego)+"\n";
                if(cicloRiegoUnidad=="" ||cicloRiegoUnidad==" ")
                    errors += getResources().getText(R.string.completar_campo)+" "+ getResources().getText(R.string.ciclo_riego)+" "+getResources().getText(R.string.unidad)+"\n";

                if(idPlanta >= 0) {
                    if(errors.length()==0) {
                        new EnviarBaseDatosModificar(new PlantasInformacion(idPlanta, nombre, nombreAlt, imagenURL, reino, division, clase, orden, familia, genero, especie, altura, alturaUnidad, diametro, diametroUnidad, cicloRiego, cicloRiegoUnidad, maceta, luminosidad, otrasRecomendaciones, descripcion, ultimoUsuario));
                        fragmentInformacion = new InformacionPlantaFragment();
                        transaction = getParentFragmentManager().beginTransaction();

                        //Enviar valor a otro Fragment
                        Bundle bundle = new Bundle();
                        bundle.putInt("idPlanta", idPlanta);
                        fragmentInformacion.setArguments(bundle);
                        transaction.replace(R.id.containerInformacionId, fragmentInformacion,"F_INFORMACION").commit();

                    }else {
                        Toast.makeText(getContext(),errors+"", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(errors.length()==0) {
                        new EnviarBaseDatosModificar(new PlantasInformacion(idPlanta, nombre, nombreAlt, imagenURL, reino, division, clase, orden, familia, genero, especie, altura, alturaUnidad, diametro, diametroUnidad, cicloRiego, cicloRiegoUnidad, maceta, luminosidad, otrasRecomendaciones, descripcion, ultimoUsuario));
                        getActivity().finish();
                    }else {
                        Toast.makeText(getContext(),errors+"", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




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

            inicializarElementos(root, idPlanta, getContext(),
                    imgPlanta,
                    edtNombre,
                    edtNombreAlt,
                    edtURL,
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
        }
        else {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.title_agregar);
            //Glide.with(getContext()).load("https://source.unsplash.com/collection/518317")
            Glide.with(getContext()).load("https://source.unsplash.com/user/mj3824")
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .into(imgPlanta);
            btnGuardar.setText(R.string.boton_agregar);
        }

        return root;
    }

    public void inicializarElementos(View view, int idPlanta, Context context,ImageView imgPlanta, EditText edtNombre, EditText edtNombreAlt, EditText edtURL, EditText edtReino, EditText edtDivision, EditText edtClase, EditText edtOrden, EditText edtFamilia, EditText edtGenero, EditText edtEspecie, EditText edtAlturaVal, EditText edtAlturaUni, EditText edtDiametroVal, EditText edtDiametroUni, EditText edtCicloRiegoVal, EditText edtCicloRiegoUni, RadioButton rbtMaceta, RadioButton rbtJardin, RadioButton rbtLuz, RadioButton rbtMediaSombra, RadioButton rbtSombra, EditText edtOtrasRecomendaciones, EditText edtDescripcion) {
        CargarBaseDatosModificar bd =  new CargarBaseDatosModificar(
                idPlanta, context, view, getResources(),
                imgPlanta,
                edtNombre,
                edtNombreAlt,
                edtURL,
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
                .error(R.drawable.error)
                .centerCrop()
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        int newWidth = (height > width) ? width : height;
                        int newHeight = (height > width) ? width : height;
                        int cropW = (width - height) / 2;
                        cropW = (cropW < 0) ? 0 : cropW;
                        int cropH = (height - width) / 2;
                        cropH = (cropH < 0) ? 0 : cropH;
                        Bitmap cropImg = Bitmap.createBitmap(resource, cropW, cropH, newWidth, newHeight);
                        imgPlanta.setImageBitmap(cropImg);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        imgPlanta.setImageDrawable(placeholder);
                    }

                    /*@Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        imgPlanta.setImageDrawable(errorDrawable);
                    }*/

                });
    }

}