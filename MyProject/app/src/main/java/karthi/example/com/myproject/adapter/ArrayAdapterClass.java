package karthi.example.com.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.data.ListData;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class ArrayAdapterClass extends BaseAdapter{

    private static final String TAG = "ArrayAdapterClass";
    private Context context;
    private ArrayList<String> strings = new ArrayList<>();
    private LayoutInflater inflater;

    public ArrayAdapterClass(Context context,ArrayList<String> strings) {
        this.context = context;
        this.strings = strings;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.listviewlayout,viewGroup,false);

        TextView textView = (TextView) view.findViewById(R.id.text);
        String Value = strings.get(position);
        textView.setText(Value);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strings.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
