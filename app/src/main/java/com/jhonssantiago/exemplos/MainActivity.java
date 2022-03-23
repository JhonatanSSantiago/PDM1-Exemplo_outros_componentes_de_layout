package com.jhonssantiago.exemplos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText nome;
    private RadioButton regular, irregular;
    private Button button;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private String idades[] = {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    private ProgressBar progressBar;
    private TextView resultado;
    private Estudante e;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome=findViewById(R.id.editTextNome);
        regular=findViewById(R.id.radioButtonRegular);
        irregular=findViewById(R.id.radioButtonIrregular);
        button=findViewById(R.id.button);
        spinner=findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, idades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        progressBar=findViewById(R.id.progressbar);
        resultado=findViewById(R.id.textViewResultado);



    }

    private void executarProgressBar(){

        Handler handler = new Handler();
        progressBar.setVisibility(View.VISIBLE);
        i = progressBar.getProgress();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<100){
                    i = i+15;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);
                            if (i >= 100){
                                resultado.setText(e.toString());
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }).start();
    }

    private String obterSituacao(){
        String s="";
        if(regular.isChecked()){
            s = "regular";
        }
        if(irregular.isChecked()){
            s = "irregular";
        }

        return s;
    }

    public void clicar(View v){
        if(e!=null){
            executarProgressBar();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int item = Integer.parseInt(idades[i]);
        e = new Estudante(nome.getText().toString(), obterSituacao(), item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}