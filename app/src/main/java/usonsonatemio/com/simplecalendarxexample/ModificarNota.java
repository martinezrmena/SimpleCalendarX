package usonsonatemio.com.simplecalendarxexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import usonsonatemio.com.simplecalendarxexample.SQLite.DB;
import usonsonatemio.com.simplecalendarxexample.SQLite.Notas;

public class ModificarNota extends AppCompatActivity {

    EditText NotaPrincipal;
    Notas nota;
    private String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_nota);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NotaPrincipal = findViewById(R.id.txbNotaModificar);
        db = new DB(ModificarNota.this);
        nota = (Notas) getIntent().getExtras().getSerializable("NOTA");

        //Obtener la fecha de la nota para poder mostrarla en la app bar
        Calendar calendar = convertirACalendar(nota.getFechanota());
        setTitle("Modificar nota de: " + MES[calendar.get(Calendar.MONTH)]+ ", " + calendar.get(Calendar.DAY_OF_MONTH) + " - " + calendar.get(Calendar.YEAR ));

        NotaPrincipal.setText(nota.getNota());

    }

    public void btnModificarOnClick(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(ModificarNota.this);
        builder.setIcon(R.drawable.update_note).
                setTitle("Atención").setMessage("¿Está seguro de modificar la nota?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nota.setNota(NotaPrincipal.getText().toString());
                db.guardar_O_ActualizarNotas(nota);
                Toast.makeText(ModificarNota.this, "La nota fue modificada exitosamente", Toast.LENGTH_SHORT).show();
                finish();


            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                Toast.makeText(ModificarNota.this, "La acción fue cancelada.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
