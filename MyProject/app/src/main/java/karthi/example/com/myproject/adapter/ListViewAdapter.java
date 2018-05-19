package karthi.example.com.myproject.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.data.ListData;
import karthi.example.com.myproject.fragment.ArrayPage;
import karthi.example.com.myproject.helper.DataBaseHelperListSqlite;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class ListViewAdapter extends BaseAdapter {

    private static final String TAG = "ListViewAdapter";
    private Context context;
    private List<ListData> list = new ArrayList<>();
    private LayoutInflater inflater;
    private PassDataValue passDataValue;
    private DataBaseHelperListSqlite dataBaseHelperListSqlite;

//    public ListViewAdapter(Context context, List<ListData> list) {
//        this.context = context;
//        this.list = list;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }


    public ListViewAdapter(Context context, List<ListData> list, PassDataValue passDataValue) {
        this.context = context;
        this.list = list;
        this.passDataValue = passDataValue;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBaseHelperListSqlite = new DataBaseHelperListSqlite(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.listviewlayout,viewGroup,false);

        TextView textView = (TextView) view.findViewById(R.id.text);

        String Value = list.get(position).getTitle();
        textView.setText(Value);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = list.get(position).getTitle();
                passDataValue.Datasent(value);
//                Log.e(TAG, "ListViewAdapterrrrrrrrrrrrrrr: " + value );
                dataBaseHelperListSqlite.InsertListRow(value);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public interface PassDataValue{
        void Datasent(String value);
    }

    public void MethodFunction(PassDataValue passDataValue){
        this.passDataValue = passDataValue;
    }

}
