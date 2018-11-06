package usonsonatemio.com.simplecalendarxexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import usonsonatemio.com.simplecalendarxexample.SQLite.Notas;

public class ListaNotasDia extends AppCompatActivity {

    ArrayList<Notas> listaNotas, listaNotasNuevas;
    private AdaptadorNotas adaptadorNotas;
    ListView lstNotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas_dia);

        lstNotas = findViewById(R.id.lstNotasDia);

        Bundle contenedor = getIntent().getExtras();
        listaNotas = (ArrayList<Notas>) contenedor.getSerializable("array");
        listaNotasNuevas  = new ArrayList<>();


        //creo el adaptador
        adaptadorNotas = new AdaptadorNotas(ListaNotasDia.this, listaNotasNuevas );
        //le establezco el adaptador al Listview
        lstNotas.setAdapter(adaptadorNotas);

        FillListEncuestado();
    }

    private void FillListEncuestado(){
        for (final Notas c:listaNotas){
            listaNotasNuevas.add(c);
            adaptadorNotas.notifyDataSetChanged();
        }
    }
}
