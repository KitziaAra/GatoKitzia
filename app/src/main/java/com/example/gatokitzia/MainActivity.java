package com.example.gatokitzia;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private boolean jugador;
    private final Button[] boton = new Button[9];
    private Button reset;
    TextView textView;
    View imgTurnoX;
    View imgTurnoO;

    int[] estado = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public int[][] ganar = {{0, 4, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset = findViewById(R.id.btn1);
        imgTurnoX = findViewById(R.id.imageView1);
        imgTurnoO = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView);
        imgTurnoX.setBackgroundColor(Color.BLUE);
        imgTurnoO.setBackgroundColor(Color.WHITE);

        for (int i = 0; i < 9; i++) {
            String Idbtn = "boton" + (i + 1);
            int ID = getResources().getIdentifier(Idbtn, "id", getPackageName());
            boton[i] = findViewById(ID);
        }
        jugador = true;

    }
    @Override
    public void onClick(View v){
        imgTurnoX = findViewById(R.id.imageView1);
        imgTurnoO = findViewById(R.id.imageView2);
        jugador = true;

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);

        String Idbtn = v.getResources().getResourceEntryName(v.getId());
        int estadoJuego = Integer.parseInt(Idbtn.substring(Idbtn.length()-1, Idbtn.length()));

        if (jugador == true){
            ((Button) v).setBackgroundResource(R.drawable.tache);
            imgTurnoX.setBackgroundColor(Color.WHITE);
            imgTurnoO.setBackgroundColor(Color.RED);
            estado[estadoJuego] = 1;
        } else {
            ((Button) v).setBackgroundResource(R.drawable.circulo);
            imgTurnoX.setBackgroundColor(Color.BLUE);
            imgTurnoO.setBackgroundColor(Color.WHITE);
            estado[estadoJuego] = 2;
        }
        contador++;

        String jugador1 = editText1.getText().toString();
        String jugador2 = editText2.getText().toString();
        if (ganador()){
            if (jugador){
                textView.setText("¡Ganó " + jugador1);
                resetear();
            } else {
                textView.setText("¡Ganó " + jugador2);
                resetear();
            }
        } else if(contador==9){
            textView.setText("Empate...");
            resetear();
        } else {
            jugador = !jugador;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetear();
            }
        });
    }

    public boolean ganador(){
        boolean resultadoG = false;

        for (int []posicion : ganar){
            if (estado[posicion[0]]==estado[posicion[1]] && estado[posicion[1]]==estado[posicion[2]] && estado[posicion[0]]!=0){
                resultadoG = true;
            }
        }
        return resultadoG;
    }

    public void resetear(){
        jugador= true;
        for (int i = 0; i<9; i++){
            estado[i]= 0;
            boton[i].setBackground(null);
        }
    }

}