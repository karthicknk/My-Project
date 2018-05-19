package karthi.example.com.myproject.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.R;
import karthi.example.com.myproject.adapter.ListViewAdapter;
import karthi.example.com.myproject.data.ListData;
import karthi.example.com.myproject.interfaces.Communication;
import karthi.example.com.myproject.volley.NetworkController;

/**
 * Created by AshokKumar on 20/10/2017.
 */

public class ListviewPage extends Fragment implements Communication, ListViewAdapter.PassDataValue {

    private static final String TAG = "ListviewPage";
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<ListData> list = new ArrayList<>();
    private String url = "https://api.myjson.com/bins/w86a";
    private NetworkController networkController;
    private ListViewAdapter.PassDataValue passDataValue;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        passDataValue = (ListViewAdapter.PassDataValue) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.listvewlayout, container, false);

        networkController = NetworkController.getInstance(getContext());
        networkController.JsonFunction(url, this);
        listViewAdapter = new ListViewAdapter(getContext(), list, this);

        listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);

//        listViewAdapter.MethodFunction(new ListViewAdapter.PassDataValue() {
//            @Override
//            public void Datasent(String value) {
////                Log.e(TAG, "Datasent11111111111111111111111: " + value );
//                passDataValue.Datasent(value);
//            }
//        });

        return view;
    }


    @Override
    public void Success(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            try {
                JSONObject jsonObject = array.getJSONObject(i);
                ListData listData = new ListData();
                listData.setTitle(jsonObject.getString("title"));
                list.add(listData);

            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                listViewAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void Datasent(String value) {
//        Log.e(TAG, "ListViewPageeeeeeeeeeeeeeeeee: " + value );
        passDataValue.Datasent(value);

//        Intent intent = new Intent("Test");
//        intent.putExtra("name", value );
//        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
//        EventBus.getDefault().post(new MessageEvent(value));
    }
}
