package usonsonatemio.com.simplecalendarxexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import usonsonatemio.com.simplecalendarxexample.SQLite.Notas;

public class ListaNotasDia extends AppCompatActivity {

    ArrayList<Notas> listaNotas, listaNotasNuevas;
    private AdaptadorNotas adaptadorNotas;
    ListView lstNotas;
    String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    int mes;

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

        Calendar calendar = convertirACalendar(listaNotas.get(0).getFechanota());

        setTitle(MES[calendar.get(Calendar.MONTH)]+ ", " + calendar.get(Calendar.DAY_OF_MONTH) + " - " + calendar.get(Calendar.YEAR ));
    }

    private void FillListEncuestado(){
        for (final Notas c:listaNotas){
            listaNotasNuevas.add(c);
            adaptadorNotas.notifyDataSetChanged();
        }
    }


    private Calendar convertirACalendar(String fecha){
        String[] fechArray = fecha.split("-");


        int anio = Integer.parseInt(fechArray[0]);
        int mes =  Integer.parseInt(fechArray[1]) - 1;
        int dia =  Integer.parseInt(fechArray[2]);

        /*
         *
         * Al mes lo resto 1 (-1) ya que el formato de Calendar el mes empieza en 0
         * Enero=0, Febrero=1, Marzo=2, ... , Diciembre=11
         * De lo contrario Diciembre (12) no funcionaria
         *
         * */

        Calendar c1 = new GregorianCalendar(anio, mes, dia);

        return c1;
    }
}
