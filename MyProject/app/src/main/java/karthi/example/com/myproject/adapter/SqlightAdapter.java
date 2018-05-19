package karthi.example.com.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.data.ListSqlight;
import karthi.example.com.myproject.helper.DataBaseHelperListSqlite;

/**
 * Created by AshokKumar on 21/10/2017.
 */

public class SqlightAdapter extends BaseAdapter{

    private static final String TAG = "SqlightAdapter";
    private Context context;
    private ArrayList<ListSqlight> listSqlights = new ArrayList<>();
    private LayoutInflater inflater;
    private TextView textView;
    private DataBaseHelperListSqlite dataBaseHelperListSqlite;

    public SqlightAdapter(Context context,ArrayList<ListSqlight> listSqlights) {
        this.context = context;
        this.listSqlights = listSqlights;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBaseHelperListSqlite = new DataBaseHelperListSqlite(context);
    }

    @Override
    public int getCount() {
        return listSqlights.size();
    }

    @Override
    public Object getItem(int i) {
        return listSqlights.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.listsqlightlayout,viewGroup,false);

        textView = (TextView) view.findViewById(R.id.text11);

        ListSqlight val = listSqlights.get(i);
        textView.setText(val.getTitle());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = listSqlights.get(i).getId();
                Log.e(TAG, "onClick: " + id );
                dataBaseHelperListSqlite.DeleteListRow(id);
                listSqlights.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
