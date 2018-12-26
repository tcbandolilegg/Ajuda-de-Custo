package br.com.ascenderideias.www.ajudadecusto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Principal extends AppCompatActivity {
    EditText origem;
    EditText destino;
    EditText distancia;
    Spinner combustivel;
    EditText valor_litro;
    EditText autonomia;
    float ajuda_custo;
    TextView m_ajuda_custo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setTitle("Ajuda de Custo");

        origem = findViewById(R.id.get_cidorigem);
        destino = findViewById(R.id.get_ciddestino);
        distancia = findViewById(R.id.get_distkm);

        combustivel = findViewById(R.id.spn_combustivel);// Anaçisar ID spn_combustivel....
        valor_litro = findViewById(R.id.get_vallitro);  // TODO  implementar a recuperação do Val_litro

        autonomia = findViewById(R.id.get_autonomia);

        Button calcular = findViewById(R.id.bt_calcular);
        m_ajuda_custo = findViewById(R.id.t_ajuda_custo);

        Spinner escolheComustivel = (Spinner) findViewById(R.id.spn_combustivel);// Anaçisar ID spn_combustivel....
        ArrayAdapter<CharSequence> vinculo = ArrayAdapter.createFromResource(this, R.array.tipo_combustivel, android.R.layout.simple_spinner_dropdown_item);
        escolheComustivel.setAdapter(vinculo);

 // TODO       carregarValoresCombustiveis();

    }


    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_calcular) {
            float v1, v2, v3;
            v1 = Float.parseFloat(distancia.getText().toString());
            v2 = Float.parseFloat(autonomia.getText().toString());
            v3 = Float.parseFloat(valor_litro.getText().toString());
            ajuda_custo = (v1 / v2) * v3;
            m_ajuda_custo.setText(String.valueOf(ajuda_custo));
        }
    }

}
