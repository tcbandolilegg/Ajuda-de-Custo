package br.com.ascenderideias.www.ajudadecusto;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;


public class Principal extends AppCompatActivity {
    public final String PREFS_NAME = "CombPrecos";
    EditText origem;
    EditText destino;
    EditText distancia;//
    Spinner combustivel;
    EditText valor_litro;
    EditText autonomia;
    float ajuda_custo;
    EditText m_ajuda_custo;
    public float v_litro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO COMO DESABILITAR O VOLTAR PARA ACTIVITY ANTERIOR? COMBUSTIVEIS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setTitle("Ajuda de Custo");
        origem = findViewById(R.id.get_cidorigem); //TODO usar MAPS
        destino = findViewById(R.id.get_ciddestino);  //TODO usar MAPS
        distancia = findViewById(R.id.get_distkm);//TODO calcular com MAPS
        valor_litro = findViewById(R.id.get_vallitro);
        autonomia = findViewById(R.id.get_autonomia);
        m_ajuda_custo = findViewById(R.id.get_ajudacusto);

        // Capture our button from layout
        Button calcular = (Button) findViewById(R.id.bt_calcular);
        Button sair = (Button) findViewById(R.id.bt_sair);
        // Register the onClick listener with the implementation above
        calcular.setOnClickListener(meusBotoes);
        sair.setOnClickListener(meusBotoes);


        final Spinner escolheComustivel = (Spinner) findViewById(R.id.spn_combustivel);
        ArrayAdapter<CharSequence> vinculo =
                ArrayAdapter.createFromResource(this, R.array.tipo_combustivel,
                        android.R.layout.simple_spinner_dropdown_item);
        escolheComustivel.setAdapter(vinculo);
        AdapterView.OnItemSelectedListener combescolhido = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = escolheComustivel.getSelectedItem().toString();
                SharedPreferences sharedPref;
                sharedPref = getSharedPreferences(PREFS_NAME, 0);

                switch (item) {
                    case "Gasolina":
                        v_litro = sharedPref.getFloat("val_gasolina", 0.1f);
                        break;
                    case "Alcool":
                        v_litro = sharedPref.getFloat("val_alcool", 0.0f);
                        break;
                    case "Diesel":
                        v_litro = sharedPref.getFloat("val_diesel", 0.0f);
                        break;
                    case "GNV":
                        v_litro = sharedPref.getFloat("val_gnv", 0.0f);
                        break;
                    default:
                        v_litro = 0;
                        break;
                }
                valor_litro.setText(String.valueOf(v_litro));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        escolheComustivel.setOnItemSelectedListener(combescolhido);
    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener meusBotoes = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            // Yes we will handle click here but which button clicked??? We don't know

            // So we will make
            switch (v.getId() /*to get clicked view id**/) {
                case R.id.bt_calcular:
                    Toast.makeText(getBaseContext(), "Calculando", Toast.LENGTH_LONG).show();
                    float v1, v2;
                    v1 = Float.parseFloat(distancia.getText().toString());
                    v2 = Float.parseFloat(autonomia.getText().toString());
                    ajuda_custo = 2 * (v1 / v2) * v_litro;
                    m_ajuda_custo.setText(String.valueOf(ajuda_custo));
                    break;
                case R.id.bt_sair:
                    finish();
                    break;

                default:
                    break;
            }
        }
    };
}
