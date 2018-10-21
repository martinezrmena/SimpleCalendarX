package usonsonatemio.com.simplecalendarxexample.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private String crearNotas="create table notas"+
            "("+
            "id_nota INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "fecha DATE,"+
            "nota TEXT"+
            ")";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearNotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS notas");
    }
}
