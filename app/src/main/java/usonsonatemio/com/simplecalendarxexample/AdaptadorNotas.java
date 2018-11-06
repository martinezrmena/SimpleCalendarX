package usonsonatemio.com.simplecalendarxexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import usonsonatemio.com.simplecalendarxexample.SQLite.Notas;

public class AdaptadorNotas extends ArrayAdapter<Notas> {

    public AdaptadorNotas(Context context, List<Notas> objects) {
            super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            // Obteniendo el dato
            Notas nota = getItem(position);
            //TODO inicializando el layout correspondiente al item (Contacto)
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nota, parent, false);
            }

            TextView lblId = (TextView) convertView.findViewById(R.id.lblId);
            TextView lblNota = (TextView) convertView.findViewById(R.id.lblNota);
            String p = String.valueOf(position+1);


            // mostrar los datos

            lblId.setText(p);
            lblNota.setText(nota.getNota());

            // Return la convertView ya con los datos
            return convertView;
        }
}