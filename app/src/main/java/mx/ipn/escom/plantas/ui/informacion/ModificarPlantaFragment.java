package mx.ipn.escom.plantas.ui.informacion;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentModificarPlantaBinding;


public class ModificarPlantaFragment extends Fragment {
    FragmentModificarPlantaBinding binding;
    Button btnGuardar;
    Button btnActualizar;
    EditText edTURL;
    ImageView modImg;

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
                    getActivity().finish();
                }
            });

        }

        modImg = (ImageView) root.findViewById(R.id.modImgId);
        btnActualizar = (Button) root.findViewById(R.id.modBtnActualizar);
        edTURL = (EditText) root.findViewById(R.id.modUrlImg);
        edTURL.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnActualizar.performClick();
                    return true;
                }
                return false;
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(getContext()).load(edTURL.getText().toString())
                        .placeholder(R.drawable.loading)
                        .centerCrop()
                        .into(modImg);
            }
        });


        return root;
    }
}