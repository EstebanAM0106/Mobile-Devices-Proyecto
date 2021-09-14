package mx.ipn.escom.plantas.ui.busqueda;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import mx.ipn.escom.plantas.R;
import mx.ipn.escom.plantas.databinding.FragmentBusquedaBinding;
import mx.ipn.escom.plantas.ui.resultados.ResultadosActivity;

public class BusquedaFragment extends Fragment {

    private FragmentBusquedaBinding binding;

    Button botonBuscarId;
    EditText barraBusquedaId;
    String frase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBusquedaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        barraBusquedaId = (EditText) root.findViewById(R.id.barraBusquedaId);
        botonBuscarId = (Button) root.findViewById(R.id.botonBuscarId);
        barraBusquedaId.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    botonBuscarId.performClick();
                    return true;
                }
                return false;
            }
        });
        botonBuscarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                frase = (String) barraBusquedaId.getText().toString();
                if(frase.length()>=1 )
                {
                    Intent resultados = new Intent(getContext(), ResultadosActivity.class);
                    //Intent resultados = new Intent(getContext(), InformacionActivity.class);
                    resultados.putExtra("Frase", frase);
                    startActivity(resultados);

                }else {
                    Toast.makeText(getContext(),R.string.error_barra_busqueda,Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}