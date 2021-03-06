package usonsonatemio.com.simplecalendarxexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import usonsonatemio.com.simplecalendarxexample.SQLite.DB;
import usonsonatemio.com.simplecalendarxexample.SQLite.Notas;

public class ListaNotasDia extends AppCompatActivity {

    private ArrayList<Notas> listaNotas, listaNotasNuevas;
    private AdaptadorNotas adaptadorNotas;
    private ListView lstNotas;
    private String MES[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private int mes;
    private FloatingActionsMenu btnMenu;
    private FloatingActionButton btnUpdateNote, btnDeleteNote;
    private Boolean Modificar, Eliminar;
    private String Message = "";
    private DB db;
    public static final int NOTA_MODIFICAR = 78;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas_dia);

        lstNotas = findViewById(R.id.lstNotasDia);
        btnMenu = findViewById(R.id.btnMainButton);
        btnUpdateNote = findViewById(R.id.btnUpdateNote);
        btnDeleteNote = findViewById(R.id.btnDeleteNote);

        db = new DB(ListaNotasDia.this);

        Modificar = false;
        Eliminar = false;
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

        lstNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                int id = 0;

                if (Eliminar || Modificar){
                    final Notas lastnota = listaNotas.get(position);

                    if (Eliminar){
                        Message = "¿Esta seguro de eliminar la nota?";
                        id = R.drawable.ic_delete_note;
                    }else if(Modificar){
                        Message = "¿Esta seguro de modificar la nota?";
                        id = R.drawable.update_note;
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(ListaNotasDia.this);
                    builder.setIcon(id).
                            setTitle("Atención").setMessage(Message).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (Eliminar){
                                db.borrarNota(String.valueOf(lastnota.getId()));
                                Toast.makeText(ListaNotasDia.this, "Nota eliminada exitosamente.", Toast.LENGTH_SHORT ).show();
                            }else if(Modificar){
                                Intent intent = new Intent(ListaNotasDia.this, ModificarNota.class);
                                intent.putExtra("NOTA", lastnota);
                                startActivity(intent);
                            }

                            Intent resultIntent = new Intent();
                            setResult(MainActivity.LIST_NOTES, resultIntent);
                            finish();

                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ListaNotasDia.this, "Cancelaste la acción.", Toast.LENGTH_SHORT ).show();
                            Modificar = false;
                            Eliminar = false;
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else{
                    Toast.makeText(ListaNotasDia.this, "Primero debe seleccionar una acción a realizar.", Toast.LENGTH_SHORT ).show();
                }


            }
        });
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

    public void btnActualizarOnClick(View view){
        Toast.makeText(this, "Pulsa una nota para actualizar.", Toast.LENGTH_SHORT ).show();
        Modificar = true;
        Eliminar = false;
    }

    public void btnEliminarOnClick(View view){
        Toast.makeText(this, "Pulsa una nota para eliminarla.", Toast.LENGTH_SHORT ).show();
        Eliminar = true;
        Modificar = false;
    }
}
