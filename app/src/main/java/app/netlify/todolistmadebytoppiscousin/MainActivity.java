package app.netlify.todolistmadebytoppiscousin;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> todoList;
    private TodoListAdapter todoAdapter;
    private ListView listView;
    private TextView completionPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = new ArrayList<>();
        todoAdapter = new TodoListAdapter(this, todoList);

        listView = findViewById(R.id.listView);
        completionPercentage = findViewById(R.id.completionPercentage);

        listView.setAdapter(todoAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText newItemEditText = findViewById(R.id.newItemEditText);
                String newItem = newItemEditText.getText().toString();
                if (!newItem.isEmpty()) {
                    todoList.add(newItem);
                    todoAdapter.notifyDataSetChanged();
                    updateCompletionPercentage();
                    newItemEditText.setText("");
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, true);
            }
        });
        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "A", Toast.LENGTH_SHORT).show();
                int selectedItemPosition = listView.getCheckedItemPosition();
                if (selectedItemPosition != -1) {
                    todoList.remove(selectedItemPosition);
                    todoAdapter.notifyDataSetChanged();
                    updateCompletionPercentage();
                }
            }
        });

        Button openButton = findViewById(R.id.openButton);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedItemPosition = listView.getCheckedItemPosition();
                if (selectedItemPosition != -1) {
                    String selectedItem = todoList.get(selectedItemPosition);
                    Button removeButton = findViewById(R.id.removeButton);
                    removeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int selectedItemPosition = listView.getCheckedItemPosition();
                            if (selectedItemPosition != ListView.INVALID_POSITION) {
                                todoList.remove(selectedItemPosition);
                                todoAdapter.notifyDataSetChanged();
                                updateCompletionPercentage();
                            }
                        }
                    });
                }
            }
        });
    }

    private void updateCompletionPercentage() {
        int completedCount = todoList.size();
        int totalCount = todoAdapter.getCount();
        int percentage = (totalCount == 0) ? 0 : (completedCount * 100) / totalCount;
        completionPercentage.setText(getString(R.string.completion_percentage, percentage));
    }
}
