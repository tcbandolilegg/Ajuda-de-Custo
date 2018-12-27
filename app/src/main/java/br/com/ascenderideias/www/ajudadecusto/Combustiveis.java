package br.com.ascenderideias.www.ajudadecusto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Combustiveis extends AppCompatActivity implements View.OnClickListener {
    EditText gasolina;
    EditText alcool;
    EditText diesel;
    EditText gnv;
    private boolean editmod;


    public float val_gasolina;
    public float val_alcool;
    public float val_diesel;
    public float val_gnv;

    public final String PREFS_NAME = "CombPrecos";
    public SharedPreferences sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combustiveis);
        setTitle("Precificação de Combustíveis");
        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        sharedPref = getSharedPreferences(PREFS_NAME,this.MODE_PRIVATE);

        editmod = false;
        gasolina = findViewById(R.id.getGasolina);
        alcool = findViewById(R.id.getAlcool);
        diesel = findViewById(R.id.getDiesel);
        gnv = findViewById(R.id.getGnv);//

        Button atualizar = findViewById(R.id.bt_atualiza);
        Button prosseguir = findViewById(R.id.bt_prossegue);
        carregarValoresCombustiveis();
        atualizar.setOnClickListener(this);
        prosseguir.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_atualiza) {
            gasolina.setEnabled(true);
            alcool.setEnabled(true);
            diesel.setEnabled(true);
            gnv.setEnabled(true);
            editmod = true;

        }
        if (id == R.id.bt_prossegue) {
            if (editmod) {
                salvarValoresCombustiveis();
              //  editmod = false;
            }
            startActivity(new Intent(getBaseContext(), Principal.class));
//            finish();
        }
    }

    public void salvarValoresCombustiveis() {

        SharedPreferences.Editor editor = sharedPref.edit();//TODO ver  sobreescrever ???
        val_gasolina = Float.parseFloat(gasolina.getText().toString());
        val_alcool = Float.parseFloat(alcool.getText().toString());
        val_diesel = Float.parseFloat(diesel.getText().toString());
        val_gnv = Float.parseFloat(gnv.getText().toString());
        editor.putFloat("val_gasolina", val_gasolina);
        editor.putFloat("val_alcool", val_alcool);
        editor.putFloat("val_diesel", val_diesel);
        editor.putFloat("val_gnv", val_gnv);

        editor.apply();
    }

    public void carregarValoresCombustiveis() {
        gasolina.setText(String.valueOf(sharedPref.getFloat("val_gasolina", val_gasolina)));
        alcool.setText(String.valueOf(sharedPref.getFloat("val_alcool", val_alcool)));
        diesel.setText(String.valueOf(sharedPref.getFloat("val_diesel", val_diesel)));
        gnv.setText(String.valueOf(sharedPref.getFloat("val_gnv", val_gnv)));
    }
}
