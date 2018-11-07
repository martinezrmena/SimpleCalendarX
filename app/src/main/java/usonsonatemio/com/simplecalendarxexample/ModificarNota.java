package usonsonatemio.com.simplecalendarxexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import usonsonatemio.com.simplecalendarxexample.SQLite.DB;
import usonsonatemio.com.simplecalendarxexample.SQLite.Notas;

public class ModificarNota extends AppCompatActivity {

    EditText NotaPrincipal;
    Notas nota;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_nota);

        NotaPrincipal = findViewById(R.id.txbNotaModificar);
        db = new DB(ModificarNota.this);
        nota = (Notas) getIntent().getExtras().getSerializable("NOTA");

        NotaPrincipal.setText(nota.getNota());

    }

    public void btnModificarOnClick(View view){

        nota.setNota(NotaPrincipal.getText().toString());
        db.guardar_O_ActualizarNotas(nota);
    }


}
