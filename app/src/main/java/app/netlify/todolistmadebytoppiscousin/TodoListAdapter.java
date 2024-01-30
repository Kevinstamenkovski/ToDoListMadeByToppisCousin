package app.netlify.todolistmadebytoppiscousin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class TodoListAdapter extends ArrayAdapter<String> {

    public TodoListAdapter(Context context, ArrayList<String> todoList) {
        super(context, 0, todoList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }

        TextView itemName = convertView.findViewById(R.id.itemName);
        itemName.setText(item);

        return convertView;
    }
}
