package sg.edu.rp.c346.id21000243.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTasks;
    EditText etTasks;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    Spinner spnSelect;

    String[] arrTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = findViewById(R.id.listViewTask);
        etTasks = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        spnSelect = findViewById(R.id.spinner);

        ArrayList<String> arrTasks = new ArrayList<>();

        ArrayAdapter aaTasks = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrTasks);

        spnSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,View view, int position, long id) {
                switch(position) {
                    case 0:
                        etTasks.setHint("Enter Task");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String tasks = etTasks.getText().toString();
                                etTasks.getText().clear();
                                arrTasks.add(tasks);
                                aaTasks.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:
                        etTasks.setHint("Tpye in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String tasks = etTasks.getText().toString();
                                int pos = Integer.parseInt(etTasks.getText().toString());
                                etTasks.getText().clear();
                                arrTasks.remove(pos);
                                aaTasks.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });
        lvTasks.setAdapter(aaTasks);
    }
}