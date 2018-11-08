package usonsonatemio.com.simplecalendarxexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.widget.EditText;

import com.applandeo.materialcalendarview.CalendarView;

public class AddNoteActivity extends AppCompatActivity {

    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        setTitle("Agregar nota");

        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        button = findViewById(R.id.addNoteButton);
        final EditText noteEditText = findViewById(R.id.noteEditText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.note, noteEditText.getText().toString());
                returnIntent.putExtra(MainActivity.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
