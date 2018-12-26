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


public class Principal extends AppCompatActivity {
    EditText origem;
    EditText destino;
    EditText distancia;
    Spinner combustivel;
    EditText valor_litro;

    EditText gasolina;
    EditText alcool;
    EditText diesel;
    EditText gnv;

    EditText autonomia;
    float ajuda_custo;

    TextView m_ajuda_custo;

    public SharedPreferences sharedPref;

    public float val_gasolina;
    public float val_alcool;
    public float val_diesel;
    public float val_gnv;
    public float v_litro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setTitle("Ajuda de Custo");
        sharedPref = getPreferences(MODE_PRIVATE);

        origem = findViewById(R.id.get_cidorigem); //TODO usar MAPS
        destino = findViewById(R.id.get_ciddestino);  //TODO usar MAPS
        distancia = findViewById(R.id.get_distkm);//TODO calcular com MAPS

//        combustivel = findViewById(R.id.spn_combustivel);
        valor_litro = findViewById(R.id.get_vallitro);
        autonomia = findViewById(R.id.get_autonomia);

        Button calcular = findViewById(R.id.bt_calcular);
        m_ajuda_custo = findViewById(R.id.t_ajuda_custo);

        final Spinner escolheComustivel = (Spinner) findViewById(R.id.spn_combustivel);
        ArrayAdapter<CharSequence> vinculo =
                ArrayAdapter.createFromResource(this, R.array.tipo_combustivel,
                        android.R.layout.simple_spinner_dropdown_item);
        escolheComustivel.setAdapter(vinculo);
        AdapterView.OnItemSelectedListener combescolhido = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = escolheComustivel.getSelectedItem().toString();
                switch (item) {
                    case "Gasolina":
                        v_litro = sharedPref.getFloat("val_gasolina", val_gasolina);
                        break;
                    case "Alcool":
                        v_litro = sharedPref.getFloat("val_alcool", val_alcool);
                        break;
                    case "Diesel":
                        v_litro = sharedPref.getFloat("val_diesel", val_diesel);
                        break;
                    case "GNV":
                        v_litro = sharedPref.getFloat("val_gnv", val_gnv);
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

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_calcular) {
            float v1, v2, v3;
            v1 = Float.parseFloat(distancia.getText().toString());
            v2 = Float.parseFloat(autonomia.getText().toString());
//            v3 = Float.parseFloat(valor_litro.getText().toString());
            v3 = v_litro;
            ajuda_custo = 2*(v1 / v2) * v3;
            m_ajuda_custo.setText(String.valueOf(ajuda_custo));
        }
    }
