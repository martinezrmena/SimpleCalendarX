package usonsonatemio.com.simplecalendarxexample.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class DB {
    private DBHelper dbHelper;
    public DB(Context context) {
        //definimos el nombre de la BD
        dbHelper = new DBHelper(context, "BD_nueva2", null, 1);
    }

    public Cursor getCursorNota(String mes){

        Log.d("CONSULTA", "select * from notas where strftime('%m', fecha) = '"+mes+"'");
        return dbHelper.getReadableDatabase().rawQuery(
                "select * from notas where strftime('%m', fecha) = '"+mes+"'",null);
    }

    public ArrayList<Notas> getArrayNotas(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<Notas> lstNotas = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstNotas.add(new Notas(
                        cursor.getString(0),//id_nota
                        cursor.getString(1),//fechanota
                        cursor.getString(2)//nota
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstNotas;
        }
        return null;//si no entro en el if
    }

    public boolean guardar_O_ActualizarNotas(Notas notas) {
        ContentValues initialValues = new ContentValues();
        Log.d("Notas","id_nota "+notas.getId());
        Log.d("Notas","fecha "+notas.getFechanota());
        Log.d("Notas","nota "+notas.getNota());

        if(!notas.getId().isEmpty())
            initialValues.put("id_nota", Integer.parseInt(notas.getId()));
            initialValues.put("fecha",notas.getFechanota());
            initialValues.put("nota",notas.getNota());

        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "notas",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }

    public void  borrarNota(String id){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from notas where id_nota='%s'",id));
    }

}
