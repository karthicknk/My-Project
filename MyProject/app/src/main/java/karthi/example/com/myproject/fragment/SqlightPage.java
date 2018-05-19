package karthi.example.com.myproject.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.SqlightAdapter;
import karthi.example.com.myproject.data.ListSqlight;
import karthi.example.com.myproject.helper.DataBaseHelperListSqlite;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class SqlightPage extends Fragment {

    private static final String TAG = "SqlightPage";
    private ListView listView;
    private SqlightAdapter sqlightAdapter;
    private ArrayList<ListSqlight> listSqlights = new ArrayList<>();
    private DataBaseHelperListSqlite dataBaseHelperListSqlite;
        
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.listvewlayout,container,false);

        dataBaseHelperListSqlite = new DataBaseHelperListSqlite(getContext());
        ArrayList<ListSqlight> listSqlights = dataBaseHelperListSqlite.Getall();
        

        sqlightAdapter = new SqlightAdapter(getContext(),listSqlights);
        listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(sqlightAdapter);
        
        return view;
    }
}
